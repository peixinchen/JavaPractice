class Super {
    static int taxi = 1729;

    static { System.out.println("Super 类初始化 "); }
}

class Sub extends Super {
    static { System.out.println("Sub 类初始化"); }
}

/**
 * 结果是
 * Super 类初始化
 * 1729
 */
class Test2 {
    public static void main(String[] args) {
        // 虽然写作 Sup.taxi，但实际访问的是 Super.taxi，所以会触发 Super 类的初始化
        System.out.println(Sub.taxi);
    }
}
