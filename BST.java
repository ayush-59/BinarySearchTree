package binarySearchTree;

public class BST<K extends Comparable<K>, V> {
	private Node root;
	
	public BST() {
		this.root = null;
	}
	
	public BST(K key, V value) {
		this.root = new Node(key, value);
	}
	
	private class Node {
		private K key;
		private V value;
		private Node left;
		private Node right;
		private int size;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
			this.size = 1;
		}	
		
	}
	
	public int sizeOf(Node node) {
		return (node == null ? 0 : node.size);
	}
	
	public K min() {
		if(root == null)
			return null;
		return min(root).key;
	}
	
	private Node min(Node curr) {
		if(curr.left == null)
			return curr;
		return min(curr.left);
	}
	
	public K max() {
		if(root == null)
			return null;
		return max(root).key;
	}
	
	private Node max(Node curr) {
		if(curr.right == null)
			return curr;
		return max(curr.right);
	}
	
	public K floor(K key) {
		// largest key in tree less than or equal to me
		if(root == null)
			return null;
		
		return floor(key, root, null);
	}
	
	private K floor(K key, Node curr, K k) {
		if(curr == null)
			return k;
		
		int cmp = key.compareTo(curr.key);
		
		if(cmp == 0) {
			if(curr.left != null)
				if(k.compareTo(curr.left.key) < 0) {
					k = max(curr.left).key;
				}
			return k;
		}
		else if(cmp < 0) 			
			return floor(key, curr.left, k);
		else
			return floor(key, curr.right, curr.key);
		
	}
	
	public int rank(K key) {
		return rank(key, root, 0);
	}
	
	private int rank(K key, Node curr, int currRank) {
		if(curr == null)
			return -1;
		int cmp = key.compareTo(curr.key);
		if(cmp == 0) 
			return currRank + sizeOf(curr.left);
		else if(cmp < 0) 
			return rank(key, curr.left, currRank);
		else {
			return rank(key, curr.right, currRank + 1 + sizeOf(curr.left));
		}
		
	}
	
	public void insert(K key, V value) {
		Node newNode = new Node(key, value);
		root = insert(key, newNode, root);
	}
	
	private Node insert(K key, Node newNode, Node curr) {
		if(curr == null)
			return newNode;
		
		int cmp = key.compareTo(curr.key);
		
		if(cmp == 0) {
			curr.value = newNode.value;
			return curr;
		}
		else if(cmp < 0) {
			curr.size++;
			curr.left = insert(key, newNode, curr.left);
		}
		else {
			curr.size++;
			curr.right = insert(key, newNode, curr.right);
		}
				
		return curr;
		
	}
	
	public void delMin() {
		root = delMin(root);
	}
	
	private Node delMin(Node curr) {
		if(curr.left == null)
			return curr.right;
		curr.left = delMin(curr.left);
		curr.size--;
		return curr;
	}
	
	public void delMax() {
		root = delMax(root);
	}
	
	private Node delMax(Node curr) {
		if(curr.right == null)
			return curr.left;
		curr.right = delMax(curr.right);
		curr.size--;
		return curr;
	}
	
	public void delete(K key) {
		if(root == null)
			return;
		root = delete(key, root);
	}
	
	private Node delete(K key, Node curr) {
		if(curr == null)
			return curr;
		
		int cmp = key.compareTo(curr.key);
		
		if(cmp == 0) {
			if(curr.left == null)
				return curr.right;
			else if(curr.right == null)
				return curr.left;
			else {
				Node n =  max(curr.left);
				curr.left = delMax(curr.left);
				n.left = curr.left;
				n.right = curr.right;
				return n;
			}
				
		}else if(cmp < 0) {
			curr.left = delete(key, curr.left);
		}else {
			curr.right = delete(key, curr.right);
		}
		
		return curr;
	}
	
	public V get(K key) {
		Node node = get(key, root);
		return node == null ? null : node.value;
		
	}
	
	private Node get(K key, Node curr) {
		if(curr == null)
			return null;
		System.out.println(curr.value);
		
		int cmp = key.compareTo(curr.key);
		
		if(cmp == 0)
			return curr;
		else if(cmp < 0)
			return get(key, curr.left);
		else
			return get(key, curr.right);
	}
	
	
	public static void main(String[] args) {
		BST<Integer, Student> bst = new BST<Integer, Student>();
		Student s1 = new Student(1, "Simran", 9.8);
		Student s2 = new Student(2, "Ayush", 9.6);
		Student s3 = new Student(3, "Abhishek", 9.4);
		Student s4 = new Student(4, "Amisha", 9.7);
		Student s5 = new Student(5, "a", 9.7);
		Student s6 = new Student(6, "b", 9.7);
		Student s7 = new Student(7, "c", 9.7);
		Student s8 = new Student(8, "d", 9.7);
		Student s9 = new Student(9, "f", 9.7);
		bst.insert(2, s2);
		bst.insert(1, s1);
		bst.insert(8, s8);
		bst.insert(3, s3);
		bst.insert(5, s5);
		bst.insert(7, s7);
		bst.insert(6, s6);
		bst.insert(4, s4);
		bst.insert(9, s9);
		
		System.out.println(bst.rank(6));
	}
}
