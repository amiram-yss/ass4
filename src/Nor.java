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
public class Nor extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param prefix  The expression followed by NOR (V) the operator.
     * @param postfix The expression following the NOR (V) operator.
     */
    protected Nor(Expression prefix, Expression postfix) {
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
        return !(super.getPrefix().evaluate(assignment)
                || super.getPostfix().evaluate(assignment));
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
        return !(super.getPostfix().evaluate() || super.getPostfix().evaluate());
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
        return new Nor(getPrefix().assign(var, expression), getPostfix().assign(var, expression));
    }

    /**
     * Returns the expression tree resulting from converting all the operations
     * to the logical Nand operation.
     *
     * @return Expression in Nand format.
     */
    @Override
    public Expression nandify() {
        /*
         * Logic: A NOR B
         * =[(A NAND A) NAND (B NAND B)] NAND [(A NAND A) NAND (B NAND B)]
         */
        return new Nand(
                new Nand(
                        new Nand(
                                super.getPrefix().nandify(),
                                super.getPrefix().nandify()),
                        new Nand(super.getPostfix().nandify(),
                                super.getPostfix().nandify()
                        )
                ),
                new Nand(
                        new Nand(
                                super.getPrefix().nandify(),
                                super.getPrefix().nandify()),
                        new Nand(
                                super.getPostfix().nandify(),
                                super.getPostfix().nandify()
                        )
                )
        );
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
                getPrefix().norify(),
                getPostfix().norify()
        );
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @return Simplified version of the expression.
     */
    @Override
    public Expression simplify() {
        // Store simplified expressions.
        Expression prefixSimplified = getPrefix().simplify();
        Expression postfixSimplified = getPostfix().simplify();
        ExpressionType prefixType = Expression.getExpressionType(
                prefixSimplified);
        ExpressionType postfixType = Expression.getExpressionType(
                postfixSimplified);
        /*
         * Since the simplified form of NAND, is negated to the simplified form
         * of OR we will create a correct form for our purpose.
         */
        if (prefixType == ExpressionType.Val
                || postfixType == ExpressionType.Val) {
            return new Not(
                    new Or(
                            getPrefix().simplify(),
                            getPostfix().simplify()
                    ).simplify()
            ).simplify();
        }
        //If both params are equal, return one of them.
        if (prefixSimplified.toString().equals(postfixSimplified.toString())) {
            return new Not(prefixSimplified);
        }
        //No simplification is made.
        return new Nor(prefixSimplified, postfixSimplified);
        //return this;
    }


    @Override
    public String toString() {
        return "(" + getPrefix() + " V " + getPostfix() + ")";
    }
}
