/*
 * Name: Jonathan Tran
 * PID: A15967290
 */

import java.util.Arrays;

/**
 * TODO
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
    private int numHash = 0;
    private int numCollision;

    public HashTable() {
        this(15);
    }

    public HashTable(int capacity) {
        if (capacity < 5) {
            throw new IllegalArgumentException();
        }
        this.table = new String[capacity];
    }

    @Override
    public boolean insert(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        double loadF = (double) size() / (int) capacity();
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

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.table.length;
    }

    public String getStatsLog() {
        double loadF = (double) size() / (int) capacity();
        String s1 = String.format("Before rehash # %d: load factor ",
                this.numHash + 1);
        String s2 = String.format("%.2f, ", loadF);
        String s3 = String.format("%d collision(s).\n", numCollision);
        String sentence = s1 + s2+ s3;
        return sentence;
    }

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
