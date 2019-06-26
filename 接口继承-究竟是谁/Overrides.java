class B {}
class E extends B {}

class Base {
    B method(String param) {
        System.out.println("父类的方法(" + param + ")");
        return null;
    }
}

class Derived extends Base {
    // 这个语法是注解，回头再详细讲
    @Override
    // 返回值类型必须是父类指定的返回值类型或者返回值类型的子类型
    E method(String param) {
        System.out.println("子类的方法(" + param + ")");
        return null;
    }
}

public class Overrides {
    public static void main(String[] args) {
        Derived o = new Derived();
        o.method("param");
    }
}