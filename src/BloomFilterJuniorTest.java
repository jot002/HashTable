import org.junit.Test;

import static org.junit.Assert.*;

public class BloomFilterJuniorTest {

    @Test
    public void insert() {
        BloomFilterJunior test = new BloomFilterJunior(50);
        test.insert("hello");
        test.insert("hell");
        test.insert("hel");
        test.insert("he");
        test.insert("h");
        assertEquals(true, test.lookup("he"));
    }

    @Test
    public void lookup() {
        BloomFilterJunior test = new BloomFilterJunior(50);
        test.insert("hello");
        test.insert("hell");
        test.insert("hel");
        test.insert("he");
        test.insert("h");
        assertEquals(true, test.lookup("he"));
    }
}