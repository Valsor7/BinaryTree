import java.util.ArrayList;


public class Tree {

	Node root;
	private int cntLeft, cntRight;
	private int mainCnt;
	private boolean isFirst;
	
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
				if (newNode.getValue() > node.getValue()) {
					if (node.getRight() == null) {
						node.setRight(newNode);
						node.getRight().setParent(node);
					} else  {
						addNode(node.getRight(), newNode);
					}
				}	
				if (newNode.getValue() == node.getValue()) {
					System.out.println("already exist");
					return;
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
		heightL = getHeight(node.getLeft(), true);
		heightR = getHeight(node.getRight(), true);
	
		if ((heightL - heightR) > 1) {
			if (getHeight(node.getLeft().getLeft(), true) - getHeight(node.getLeft().getRight(), true) == -1) {
				child = node.getLeft();
				main = node.getLeft().getRight();
				main.setParent(node);
				child.setParent(main);
				child.setRight(main.getLeft());
				if(child.getRight() != null){
					child.getRight().setParent(child);
				}
				node.setLeft(main);
				node.getLeft().setLeft(child);
			}
			
			if (node == root) {
				main = node.getLeft();
				main.setParent(null);
				node.setLeft(main.getRight());
				if (main.getRight() != null) {
					main.getRight().setParent(node);
				}
				main.setRight(node);
				root = main;
				node.setParent(main);
			} else {
				main = node.getLeft();
				main.setParent(node.getParent());
				node.setLeft(main.getRight());
				if (main.getRight() != null) {
					main.getRight().setParent(node);
				}
				main.setRight(node);
				if (node.getParent().getLeft() == node) {node.getParent().setLeft(main);} 
				else {
					if (node.getParent().getRight() == node) {node.getParent().setRight(main);}	
				}
				node.setParent(main);
			}	
		} else {
			if ((heightL - heightR) < -1) {
				if (getHeight(node.getRight().getLeft(), true) - getHeight(node.getRight().getRight(), true) == 1) {
				child = node.getRight();
				main = node.getRight().getLeft();
				main.setParent(node);
				child.setParent(main);
				child.setLeft(main.getRight());
				if(child.getLeft() != null){
					child.getLeft().setParent(child);
				}
				node.setRight(main);
				node.getRight().setRight(child);
				}
				if (node == root) {
					main = node.getRight();
					main.setParent(null);
					node.setRight(main.getLeft());
					if (main.getLeft() != null) {
						main.getLeft().setParent(node);
					}
					main.setLeft(node);
					root = main;
					node.setParent(main);
				} else {
					main = node.getRight();
					main.setParent(node.getParent());
					node.setRight(main.getLeft());
					if (main.getLeft() != null) {
						main.getLeft().setParent(node);
					}
					main.setLeft(node);
					if (node.getParent().getLeft() == node) {node.getParent().setLeft(main);} 
					else {
						if (node.getParent().getRight() == node) {node.getParent().setRight(main);}	
					}
					node.setParent(main);
				}	
			}
		}	
		
	}
	
	
	
	private int getHeight(Node node, boolean isFirst){
		int cntL = 0, cntR = 0, cnt = 0;
		if (node == null) {
			return 0;
		}
		if (node != null) {
			if (isFirst) {
			cnt++;
			isFirst = false;
		}
		}
		
		if (node.getLeft() != null && node.getRight() != null) {
			cntL++;
			cntL += getHeight(node.getLeft(), isFirst);
			cntR++;
			cntR += getHeight(node.getRight(), isFirst);
		} else {
			if (node.getLeft() != null) {
				cntL++;
				cntL += getHeight(node.getLeft(), isFirst);
			} else {
				if (node.getRight() != null) {
					cntR++;
					cntR += getHeight(node.getRight(), isFirst);
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
			l = node.getLeft().getValue() + "[id=" + node.getLeft().getId() + ", " + node.getLeft().getDate() + "]" + " <= ";
		} 
		
		if (node.getRight() != null) {
			r = " => " + node.getRight().getValue() + "[id=" + node.getRight().getId() + ", " + node.getRight().getDate() + "]";
		}
		if ((node.getRight() != null || node.getLeft() != null) || node == root ) {
			System.out.println(l + node.getValue() + "[id=" + node.getId() + ", " + node.getDate() + "]" + r);
			System.out.println();
			printNodes(node.getLeft());
			printNodes(node.getRight());
		}
	}
}	
