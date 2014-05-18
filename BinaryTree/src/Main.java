
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
		switch (scanOper.nextLine()) {
		case "add":
			System.out.println("val: ");
			val = scanVal.nextInt();
			t.add(val);	
			t.printNodes(t.root);
			System.out.println("do you want add?");
			changeOper(t);
			break;
		case "s":
			System.out.println("Find some val:");
			val = scanVal.nextInt();
			t.search(val);
			System.out.println("do you want search again?");
			changeOper(t);
			break;
		case "del":
			System.out.println("val to delete:");
			val = scanVal.nextInt();
			t.delete(val);
			t.printNodes(t.root);
			System.out.println("do you want delete again?");
			changeOper(t);
			break;
		case "change":
			System.out.println("val to change:");
			val = scanVal.nextInt();
			System.out.println("new value:");
			int newVal = scanVal.nextInt();
			t.refactor(val, newVal);
			t.printNodes(t.root);
			System.out.println("do you want change value again?");
			changeOper(t);
		default:
			changeOper(t);
			break;
		}
		
	
		
	}

}
