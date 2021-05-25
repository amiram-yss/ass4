/**
 * .
 */
public class Test6 {
    /**
     * .
     * @param args .
     */
    public static void main(String[] args) {
        Expression e = new Or(new Var("x"), new Var("y"));
        System.out.println(e.nandify());
        System.out.println(e.norify());
        // should print:
        // ((x ↑ (x ↑ y)) ↑ (y ↑ (x ↑ y)))
        // (((x ↓ x) ↓ (y ↓ y)) ↓ (x ↓ y))
    }
}
