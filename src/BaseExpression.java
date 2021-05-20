/**
 * @author Amiram Yassif
 * 314985474
 * ASS4
 */
public abstract class BaseExpression implements Expression {
    /**
     * Postfix value, the expression followed by the operator.
     */
    protected final Expression postfix;

    /**
     * Constructor.
     *
     * @param postfix The expression following an operator.
     */
    protected BaseExpression(Expression postfix) {
        this.postfix = postfix;
    }

    @Override
    public boolean equals(Expression e) {
        return this.toString().equals(e.toString());
    }
}