package com.example;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class MatrixTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void sum1() {
        double[][] matrix1 = {{1,2},{3,4}};
        double[][] matrix2 = {{4,3},{2,1}};
        double[][] result = {{5,5},{5,5}};
        assertArrayEquals(result, Matrix.sum(matrix1, matrix2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void sum2() {
        double[][] matrix1 = {{1,2},{3,4},{4,5}};
        double[][] matrix2 = {{4,3},{2,1}};
        Matrix.sum(matrix1, matrix2);
    }

    @Test
    public void subtract1() {
        double[][] matrix1 = {{1,2},{3,4}};
        double[][] matrix2 = {{4,3},{2,1}};
        double[][] result = {{-3,-1},{1,3}};
        assertArrayEquals(result, Matrix.subtract(matrix1, matrix2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void subtract2() {
        double[][] matrix1 = {{1,2},{3,4},{4,5}};
        double[][] matrix2 = {{4,3},{2,1}};
        Matrix.subtract(matrix1, matrix2);
    }

    @Test
    public void multiply1() {
        double[][] matrix1 = {{1,2},{3,4}};
        double[][] matrix2 = {{4,3},{2,1}};
        double[][] result = {{8,5},{20,13}};
        assertArrayEquals(result, Matrix.multiply(matrix1, matrix2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void multiply2() {
        double[][] matrix1 = {{1,2},{3,4},{4,5}};
        double[][] matrix2 = {{4,3},{2,1}};
        Matrix.subtract(matrix1, matrix2);
    }

    @Test
    public void isEqual1() {
        double[][] matrix1 = {{1,2},{3,4}};
        double[][] matrix2 = {{4,3},{2,1}};
        boolean result = Matrix.isEqual(matrix1, matrix2);
        assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isEqual2() {
        double[][] matrix1 = {{1,2},{3,4},{5,6}};
        double[][] matrix2 = {{4,3},{2,1}};
        Matrix.isEqual(matrix1, matrix2);
    }
    @Test
    public void fileRead1() throws IOException {
        double[][] matrix1 = {{1,2},{3,4}};
        double[][] matrix2 = Matrix.readFromFile(Paths.get("").toAbsolutePath().toString() + "/src/main/resources/input.txt");
        assertArrayEquals(matrix1, matrix2);
    }
    @Test(expected = NoSuchFileException.class)
    public void fileRead2() throws IOException {
        Matrix.readFromFile("C:\\321");
    }
    @Test
    public void print1() {
        double[][] matrix1 = {{1.0,2.0},{3.0,4.0}};
        Matrix.print(matrix1);
        assertEquals("1.0 2.0 "+System.lineSeparator()+"3.0 4.0 "+System.lineSeparator(), outContent.toString());
    }
    @Test
    public void print2() {
        double[][] matrix1 = {{2.0,2.0},{3.0,4.0}};
        Matrix.print(matrix1);
        assertNotEquals("1.0 2.0 "+System.lineSeparator()+"3.0 4.0 "+System.lineSeparator(), outContent.toString());
    }

}