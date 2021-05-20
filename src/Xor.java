import java.util.*;

/**
 * @author Amiram Yassif
 * 314985474
 * ASS4
 */
public class Xor extends BinaryExpression{
    /**
     * Solves a simple XOR expression.
     * @param b1    Binary value 1.
     * @param b2    Binary value 2.
     * @return      XOR result for (b1 ^ b2).
     */
    private static Boolean simpleXorSolver(Boolean b1, Boolean b2) {
        return (b1 && !b2) || (!b1 && b2);
    }
    /**
     * Constructor.
     *
     * @param prefix  The expression followed by XOR (^) the operator.
     * @param postfix The expression following the XOR (^) operator.
     */
    protected Xor(Expression prefix, Expression postfix) {
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
        return simpleXorSolver
                (super.prefix.evaluate(assignment)
                        ,super.postfix.evaluate(assignment));
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
        return simpleXorSolver
                (super.prefix.evaluate()
                        , super.postfix.evaluate());
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
        str.addAll(super.prefix.getVariables());
        str.addAll(super.postfix.getVariables());
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
        return new Xor(prefix.assign(var, expression)
                , postfix.assign(var, expression));
    }

    /**
     * Returns the expression tree resulting from converting all the operations
     * to the logical Nand operation.
     *
     * @return Expression in Nand format.
     */
    @Override
    public Expression nandify() {
        return new Nand(
                new Nand(
                        super.prefix.nandify()
                        ,new Nand(
                                super.prefix.nandify()
                                ,super.postfix.nandify()
                        )
                )
                ,new Nand(
                    super.postfix.nandify()
                    ,new Nand(
                            super.prefix.nandify()
                            ,super.postfix.nandify()
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
                new Nor(
                        new Nor(
                                super.prefix.norify()
                                ,super.prefix.norify()
                        )
                        ,new Nor(
                                super.postfix.norify()
                                ,super.postfix.norify()
                        )
                )
                ,new Nor(
                        super.prefix.norify()
                        ,super.postfix.norify()
                )
        );
    }

    /**
     /**
     * Returned a simplified version of the current expression.
     *
     * @return Simplified version of the expression.
     */
    @Override
    public Expression simplify() throws Exception {
        // Store simplified data, and get each type (Val, Var, Complex).
        Expression prefixExpressionSimplified = this.prefix.simplify();
        ExpressionType prefixType
                = Expression.getExpressionType(prefixExpressionSimplified);
        Expression postfixExpressionSimplified = this.postfix.simplify();
        ExpressionType postfixType
                = Expression.getExpressionType(postfixExpressionSimplified);
        // If "expression ^ expression" occurs, return False (x^x=F).
        if (prefixExpressionSimplified.equals(postfixExpressionSimplified))
            return new Val(false);
        // If both expressions are Vals, an evaluation can be made. Return it.
        if (prefixType == ExpressionType.Val
                && postfixType == ExpressionType.Val)
            return new Val(this.evaluate());
        // If (expression ^ F) occurs, return expression (e ^ F = e).
        if (prefixType == ExpressionType.Val){
            if(prefixExpressionSimplified.toString().equals(FALSE_EXPRESSION))
                return postfixExpressionSimplified;
                // Otherwise wh have a (expression ^ T) situation.
            else
                // Return true (e ^ T = ~T)
                return new Not(postfixExpressionSimplified);
        }
        //Same method as before, but for the postfix.
        if (postfixType == ExpressionType.Val){
            if(postfixExpressionSimplified.toString().equals(FALSE_EXPRESSION))
                return prefixExpressionSimplified;
                // Otherwise wh have a (expression ^ T) situation.
            else
                // Return true (e ^ T = ~T)
                return new Not(prefixExpressionSimplified);
        }
        /*
         *   If non of the situations above has occurred,
         *   no simplification can be made. So no changes will be made.
         */
        return new Xor(prefixExpressionSimplified,postfixExpressionSimplified);
    }

    @Override
    public String toString() {
        return "("+prefix+" ^ "+postfix+")";
    }
}