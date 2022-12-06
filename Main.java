

public class Main {
	
	public static void main(String[] args) {
		SkipList skipList = new SkipList();
		
		skipList.Insert(8);
		skipList.Insert(17);
		skipList.Insert(1);
		skipList.Insert(27);
		skipList.Insert(9);
		skipList.Insert(7);
		skipList.Insert(11);
		skipList.Insert(10);
		skipList.Insert(3);
		skipList.Insert(2);
		skipList.printList();
		
		skipList.remove(17);
		skipList.printList();
		
		skipList.Search(17);
		
	}
}
