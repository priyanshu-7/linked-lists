package base;

class Node
{
	int data;
	Node next;
}
public class Base{
	Node head, head1, head2;
	public void insert(int data, int ll)
	{
		Node node = new Node();
		node.data = data;
		node.next = null;
		if(ll==1)
		{
			if(head == null)
			{
				head = node;
			}
			else 
			{
				Node n = head;
				while(n.next!=null)
				{
					n = n.next;
				}
				n.next = node;
			}
		}
		else if(ll == 2)
		{
			if(head1 == null)
			{
				head1 = node;
			}
			else 
			{
				Node n = head1;
				while(n.next!=null)
				{
					n = n.next;
				}
				n.next = node;
			}
		}
		else if(ll == 3)
		{
			if(head2 == null)
			{
				head2 = node;
			}
			else 
			{
				Node n = head2;
				while(n.next!=null)
				{
					n = n.next;
				}
				n.next = node;
			}
		}
	}
	public void show(int ll)
	{
		Node node = null;
		if(ll == 1)
		{
			node = head;
		}
		else 
		{
			node = head1;
		}
		while(node.next!=null)
		{
			System.out.println(node.data);
			node = node.next;
		}
		System.out.println(node.data);
	}
	public int countNodes(int ll)
	{
		int counter = 0;
		Node node = null;
		if(ll==1)
		{node = head;}
		else 
		{node = head1;}
		while(node.next!=null)
		{
			counter++;
			node = node.next;
		}
		counter++; //for last element
		return counter;
	}
	public void returnMiddleElement(int ll)
	{
		Node node = head;
		int counter = 0;
		int middle = countNodes(ll)/2;
		if(countNodes(ll)<2)
		{
			System.out.println("Number of nodes less than 2");
		}
		else
		{
			while(counter<middle)
			{
				counter++;
				node = node.next;
			}
			System.out.println(node.data);
		}
	}
	public Node reverseList(Node node)
	{
		//1 -> 2 - > 3 - > 4
		Node current = node;
		Node previous = null;
		Node next = null;
		while(current!=null)
		{
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		return previous;
	}
	public void rotateTillkthNode(int k)
	{
		Node current = head;
		int counter = 1;
		while(counter<k && current.next != null)
		{
			current = current.next;
			counter++;
		}
		Node kthNode = current;
		Node kthPlusOne = current.next;
		current = kthPlusOne;
		while(current.next!=null)
		{
			current = current.next;
		}
		Node last = current;
		last.next = head;
		head = kthPlusOne;
		kthNode.next = null;
	}
	
	public int getIntersectionNode()
	{
		int c1 = countNodes(1); //number of nodes in 1st ll
		int c2 = countNodes(2); //in 2nd ll\
		int diff = c1-c2;
		if(diff>0)
		{
			return getIntersection(head, head1, diff); //LL1 bigger than LL2
		}
		if(diff<0)
		{
			return getIntersection(head1, head, diff); //LL2 bigger than LL1
		}
		return -1;
	}
	public int getIntersection(Node node1, Node node2, int diff)
	{
		//traverse till d in bigger node (first argument so that we reach a point where the LLs are equal in length
		Node current1 = node1;
		Node current2 = node2;
		for(int i = 0; i<diff; i++)
		{
			current1 = current1.next; //now we are at the point where the lengths are equal
		}
		//traverse both the linked lists simultaneously now
		while(current1.next!=null && current2.next!=null)
		{
			if(current1.data == current2.data)
			{
				return current1.data;
			}
			current1 = current1.next;
			current2 = current2.next;
		}
		return -1;
	}
	
	public Node reverseInGroups(Node head, int k)
	{
		Node next = null;
		Node previous = null;
		Node current = head;
		int counter = 0;
		while(current!=null && counter<k)
		{
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
			counter++;
		}
		if(next!=null)
		{
			head.next = reverseInGroups(next, k); //make the rest of the list as next of first node
		}
		return previous;
	}
	public boolean detectLoop(Node head)
	{
		Node fastPtr = head;
		Node slowPtr = head;
		while(fastPtr!=null && fastPtr.next!=null)
		{
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
			if(fastPtr == slowPtr) //check for address not data
			{
				return true;
			}
		}
		return false;
	}
	public void removeLoop(Node head)
	{
		/* Loop detection algo */
		Node fastPtr = head;
		Node slowPtr = head;
		while(fastPtr!=null && fastPtr.next!=null)
		{
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
			if(fastPtr == slowPtr) //check for address not data
			{
				slowPtr = head;
				break;
			}
		}
		/* Loop detection algo end */
		
		
		/* Find start point of loop */
		Node loopStartsAt = null;
		//iterate slowPtr (which is now at start) and fastPtr (fixed where loop was detected) 
		while(1==1)
		{
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next;
			if(slowPtr == fastPtr) //start position of loop found
			{
				loopStartsAt = slowPtr;
				break;
			}
		}
		
		/* Find end point of loop */
		Node endPointOfLoop = null;
		while(fastPtr.next!=slowPtr) 
		{
			fastPtr = fastPtr.next;
		}
		endPointOfLoop = fastPtr;
		fastPtr.next = null; //remove the loop
	}
	public int kthFromEnd(int k)
	{
		int count = countNodes(1);
		int diff = count-k;
		if(diff<0) //if k>total elements in linked list
		{
			return -1;
		}
		Node current = head;
		int counter = 0;
		while(counter<diff)
		{
			current = current.next;
			counter++;
		}
		return current.data;
	}
	public void swap()
	{
		Node current = head;
		int temp = 0;
		while(current!=null && current.next!=null) //at least 2 elements should be present
		{
			temp = current.data;
			System.out.println(current.data + " " + current.next.data);
			current.data = current.next.data;
			current.next.data = temp;
			current = current.next.next;
		}
	}
	public boolean palindromeCheck()
	{
		Node current = head;
		String s = "";
		while(current!=null)
		{
			s+=current.data;
			System.out.println(s);
			current = current.next;
		}
		StringBuffer sb = new StringBuffer(s);
		String rev = sb.reverse().toString();
		if(s.equals(rev))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	public void enqueue(int data)
	{
		Node node = new Node();
		node.data = data;
		node.next = null;
		if(head == null)
		{
			head = node;
		}
		else 
		{
			Node current = head;
			while(current.next!=null)
			{
				current = current.next;
			}
			current.next = node;
		}
	}
	public void dequeue()
	{
		head = head.next;
	}
	public void push(int data)
	{
		Node node = new Node();
		node.data = data;
		node.next = head; 
		head = node;
	}
	public void pop()
	{
		head = head.next;
	}
	public void zeroOneTwoSort(Node node)
	{
		Node current = node;
		int zero_count = 0, one_count = 0, two_count = 0;
		while(current!=null)
		{
			if(current.data == 0)
			{
				zero_count++;
			}
			if(current.data == 1)
			{
				one_count++;
			}
			if(current.data == 2)
			{
				two_count++;
			}
			current = current.next;
		}
		current = node;
		while(current!=null && zero_count>0)
		{
			current.data = 0;
			zero_count--;
			current = current.next;
		}
		while(current!=null && one_count>0)
		{
			current.data = 1;
			one_count--;
			current = current.next;
		}
		while(current!=null && two_count>0)
		{
			current.data = 2;
			two_count--;
			current = current.next;
		}
	}
	public void addLists()
	{
		int nodesList1 = countNodes(1);
		int nodesList2 = countNodes(2);
		int diff = Math.abs(nodesList1-nodesList2);
		System.out.println(diff);
	}
	public static void main(String args[])
	{
		Base base = new Base();
		base.insert(1,1);
		base.insert(2,1);
		base.insert(3,1);
		base.insert(4,1);
		base.insert(5,1);
		base.insert(9, 2);
		base.insert(6, 2);
		base.insert(3, 2);
		base.head = base.reverseList(base.head); 
		base.show(1);
		base.head1 = base.reverseList(base.head1);
		base.addLists();
		//base.zeroOneTwoSort(base.head);
		//base.show(1);
		//base.push(1);
		//base.push(2);
		//base.push(3);
		//base.push(4);
		//base.push(5);
		//base.show(1);
		//base.pop();
		//base.show(1);
		//base.dequeue();
		//base.show(1);
		//System.out.println(base.palindromeCheck());
		//base.head.next.next.next.next.next.next = base.head.next.next;  //create a loop 
		//base.head = base.reverseInGroups(base.head, 2);
		//System.out.println(base.head.data);
		//base.show(1);
		//System.out.println(base.countNodes(1));
		//System.out.println(base.getIntersectionNode());
		//base.rotateTillkthNode(3);
		//base.show();
		//base.returnMiddleElement();
		//base.removeLoop(base.head);
		//System.out.println(base.kthFromEnd(2));
	}
}