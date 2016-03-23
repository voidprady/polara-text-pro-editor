/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		try{
			int b = emptyList.remove(0);
			fail("Check out of bounds");
		}catch(IndexOutOfBoundsException e){
			
		}
		
		try{
			String c = shortList.remove(-1);
			fail("Check out of bounds");
		}catch(IndexOutOfBoundsException e){
			
		}
		try{
			String d = shortList.remove(2);
			fail("Check out of bounds");
		}catch(IndexOutOfBoundsException e){
			
		}
		
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		// test short list contents
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test longer list contents
		assertEquals("Check first", (Integer)65, list1.get(0));
		assertEquals("Check second", (Integer)21, list1.get(1));
		

		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Check size", (Integer)2, (Integer)shortList.size());
		assertEquals("Check size", (Integer)10, (Integer)longerList.size());
		assertEquals("Check size", (Integer)3, (Integer)list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try {
			emptyList.add(1, 0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			shortList.add(-1, "wrong index mate");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			shortList.add(3, "wrong place");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		list1.add(0, 33);
		assertEquals("Add: check element 0 is correct ", (Integer)33, list1.get(0));
		assertEquals("Add: check element 1 is correct ", (Integer)65, list1.get(1));
		assertEquals("Add: check size is correct ", 4, list1.size());
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try{
			int oldData = emptyList.set(1, 42);
			fail("check out bounds");
		}catch(IndexOutOfBoundsException e){
			
		}
		try{
			String oldString = shortList.set(-1, "yo homie");
			fail("check out bounds");
		}catch(IndexOutOfBoundsException e){
			
		}
		
		try{
			int old = list1.set(0, null);
			fail("check out element");
		}catch(NullPointerException e){
			
		}
		
		int oldInteger = list1.set(1, 234);
	    assertEquals("check: replaced data", (Integer)21, (Integer)oldInteger);
	    assertEquals("check: new data", (Integer)234, list1.get(1));
	}
	
	
	// TODO: Optionally add more test methods.
	
}
