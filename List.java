
public class List
{
	private class Node
	{
		int data;
		Node next;
		Node prev;
		
		Node(int data)
		{
			this.data = data;
			this.prev = null;
			this.next = null;
		}	
	}
	
	private Node head, curr, prev, tail = null;
	
	private int cursor, length;

	//Constructor
	List()
	{
		head = null; 
		tail = null;
		curr = null;
		cursor = -1;
		length = 0;
	}
	
	
	int length() { return length; }
	
	int getIndex()	
	{ 
		return cursor;
	}
	
	//Returns front element in this list.
	//Pre: length()>0
	int front()
	{
		if(isEmpty())
			throw new RuntimeException("Empty List.");
		return head.data;
	}
	
	int back()
	{
		if(isEmpty())
			throw new RuntimeException("Empty List.");
		return tail.data;
	}
	
	// Returns cursor element in this list.
	// Pre: length()>0, getIndex()>=0
	int getElement() 
	{				
		if(isEmpty())
			throw new RuntimeException("Empty List.");
		return curr.data;
	}
	
	boolean equals(List L)
	{
		Node one = head;
		Node two = L.head;
		
		for(int i = 0; i < length(); i++)
		{
			if(one.data != two.data)
				return false;
			one = one.next;
			two = two.next;
		}
		return true;		
	}
	
	void clear()
	{
		curr = null;
		head = null;
		tail = null;
		length = 0;
	}

	void moveTo(int i)
	{
		if(i < 0)
		{
			curr = null;
			cursor = -1;
		}
		else
		{
			curr = head;
			for(int t = 0; t < i; t++)
				curr = curr.next;
		}
		cursor = i;
	}
	
	void movePrev()
	{
		int index = getIndex();
		
		if(index <= 0)
		{
			cursor = -1;
			curr = null;
		}
		else
			moveTo(index-1);	
	}

	void moveNext()
	{
		int index = getIndex();
		
		if(index >= 0 && index < length() -1)
		{
			curr = curr.next;
			cursor++;
		}
		
		else
		{
			curr = null;
			cursor = -1;
		}
	}

	//check is stack is empty
	boolean isEmpty()
	{
		return (head == null);
	}
	
	void prepend(int data)
	{
		Node newNode = new Node(data);
		
		if(isEmpty())
			tail = newNode;
		else
		{
			newNode.next = head;
			head.prev = newNode;
		}
		head = newNode;	
		
		length++;
	}
	
	//Add to the stack
	void append(int data)
	{
		Node newNode = new Node(data); //create temp node
	
		if(isEmpty())		 //add first element
			head = newNode;
		else
		{
			tail.next = newNode; // add to the back of list 	
			newNode.prev = tail;
		}
		tail = newNode;			 //point back to node
	
		length++;	
	}
	
	void insertBefore(int data)
	{
		Node newNode = new Node(data);
		if(curr.prev != null)
		{
			curr.prev.next = newNode;
			newNode.prev = curr.prev;
		}
		else
		{
			head = newNode;
		}
		curr.prev = newNode;
		newNode.next = curr;
	}
	
	void insertAfter(int data)
	{
		Node newNode = new Node(data);
		if(curr.next != null)
		{
			curr.next.prev = newNode;
			newNode.next = curr.next;
		}
		else
			tail = newNode;
		
		curr.next = newNode;
		newNode.prev = curr;
		
		length++;
		
	}

	void deleteFront()
	{
		if(isEmpty())
			throw new RuntimeException("Empty List.");
		else
		{
			head = head.next;
			head.prev = null;
			cursor = -1;
		}
	}
	
	//Delete from back of stack
	void deleteBack() 
	{
		prev = null;
		curr = head;
		
		if(isEmpty())
				throw new RuntimeException("Stack is empty.");
		else
		{
			while(curr.next != null)
			{
				prev = curr;
				curr = curr.next;
			}
			
			if(prev == null)
				head = null;
			else
				prev.next = null;
		}
		length--;
	}
	
	void delete()
	{
		if(curr == head)
			deleteFront();
		else if(curr == tail)
			deleteBack();
		
		if(!isEmpty())
		{
			curr.next.prev = curr.prev;
			curr.prev.next = curr.next;
		}
			curr = null;
			cursor = -1;
	}

	public String toString()
	{
		String result = "";	
		Node current = head;	//local variable equal to first 
		while(current != null)
		{
			result += current.data + " "; 
			current = current.next;
		}
		return result;
	}
	
	List copy()
	{
		List newList = new List();
		for(Node i = head; i != null; i = i.next)
			newList.append(i.data);
		
		return newList;
	}	
}
