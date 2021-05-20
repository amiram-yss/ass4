/**
 * .
 */
public class Test5 {
    /**
     * .
     * @param args  a
     * @throws Exception    a
     */
    public static void main(String[] args) throws Exception {
        Expression e = new And(
                new Var("x"),
                new Val(true)
        );
        System.out.println(e);
        System.out.println(e.simplify());
    }
}
