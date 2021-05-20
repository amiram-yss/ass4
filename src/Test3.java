import java.util.Map;

public class Test3 {
    public static void main(String[] args) throws Exception {
        Expression e = new And(
                new Or(
                        new Val(false)
                        ,new Xor(
                        new Val(false)
                        ,new Val(false)
                ))
                ,new Val(true));
        System.out.println(e);
        System.out.println(e.evaluate());
    }
}
