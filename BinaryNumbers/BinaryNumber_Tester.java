package BinaryNumbers;

import java.util.Arrays;

import BinaryNumbers.BinaryNumber;

public class BinaryNumber_Tester {

    public static void main(String[] args) {

        int failed = 0;
        boolean DEBUG = false;

        BinaryNumber b1 = new BinaryNumber(0);
        BinaryNumber b2 = new BinaryNumber("1");
        BinaryNumber b3 = new BinaryNumber("10101001");
        BinaryNumber b4 = new BinaryNumber("10100");
        BinaryNumber b5 = new BinaryNumber("11101");

        System.out.println("Testing getLength()    ...");

        if (b3.getLength() != 8) {
            ++failed;
            System.out.println("Expected: " + 4 + "   Got: " + b3.getLength());
        }

        System.out.println("Testing getInnerArray()    ...");

        if (!Arrays.equals(b4.getInnerArray(), new int[] { 1, 0, 1, 0, 0 })) {
            ++failed;
            System.out.println("Expected: { 1, 0, 1, 0, 0 }" + "   Got: " + Arrays.toString(b4.getInnerArray()));
        }

        System.out.println("Testing toDecimal()    ...");

        if (b3.toDecimal() != 169) {
            ++failed;
            System.out.println("Expected: " + 169 + "   Got: " + b3.toDecimal());
        }

        System.out.println("Testing toString()    ...");

        if (!b4.toString().equals("10100")) {
            ++failed;
            System.out.println("Expected: " + "10100" + "   Got: " + b4.toString());
        }
        
        System.out.println("Testing addition    ...");
        for (int i = 0; i != 32; ++i) {
            b1.add(b2);
            if (b1.toDecimal() != i + 1) {
                System.out.println("Expected: " + i + "   Got: " + b1.toDecimal());
                ++failed;
            }
            if (DEBUG)
                System.out.println("" + b1.toDecimal() + ": " + b1.toString());
        }

        if (b1.getLength() != 6) {
            ++failed;
            System.out.println("Expected length 6,   Got: " + b1.getLength());
        }


   
        System.out.println("Testing bit-shift   ...");
     

        b1 = new BinaryNumber("1");

        int n = 5;

        for (int i = 0; i != n; ++i) {
            b1.bitShift(-1, 1);
            if (b1.toDecimal() != (2 << i)) {
                System.out.println("Expected: " + (2 << i) + "   Got: " + b1.toDecimal());
                ++failed;
            }
            if (DEBUG)
                System.out.println(b1.toDecimal() + ": " + b1.toString());
        }

        if (b1.getLength() != 6) {
            ++failed;
            System.out.println("Expected length 6,   Got: " + b1.getLength());
        }

        for (int i = 0; i != n; ++i) {
            b1.bitShift(1, 1);
            if (b1.toDecimal() != (2 << n) >> (i + 2)) {
                System.out.println("Expected: " + ((2 << n) >> (i + 2)) + "   Got: " + b1.toDecimal());
                ++failed;
            }
            if (DEBUG)
                System.out.println(b1.toDecimal() + ": " + b1.toString());
        }

        System.out.println("Testing bitwise and   ...");

        int[] t1 = BinaryNumber.bwand(b4, b5);
        int[] a1 = { 1, 0, 1, 0, 0 };

        if (!Arrays.equals(a1, t1)) {
            ++failed;
            System.out.println("Expected: " + Arrays.toString(a1) + "   Got: " + Arrays.toString(t1));
        }

        System.out.println("Testing bitwise or   ...");

        int[] t2 = BinaryNumber.bwor(b4, b5);
        int[] a2 = { 1, 1, 1, 0, 1 };

        if (!Arrays.equals(a2, t2)) {
            ++failed;
            System.out.println("Expected: " + Arrays.toString(a2) + "   Got: " + Arrays.toString(t2));
        }

      

        System.out.println("------------------------------");
        System.out.println("Passed " + (57 - failed) + "/57 tests.");
        System.out.println("------------------------------");

    }

}