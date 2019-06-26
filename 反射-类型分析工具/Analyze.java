import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Member;
import java.util.List;
import java.util.ArrayList;

public class Analyzer {
    private static void getAncestors(Class<?> cls, List<Class> list) {
        Class<?> ancestor = cls.getSuperclass();
        if (ancestor != null) {
            list.add(ancestor);
            getAncestors(ancestor, list);
        }
    }

    private static void printMembers(Member[] members, String s) {
        if (members.length == 0) {
            System.out.println("    没有" + s);
            return;
        }

        System.out.println("    " + s + "有:");
        for (Member m : members) {
            if (m instanceof Field) {
                System.out.println("        " + ((Field)m).toGenericString());
            } else if (m instanceof Constructor) {
                System.out.println("        " + ((Constructor)m).toGenericString());
            } else if (m instanceof Method) {
                System.out.println("        " + ((Method)m).toGenericString());
            } else {
                System.out.println("        不认识的成员类型");
            }
        }
    }

    private static void analyze(String className) throws ClassNotFoundException {
        Class cls = Class.forName(className);
        System.out.println("正在分析: " + className);
        Package p = cls.getPackage();
        System.out.println("    所属包: " + (p == null ? "不具名包" : p.getName()));
        System.out.println("    名称: " + cls.getName());
        System.out.println("    权威名称: " + cls.getCanonicalName());
        System.out.println("    限定符: " + Modifier.toString(cls.getModifiers()));

        // 打印类型参数
        TypeVariable[] tvs = cls.getTypeParameters();
        if (tvs.length != 0) {
            System.out.println("    类型参数有:");
            for (TypeVariable tv : tvs) {
                System.out.println("        " + tv.getName());
            }
        } else {
            System.out.println("    没有类型参数");
        }

        // 打印祖先
        List<Class> list = new ArrayList<>();
        getAncestors(cls, list);
        if (list.size() != 0) {
            System.out.println("    祖先类有:");
            for (Class<?> ac : list) {
                System.out.println("        " + ac.getCanonicalName());
            }
        } else {
            System.out.println("    没有祖先");
        }

        // 打印接口
        Type[] intfs = cls.getGenericInterfaces();
        if (intfs.length != 0) {
            System.out.println("    实现了接口:");
            for (Type intf : intfs) {
                System.out.println("        " + intf.toString());
            }
        } else {
            System.out.println("    没有实现接口");
        }

        // 打印注解
        Annotation[] ann = cls.getAnnotations();
        if (ann.length != 0) {
            System.out.println("    被以下注解修饰:");
            for (Annotation a : ann) {
                System.out.println("        " + a.toString());
            }
        } else {
            System.out.println("    没有被注解修饰");
        }

        // 打印构造方法
        printMembers(cls.getConstructors(), "构造方法");

        // 打印属性
        printMembers(cls.getFields(), " public 的属性（包括继承下来的）");
        printMembers(cls.getDeclaredFields(), "直接定义的属性（包括 private 的）");

        // 打印方法
        printMembers(cls.getMethods(), " public 的方法（包括继承下来的）");
        printMembers(cls.getDeclaredMethods(), "直接定义的方法（包括 private 的）");
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("至少指定一个类型名称");
        }

        for (String className : args) {
            try {
                analyze(className);
            } catch (ClassNotFoundException ignored) {
                System.out.println(className + " 没找到这个类型");
            }
        }
    }
}