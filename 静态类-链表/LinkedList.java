// 只是演示静态类的使用，链表的实现并不完整
public class LinkedList {
	// 基于两点原因把 Node 定义为静态类
	// 1. Node 类型只是用在链表实现内部的细节，和使用链表的用户无关，所以不适合定义成顶级类暴露出去
	// 2. Node 类型不需要和链表的对象有绑定关系，所以适合用静态类
	private static class Node {
		// 即使是 private，外部的类也有访问权限
		private int val;
		private Node next;
		
		private Node(int val, Node next) {
			this.val = val;
			this.next = next;
		}
		
		private Node(int val) {
			this(val, null);
		}
	}
	
	private Node head = null;
	private int size = 0;
	
	public void add(int index, int val) {
		if (index < 0 || index > size) {
			System.out.println("错误的下标");
			return;
		}
		
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
	
	public void add(int val) {
		add(size, val);
	}
}