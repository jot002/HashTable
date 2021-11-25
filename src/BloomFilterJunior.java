/*
 * NAME: Jonathan Tran
 * PID: A15967290
 */

/**
 * BloomFilterJunior will implement a simple prototype of a
 * bloom filter. The main purpose of the bloom filter is to
 * check if the given value is a member of the bloom filter.
 * This algorithm is efficient since both insertion and
 * lookup will have O(1) time complexity.
 *
 * @author Jonathan Tran
 * @since A15967290
 */
public class BloomFilterJunior {

    /* Constants */
    private static final int MIN_INIT_CAPACITY = 50;
    private static final int BASE256_LEFT_SHIFT = 8;
    private static final int HORNERS_BASE = 27;

    /* Instance variables */
    private boolean[] table;

    /**
     * Initialize a BloomFilterJunior with a table (boolean array)
     * with a given capacity.
     * @param capacity the total amount of space in the boolean table
     * @exception IllegalArgumentException if capacity is less than minimum
     * initial capacity
     */
    public BloomFilterJunior(int capacity) {
        if (capacity < 50) {
            throw new IllegalArgumentException();
        }
        this.table = new boolean[capacity];
    }

    /**
     * Insert element value in the BloomFilterJunior.
     * @param value element being inserted into BloomFilterJunior
     * @exception NullPointerException if value is null
     */
    public void insert(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int indexOne = hashBase256(value);
        int indexTwo = hashCRC(value);
        int indexThree = hashHorners(value);
        // sets the three indices as true
        this.table[indexOne] = true;
        this.table[indexTwo] = true;
        this.table[indexThree] = true;
    }

    /**
     * Check if value is a member of the BloomFilterJunior.
     * @param value element being checked if it is in BloomFilterJunior
     * @exception NullPointerException if value is null
     */
    public boolean lookup(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int indexOne = hashBase256(value);
        int indexTwo = hashCRC(value);
        int indexThree = hashHorners(value);
        // gets the three booleans at the given three indices
        boolean boolOne = this.table[indexOne];
        boolean boolTwo = this.table[indexTwo];
        boolean boolThree = this.table[indexThree];
        // checks if all three elements are true
        if (boolOne && boolTwo && boolThree) {
            return true;
        }
        return false;
    }

    /**
     * Base-256 hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashBase256(String value) {
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = ((hash << BASE256_LEFT_SHIFT) + c) % table.length;
        }
        return Math.abs(hash % table.length);
    }

    /**
     * Simplified CRC hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashCRC(String value) {
        int hashVal = 0;
        for (int i = 0; i < value.length(); i++) {
            int leftShift = hashVal << 5;
            int rightShift = hashVal >>> 27;
            hashVal = (leftShift | rightShift) ^ value.charAt(i);
        }
        return Math.abs(hashVal % this.table.length);
    }

    /**
     * Horner's hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashHorners(String value) {
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = (hash * HORNERS_BASE + c) % table.length;
        }
        return Math.abs(hash % table.length);
    }
}
