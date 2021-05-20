import java.util.*;

/**
 * @author Amiram Yassif
 * 314985474
 * ASS4
 * @version 0.1
 */
public class Var implements Expression {
    /**
     * Properties.
     */
    final protected String key;

    /**
     * Constructor.
     * @param key   The name of the variable.
     */
    public Var(String key) {
        this.key = key;
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
        if (!assignment.containsKey(this.key))
            throw new Exception
                    ("Exception thrown: Key \""+key+"\" not found in map.");
        return assignment.get(this.key);
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
        throw new Exception
                ("Exception thrown: Cannot resolve with an empty map.");
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return A list of the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        List<String> ltr = new ArrayList<>();
        ltr.add(this.key);
        return ltr;
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
        if(this.key.equals(var))
            return expression;
        return this;
    }

    @Override
    public String toString(){
        return this.key;
    }
}
