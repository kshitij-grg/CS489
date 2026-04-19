package edu.miu.cs.cs489appsd.lab11.arrayreversor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArrayReversorTest {

    private ArrayFlattenerService arrayFlattenerService;
    private ArrayReversor arrayReversor;

    @Before
    public void setUp() {
        arrayFlattenerService = mock(ArrayFlattenerService.class);
        arrayReversor = new ArrayReversor(arrayFlattenerService);
    }

    @Test
    public void testReverseArrayWithValidNestedArray() {
        int[][] input = {{1, 3}, {0}, {4, 5, 9}};
        when(arrayFlattenerService.flattenArray(input)).thenReturn(new int[]{1, 3, 0, 4, 5, 9});

        int[] actual = arrayReversor.reverseArray(input);

        assertArrayEquals(new int[]{9, 5, 4, 0, 3, 1}, actual);
        verify(arrayFlattenerService).flattenArray(input);
    }

    @Test
    public void testReverseArrayWithNullInput() {
        when(arrayFlattenerService.flattenArray(null)).thenReturn(null);

        int[] actual = arrayReversor.reverseArray(null);

        assertNull(actual);
        verify(arrayFlattenerService).flattenArray(null);
    }
}