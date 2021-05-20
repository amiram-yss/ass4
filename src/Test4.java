public class Test4 {
    public static void main(String[] args) throws Exception{
        Expression e = new And(
                new And(new Var("x"), new Var("y"))
                ,new Val(false)
        );
        System.out.println(e);
        System.out.println(e.simplify());
    }
}
