package ru.job4j.tree;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> optionalParent = findBy(parent);
        if (optionalParent.isPresent()) {
            Optional<Node<E>> optionalChild = findBy(child);
            if (optionalChild.isEmpty()) {
                    optionalParent.get().children.add(new Node<>(child));
                    rsl = true;
            }
        }

        return rsl;
    }

    public boolean isBinary() {
        Optional<Node<E>> rsl = findByPredicate(el -> el.children.size() > 2);
        return rsl.isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(el -> el.value.equals(value));
    }
}
