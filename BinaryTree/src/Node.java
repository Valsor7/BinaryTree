import java.util.Date;


public class Node {
	private static int cnt = 0;

	private int value;
	private int id;
	private Date date;
	private Node right;
	private Node left;
	private Node parent;
	
	public Node(int value) {
		// TODO Auto-generated constructor stub
		
		this.value = value;
		this.date = new Date();	
		this.id = cnt++;
	//	System.out.println(date);
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	

	
	
	
	
	
}
