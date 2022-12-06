import java.util.*;

public class SkipList {

	private Node head;
	private Node tail;
	private int listheight = 0;
	public Random random = new Random();
	private final int NegativeInf = Integer.MIN_VALUE;
	private final int PositiveInf = Integer.MAX_VALUE;
	
	public SkipList () {
		head = new Node(NegativeInf);
		tail = new Node(PositiveInf);
		head.next=tail;
		tail.previous=head;
	}
	
	public Node Search(int key) {
		Node n = head;
		while (n.below != null) {
			n = n.below;
			while (key >= n.next.key) {
				n=n.next;
			}
			
		}
		return n;
	}
	
	
	public Node Insert(int key) {
		Node index = Search(key);
		Node q;
		
		int level = -1;
		int numberOfHeads = -1;
		
		if (index.key == key) {
			return index;
		}
		do {
			numberOfHeads++;
			level++;
			
			canIncreaseLevel(level);
			q=index;
			while (index.above == null) {
				index = index.previous;
			}
			
			index=index.above;
			
			q = insertAfterAbove(index, q, key);
			
		} while (random.nextBoolean()== true);
		
		return q;
	}
	
	public Node remove(int key) {
		Node nodeToBeRemoved = Search(key);
		
		if (nodeToBeRemoved.key != key) {
			return null;
		}
		
		removeReferencesToNode(nodeToBeRemoved);
		
		while (nodeToBeRemoved != null) {
			removeReferencesToNode(nodeToBeRemoved);
			
			if (nodeToBeRemoved.above != null) {
				nodeToBeRemoved = nodeToBeRemoved.above;
				
			} else {
				break;
			}
		}
		return nodeToBeRemoved;
	}
	
	private void removeReferencesToNode(Node nodeToBeRemoved) {
		Node afterNodeToBeRemoved = nodeToBeRemoved.next;
		Node beforeNodeToBeRemoved = nodeToBeRemoved.previous;
		
		beforeNodeToBeRemoved.next=afterNodeToBeRemoved;
		afterNodeToBeRemoved.previous= beforeNodeToBeRemoved;
		
		
				
	}
	
	private void canIncreaseLevel(int level) {
		if (level >= listheight) {
			listheight++;
			addEmptyLevel();
			
		}
	}
	
	private void addEmptyLevel() {
		
		Node newHead = new Node(NegativeInf);
		Node newTail = new Node(PositiveInf);
		
		newHead.next = newTail;
		newHead.below = head;
		newTail.previous = newHead;
		newTail.below = tail;
		
		head.above = newHead;
		tail.above = newTail;
		head = newHead;
		tail = newTail;
		
		
		
	}
	
	private Node insertAfterAbove(Node index, Node q, int key) {
		Node newNode = new Node(key);
		Node nodeBeforeNewNode = index.below.below;
		
		setBeforeAndAfterReferences(q, newNode);
		setAboveAndBelowReferences(index, key, newNode, nodeBeforeNewNode);
		
		return newNode;
	}
	
	private void setBeforeAndAfterReferences(Node q, Node newNode) {
		newNode.next = q.next;
		newNode.previous= q;
		q.next.previous= newNode;
		q.next = newNode;
	}
	
	private void setAboveAndBelowReferences(Node index, int key, Node newNode, Node nodeBeforeNewNode) {
		if (nodeBeforeNewNode != null) {
			while (true) {
				if (nodeBeforeNewNode.next.key != key) {
					nodeBeforeNewNode = nodeBeforeNewNode.next;
					
				} else {
					break;
				}
			}
			newNode.below = nodeBeforeNewNode.next;
			nodeBeforeNewNode.next.above = newNode;
			
		}
		if (index != null) {
			if(index.next.key == key) {
				newNode.above = index.next;
			}
		}
	}
	
	public void printList() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n Skip List starting with the top-left most node.\n");
		
		Node starting = head;
		Node highestLevel = starting;
		int level = listheight;
		
		while (highestLevel != null) {
			sb.append("\nLevel: "+ level + "\n");
			
			sb.append("Negative Infinite|");
			while (starting.next != null) {
				sb.append(starting.key);
				
				
				if (starting.next != null) {
					sb.append("->");
				}
				
				starting = starting.next;
			}
			sb.append("+2147483648|Positive Infinite");
			
			highestLevel = highestLevel.below;
			starting = highestLevel;
			level--;
			
		
		}
		
		System.out.println(sb.toString());
		
	}
}
