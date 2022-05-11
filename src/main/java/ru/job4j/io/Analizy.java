package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Analizy {


    public void unavailable(String source, String target) {
        List<String> log = new LinkedList<>();
        try {
            FileReader fileReader = new FileReader(source);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            log = bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            Iterator<String> iterator = log.iterator();
            while (iterator.hasNext()) {
                String temp = iterator.next();
                if (temp.startsWith("400") || temp.startsWith("500")) {
                    out.print(temp.substring(4) + ";");
                    while (iterator.hasNext()) {
                        temp = iterator.next();
                        if (!temp.startsWith("400") && !temp.startsWith("500")) {
                            out.println(temp.substring(4) + ";");
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

            public static void main(String[]args) {
                Analizy analizy = new Analizy();
                analizy.unavailable("./data/source.txt", "./data/target.txt");
            }
        }