import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Amiram Yassif
 * 314985474
 * ASS4
 * @version 0.1
 */
public class Val implements Expression{
    /**
     * Properties
     */
    protected Boolean value;

    /**
     * Constructor
     * @param val   Boolean fixed value (True/ False).
     */
    public Val(Boolean val){
        this.value = val;
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
        return this.value;
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
        return this.value;
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return A list of the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
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
        return new Val(this.value);
    }

    @Override
    public String toString() {
        return value ? "T" : "F";
    }
}
