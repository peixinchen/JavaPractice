public class Obscure {
    public static void main(String[] args) {
        // 定义了一个 package_name 的变量
		int package_name = 100;
		
        // 发生了 Obscuring（遮掩）
        // 无法通过编译，因为编译器把 package_name 当成 int 型的变量了，而不是包
        // System.out.println(package_name.ClassInPackage.NAME);
    }
}