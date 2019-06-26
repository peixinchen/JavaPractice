import java.util.Random;

// 迭代器，专门用于进行数据迭代的一类结构
interface Iterator {
    boolean hasNext();  // 用于判断是否还能继续往后走？
    int next();         // 返回当前的数据，并且让迭代器往后走一步
}

// 线性表
interface List {
    void add(int value);    // 尾插

    Iterator iterator();    // 返回统一的迭代器，方便大家用同样的方式进行遍历
}

// 顺序表是线性表
class ArrayList implements List {
    private int[] array = new int[1000];
    private int size;

    public void add(int value) {
        // 一开始分配给 array 的空间大一些，就不考虑扩容的问题了
        array[size++] = value;
    }

    // 1. 利用 inner class 实现
    // 因为需要获取当前顺序表的 array 和 size
    // 也就是需要顺序表对象
    // 所有必须用 inner class 而不能使用 static class
    class ArrayIterator implements Iterator {
        private int index = 0;

        @Override
        public boolean hasNext() {
            // index 是迭代器对象的属性
            // size 是顺序表对象的属性
            return index < size;
            // 完整的写法，只要没有出现 name shadowing 的情况，就没必要这么写
            // return this.index < ArrayList.this.index;
        }

        @Override
        public int next() {
            return array[index++];
        }
    }

    public Iterator iterator() {
        // return new ArrayIterator();

        // 2. 利用 local class 实现
        class ArrayIteratorLocal implements Iterator {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public int next() {
                return array[index++];
            }
        }
        //return new ArrayIteratorLocal();
        
        // 3. 利用匿名类实现
        return new Iterator() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public int next() {
                return array[index++];
            }
        };
    }
}

// 链表是线性表
class LinkedList implements List {
    // 链表的结点只是内部使用的，没必要暴露出去
    // 所以使用内部嵌套类
    // 又因为不需要和对象绑定，所以使用普通的静态内部类即可
    private static class Node {
        int value;
        Node next;      // 隐含着 next = null，因为默认值的原因
        Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node last;

    public void add(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
        } else {
            last.next = node;
        }
        last = node;
    }

    public Iterator iterator() {
        return new Iterator() {
            Node cur = head;

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public int next() {
                int v = cur.value;
                cur = cur.next;
                return v;
            }
        };
    }
}

public class InnerClassExample {
    // 有了迭代器之后，要遍历一个线性表，就不用管内部是怎么实现的了
    // 实际是顺序表还是链表其实都和我们无关了
    public static void testList(List list) {
        Random random = new Random(20190612);
        for (int i = 0; i < 30; i++) {
            list.add(random.nextInt(100));
        }

        Iterator it1 = list.iterator();
        Iterator it2 = list.iterator();

        it1.next();
        it1.next();
        it1.next();

        // it1 的移动完全不影响 it2 的移动
        System.out.println("利用 it2 进行所有数据的打印");
        while (it2.hasNext()) {
            int value = it2.next();
            System.out.printf("%d ", value);
        }
        System.out.println();

        // it1 跳过了前三个数
        System.out.println("跳过了前三个数");
        while (it1.hasNext()) {
            int value = it1.next();
            System.out.printf("%d ", value);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List list1 = new ArrayList();
        List list2 = new LinkedList();

        testList(list1);
        testList(list2);
    }
}