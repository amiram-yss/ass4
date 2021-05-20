public class Test5 {
    public static void main(String[] args) throws Exception{
        Expression e = new Or(
                new Var("x"),
                new Or(new Val(false), new Var("x"))
        );
        System.out.println(e);
        System.out.println(e.simplify());
    }
}
