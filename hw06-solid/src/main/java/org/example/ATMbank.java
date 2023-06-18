package org.example;

import java.util.*;

public class ATMbank implements ATM {

    private final Map<Integer, Denominations> cells = new HashMap<>();

    @Override
    public void deposit(int denomination, int count) {
        if (!cells.containsKey(denomination)) {
            cells.put(denomination, new Denominations(denomination));
        }
        cells.get(denomination).load(count);
    }

    @Override
    public boolean withdraw(int amount) {
        List<Integer> denominations = new ArrayList<>(cells.keySet());
        denominations.sort(Collections.reverseOrder());

        Map<Integer, Integer> withdrawn = new HashMap<>();
        for (int denomination : denominations) {
            int count = amount / denomination;
            if (count > 0) {
                Denominations cell = cells.get(denomination);
                if (cell.getCount() >= count) {
                    withdrawn.put(denomination, count);
                    amount -= denomination * count;
                }
            }
        }

        if (amount > 0) {
            return false;
        }

        for (Map.Entry<Integer, Integer> entry : withdrawn.entrySet()) {
            Denominations cell = cells.get(entry.getKey());
            cell.unload(entry.getValue());
        }

        return true;
    }

    @Override
    public int getBalance() {
        int sum = 0;
        for (Denominations cell : cells.values()) {
            sum += cell.getDenomination() * cell.getCount();
        }
        return sum;

    }
}
