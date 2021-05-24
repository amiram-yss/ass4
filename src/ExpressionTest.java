import java.util.Map;
import java.util.TreeMap;

/**
 * @author Amiram Yassif
 * 314985474
 */
public class ExpressionTest {
    /**
     * Testing the program.
     * @param args empty
     * @throws Exception If exception in assignment(map) if happens (it doesn't)
     */
    public static void main(String[] args) throws Exception {
        //Create an expression with at least 3 variables
        Expression e = new Not(
                new Xor(
                        new And(
                                new Val(true),
                                new Or(
                                        new Var("x"),
                                        new Var("y")
                                )
                        ),
                        new Var("z")
                )
        );
        // Prints the expression ~(((T & (x | y)) ^ z))
        System.out.println(e);
        // Print the value of the expression with an assignment to every var.
        Map<String, Boolean> map = new TreeMap<>();
        map.put("x", true);
        map.put("y", false);
        map.put("z", true);
        System.out.println(e.evaluate(map));
        // Print in nandified format.
        System.out.println(e.nandify());
        // Print norified format.
        System.out.println(e.norify());
        // Print simplified expression of the expression.
        System.out.println(e.simplify());
    }
}
