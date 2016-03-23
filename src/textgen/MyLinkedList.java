package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		LLNode<E> newList = new LLNode<E>(element);
		
		newList.next = tail;
		newList.prev = tail.prev;
		tail.prev = newList;
		if(element == null)
			throw new NullPointerException("Null cannot be added");
		if(head.next == tail)
		{
			head.next = newList;
		}
		else
		{
			newList.prev.next = newList;
		}
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		LLNode<E>nextNode = head.next;
		if(nextNode.data == null)
			throw new IndexOutOfBoundsException("Null Pointer is selected");
		else if((index < 0)||(index>=size))
			throw new IndexOutOfBoundsException("Index is not valid");
		else
			for(int i=0; i<index; i++){
				nextNode = nextNode.next;
			}
			return nextNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		LLNode<E> newList = new LLNode<E>(element);
		LLNode<E>nextNode = head.next;
		
		if(element == null)
		{
			throw new NullPointerException("Null cannot be added");
		}
		else if((index < 0)||(index > size))
		{
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		else{
			for(int i=0; i<index; i++){
				nextNode = nextNode.next;
			}
			newList.prev = nextNode.prev;
			newList.next = nextNode;
			newList.prev.next = newList;
			nextNode.prev = newList;
			size++;
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		LLNode<E>expelNode = head.next;
		if(index < 0)
		{
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		else if(index >= size)
		{
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		else{
			for(int i=0; i<index; i++){
				expelNode = expelNode.next;
			}
			expelNode.next.prev = expelNode.prev;
			expelNode.prev.next = expelNode.next;
			expelNode.next = null;
			expelNode.prev = null;
			size--;
		}
		return expelNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		LLNode<E>setNode = head.next;
		if(element == null){
			throw new NullPointerException("cannot set to null");
		}
		if(index < 0)
		{
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		else if(index >= size)
		{
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		else{
			for(int i=0; i<index; i++){
				setNode = setNode.next;
			}
			E oldData = setNode.data;
			setNode.data = element;
			return oldData;
		}
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
