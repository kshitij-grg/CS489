package edu.miu.cs.cs489appsd.lab11.arrayreversor;

import java.util.Arrays;

public class ArrayReversor {

    private final ArrayFlattenerService arrayFlattenerService;

    public ArrayReversor(ArrayFlattenerService arrayFlattenerService) {
        this.arrayFlattenerService = arrayFlattenerService;
    }

    public int[] reverseArray(int[][] nestedArray) {
        int[] flattenedArray = arrayFlattenerService.flattenArray(nestedArray);
        if (flattenedArray == null) {
            return null;
        }

        int[] reversedArray = Arrays.copyOf(flattenedArray, flattenedArray.length);
        for (int i = 0, j = reversedArray.length - 1; i < j; i++, j--) {
            int temp = reversedArray[i];
            reversedArray[i] = reversedArray[j];
            reversedArray[j] = temp;
        }

        return reversedArray;
    }
}