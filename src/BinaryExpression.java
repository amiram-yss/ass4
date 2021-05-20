/**
 * @author Amiram Yassif
 * 314985474
 * ASS4
 */
public abstract class BinaryExpression extends BaseExpression {

    protected Expression prefix;
    /**
     * Constructor.
     * @param prefix    The expression followed by the operator.
     * @param postfix   The expression following the operator.
     */
    protected BinaryExpression(Expression prefix, Expression postfix) {
        super(postfix);
        this.prefix = prefix;
    }
}
