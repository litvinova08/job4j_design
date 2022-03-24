package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Margarita Litvinova
 * @version 1.0
 */
public class EvenNumbersIt implements Iterator<Integer> {
    /**
     * index - это счетчик итератора
     */
    private int[] data;
    private int index;

    /**
     * метод принимает массив для последующего использования итератора
     * @param data массив, из которого будут отобраны четные числа
     */
    public EvenNumbersIt(int[] data) {
        this.data = data;
    }

    /**
     * метод позволяет определить, есть ли еще елементы в массиве
     * @return true если в массиве еще есть четные элементы
     */
    @Override
    public boolean hasNext() {
        boolean rsl = false;
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                index = i;
                rsl = true;
                break;
            }
        } return rsl;
    }

    /**
     * метдо возвращает следующий четный элемент
     * @return следующий четный элемент или -1, если нет больше четных элементов
     */
    @Override
    public Integer next() {
        int rsl;
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            rsl = data[index];
            index++;
            return rsl;
        }
    }
}
