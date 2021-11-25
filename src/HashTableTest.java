import org.junit.Test;
import static org.junit.Assert.*;

public class HashTableTest {

    @Test
    public void testInsert() {
        HashTable test = new HashTable();
        assertEquals(true,test.insert("hello"));
        assertEquals(true,test.insert("hello"));
        assertEquals(true,test.insert("mmmm"));
        assertEquals(true,test.insert("bye"));
        System.out.print(test.toString());
    }

    @Test(expected = NullPointerException.class)
    public void testInsert1() {
        HashTable test = new HashTable();
        test.insert(null);
    }

    @Test
    public void testDelete() {
        HashTable test = new HashTable();
        test.insert("hello");
        assertEquals(1, test.size());
        test.delete("hello");
        assertEquals(0, test.size());
        test.insert("bye");
        test.insert("mmmm");
        test.insert("mmmm");
        test.delete("mmmm");
        assertEquals(2, test.size());
        test.delete("mmmm");
        assertEquals(1, test.size());
        System.out.print(test.toString());
    }

    @Test(expected = NullPointerException.class)
    public void testDelete1() {
        HashTable test = new HashTable();
        test.delete(null);
    }

    @Test
    public void testLookup() {
        HashTable test = new HashTable();
        test.insert("hello");
        assertEquals(true, test.lookup("hello"));
        test.insert("bye");
        assertEquals(true, test.lookup("bye"));
        test.insert("mmmm");
        assertEquals(true, test.lookup("mmmm"));
        test.insert("mmmm");
        assertEquals(false, test.lookup("broo"));
        System.out.print(test.toString());
    }

    @Test(expected = NullPointerException.class)
    public void testLook1() {
        HashTable test = new HashTable();
        test.lookup(null);
    }

    @Test
    public void testSize() {
        HashTable test = new HashTable();
        test.insert("hello");
        assertEquals(1, test.size());
        test.insert("bye");
        assertEquals(2, test.size());
        test.insert("mmmm");
        assertEquals(3, test.size());
        test.insert("mmmm");
        assertEquals(4, test.size());
        System.out.print(test.toString());
    }

    @Test
    public void testCapacity() {
        HashTable test = new HashTable(6);
        test.insert("hello");
        test.insert("hello");
        test.insert("hello");
        assertEquals(6, test.capacity());
        test.insert("hello");
        test.insert("hello");
        assertEquals(12, test.capacity());
        test.delete("hello");
        test.delete("hello");
        assertEquals(12, test.capacity());
    }

    @Test
    public void testGetStatsLog() {
        HashTable test = new HashTable(6);
        test.insert("hello");
        test.insert("hello");
        test.insert("hello");
        test.insert("hello");
        String s1 = "Before rehash # 1: load factor 0.67, 0 collision(s).\n";
        assertEquals(s1,test.getStatsLog());
        test.insert("hello");
        String s2 = "Before rehash # 2: load factor 0.42, 0 collision(s).\n";
        assertEquals(s2,test.getStatsLog());
        test.insert("hello");
        test.insert("hello");
        String s3 = "Before rehash # 2: load factor 0.58, 0 collision(s).\n";
        assertEquals(s3,test.getStatsLog());
        test.insert("bye");
        test.insert("mmmm");
        System.out.println(test.getStatsLog());
    }

    @Test
    public void testToString1() {
        HashTable test = new HashTable(6);
        test.insert("hello");
        String s0 = "[null, null, null, null, null, hello]";
        assertEquals(s0,test.toString());
        test.insert("hello");
        String s1 = "[hello, null, null, null, null, hello]";
        assertEquals(s1,test.toString());
        test.insert("hello");
        String s2 = "[hello, hello, null, null, null, hello]";
        assertEquals(s2,test.toString());
    }
}