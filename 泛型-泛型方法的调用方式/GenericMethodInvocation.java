public class GenericMethodInvocation {
    static private <T extends Comparable<T>> T max(T a, T b) {
        if (a.compareTo(b) >= 0) {
            return a;
        } else {
            return b;
        }
    }

    public static void main(String[] args) {
        System.out.println(max(1, 2));      // 类型推导 T 是 Integer 类型
        System.out.println(max(1.0, 2.0));  // 类型推导 T 是 Double 类型
        // System.out.println(max(1, 2.0)); 错误的，推到不出 T 的类型

        // 显式指定 T 的类型，前面的 GenericMethodInvocation 不能省略
        System.out.println(GenericMethodInvocation.<Double>max((double)1, 2.0));
        System.out.println(max((double)1, 2.0)); // 其实有了类型转换后，不需要显式指定 T 的类型
    }
}