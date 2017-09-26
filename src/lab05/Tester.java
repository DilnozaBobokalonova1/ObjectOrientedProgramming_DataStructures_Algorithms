package lab05;


public class Tester {

	public static void main(String[] args) {
		BinarySearchSet bss = new BinarySearchSet();
		//double[] storage = {};
		System.out.println("The size of an empty list if: " + bss.size());
		System.out.println("The list is empty: " + bss.isEmpty());
		System.out.println("There are items to remove: " + bss.remove(40));
		bss.binary_add(3);
		bss.binary_add(8);
		bss.binary_add(2);
		bss.binary_add(1);
		bss.binary_add(15);
		bss.binary_add(5);
		System.out.print(bss);
		
		bss.binary_add(4);
		bss.binary_add(9);
		bss.binary_add(10);
		bss.binary_add(13);
		bss.binary_add(30);
		bss.binary_add(80);
		bss.binary_add(24);
		bss.binary_add(1);
		bss.binary_add(15);
		System.out.print(bss);
		
		System.out.println("I can remove number 80: " + bss.remove(80));
		System.out.print(bss);
		
		System.out.println("I can remove number 69: " + bss.remove(69));
		System.out.print(bss);
		
		System.out.println("The list contains the integer 80: " + bss.contains(80));
		
		double[] anotherOne = {4,5,6,2,7};
		System.out.println("The list contains all of another list: " + bss.containsAll(anotherOne));
		
		bss.clear();
		System.out.println("The list is now empty: " + bss.isEmpty());
		
	}
}
