package com.achraf.katalog.StringCalculatorKata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorKataTest {

    private static StringCalculatorKata stringCalculatorKata;

    @BeforeEach
    public void initEach(){
        stringCalculatorKata = new StringCalculatorKata();
    }

    @Test
    @DisplayName("Step 1 - Should add two numbers, separated by commas")
    public void step1(){
        assertEquals(0, stringCalculatorKata.add(null));
        assertEquals(0, stringCalculatorKata.add(""));
        assertEquals(1, stringCalculatorKata.add("1"));
        assertEquals(3, stringCalculatorKata.add("1,2"));
    }

    @Test
    @DisplayName("Step 2 - Should support unknown amount of numbers")
    public void step2(){
        assertEquals(15, stringCalculatorKata.add("1,2,3,4,5"));
    }

    @Test
    @DisplayName("Step 3 - Should support new lines")
    public void step3(){
        assertEquals(6, stringCalculatorKata.add("1\n2,3"));
    }

    @Test
    @DisplayName("Step 4 - Should support a new special delimiter which is ';'")
    public void step4(){
        assertEquals(12, stringCalculatorKata.add("//;\n4;6;2"));
    }


    @Test
    @DisplayName("Step 5 - Should throw an IllegalArgumentException when one or multiple number(s) is(are) negative(s)")
    public void step5(){
        assertThrows(IllegalArgumentException.class, () -> stringCalculatorKata.add("1,-2,3"), "negatives not allowed: -2");
        assertThrows(IllegalArgumentException.class, () -> stringCalculatorKata.add("1,-2,-3"), "negatives not allowed: -2,-3");
    }
}