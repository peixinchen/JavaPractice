import java.util.List;
import java.util.ArrayList;


public class GenericWildcards {
    // 把 from 中的元素 merge 到 to 中
    private static <T> void merge(List<T> to, List<? extends T> from) {
    }

    // 把 t 放入到 list 中
    private static <T> void add(T t, List<? super T> list) {
    }

    // 下面的代码只演示写形参过程中的通配符怎么写，并没有直接去执行真正的代码
    // 先写出使用的场景，反过来推断形参中的通配符怎么写
    public static void main(String[] args) {
        // 要保证 from 中的所有元素都能放到 to 中
        Main.<Object>merge(new ArrayList<Object>(), new ArrayList<Object>());
        Main.<Object>merge(new ArrayList<Object>(), new ArrayList<Number>());
        Main.<Object>merge(new ArrayList<Object>(), new ArrayList<Integer>());

        // 要保证 t 放到 list 中
        Main.<Integer>add(10, new ArrayList<Integer>());
        Main.<Integer>add(10, new ArrayList<Number>());
        Main.<Integer>add(10, new ArrayList<Object>());
    }
}