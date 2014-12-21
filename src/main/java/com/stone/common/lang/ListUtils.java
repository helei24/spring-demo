package com.stone.common.lang;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ListUtils {

    /**
     * Split the provided String into an array of String objects.
     * 
     * @param listOfStrings
     *            String to split.
     * @return Array of String.
     */
    static String[] getSplitString(final String listOfStrings) {

        String s = listOfStrings.trim();

        if (listOfStrings.startsWith("[")) {
            s = listOfStrings.replaceFirst("\\[", "");
        }
        if (listOfStrings.endsWith("]")) {
            s = s.replaceFirst("\\]", "");
        }

        return s.split(",");
    }

    /**
     * Parses a list of Numbers from a string. The format of the string must be:
     * 1, 2, 3, 4 ... and it can be enveloped in brackets [].
     * 
     * @param type
     *            Class descriptor for the Number type to parse into.
     * @param listToParse
     *            The list to parse.
     * @return List of the specified Number type, parsed from the provided
     *         input.
     */
    public static <T extends Number> List<T> parseListOfNumbers(final Class<T> type,
            final String listToParse) {

        final List<T> list = new ArrayList<T>();
        for (final String s : getSplitString(listToParse)) {
            list.add(parseString(type, s.trim()));
        }
        return list;
    }

    /**
     * Parses the input string from String to the specified number type. Note
     * that the type must have a valueOf-method in order for it to be accepted.
     * 
     * @param <T>
     * @param type
     *            Type to parse string as.
     * @param input
     *            Input string to parse.
     * @return The string, cast to the specified type.
     */
    static <T extends Number> T parseString(final Class<T> type, final String input) {

        try {
            final Method method = type.getMethod("valueOf", String.class);
            return type.cast(method.invoke(type, input));
        } catch (final Exception e) {
            throw new IllegalArgumentException("Input String supplied cannot be parsed as [" + type
                    + "]", e);
        }

    }

    public static <T extends Object> T getFirstOrNull(final Collection<T> collection) {

        if (collection == null || collection.size() == 0) {
            return null;
        }

        return collection.iterator().next();
    }

    /**
     * Creates a comma separated list of each returned toString of the objects
     * in the list
     * 
     * @param listToSerialize
     * @return a string of commaseperated strings
     */
    public static <T> String serializeListToCSVString(final List<T> listToSerialize) {

        StringBuffer stringBuffer = new StringBuffer();
        Iterator<T> iterator = listToSerialize.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            stringBuffer.append(element.toString());
            ifItHasMoreElementsAddComma(stringBuffer, iterator);
        }

        return stringBuffer.toString();
    }

    private static <T> void ifItHasMoreElementsAddComma(final StringBuffer stringBuffer,
            final Iterator<T> iterator) {

        final boolean itHasNextElement = iterator.hasNext();
        if (itHasNextElement) {
            stringBuffer.append(",");
        }
    }

    /**
     * A method that ensures that the input list is not null when returned
     * 
     * @param final inputList
     * @return an empty list when list is null, otherwise return the input list
     */
    public static <T> List<T> getNullSafeList(final List<T> inputList) {

        List<T> outPutList = inputList == null ? new ArrayList<T>() : inputList;
        return outPutList;
    }

    public static Long sumLongs(final Collection<Long> collectionToSummarize) {

        if (collectionToSummarize == null) {
            return 0L;
        }

        Long sum = 0L;
        for (Long value : collectionToSummarize) {

            sum += value;
        }

        return sum;
    }

    public static Long sumNumbersToLong(final Collection<Number> collectionToSummarize) {

        if (collectionToSummarize == null) {
            return 0L;
        }

        Long sum = 0L;
        for (Number value : collectionToSummarize) {

            sum += value.longValue();
        }

        return sum;
    }

    public static Integer sumNumbersToInteger(final Collection<Number> collectionToSummarize) {

        if (collectionToSummarize == null) {
            return 0;
        }

        Integer sum = 0;
        for (Number value : collectionToSummarize) {

            sum += value.intValue();
        }

        return sum;
    }

    public static Integer sumIntegers(final Collection<Integer> collectionToSummarize) {

        if (collectionToSummarize == null) {
            return 0;
        }

        Integer sum = 0;
        for (Integer value : collectionToSummarize) {

            sum += value;
        }

        return sum;
    }

}
