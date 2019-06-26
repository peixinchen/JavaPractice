import java.util.Arrays;

// 我们只定义线性表支持的功能，具体实现可以是顺序表，也可以是链表
interface List {
    int size(); // 返回已经有的数据个数

    void pushFront(int value);  // 头插
    void insert(int index, int value);  // 根据下标位置插入
    void pushBack(int value);   // 尾插

    /*
	TODO: 留给大家实现一下
    void popFront();
    void erase(int index);
    void popBack();
    */

    void display(); // 打印所有数据
}

// 能以 O(1) 时间复杂度完成根据下标取数据或者设置数据
interface RandomAccess {
    public int get(int index);      // 根据下标取数据
    public void set(int index, int value);  // 根据下标设置数据
}

abstract class AbstractList implements List {
    // 指定一个抽象方法，要求派生类必须实现
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void pushFront(int value) {
        insertInner(0, value);  // 头插也就是在下标 0 处插入
    }

    @Override
    public void insert(int index, int value) {
        // index 校验是通用的，所以放在基类中
        if (!isValidIndexForInsert(index)) {
            return;
        }

        insertInner(index, value);
    }

    @Override
    public void pushBack(int value) {
        insertInner(size, value);   // 尾插也就是在下标 size 处插入
    }

    // 指定一个抽象方法，要求派生类必须实现
    protected abstract void insertInner(int index, int value);

    // protected 访问限定符保证派生类也可以使用
    protected boolean isValidIndexForInsert(int index) {
        if (index < 0 || index > size) {
            System.err.println("错误的 index 下标");
            return false;
        } else {
            return true;
        }
    }

    // protected 访问限定符保证派生类也可以使用
    protected boolean isValidIndex(int index) {
        if (index < 0 || index >= size) {
            System.err.println("错误的 index 下标");
            return false;
        } else {
            return true;
        }
    }

    // 因为是抽象类的原因，完全不用管 display() 方法
}

class ArrayList extends AbstractList implements RandomAccess {
    private int[] array = new int[10];

    @Override
    protected void insertInner(int index, int value) {
        ensureCapacity();

        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }

        array[index] = value;
        size++;
    }

    @Override
    public int get(int index) {
        if (!isValidIndex(index)) {
            return -1;
        }

        return array[index];
    }

    @Override
    public void set(int index, int value) {
        if (!isValidIndex(index)) {
            return;
        }

        array[index] = value;
    }

    @Override
    public void display() {
        int[] validArray = Arrays.copyOf(array, size);
        String arrayString = Arrays.toString(validArray);
        System.out.println(arrayString);

        // System.out.println(Arrays.toString(Arrays.copyOf(array, size)));
    }

    private void ensureCapacity() {
        if (size < array.length) {
            return;
        }

        // 利用别人实现的方法实现数据拷贝
        array = Arrays.copyOf(array, array.length * 2);
    }
}

class Node {
    int value;
    Node next;

    Node(int value) {
        this.value = value;
    }
}

class LinkedList extends AbstractList {
    Node head;

    @Override
    protected void insertInner(int index, int value) {
        Node node = new Node(value);

        if (index == 0) {
            node.next = head;
            head = node;
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }

            node.next = prev.next;
            prev.next = node;
        }

        size++;
    }

    @Override
    public void display() {
        for (Node cur = head; cur != null; cur = cur.next) {
            System.out.printf("(%d) -> ", cur.value);
        }
        System.out.println();
    }
}

public class InterfaceInheritExample {
    public static void testList(List list) {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushFront(10);
        list.pushFront(20);
        list.pushFront(30);
        list.insert(3, 2019);
        list.insert(100, -1);  // 下标错误了

        list.display();
    }

    public static void testRandomAccess(RandomAccess ra) {
        System.out.println(ra.get(3));
        ra.set(3, 612);
		// 这里不能调用 display，因为 RandomAccess 接口没有说它支持 display
    }

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();

        testList(arrayList);
        testList(linkedList);

        testRandomAccess(arrayList);
        arrayList.display();
    }
}