
public class Node {
	
	public int key;
	public Node above;
	public Node below;
	public Node next;
	public Node previous;
	
	
	public Node(int key) {
		this.key=key;
		this.above=null;
		this.below=null;
		this.next=null;
		this.previous=null;
		
	}

}
