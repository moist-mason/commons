package com.github.moistmason.commons.test;

import com.github.moistmason.commons.StringUtil;
import com.github.moistmason.commons.type.Dictionary;
import com.github.moistmason.commons.type.Pair;
import com.github.moistmason.commons.type.Triple;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ToStringTests {
    public static final List<Fruit> FRUITS = List.of(
            new Fruit("apple", Color.RED),
            new Fruit("cherry", Color.RED),
            new Fruit("orange", Color.ORANGE),
            new Fruit("lemon", Color.YELLOW),
            new Fruit("lime", Color.GREEN),
            new Fruit("blueberry", Color.BLUE),
            new Fruit("grape", Color.PURPLE),
            new Fruit("watermelon", Color.PINK),
            new Fruit("blackberry", Color.BLACK)
    );

    @Test
    public void testList() {
        System.out.println(StringUtil.toString(FRUITS));
    }

    @Test
    public void testLinkedList() {
        final LinkedList<Fruit> linkedList = new LinkedList<>(FRUITS);
        System.out.println(StringUtil.toString(linkedList));
    }

    @Test
    public void testArray() {
        final Fruit[] array = FRUITS.toArray(new Fruit[0]);
        System.out.println(StringUtil.toString(array));
    }

    @Test
    public void testMap() {
        final Map<String, Color> map = FRUITS.stream().collect(Collectors.toMap(Fruit::name, Fruit::color));
        System.out.println(StringUtil.toString(map));
    }

    @Test
    public void testEnum() {
        System.out.println(StringUtil.toString(Color.class));
    }

    @Test
    public void testPair() {
        final Pair<String, Color> kiwi = new Pair<>("kiwi", Color.GREEN);
        System.out.println(kiwi);
    }

    @Test
    public void testTriple() {
        final Triple<String, Integer, Color> appleIndexed = new Triple<>("apple", 0, Color.RED);
        System.out.println(appleIndexed);
    }

    @Test
    public void testDictionary() {
        final Map<String, Color> map = FRUITS.stream().collect(Collectors.toMap(Fruit::name, Fruit::color));
        final Dictionary<Color> colorDictionary = Dictionary.fromMap(map);
        System.out.println(colorDictionary);
    }

    public record Fruit(String name, Color color) { }

    public enum Color {
        RED,
        ORANGE,
        YELLOW,
        GREEN,
        BLUE,
        PURPLE,
        PINK,
        BLACK;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
