package com.github.moistmason.commons;

import java.util.*;

/**
 * String manipulation methods. Includes {@link Object#toString} helpers.
 *
 * @author moist-mason
 */
public final class StringUtil {

    /**
     * Capitalizes the first character of the string. Throws an {@link IllegalArgumentException} if the first character
     * of the string is not a letter.
     *
     * @param input The string.
     * @return The capitalized string.
     */
    public static String capitalize(final String input) {
        return Util.get(
                Character.isLetter(input.charAt(0)),
                input.substring(0, 1).toUpperCase(Locale.ROOT) + input.substring(1),
                Util.illegalArgException("First character of string %s (%c) is not a letter.", input, input.charAt(0))
        );
    }

    /**
     * Joins the input strings together with spaces in between.
     *
     * @param inputs The strings.
     * @return The joined strings.
     */
    public static String spaced(final String... inputs) {
        return String.join(" ", inputs);
    }

    /**
     * Joins the input strings together with spaced commas in between.
     *
     * @param inputs The strings.
     * @return The joined strings.
     */
    public static String commas(final String... inputs) {
        return String.join(", ", inputs);
    }

    /**
     * Joins the input strings together with line breaks in between.
     *
     * @param inputs The strings.
     * @return The joined strings.
     */
    public static String lines(final String... inputs) {
        return String.join("\n", inputs);
    }

    /**
     * Surrounds the input string with square brackets [].
     *
     * @param inputs The string.
     * @return The bracketed string.
     */
    public static String brackets(final String inputs) {
        return "[" + inputs + "]";
    }

    /**
     * Checks if the input string partially contains any element of the array.
     *
     * @param array The array.
     * @param input The input string.
     * @return {@code true} if a partial match is found in the array.
     */
    public static boolean containsAny(final String[] array, final String input) {
        return Util.anyMatch(array, input::contains);
    }

    /**
     * Checks if the input string partially contains any element of the list.
     *
     * @param list The list.
     * @param input The input string.
     * @return {@code true} if a partial match is found in the list.
     */
    public static boolean containsAny(final List<String> list, final String input) {
        return Util.anyMatch(list, input::contains);
    }

    /**
     * Checks if the input string is equal to any element of the array.
     *
     * @param array The array.
     * @param input The input string.
     * @return {@code true} if an exact match is found in the array.
     */
    public static boolean equalsAny(final String[] array, final String input) {
        return Util.anyMatch(array, input::equals);
    }

    /**
     * Checks if the input string is equal to any element of the list.
     *
     * @param list The list.
     * @param input The input string.
     * @return {@code true} if an exact match is found in the list.
     */
    public static boolean equalsAny(final List<String> list, final String input) {
        return Util.anyMatch(list, input::equals);
    }

    /**
     * {@link Object#toString} formatter for arrays.
     * This method shows that this is an array of the given type. 
     * It then displays the values in the array formatted via {@link Arrays#toString(Object[])}.
     *
     * @param array The array.
     * @return The formatted string.
     * @param <T> The array type.
     */
    public static <T> String toString(final T[] array) {
        return arrayPrefix(array) + " -> " + Arrays.toString(array);
    }

    /**
     * {@link Object#toString} formatter for collections.
     * This method shows the subtype of collection (list, set, etc.), and the type iterated within the collection.
     * It then displays the values in the collection formatted via {@link StringUtil#collectionValues(Collection)}.
     *
     * @param collection The collection.
     * @return The formatted string.
     * @param <T> The collection type.
     */
    public static <T> String toString(final Collection<T> collection) {
        return collectionPrefix(collection) + " -> " + collectionValues(collection);
    }

    /**
     * {@link Object#toString} formatter for maps.
     * This method shows the subtype of map (hash map, tree map, etc.), and the types for both keys and values iterated within the map.
     * It then displays the entries in the map formatted via {@link StringUtil#mapEntries(Map)}.
     *
     * @param map The map.
     * @return The formatted string.
     * @param <K> The key type.
     * @param <V> The value type.
     */
    public static <K, V> String toString(final Map<K, V> map) {
        return mapPrefix(map) + " -> " + mapEntries(map);
    }

