package com.achraf.katalog.StringCalculatorKata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Achraf M. on 16/05/2021.
 */
public class StringCalculatorKata {

    private static final String REGEX_WITH_DELIMITER = "//(.*)\n(.*)";

    // Default regex with comma delimiter
    private String pattern = ",[ ]*|\n";

    /**
     * A simple add function that returns the sum of numbers
     *
     * @param numbers a string input containing numbers separated by comma
     * @return resulted integer
     */
    public int add(String numbers) {

        // If we receive a null or empty string, we give back 0
        if (numbers == null || numbers.trim().isEmpty()) {
            return 0;
        }

        // If we match the regex as stated in the spec, then the delimiter we are looking for is the third char
        if(numbers.matches(REGEX_WITH_DELIMITER)){
            pattern = numbers.charAt(2)+"[ ]*|\n";
            numbers = numbers.substring(4);
        }

        // Split the input by a specific delimiter, allow new lines and ignore spaces
        String[] inputNumbers = numbers.split(pattern);

        // Get negative numbers from input
        List<String> negativeNumbers = getNegativeNumbers(inputNumbers);

        // Throw an IllegalArgumentException if negatives are passed
        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " + String.join(",", negativeNumbers));
        }

        // If we receive only one number, we return It
        if (inputNumbers.length == 1)
            return parseMyInt(inputNumbers[0]);

        // return the sum of the two numbers
        return Arrays.stream(inputNumbers).mapToInt(this::parseMyInt).sum();
    }

    /**
     * Parses the string number to int & handles a non-number case
     *
     * @param number a string input
     * @return parsed integer
     */
    private int parseMyInt(String number) {
        try {
            return Integer.parseInt(number);
        }
        catch (NumberFormatException e)
        {
            return 0;
        }
    }

    /**
     * Returns a list of negative numbers
     *
     * @param numbers array of numbers in string format
     * @return List of negative numbers
     */
    private List<String> getNegativeNumbers(String[] numbers) {
         return Arrays.stream(numbers)
                .filter(s -> s.contains("-"))
                .collect(Collectors.toList());
    }
}
