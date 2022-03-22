package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIt implements Iterator<Integer> {
    private int[] data;
    private int index;

    public EvenNumbersIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return evenExist() != -1;
    }

    @Override
    public Integer next() {
        int rsl = evenExist();
        if (rsl == -1) {
            throw new NoSuchElementException();
        } else {
            index++;
            return rsl;
        }
    }

    public int evenExist() {
        int rsl = -1;
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                rsl = data[i];
                index = i;
                break;
            }
        } return rsl;
    }
}
