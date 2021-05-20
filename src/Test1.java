import java.util.Map;
import java.util.TreeMap;

public class Test1 {
    public static void main(String[] args) throws Exception{
        Expression e = new And(
                new Or(
                        new Var("y")
                        ,new Xor(
                                new Val(true)
                        ,new Var("z")
                ))
                ,new Var("x"));
        System.out.println(e);
        Map<String, Boolean> m = new TreeMap<>();
        m.put("x", true);
        m.put("y", false);
        m.put("z", false);
        System.out.println(e.evaluate(m));
        Expression f = e.assign("y",new Var("z"));
        System.out.println(f);
        System.out.println(e.getVariables());
        System.out.println(f.getVariables());
    }
}
