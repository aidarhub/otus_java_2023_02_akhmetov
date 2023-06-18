package org.example;

public class Denominations {
    private final int denomination;
    private int count;

    public Denominations(int denomination, int count) {
        this.denomination = denomination;
        this.count = count;
    }

    public Denominations(int denomination) {
        this(denomination, 0);
    }

    public int getDenomination() {
        return denomination;
    }

    public int getCount() {
        return count;
    }

    public void load(int count) {
        this.count += count;
    }

    public void unload(int count) {
        this.count -= count;
    }
}
