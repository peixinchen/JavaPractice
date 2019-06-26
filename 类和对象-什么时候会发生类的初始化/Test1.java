class Super {
    static { System.out.println("Super 类初始化"); }
}

class One {
    static { System.out.println("One 类初始化"); }
}

class Two extends Super {
    static { System.out.println("Two 类初始化"); }
}

/**
 * 结果是
 * Super 类初始化
 * Two 类初始化
 * false
 */
public class Test1 {
    public static void main(String[] args) {
        One o = null;   // One 只是创建了引用，没有构造对象，不需要初始化 One 类

        // 构造 Two 类的对象实例，需要初始化 Two 类，但是需要先初始化 Two 类的父类 Super 及 Super 的隐式父类 Object
        Two t = new Two(); 

        // 引用参与比较运算也触发类的初始化
        System.out.println((Object)o == (Object)t);
    }
}
