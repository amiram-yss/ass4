import java.util.List;
import java.util.Map;

/**
 * @author Amiram Yassif
 * 314985474
 * ASS4
 */
public class Not extends UnaryExpression {
    /**
     * Constructor.
     *
     * @param postfix The expression followed by NOT (~) the operator.
     */
    protected Not(Expression postfix) {
        super(postfix);
    }

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment Evaluate the expression using the variable values
     *                   provided in the assignment.
     * @return Result.      The result after setting vals in vars.
     * @throws Exception In case of giving an invalid var's name, throw.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !super.getPostfix().evaluate(assignment);
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return The result after setting vals in vars.
     * @throws Exception In case of giving an invalid var's name, throw.
     */
    @Override
    public Boolean evaluate() throws Exception {
        return !super.getPostfix().evaluate();
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return A list of the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        return super.getPostfix().getVariables();
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        The var's name.
     * @param expression Replace the var above with the following expression.
     * @return a new expression in which all occurrences
     * of the variable var are replaced with the provided
     * expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(super.getPostfix().assign(var, expression));
    }

    /**
     * Returns the expression tree resulting from converting all the operations
     * to the logical Nand operation.
     *
     * @return Expression in Nand format.
     */
    @Override
    public Expression nandify() {
        // Logic: ~A = A NAND A.
        return new Nand(super.getPostfix().nandify(), super.getPostfix().nandify());
    }

    /**
     * Returns the expression tree resulting from converting all the operations
     * to the logical Nor operation.
     *
     * @return Expression in Nor format.
     */
    @Override
    public Expression norify() {
        // Logic: ~A = A NOR A
        return new Nor(
                super.getPostfix().norify(), super.getPostfix().norify()
        );
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @return Simplified version of the expression.
     * @throws Exception    If simplify() throws an exception
     */
    @Override
    public Expression simplify() {
        try {
            Expression simplifiedPostFix = super.getPostfix().simplify();
            ExpressionType postfixType = Expression.
                    getExpressionType(simplifiedPostFix);
            //If both expression is a Val, an evaluation can be made. Return it.
            if (postfixType == ExpressionType.Val) {
                return new Val(this.evaluate());
            }
            return new Not(super.getPostfix().simplify());
        } catch (Exception e) {
            System.out.println("Error simplifying: " + this.toString());
            return null;
        }
    }

    @Override
    public String toString() {
        return "~(" + getPostfix() + ")";
    }
}
