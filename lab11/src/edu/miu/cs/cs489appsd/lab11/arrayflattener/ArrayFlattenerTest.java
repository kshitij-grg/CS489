package edu.miu.cs.cs489appsd.lab11.arrayflattener;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

public class ArrayFlattenerTest {

    @Test
    public void testFlattenArrayWithValidNestedArray() {
        int[][] input = {{1, 3}, {0}, {4, 5, 9}};
        int[] expected = {1, 3, 0, 4, 5, 9};

        assertArrayEquals(expected, ArrayFlattener.flattenArray(input));
    }

    @Test
    public void testFlattenArrayWithNullInput() {
        assertNull(ArrayFlattener.flattenArray(null));
    }
}
