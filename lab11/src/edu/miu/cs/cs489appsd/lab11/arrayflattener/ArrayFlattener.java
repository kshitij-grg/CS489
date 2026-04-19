package edu.miu.cs.cs489appsd.lab11.arrayflattener;

import java.util.Arrays;

public class ArrayFlattener {

    public static int[] flattenArray(int[][] nestedArray) {
        if (nestedArray == null) {
            return null;
        }

        int totalSize = 0;
        for (int[] innerArray : nestedArray) {
            if (innerArray != null) {
                totalSize += innerArray.length;
            }
        }

        int[] flattenedArray = new int[totalSize];
        int index = 0;
        for (int[] innerArray : nestedArray) {
            if (innerArray == null) {
                continue;
            }

            for (int value : innerArray) {
                flattenedArray[index++] = value;
            }
        }

        return flattenedArray;
    }

    public static void main(String[] args) {
        int[][] nestedArray = {{1, 3}, {0}, {4, 5, 9}};
        System.out.println(Arrays.toString(flattenArray(nestedArray)));
    }
}
