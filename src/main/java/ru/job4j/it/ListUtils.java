package ru.job4j.it;

import java.util.*;
import java.util.function.Predicate;

/**
 * Класс содержит методы ListIterator, позволяющие модифицировать элементы коллекции
 *
 * @author Margarita L
 * @version 1.2
 */
public class ListUtils {

    /**
     * method accepts list of elements, an element that will be added before the given index
     *
     * @param list  of elements
     * @param index of an element. a new element will be added before this index
     * @param value new element that will be added
     * @param <T>   element type
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator(index);
        iterator.add(value);
    }

    /**
     * method accepts list of elements, an element that will be added after the given index
     *
     * @param list  of elements
     * @param index of an element. a new element will be added after this index
     * @param value new element that will be added
     * @param <T>   element type
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator(index + 1);
        iterator.add(value);
    }

    /**
     * the method will delete elements that meet criteria filter
     *
     * @param list   of elements
     * @param filter condition for removal. Elements that meets the criteria will be removed from the list
     * @param <T>    element type
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**
     * the method replaces elements that meet filter criteria with a new element
     *
     * @param list   of elements
     * @param filter condition for replacement with a new element
     * @param value  element that will replace filtered elements
     * @param <T>    element type
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    /**
     * the method will remove from the first list the elements contained in the second list
     *
     * @param list     of elements that will be changed
     * @param elements list with elements for comparison
     * @param <T>      element type
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (elements.contains(iterator.next())) {
                iterator.remove();
            }
        }
    }

}