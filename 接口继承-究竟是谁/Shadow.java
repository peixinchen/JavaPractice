public class Shadow {
    public String name = "属性";

    public void printName(String name) {
        // 发生了 shadowing（遮蔽），形参 name 遮蔽了属性 name
        System.out.println(name);
    }

    public static void main(String[] args) {
        Shadow o = new Shadow();
        o.printName("实参");
    }
}