class Base {
    String field = "父类的属性";
    static String staticField = "父类的静态属性";

    static void staticMethod() {
        System.out.println("父类的静态方法");
    }
}

class Derived extends Base {
    String field = "子类的属性";
    static String staticField = "子类的属性";

    static void staticMethod() {
        System.out.println("子类的静态方法");
    }

    public void printDerived() {
        System.out.println(field);
        System.out.println(this.field);
        System.out.println(staticField);        // 不推荐这么写
        System.out.println(this.staticField);   // 不推荐这么写
        System.out.println(Derived.staticField);
        staticMethod();                         // 不推荐这么写
        Derived.staticMethod();
    }

    public void printBase() {
        System.out.println(super.field);
        System.out.println(Base.staticField);
        Base.staticMethod();
    }
}

public class Hide {
    public static void main(String[] args) {
        Derived object = new Derived();
        object.printDerived();
        System.out.println("======================");
        object.printBase();
    }
}