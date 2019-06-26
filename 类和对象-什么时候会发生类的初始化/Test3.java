interface I {
    // 接口中的属性自带隐式的 final 修饰符，i 是常量变量
    int i = 1, ii = Test3.out("ii", 2);
}

interface J extends I {
    // j 不是常量
    int j = Test3.out("j", 3), jj = Test3.out("jj", 4);
}

interface K extends J {
    int k = Test3.out("k", 5);
}

/**
 * 结果是
 * 1
 * j=3
 * jj=4
 * 3
 */
class Test3 {
    public static void main(String[] args) {
        // 写做 J.i 实际是 I.i，但因为是常量变量不触发 I 的初始化
        System.out.println(J.i);
        // 写做 K.j 实际是 J.j，J.j 不是常量变量，会触发 J 的初始化，所以 J.jj 也会被初始化
        System.out.println(K.j);
    }

    static int out(String s, int i) {
        System.out.println(s + "=" + i);
        return i;
    }
}
