/*
 * Name: Jonathan Tran
 * PID: A15967290
 */

import java.util.Arrays;

/**
 * HashTable implements IHashTable interface and works with Strings.
 * The hash table with use linear Probing to handle collisions. It will
 * maintain a bridge element to tell the lookup algorithm to keep
 * looking instead of terminating.
 * 
 * @author Jonathan Tran
 * @since 11/24/2021
 */
public class HashTable implements IHashTable {

    /* the bridge for lazy deletion */
    private static final String BRIDGE = new String("[BRIDGE]".toCharArray());
    private static final double LOAD_FACTOR = .55;
    private static final int TWICE = 2;

    /* instance variables */
    private int size; // number of elements stored
    private String[] table; // data table
    private int numHash = 0; // numbers of times hashed
    private int numCollision; // number of collisions

    /**
     * Initialize a hash table with default total capacity 15.
     */
    public HashTable() {
        this(15);
    }

    /**
     * Initialize a hash table with given total capacity,
     * and all needed instance variables.
     * @param capacity the total amount of space in the hash table
     */
    public HashTable(int capacity) {
        if (capacity < 5) {
            throw new IllegalArgumentException();
        }
        this.table = new String[capacity];
    }

    /**
     * Insert element value in the hash table. Return true if the item
     * is inserted, false if it already exists.
     * @param value element being inserted into hash table
     * @return boolean on if it got inserted if not
     * @exception NullPointerException if the value is null
     */
    @Override
    public boolean insert(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        // gets the load factor
        double loadF = (double) size() / (int) capacity();
        // rehashes if larger than .55
        if (loadF > LOAD_FACTOR) {
            this.numHash++;
            rehash();
            this.numCollision = 0;
        }
        int index = hashString(value) % this.table.length;
        String item = this.table[index];
        if ((item == null) || (item == BRIDGE)) {
            this.table[index] = value;
            this.size++;
            return true;
        }
        else {
            int i = 0;
            while (item != null) {
                i++;
                if (!value.equals(item)) {
                    this.numCollision++;
                }
                item = this.table[(index+i) % this.table.length];
            }
            this.size++;
            this.table[(index+i) % this.table.length] = value;
            return true;
        }
    }

    /**
     * Return true if the item is deleted, or false if it can’t be deleted
     * because it does not exist in the hash table
     * @param value element being deleted from hash table
     * @return boolean true if deleted, false if it can't be deleted
     * @exception NullPointerException if the value is null
     */
    @Override
    public boolean delete(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int index = hashString(value) % this.table.length;
        String item = this.table[index];
        if (item == null)
            return false;
        else {
            this.table[index] = BRIDGE;
            this.size--;
            return true;
        }
    }

    /**
     * Determines if value is in the hash table.
     * @param value element being checked in the hash table
     * @return boolean true if found, false if not
     * @exception NullPointerException if the value is null
     */
    @Override
    public boolean lookup(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int val = hashString(value);
        String current = this.table[val];
        int i = 0;
        while (current != null) {
            if (current.equals(value)) {
                return true;
            }
            i++;
            current = this.table[val+i];
        }
        return false;
    }

    /**
     * Returns the number of elements currently stored in the HashTable
     * @return the number of elements in the hash table
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns the total capacity of the table in the HashTable.
     * @return the total capacity of the hash table
     */
    @Override
    public int capacity() {
        return this.table.length;
    }

    /**
     * Returns the statistics log of the HashTable. The printed returned
     * string should be in the following format, with a new line
     * character at the end of each line.
     * @return the statistics log of the HashTable
     */
    public String getStatsLog() {
        double loadF = (double) size() / (int) capacity();
        String s1 = String.format("Before rehash # %d: load factor ",
                this.numHash + 1);
        String s2 = String.format("%.2f, ", loadF);
        String s3 = String.format("%d collision(s).\n", numCollision);
        String sentence = s1 + s2+ s3;
        return sentence;
    }

    /**
     * Rehash the table by (1) double the capacity, and (2) iterate through
     * the old table and re-insert every valid element to the new table.
     */
    private void rehash() {
        getStatsLog();
        String[] old = this.table;
        String[] temp = new String[TWICE * this.table.length];
        this.table = temp;
        this.size = 0;
        for (int i = 0; i < old.length; i++) {
            if ((old[i] != null) && (old[i] != BRIDGE)) {
                insert(old[i]);
            }
        }
    }

    /**
     * Returns the hash value of the given string.
     * @param value string that needs to given a hash value
     * @return an integer representing the hash value of the string
     */
    private int hashString(String value) {
        int hashVal = 0;
        for (int i = 0; i < value.length(); i++) {
            int leftShift = hashVal << 5;
            int rightShift = hashVal >>> 27;
            hashVal = (leftShift | rightShift) ^ value.charAt(i);
        }
        return Math.abs(hashVal % this.table.length);
    }

    /**
     * Returns the string representation of the hash table.
     * This method internally uses the string representation of the table array.
     * DO NOT MODIFY. You can use it to test your code.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return Arrays.toString(table);
    }
}
