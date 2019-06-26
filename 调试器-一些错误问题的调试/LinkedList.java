// BUG：实现了链表的迭代器，编译没有问题，但无打印输出

interface Iterator {
	boolean hasNext();
	int next();
}

interface List {
	void pushFront(int val);
	void pushBack(int val);
	void insert(int index, int val);
	int size();
	Iterator iterator();
}

abstract class AbstractList implements List {
	protected int size;
	
	protected abstract void insertInternal(int index, int val);
	
	@Override
	public void pushFront(int val) {
		insertInternal(0, val);
	}
	
	@Override
	public void pushBack(int val) {
		insertInternal(size, val);
	}
	
	@Override
	public void insert(int index, int val) {
		if (index < 0 || index > size) {
			System.out.println("错误的下标");
			return;
		}
		
		insertInternal(index, val);
	}
	
	@Override
	public int size() {
		return size;
	}
}

class Node {
	public int val;
	public Node next;
	
	public Node(int val, Node next) {
		this.val = val;
		this.next = next;
	}
	
	public Node(int val) {
		this(val, null);
	}
}

class LinkedListIterator implements Iterator {
	private Node head;
	private Node cur = head;
	
	public LinkedListIterator(Node head) {
		this.head = head;
	}
	
	public boolean hasNext() {
		return cur != null;
	}
	
	public int next() {
		int val = cur.val;
		cur = cur.next;
		return val;
	}
}

class LinkedList extends AbstractList implements List {
	private Node head;
	
	@Override
	public void insertInternal(int index, int val) {
		if (index == 0) {
			head = new Node(val, head);
		} else {
			Node prev = head;
			for (int i = 0; i < index - 1; i++) {
				prev = prev.next;
			}
			prev.next = new Node(val, prev.next);
		}
		size++;
	}
	
	@Override
	public Iterator iterator() {
		return new LinkedListIterator(head);
	}
	
	// 两个节点
	// 1. 咱的链表实现是不是有问题？
	// 2. 咱的迭代器是不是有问题？
	private static void testList(List list) {
		list.pushFront(1);
		list.pushBack(2);
		list.pushBack(3);
		list.insert(3, 4);
		
		Iterator it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public static void main(String[] args) {
		testList(new LinkedList());
	}
}




