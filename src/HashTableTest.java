import org.junit.Test;
import static org.junit.Assert.*;

public class HashTableTest {

    @Test
    public void testInsert() {
        HashTable test = new HashTable();
        test.insert("hello");
        test.insert("bye");
        test.insert("mmmm");
        test.insert("mmmm");
        test.insert("mmmm");
        //test.insert("mmmm");
        System.out.print(test.size());
//        test.insert("mmmm");
//        test.insert("mmmm");
//        test.insert("mmmm");
        //assertEquals(true, test.lookup("sss"));
        System.out.print(test.toString());
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testLookup() {
    }

    @Test
    public void testSize() {
    }

    @Test
    public void testCapacity() {
    }

    @Test
    public void testGetStatsLog() {
    }

    @Test
    public void testToString1() {
    }
}