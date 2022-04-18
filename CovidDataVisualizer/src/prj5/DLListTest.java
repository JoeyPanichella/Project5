package prj5;

import java.util.Iterator;
import java.util.NoSuchElementException;
import student.TestCase;

/**
 * Tests the methods defined in the DLList class. 
 * 
 * @author sophia rubsamen 
 * @version 2022.04.18
 *
 * SOURCE OF CODE: LAB 10
 */
public class DLListTest extends TestCase {
    /**
     * the list we will use
     */
    private DLList<String> list;

    /**
     * run before every test case
     */
    @Override
    public void setUp() {
        list = new DLList<String>();
    }

    /**
     * Tests that an IndexOutOfBounds exception is thrown when the index is
     * greater than or equal to size and less than zero
     */
    public void testRemoveException() {
        list.add("A");
        Exception e = null;
        try {
            list.remove(2);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
        e = null;
        try {
            list.remove(-1);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }

    /**
     * Tests that objects can be removed at the beginning and end and that the
     * size is changed
     */
    public void testRemoveIndex() {
        list.add("A");
        list.add("B");
        assertTrue(list.remove(1));
        assertEquals(1, list.size());
        list.add("B");
        assertTrue(list.remove(0));
        assertEquals(1, list.size());
    }

    /**
     * Tests the add method. Ensures that it adds the object is added at the end
     * and the size is increased
     */
    public void testAdd() {
        assertEquals(0, list.size());
        list.add("A");
        assertEquals(1, list.size());
        list.add("B");
        assertEquals(2, list.size());
        assertEquals("B", list.getEntry(1));

    }

    /**
     * Tests that objects can be added at the beginning and end and that they
     * are placed correctly
     */
    public void testAddIndex() {
        list.add("B");
        list.add(0, "A");
        assertEquals("A", list.getEntry(0));
        assertEquals(2, list.size());
        list.add(2, "D");
        assertEquals("D", list.getEntry(2));
        list.add(2, "C");
        assertEquals("C", list.getEntry(2));
    }

    /**
     * This tests that the add method throws a null pointer exception when
     * adding null data to the list
     */
    public void testAddNullException() {
        Exception e = null;
        try {
            list.add(null);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }

    /**
     * This tests that the add method throws a Invalid argument when adding null
     * data to the list
     */
    public void testAddIndexNullException() {
        Exception e = null;
        try {
            list.add(0, null);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }

    /**
     * This tests when the add method is called and the index is greater than
     * size or less than zero
     */
    public void testAddException() {
        list.add("A");
        Exception e = null;
        try {
            list.add(2, "B");
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
        e = null;
        try {
            list.add(-1, "B");
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue( e instanceof IndexOutOfBoundsException);
    }

    /**
     * Tests removing a object changes the size appropiately and that you can
     * remove the first and last elements
     */
    public void testRemoveObj() {
        assertFalse(list.remove(null));
        list.add("A");
        list.add("B");
        assertTrue( list.remove("A"));
        assertEquals( "B", list.getEntry(0));
        assertEquals( 1, list.size());
        list.add("C");
        assertTrue(list.remove("C"));
        assertEquals("B", list.getEntry(0));
    }

    /**
     * Tests getEntry when the index is greater than or equal to size and when the
     * index is less than zero
     */
    public void testgetEntryException() {
        Exception exception = null;
        try {
            list.getEntry(-1);
        } 
        catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
        exception = null;
        list.add("A");
        try {
            list.getEntry(1);
        } 
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
    }

    /**
     * Test contains when it does and does not contain the object
     */
    public void testContains() {
        assertFalse( list.contains("A"));
        list.add("A");
        assertTrue(list.contains("A"));
        assertFalse(list.contains("B"));
        list.add("B");
        assertTrue(list.contains("B"));
    }

    /**
     * Test lastIndexOf when the list is empty, when the object is not in the
     * list, and when it is at the beginning or end
     */
    public void testLastIndexOf() {
        assertEquals( -1, list.lastIndexOf("A"));
        list.add("A");
        assertEquals(0, list.lastIndexOf("A"));
        list.add("A");
        assertEquals(1, list.lastIndexOf("A"));
        list.add("B");
        assertEquals(1, list.lastIndexOf("A"));
        assertEquals(2, list.lastIndexOf("B"));
        list.add("A");
        assertEquals(3, list.lastIndexOf("A"));
    }

    /**
     * Tests isEmpty when empty and full
     */
    public void testIsEmpty() {
        assertTrue( list.isEmpty());
        list.add("A");
        assertFalse(list.isEmpty());
    }

    /**
     * Ensures that all of the objects are cleared and the size is changed
     */
    public void testClear() {
        Iterator<String> iter = list.iterator();
        Exception exception = null;
        try {
            iter.next();
        } 
        catch (NoSuchElementException e) {
            exception = e;
        }
        assertTrue(exception instanceof NoSuchElementException);
        list.add("A");
        list.clear();
        assertEquals(0, list.size());
        assertFalse(
                list.contains("A"));
    }

    /**
     * Tests the toString when there are 0, 1, and 2 objects in the list
     */
    public void testToString() {
        assertEquals("{}",
                list.toString());
        list.add("A");
        assertEquals("{A}",
                list.toString());
        list.add("B");
        assertEquals("{A, B}",
                list.toString());
    }

    /**
     * Tests removing from an empty list
     */
    public void testRemoveFromEmpty() {
        list.add("dance");
        list.add(0, "safety");
        list.clear();
        assertFalse(list.remove("safety"));
        Exception exception;
        exception = null;
        try {
            list.remove(0);
        } 
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue(  exception instanceof IndexOutOfBoundsException);

        DLList<String> emptyList = new DLList<String>();
        exception = null;
        try {
            emptyList.remove(0);
        } 
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue( exception instanceof IndexOutOfBoundsException);
    }
    
    /**
     * Tests the iterator 
     */
    public void testIterator() {
        list.add("name");
        list.add("is");
        list.add("Sophie");
        Iterator<String> iter = list.iterator();
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), "name");
        for (int i = 0; i < 2; i++) {
            assertTrue(iter.hasNext());
            iter.next();
        }
        Exception exception = null;
        try {
            iter.next();
        } 
        catch (NoSuchElementException e) {
            exception = e;
        }
        assertTrue(exception instanceof NoSuchElementException);
    }
    
    /**
     * Tests that the toArray method returns an array of 
     * the strings in the list in the correct order they were
     * added in. 
     */
    public void testToArray() {
        //
    }
    

}
