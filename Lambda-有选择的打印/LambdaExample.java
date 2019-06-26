import java.util.Random;

// 利用接口实现让用户随意指定判断条件
interface Condition {
    boolean test(int value);
}

class ArrayList {
    private int[] array = new int[1000];
    private int size;

    public void add(int value) {
        // 一开始分配给 array 的空间大一些，就不考虑扩容的问题了
        array[size++] = value;
    }

	// 打印全部数据
    public void display() {
        for (int i = 0; i < size; i++) {
            int value = array[i];
            System.out.printf("%d ", value);
        }
        System.out.println();
    }

	// 根据用户给定的条件，只打印符合条件的数据
    public void displayCondition(Condition condition) {
        for (int i = 0; i < size; i++) {
            int value = array[i];
            if (condition.test(value)) {
                System.out.printf("%d ", value);
            }
        }
        System.out.println();
    }
}

// 使用 top class 进行奇数判断
class OddCondition implements Condition {
    public boolean test(int value) {
        return value % 2 != 0;
    }
}

public class LambdaExample {

    // 利用 static class 进行偶数判断
    // 之所以必须是 static class 而不能用 inner class
    // 是因为要在 static 方法中使用这个类
    static class EvenCondition implements Condition {
        public boolean test(int value) {
            return value % 2 == 0;
        }
    }

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        Random random = new Random(20190612);   // 给定随机种子
        for (int i = 0; i < 30; i++) {
            arrayList.add(random.nextInt(100));
        }

        System.out.println("打印全部数:");
        arrayList.display();

        // 只打印符合我们条件的数据
        System.out.println("只打印奇数:");
        arrayList.displayCondition(new OddCondition());    // 只打印奇数


        System.out.println("只打印偶数:");
        arrayList.displayCondition(new EvenCondition());    // 只打印偶数

        // 利用 local class 判断是否是 3 的倍数
        class TimesOf3 implements Condition {
            public boolean test(int value) {
                return value % 3 == 0;
            }
        }
        System.out.println("只打印 3 的倍数:");
        arrayList.displayCondition(new TimesOf3());    // 只打印 3 的倍数

        System.out.println("只打印 5 的倍数:");
        // 利用匿名类判断是否是 5 的倍数
        arrayList.displayCondition(new Condition() {
            public boolean test(int value) {
                return value % 5 == 0;
            }
        });    // 只打印 5 的倍数

        System.out.println("只打印 11 的倍数:");
        // 利用 Lambda 表达式判断是否是 11 的倍数
        arrayList.displayCondition(value -> value % 11 == 0);                       // 只打印 11 的倍数        
		arrayList.displayCondition(value -> { return value % 11 == 0; });           // 只打印 11 的倍数
        arrayList.displayCondition((value) -> { return value % 11 == 0; });         // 只打印 11 的倍数
        arrayList.displayCondition((int value) -> { return value % 11 == 0; });     // 只打印 11 的倍数
		// 方法引用（method reference）
        arrayList.displayCondition(LambdaExample::isTimesOf11StaticMethod);         // 只打印 11 的倍数        
		arrayList.displayCondition(new LambdaExample()::isTimesOf11InstanceMethod); // 只打印 11 的倍数
        // ... 等等其他情况
    }

    private boolean isTimesOf11InstanceMethod(int value) {
        return value % 11 == 0;
    }

    private static boolean isTimesOf11StaticMethod(int value) {
        return value % 11 == 0;
    }
}