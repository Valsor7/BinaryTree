
import java.util.Scanner;


public class Main {

	private static Scanner scanOper;
	private static Scanner scanVal;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tree t = new Tree();
		scanOper = new Scanner(System.in);
		scanVal = new Scanner(System.in);
		changeOper(t);
		
		
	}
	
	public static void changeOper(Tree t){
		int val;
		help();
		switch (scanOper.nextLine()) {
		case "add":
			System.out.println("val: ");
			val = scanVal.nextInt();
			t.add(val);
			t.printNodes(t.root);
			changeOper(t);
			break;
		case "s":
			System.out.println("Find some val:");
			val = scanVal.nextInt();
			t.search(val);
			changeOper(t);
			break;
		case "del":
			System.out.println("val to delete:");
			val = scanVal.nextInt();
			t.delete(val);
			t.printNodes(t.root);
			changeOper(t);
			break;
		case "change":
			System.out.println("val to change:");
			val = scanVal.nextInt();
			System.out.println("new value:");
			int newVal = scanVal.nextInt();
			t.refactor(val, newVal);
			t.printNodes(t.root);
			changeOper(t);
			break;
		case "balance":
			t.balance();
			t.printNodes(t.root);
		default:
			changeOper(t);
			break;
		}
		
	
		
	}
	private static void help(){
		System.out.println("COMMANDS : To add some node => add; delete node => del; refactor tree => change; \n           To search => s; For balancing the tree => balance");
	}
}
