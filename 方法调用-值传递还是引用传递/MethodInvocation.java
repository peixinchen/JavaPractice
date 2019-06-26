import java.util.Arrays;

public class MethodInvocation {
    public static void swap(int a, int b) {
        int t = a;
        a = b;
        b = a;
    }

    // 方法的重载
    public static void swap(int[] a, int[] b) {
        int[] t = a;
        a = b;
        b = a;
    }

    // 方法的重载
    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        // 基本类型
        int a = 10;
        int b = 20;
        System.out.printf("交换之前：a = %d, b = %d%n", a, b);
        swap(a, b);
        // a，b 的值没有发生变化，因为 java 中基本类型作为参数调用是值传递过程
        System.out.printf("交换之后：a = %d, b = %d%n", a, b);

        // 引用类型，以数组为例
        int[] arr = { 1, 2, 3, 4, 5 };
        int[] brr = { 11, 12, 13, 14, 15 };
        System.out.println("交换之前：");
        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("brr = " + Arrays.toString(brr));
        swap(arr, brr);
        // arr，brr 引用指向的对象没有发生变化，因为 java 中引用类型作为参数调用也是值传递过程
        System.out.println("交换之后：");
        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("brr = " + Arrays.toString(brr));

        // 引用类型，以数组为例
        int[] crr = { 100, 200, 300, 400 };
        System.out.println("交换之前：");
        System.out.println("crr = " + Arrays.toString(crr));
        swap(crr, 0, 1);
        // crr 指向对象（这里是数组）内部的值是会发生变化的，因为引用虽然是值传递，但修改引用指向的内容是可见的
        System.out.println("交换之后：");
        System.out.println("crr = " + Arrays.toString(crr));
    }
}
