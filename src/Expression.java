import java.util.List;
import java.util.Map;

/**
 * @author Amiram Yassif
 * 314985474
 * ASS4
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     * @param assignment    Evaluate the expression using the variable values
     *                      provided in the assignment.
     * @return Result.      The result after setting vals in vars.
     * @throws Exception    In case of giving an invalid var's name, throw.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     * @return              The result after setting vals in vars.
     * @throws Exception    In case of giving an invalid var's name, throw.
     */
    Boolean evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     * @return A list of the variables in the expression
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     * @return A nice string representation of the expression.
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     * @param var           The var's name.
     * @param expression    Replace the var above with the following expression.
     * @return              a new expression in which all occurrences
     *                      of the variable var are replaced with the provided
     *                      expression.
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from converting all the operations
     * to the logical Nand operation.
     * @return  Expression in Nand format.
     */
    Expression nandify();

    /**
     * Returns the expression tree resulting from converting all the operations
     * to the logical Nor operation.
     * @return  Expression in Nor format.
     */
    Expression norify();
}
