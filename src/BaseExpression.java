/**
 * @author Amiram Yassif
 * 314985474
 * ASS4
 */
public abstract class BaseExpression implements Expression {
    /**
     * Postfix value, the expression followed by the operator.
     */
    private final Expression postfix;

    /**
     * Postfix getter.
     * @return Postfix
     */
    public Expression getPostfix() {
        return this.postfix;
    }

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