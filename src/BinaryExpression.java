/**
 * @author Amiram Yassif
 * 314985474
 * ASS4
 */
public abstract class BinaryExpression extends BaseExpression {

    /**
     * Properties.
     */
    private Expression prefix;

    /**
     * Getter for prefix.
     * @return  Prefix.
     */
    public Expression getPrefix() {
        return this.prefix;
    }

    /**
     * Set prefix.
     * @param e Expression to set.
     */
    public void setPrefix(Expression e) {
        this.prefix = e;
    }

    /**
     * Constructor.
     *
     * @param prefix  The expression followed by the operator.
     * @param postfix The expression following the operator.
     */
    protected BinaryExpression(Expression prefix, Expression postfix) {
        super(postfix);
        this.prefix = prefix;
    }
}
