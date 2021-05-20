/**
 * @author Amiram Yassif
 * 314985474
 * ASS4
 */
public abstract class UnaryExpression extends BaseExpression {
    /**
     * Constructor.
     * @param postfix The expression following the NOT (~) operator.
     */
    protected UnaryExpression(Expression postfix) {
        super(postfix);
    }
}
