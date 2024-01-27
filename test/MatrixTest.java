/**
 *  File: MatrixTester.java
 *  Author: Alex Brodsky
 *  Date: October 1, 2019
 *  Purpose: CSCI 2134, Lab 4
 *
 *  Description: This class implements Matrix objects for manipulating matrices.
 */

/*  FIXLIST: Missing Test Cases based on specification requirements
    0. Passing bad indices to getElem() not tested.  getElem should generate an exception in this case.
    1.
    2.
    3.
    4.
    5.
    6.
    7.
    8.
    9.
    10.
    ... Add as many as necessary
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;

class MatrixTest {
    private final static String simpleMatrix = "2 2 1 2 3 4"; // [ 1 2 ]
    // [ 3 4 ]
    private final static String nonSqMatrix = "3 2 1 2 3 4 5 6"; // [ 1 2 ]
    // [ 3 4 ]

    @Test
    void getElem() {
        Matrix m = new Matrix(new Scanner(simpleMatrix));
        assertEquals(2, m.getElem(1,2), "getElem() did not return correct value");
    }

    @Test
    // Example for item 0 in the FIX LIST above
    void getElem_BadIndex() {
        Matrix m = new Matrix(new Scanner(simpleMatrix));
        try {
            m.getElem(1, 0);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    void setElem() {
        Matrix m = new Matrix(new Scanner(simpleMatrix));
        m.setElem(2, 1, 5);
        assertEquals(5, m.getElem(2,1), "setElem() may not have set correct value");
    }

    @Test
    void negate() {
        Matrix m = new Matrix(new Scanner(simpleMatrix));
        m.negate();
        assertEquals(-1, m.getElem(1,1), "negate() did not negate element (1,1)");
        assertEquals(-2, m.getElem(1,2), "negate() did not negate element (1,2)");
        assertEquals(-3, m.getElem(2,1), "negate() did not negate element (2,1)");
        assertEquals(-4, m.getElem(2,2), "negate() did not negate element (2,2)");
    }

    @Test
    void add() {
        try {
            Matrix m = new Matrix(new Scanner(simpleMatrix));
            Matrix n = new Matrix(new Scanner(simpleMatrix));
            Matrix res = new Matrix(2, 2);
            n.negate();
            m.add(n, res);
            assertEquals(0, res.getElem(1, 1), "negate() did not add element (1,1))");
            assertEquals(0, res.getElem(1, 2), "negate() did not add element (1,2)");
            assertEquals(0, res.getElem(2, 1), "negate() did not add element (2,1)");
            assertEquals(0, res.getElem(2, 2), "negate() did not add element (2,2)");
        } catch (Matrix.DimensionMismatchException e) {
            fail("Exception occurred where none should have " + e.getMessage());
        } catch (Matrix.NullMatrixException e) {
            fail("Exception occurred where none should have " + e.getMessage());
        }
    }

    @Test
    void equals() {
        Matrix m = new Matrix(new Scanner(simpleMatrix));
        Matrix n = new Matrix(m);
        assertTrue(m.equals(n), "equals() has a false negative");
    }

    @Test
    void equal2s() {
        Matrix m = new Matrix(new Scanner(simpleMatrix));
        Matrix n = new Matrix(m);
        n.negate();
        assertFalse(m.equals(n), "equals() has a false positive");
    }

    @Test
    void multiplyWithScalar() {
        try {
            Matrix m = new Matrix(new Scanner(simpleMatrix));
            Matrix n = new Matrix(m);
            Matrix res = new Matrix(m);
            n.negate();
            m.multiplyWithScalar(-1, res);
            assertTrue(res.equals(n), "matrix not scaled by -1");
        } catch (Matrix.DimensionMismatchException e) {
            fail("Exception occurred where none should have " + e.getMessage());
        } catch (Matrix.NullMatrixException e) {
            fail("Exception occurred where none should have " + e.getMessage());
        }
    }

    @Test
    void multiplyWithMatrix() {
        try {
            Matrix m = new Matrix(new Scanner(simpleMatrix));
            Matrix n = new Matrix(m);
            Matrix res = new Matrix(m);
            n.negate();
            m.multiplyWithScalar(-1, res);
            assertTrue(res.equals(n), "matrix not scaled by -1");
        } catch (Matrix.DimensionMismatchException e) {
            fail("Exception occurred where none should have " + e.getMessage());
        } catch (Matrix.NullMatrixException e) {
            fail("Exception occurred where none should have " + e.getMessage());
        }
    }

    @Test
    void getHeight() {
        Matrix m = new Matrix(new Scanner(nonSqMatrix));
        assertEquals(3, m.getHeight(), "getHeight() did not return correct height");
    }

    @Test
    void getWidth() {
        Matrix m = new Matrix(new Scanner(nonSqMatrix));
        assertEquals(2, m.getWidth(), "getWidth() did not return correct width");
    }
}
