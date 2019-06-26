class Base {
    private static final String initStaticBase() {
        System.out.println("Base-静态属性初始化");
        return "staticBase";
    }
    
    private String base = initBase();
    
    private final String initBase() {
        System.out.println("Base-属性初始化");
        return "base";
    }
    
    static {
        System.out.println("Base-静态构造代码块-1");
    }
    
    private static String staticBase = initStaticBase();
    
    {
        System.out.println("Base-构造代码块-1");
    }
    
    Base() {
        System.out.println("Base-构造方法-无参");
    }
    
    {
        System.out.println("Base-构造代码块-2");
    }
    
    static {
        System.out.println("Base-静态构造代码块-2");
    }
}

class Derived extends Base {
    private static String staticDerived = initStaticDerived();
    
    private String derived = initDerived();
    
    private static final String initStaticDerived() {
        System.out.println("Derived-静态属性初始化");
        return "staticDerived";
    }
    
    private final String initDerived() {
        System.out.println("Derived-属性初始化");
        return "derived";
    }
    
    static {
        System.out.println("Derived-静态构造代码块-1");
    }
    
    {
        System.out.println("Derived-构造代码块-1");
    }
    
    Derived() {
        this("无参构造方法中调用");
        System.out.println("Derived-构造方法-无参");
    }
    
    {
        System.out.println("Derived-构造代码块-2");
    }
    
    static {
        System.out.println("Derived-静态构造代码块-2");
    }
    
    Derived(String s) {
        System.out.println("Derived-构造方法-有参-" + s);
    }
}

public class OrderOfStatement {
    public static void main(String[] args) {
        System.out.println("开始");
        new Derived();
        System.out.println("==================================");
        new Derived("直接传入");
        System.out.println("结束");
    }
}