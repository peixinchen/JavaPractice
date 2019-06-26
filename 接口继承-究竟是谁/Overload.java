public class Overload {
    public void method() {
        System.out.println("无参方法");
    }

    public void method(String param) {
        System.out.println("String 参数方法(" + param + ")");
    }

    public void method(int param) {
        System.out.println("int 参数方法(" + param + ")");
    }

    // 返回值不重要
    public float method(float param) {
        System.out.println("float 参数方法(" + param + ")");
        return param;
    }

    public static void main(String[] args) {
        Overload o = new Overload();
        o.method();
        o.method("String");
        o.method(1);
        o.method(1.0F);
    }
}