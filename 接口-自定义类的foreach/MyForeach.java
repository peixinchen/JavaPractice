import java.util.Iterator;

// foreach 的关键就在于实现了 Iterable 接口
class MyClass implements Iterable<Integer> {
    // 这里用静态类或者内部类都可以
    private static class MyIterator implements Iterator<Integer> {
        private int i = 0;
        @Override
        public boolean hasNext() {
            return i < 10;
        }

        @Override
        public Integer next() {
            int v = 100 + i * 3;
            i++;
            return v;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyIterator();
    }
}

public class MyForeach {
    public static void main(String[] args) {
        for (int v : new MyClass()) {
            System.out.println(v);
        }
    }
}
