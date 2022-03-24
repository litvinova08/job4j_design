package ru.job4j.collections;

import java.util.ArrayList;
import java.util.List;

public class ListCheck {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        System.out.println(list.size());
        list.add(null);
        list.add(null);
        System.out.println(list.size());
    list.remove(null);
        System.out.println(list.size());
    }
}
