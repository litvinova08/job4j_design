package ru.job4j.io;

import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Config class должен считать все ключи в карту values.
 * Важно в файле могут быть пустые строки и комментарии их нужно пропускать.
 * @author Margarita L
 * @version 1.0
 */

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * load() - должен считать все ключи в карту values.
     * Важно в файле могут быть пустые строки и комментарии их нужно пропускать.
     */
    public void load() throws IllegalArgumentException {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            List<String> strings = read.lines().filter(s -> !s.startsWith("#") && !s.isEmpty()).toList();
            for (String s : strings) {
                String[] stringsSplit = s.split("=");
                if (stringsSplit.length <= 1) {
                    throw new IllegalArgumentException("broken key/value combination");
                } else {
                    values.put(stringsSplit[0], stringsSplit[1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

}