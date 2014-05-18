import java.util.ArrayList;


public class Tree {

	Node root;
	private int cntLeft, cntRight;
	private int mainCnt;
	
	public Tree() {
		// TODO Auto-generated constructor stub
		root = null;
		mainCnt = 0;
	}
	
	private void addNode(Node node, Node newNode){

			if (newNode.getValue() < node.getValue()) {
				if (node.getLeft() == null) {
					node.setLeft(newNode);
					node.getLeft().setParent(node);
				} else  {
					addNode(node.getLeft(), newNode);
				}
			} else {
				if (node.getRight() == null) {
					node.setRight(newNode);
					node.getRight().setParent(node);
				} else  {
					addNode(node.getRight(), newNode);
				}
			} 
	}
	
	public void add(int val){
		
		Node newNode = new Node(val);
		if (root == null) {
			root = newNode;
		} else {
			addNode(root, newNode);
		}
	}
	
	public void search(int val){
		Node search = new Node(val);
		if (root == null) {
			System.out.println("Tree doesn't exist");
			return;
		} else {
			search = searchNode(root, new Node(val));
		}
		
		if (search == null) {
			System.out.println("Doesn't exist!");
		} else {
			System.out.println("find " + search.getValue() + "[id=" + search.getId() + " " + search.getDate() + "]");
		}
		
	}
	private Node searchNode(Node node, Node newNode){
		
		Node n;
		if (node == null) {
			return null;
		} else { 
			if (newNode.getValue() == node.getValue()) {
				return node;
			} else { 
				if (newNode.getValue() < node.getValue()) {
					n = searchNode(node.getLeft(), newNode);
				} else {  
					n = searchNode(node.getRight(), newNode);
				}
			}	
		}
		
		return n;
	}
	
	public void delete(int val){
		Node search = new Node(val);
		if (root == null) {
			System.out.println("Tree doesn't exist");
			return;
		} else {
			search = searchNode(root, new Node(val));
		}
		
		if (search == null) {
			System.out.println("Doesn't exist!");
		} else {
			delete(search);
		}
		
	}
	
	private void delete(Node node){
		
		if (node.getLeft() == null && node.getRight() == null) {
			if (node == root) {
				root = null;
			} else {
				if (node.getParent().getLeft() == node) {
					node.getParent().setLeft(null);
				} else {
					if (node.getParent().getRight() == node) {
						node.getParent().setRight(null);
					}	
				}
			}	
		} 
		
		if (node.getLeft() != null && node.getRight() == null) {
			if (node == root) {
				root = node.getLeft();
			} else {
				if (node.getParent().getLeft() == node) {
					node.getParent().setLeft(node.getLeft());
				} else {
					if (node.getParent().getRight() == node) {
						node.getParent().setRight(node.getLeft());
					}	
				}
				node.getLeft().setParent(node.getParent());
			}	
		} else {
			if (node.getLeft() == null && node.getRight() != null) {
				if (node == root) {
					root = node.getRight();
				} else {
					if (node.getParent().getLeft() == node) {
						node.getParent().setLeft(node.getRight());
					} else {
						if (node.getParent().getRight() == node) {
							node.getParent().setRight(node.getRight());
						}	
					}
					node.getRight().setParent(node.getParent());
				}
			}	
		} 
		
		if (node.getLeft() != null && node.getRight() != null) {
			Node replaceNode = goToMin(node.getRight());
			node.getLeft().setParent(replaceNode);
			if (node.getRight() != null) {
				node.getRight().setParent(replaceNode);
			}
			replaceNode.setLeft(node.getLeft());
			replaceNode.setRight(node.getRight());
			if (node != root) {
				replaceNode.setParent(node.getParent());
			}
			
			if (node == root) {
				root = replaceNode;
			} else {
				if (node.getParent().getLeft() == node) {		
					node.getParent().setLeft(replaceNode);
				} else {
					if (node.getParent().getRight() == node) {
						node.getParent().setRight(replaceNode);
					}	
				}
			}
		}
		 				
	}
	public void balance(){
		traverce(root);
		
	}
	private void traverce(Node node){
		
		if (node == null){
			return; 
		} else { 
			if(node.getLeft() != null && node.getRight() != null){
			traverce(node.getLeft());
			traverce(node.getRight());
			} else  {
				if (node.getRight() != null) {
					traverce(node.getRight());
				} else {
					traverce(node.getLeft());
				}
			}
		}
		checkFactor(node);
		
	}
	
	private void checkFactor(Node node) {
		// TODO Auto-generated method stub
		int heightL = 0, heightR = 0;
		Node main, child;
		heightL = getHeight(node.getLeft());
		heightR = getHeight(node.getRight());
		if ((heightL - heightR) > 1) {
			//rotate // check left or right
			child = node.getLeft();
			main = node.getLeft().getRight();
			if (getHeight(node.getLeft().getLeft()) - getHeight(node.getLeft().getRight()) == -1) {
				main.setParent(node);
				child.setParent(main);
				child.setRight(main.getLeft());
				child.getRight().setParent(child);
				node.setLeft(main);
				node.getLeft().setLeft(child);
			} else {
				if (node == root) {
			
				} else {
					if (node.getParent().getLeft() == node) {
						main.setParent(node.getParent());
						node.setLeft(main.getRight());
						main.getRight().setParent(node);
						main.setRight(node);
						main.setLeft(child);
						node.getParent().setLeft(main);
					} else {
						if (node.getParent().getRight() == node) {
						//	node.getParent().setRight(replaceNode);
						}	
					}
				}
				
			}
		} else {
			if ((heightL - heightR) < -1) {
			//	rotate // check left or right 
			}
		}
	}
	
	
	
	private int getHeight(Node node){
		int cntL = 0, cntR = 0, cnt = 0;
		if (node == null) {
			return 0;
		}
		if (node.getLeft() != null && node.getRight() != null) {
			cntL++;
			cntL += getHeight(node.getLeft());
			cntR++;
			cntR += getHeight(node.getRight());
		} else {
			if (node.getLeft() != null) {
				cntL++;
				cntL += getHeight(node.getLeft());
			} else {
				if (node.getRight() != null) {
					cntR++;
					cntR += getHeight(node.getRight());
				}
			}
			
		}
		if (cntL >= cntR) {
			cnt += cntL;
		} else{cnt+=cntR;}
		return cnt;
	}


	public void refactor(int val, int newVal){
		delete(val);
		add(newVal);
	}
	
	private Node goToMin(Node node) {
		// TODO Auto-generated method stub
		Node min;
		if (node.getLeft() == null) {
			min = node;
			delete(node);
		} else {
			min = goToMin(node.getLeft());
		}	
		return min;
	}

	public void printNodes(Node node){
		
		String l = "";
		String r = "";
	
		if (node == null) {
			return;
		}
		if (node.getLeft() != null) {
			l = node.getLeft().getValue() + "[id=" + node.getLeft().getId() + "]" + " <= ";
		} 
		
		if (node.getRight() != null) {
			r = " => " + node.getRight().getValue() + "[id=" + node.getRight().getId() + "]";
		}
		if ((node.getRight() != null || node.getLeft() != null) || node == root ) {
			System.out.println(l + node.getValue() + "[id=" + node.getId() + "]" + r);
			System.out.println();
			printNodes(node.getLeft());
			printNodes(node.getRight());
		}
	}
}	
