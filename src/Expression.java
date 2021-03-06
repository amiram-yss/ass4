import java.util.List;
import java.util.Map;

/**
 * @author Amiram Yassif
 * 314985474
 * ASS4
 */
public interface Expression {
    /**
     * Constants.
     */
    String FALSE_EXPRESSION = "F";
    String TRUE_EXPRESSION = "T";
    short SIMPLE_EXPRESSION_STRING_LENGTH = 1;

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
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return The result after setting vals in vars.
     * @throws Exception In case of giving an invalid var's name, throw.
     */
    Boolean evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return A list of the variables in the expression
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return A nice string representation of the expression.
     */
    String toString();

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
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from converting all the operations
     * to the logical Nand operation.
     *
     * @return Expression in Nand format.
     */
    Expression nandify();

    /**
     * Returns the expression tree resulting from converting all the operations
     * to the logical Nor operation.
     *
     * @return Expression in Nor format.
     */
    Expression norify();

    /**
     * Returned a simplified version of the current expression.
     *
     * @throws Exception In case of evaluate() failure.
     * @return Simplified version of the expression.
     */
    Expression simplify();

    /**
     * Checks if two expressions are equal.
     *
     * @param e The expression
     * @return True if has equal variables, False otherwise.
     */
    boolean equals(Expression e);

    /**
     * Returns if an expression is a Var, Val, or a complex expression.
     *
     * @param e The expression
     * @return If e is an instance of:
     * val     ->  ExpressionType.Val
     * var     ->  ExpressionType.Var
     * Complex ->  ExpressionType.Complex
     */
    static ExpressionType getExpressionType(Expression e) {
        String expressionStringRepresentation = e.toString();
        // If string representation is longer than 1 dig, it's complex.
        if (expressionStringRepresentation.length()
                != SIMPLE_EXPRESSION_STRING_LENGTH) {
            return ExpressionType.Complex;
            // If the string representation is T/F, it means it's a Val.
        }
        if (e.toString().equals(TRUE_EXPRESSION)
                || e.toString().equals(FALSE_EXPRESSION)) {
            return ExpressionType.Val;
        }
        // Otherwise, it's a Var.
        return ExpressionType.Var;
    }

    /**
     * Checks if an expression is evaluateable, which means it doesn't include
     * Vals and complex only (no Vars are included), and therefore, it is
     * possible to run evaluate() without getting an exception.
     *
     * @param e The expression.
     * @return True if evaluate() will run properly. False otherwise.
     */
    static boolean evaluateable(Expression e) {
        // Try to run evaluate.
        try {
            // If operation succeeded, return True.
            e.evaluate();
            return true;
        } catch (Exception ex) {
            //If failed, return False.
            return false;
        }
    }
}