    /**
     * {@link Object#toString} formatter for enum classes.
     * This method shows the declaring enum class.
     * It then displays the constants formatted via {@link StringUtil#enumConstants(Class)}.
     *
     * @param enumClass The enum class.
     * @return The formatted string.
     * @param <T> The enum type.
     */
    public static <T extends Enum<T>> String toString(final Class<T> enumClass) {
        return enumPrefix(enumClass) + " -> " + enumConstants(enumClass);
    }

    /**
     * Formats the values in a collection as an array with {@link Arrays#toString(Object[])}.
     * 
     * @param collection The collection.
     * @return The formatted string.
     * @param <T> The collection type.
     */
    private static <T> String collectionValues(final Collection<T> collection) {
        return Arrays.toString(collection.toArray());
    }

    /**
     * Formats the entries in a map. Each entry is formatted within square brackets as {@code [K.toString: V.toString]}, 
     * and entries are separated by commas.
     *
     * @param map The map.
     * @return The formatted string.
     * @param <K> The key type.
     * @param <V> The value type.
     */
    private static <K, V> String mapEntries(final Map<K, V> map) {
        String[] entries = map.entrySet().stream()
                .map(e -> spaced(
                        e.getKey().toString() + ":", 
                        e.getValue().toString()))
                .map(StringUtil::brackets)
                .toArray(String[]::new);

        return commas(entries);
    }

    /**
     * Formats the constants in an enum class as an array with {@link Arrays#toString(Object[])}.
     *
     * @param enumClass The enum.
     * @return The formatted string.
     * @param <T> The enum type.
     */
    private static <T extends Enum<T>> String enumConstants(final Class<T> enumClass) {
        return Arrays.toString(enumClass.getEnumConstants());
    }

    /**
     * Formats the type information for a provided array.
     *
     * @param array The array.
     * @return The formatted type information.
     * @param <T> The array type.
     */
    private static <T> String arrayPrefix(final T[] array) {
        return spaced("Array of Type:", typeName(array.getClass().getComponentType()));
    }

    /**
     * Formats the type information of a collection. 
     * Shows what subtype of collection it is (list, set, etc.) and the type iterated within the collection.
     *
     * @param collection The collection.
     * @return The formatted type information.
     * @param <T> The collection type.
     */
    private static <T> String collectionPrefix(final Collection<T> collection) {
        return spaced(
                simpleName(collection),
                "of Type:",
                typeName(collection)
        );
    }

    /**
     * Formats the type information of a map. 
     * Shows what subtype of map it is (hash map, tree map, etc.) and the key and value types.
     *
     * @param map The map.
     * @return The formatted type information.
     * @param <K> The key type.
     * @param <V> The value type.
     */
    private static <K, V, M extends Map<K, V>> String mapPrefix(final M map) {
        return spaced(
                simpleName(map),
                "with Key Type:", typeName(Util.list(map.keySet()).getFirst()),
                "and Value Type:", typeName(Util.list(map.values()).getFirst())
        );
    }

    /**
     * Formats the type information of an enum. 
     * Shows the declaring class for this enum.
     *
     * @param enumClass The enum class.
     * @return The formatted type information.
     * @param <T> The enum type.
     */
    private static <T extends Enum<T>> String enumPrefix(final Class<T> enumClass) {
        return "Enum " + typeName(enumClass);
    }

    /**
     * Gets the simple (package-exclusive) class name of this value's type. Used for collections, maps, and other hierarchies.
     * @param value The value.
     * @return The simple class name.
     * @param <T> The type.
     */
    private static <T> String simpleName(final T value) {
        return value.getClass().getSimpleName();
    }

    /**
     * Gets the type name of this value.
     *
     * @param value The value.
     * @return The type name.
     * @param <T> The type.
     */
    public static <T> String typeName(final T value) {
        return value.getClass().getTypeName();
    }

    /**
     * Gets the type name of this collection, by checking the type of the first value.
     *
     * @param collection The collection.
     * @return The type name.
     * @param <T> The type.
     */
    public static <T> String typeName(final Collection<T> collection) {
        return collection.stream().findFirst().map(StringUtil::typeName).orElseThrow();
    }

    /**
     * Gets the name of this class. Used for types that call another class during type retrieval, like enums and arrays.
     * @param value The value.
     * @return The class name.
     * @param <T> The type.
     */
    public static <T> String typeName(final Class<T> value) {
        return value.getTypeName();
    }
}