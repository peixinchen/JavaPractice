import java.util.Random;

// parameter 对应就是形参，也就是占位符
// arguments 对象就是实参，也就是真正填入的值
interface Iterator<E> {
    boolean hasNext();
    E next();
}

// 这里的 E 都是 类型参数 (type parameter)
interface List<E> {
    void pushFront(E val);
    void pushBack(E val);
    void insert(int index, E val);
    int size();
    int indexOf(E val);
    
    // 这里的 E 是类型引元 (type argument)
    Iterator<E> iterator();
}

// AbstractList 中的 E 是类型参数；List 中的 E 是类型引元
abstract class AbstractList<E> implements List<E> {
    protected int size = 0;
    
    protected abstract void insertInternal(int index, E val);
    
    @Override
    public void pushFront(E val) {
        insertInternal(0, val);
    }
    
    @Override
    public void pushBack(E val) {
        insertInternal(size, val);
    }
    
    @Override
    public void insert(int index, E val) {
        if (index < 0 || index > size) {
            System.out.println("下标错误");
            return;
        }
        
        insertInternal(index, val);
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public int indexOf(E val) {
        // 这里的 E 是类型引元
        Iterator<E> it = iterator();
        int i = 0;
        // 分成 null 和 非null 两种情况
        // 分布用 == 和 equals 方法判断相等
        if (val == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    return i;
                }
                i++;
            }
        } else {
            while (it.hasNext()) {
                if (it.next().equals(val)) {
                    return i;
                }
                
                i++;
            }
        }
        
        return -1;
    }
}

// GenericArrayList 中的 E 是类型参数；AbstractList 和 List 中的 E 中类型引元
public class GenericArrayList<E> extends AbstractList<E> implements List<E> {
    private E[] array;
    
    // 构造方法中不用再标注类型变量
    // 利用 SuppressWarnings 注解来压制警告
    @SuppressWarnings("unchecked")
    public GenericArrayList() {
        // array = new E[20];   错误的，因为类型参数无法创建数组。
        array = (E[])new Object[20];
    }
    
    @Override
    protected void insertInternal(int index, E val) {
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        
        array[index] = val;
        size++;
    }

    // 这个 E 是类型引元
    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }
    
    // ArrayListIterator 中不用声明类型参数，因为是内部类，直接用 GenericArrayList 的类型参数 E
    // Iterator 中的 E 是类型引元
    private class ArrayListIterator implements Iterator<E> {
        private int index = 0;
        
        @Override
        public boolean hasNext() {
            return index < size;
        }
        
        // 这里
        @Override
        public E next() {
            return array[index++];
        }
    }
    
    // 静态上下文中无法使用 E 类型参数，所以这里的 E 是重新定义的泛型方法，只是咱们用了相同的名字 E
    private static <E> void testIndexOf(E val, List<? super E> list) {
        int index = list.indexOf(val);
        System.out.printf("%s 所在的下标是: %d%n", val, index);
    }
    
    // 打印使用通配符 ?，表示任何类型的 List 咱都可以打印
    private static void printList(List<?> list) {
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            System.out.printf("%s ", it.next());
        }
        System.out.println();
    }
    
    // Integer 是 Number 的子类；Number 是 Object 的子类
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // 引用可以用通配符；实例化的时候不能用通配符
        // List<?> listOfWildcard = new GenericArrayList<?>();  错误的，无法实例化
        List<?> listOfInteger = new GenericArrayList<Integer>();
        List<Number> listOfNumber = new GenericArrayList<Number>();
        List<Object> listOfObject = new GenericArrayList<Object>();
        
        Random random = new Random(20190626);
        for (int i = 0; i < 10; i++) {
            Integer val = random.nextInt(20);
            // listOfInteger.pushBack(val);     无法直接插入，因为编译器不知道应该插入什么类型到 listOfInteger 中
            ((List<Integer>)listOfInteger).pushBack(val);   // 强制转换后可以，但这种做法其实挺糟的
            listOfNumber.pushBack(val);
            listOfObject.pushBack(val);
        }
        
        printList(listOfInteger);
        printList(listOfNumber);
        printList(listOfObject);
        
        testIndexOf(18, (List<Integer>)listOfInteger);
        testIndexOf(18, listOfNumber);
        testIndexOf(18, listOfObject);
    }
}