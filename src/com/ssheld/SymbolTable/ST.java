package com.ssheld.SymbolTable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * Author: Stephen Sheldon 3/22/2019
 *
 * API to be used for algorithms involving the use
 * of a symbol table.
 * Some methods in this API are redundant but we implement
 * them for the API user's reference.
 */

public class ST<Key extends Comparable<Key>, Value> {

    private TreeMap<Key, Value> st;

    public ST() {
        st = new TreeMap<Key, Value>();
    }

    // Put key-value pair into the table (remove key from table if value is null)
    public void put(Key key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("Call to put() with null key");
        if (val == null)
            st.remove(key);
        else
            st.put(key, val);
    }

    // Value paired with key (null is absent)
    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("Call to get() with null key");
        return st.get(key);
    }

    // Remove key (and its value) from table
    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("Call to delete() with null key");
        st.remove(key);
    }

    // Check if there is a value paired with a key
    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("Call to contains() with null key");
        return st.containsKey(key);
    }

    // Check is the table is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Return the number of key-value pairs
    public int size() {
        return st.size();
    }

    // Return the smallest key
    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("Call to min() with empty symbol table");
        return st.firstKey();
    }

    // Return the largest key
    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("Call to max() with empty symbol table");
        return st.lastKey();
    }

    // Return the largest key less than or equal to key
    public Key floor(Key key) {
        if (key == null)
            throw new IllegalArgumentException("Call to floor() with null key");
        Key k = st.floorKey(key);
        if (k == null)
            throw new NoSuchElementException("All keys are greater than " + key);
        return k;
    }

    // Return the smallest key greater than or equal to key
    public Key ceiling(Key key) {
        if (key == null)
            throw new IllegalArgumentException("Call to ceiling() with null key");
        Key k = st.ceilingKey(key);
        if (k == null)
            throw new NoSuchElementException("All keys are less than " + key);
        return k;
    }

    // Delete the smallest key
    public void deleteMin() {
        delete(min());
    }

    // Delete the largest key
    public void deleteMax() {
        delete(max());
    }

    // Return number of keys less than key
    public int rank(Key key) {
        if (key == null)
            throw new IllegalArgumentException("Call to rank() with null key");
        if (isEmpty())
            throw new NoSuchElementException("Call to rank() when table is currently empty");
        // If the ST doesn't contain the key then return the size of the entire table
        if (!contains(key))
            return size();
        int count = 0;
        for (Map.Entry<Key, Value> entry : st.entrySet()) {
            Key k = entry.getKey();
            if (k.compareTo(key) == 0)
                break;
            count++;
        }
        return count;
    }

    // Return key of rank k.
    // Return null if key doesn't exist in symbol table.
    public Key select(int k) {
        // Throw an exception if user tries to make api call with a negative index...
        if (k < 0 || k >= size())
            throw new IllegalArgumentException("Call to select() where index is less than 0");
        // Throw an exception if st is empty
        if (isEmpty())
            throw new NoSuchElementException("Call to select() when table is currently empty");
        int count = 0;
        Key key = null;
        for (Map.Entry<Key, Value> entry : st.entrySet()) {
            key = entry.getKey();
            if (count == k) {
                break;
            }
        }
        return key;
    }

    // Return number of keys in [lo..hi]
    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0)
            return 0;
        else if (contains(hi))
            return rank(hi) - rank(lo) - 1;
        // ST doesn't contain the hi key so let's take the size of
        // the ST and subtract the lo rank
        else
            return rank(hi) - rank(lo);
    }
}
