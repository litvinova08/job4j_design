package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        List<User> previousSorted = previous.stream().sorted().toList();
        Queue<User> queuePrevious = new LinkedList<>(previousSorted);
        List<User> currentSorted = current.stream().sorted().toList();
        Queue<User> queueCurrent = new LinkedList<>(currentSorted);
        User comparePrevious = queuePrevious.poll();
        User compareCurrent = queueCurrent.poll();
        while (comparePrevious != null || compareCurrent != null) {
            if (comparePrevious == null || compareCurrent == null) {
                if (comparePrevious == null) {
                    rsl.addAdded();
                    compareCurrent = queueCurrent.poll();
                } else {
                    rsl.addDelete();
                    comparePrevious = queuePrevious.poll();
                }
            } else if (comparePrevious.getId() == compareCurrent.getId()) {
                if (!comparePrevious.getName().equals(compareCurrent.getName())) {
                    rsl.addChange();
                }
                comparePrevious = queuePrevious.poll();
                compareCurrent = queueCurrent.poll();
            } else if (comparePrevious.getId() > compareCurrent.getId()) {
                rsl.addAdded();
                comparePrevious = queuePrevious.poll();
            } else if (comparePrevious.getId() < compareCurrent.getId()) {
                rsl.addDelete();
                comparePrevious = queuePrevious.poll();
            }
        }

        return rsl;
    }
}