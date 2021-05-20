import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Amiram Yassif
 * 314985474
 * ASS4
 */
public class Nand extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param prefix  The expression followed by NAND (A) the operator.
     * @param postfix The expression following the NAND (A) operator.
     */
    protected Nand(Expression prefix, Expression postfix) {
        super(prefix, postfix);
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
        return !(super.getPostfix().evaluate(assignment)
                && super.getPostfix().evaluate(assignment));
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
        return !(super.getPrefix().evaluate() && super.getPostfix().evaluate());
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return A list of the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        //Create the Set To Return (=str) for avoiding doubles.
        Set<String> str = new HashSet<>();
        //Gets prefix and postfix variables, and unions them.
        str.addAll(super.getPrefix().getVariables());
        str.addAll(super.getPostfix().getVariables());
        return new ArrayList<>(str);
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
        return new Nand(getPrefix().assign(var, expression),
                getPostfix().assign(var, expression));
    }

    /**
     * Returns the expression tree resulting from converting all the operations
     * to the logical Nand operation.
     *
     * @return Expression in Nand format.
     */
    @Override
    public Expression nandify() {
        return this;
    }

    /**
     * Returns the expression tree resulting from converting all the operations
     * to the logical Nor operation.
     *
     * @return Expression in Nor format.
     */
    @Override
    public Expression norify() {
        return new Nor(
                new Nor(
                        new Nor(
                                super.getPrefix().norify(),
                                super.getPrefix().norify()
                        ),
                        new Nor(
                                super.getPostfix().norify(),
                                super.getPostfix().norify()
                        )
                ),
                new Nor(
                    new Nor(
                            super.getPrefix().norify(),
                            super.getPrefix().norify()
                    ),
                    new Nor(
                            super.getPostfix().norify(),
                            super.getPostfix().norify()
                    )
                )
        );
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @return Simplified version of the expression.
     */
    @Override
    public Expression simplify() throws Exception {
        /*
         * Since the simplified form of NAND, is negated to the simplified form
         * of AND we will create a correct form for our purpose.
         */
        And helper = new And(getPrefix(), getPostfix());
        return new Not(helper.simplify());
    }

    @Override
    public String toString() {
        return "(" + getPrefix() + " A " + getPostfix() + ")";
    }
}
