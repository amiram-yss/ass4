/**
 * .
 */
public class Test6 {
    /**
     * .
     * @param args .
     */
    public static void main(String[] args) {
        Expression e = new Not(new Or(new Val(false), new Var("x")));
        System.out.println(e);
        System.out.println(e.simplify());
    }
}
