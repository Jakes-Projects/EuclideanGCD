import java.util.Scanner;

/**
 * This class uses the Euclidean algorithm to find the greatest
 * common divisor of two integers and then expresses the gcd as a
 * linear combination of the two integers.
 *
 * @author Jake Alsept
 * @version 1.0
 */
public class EuclideanGCD {

    /**
     * Calculates the GCD of two user specified integers.
     *
     * @param valueA - the first user specified integer.
     * @param valueB - the second user specified integer.
     * @return - The GCD of values A and B.
     */
    public static int GCD(int valueA, int valueB) {

        int tempValue;

        while (valueB != 0) {
            tempValue = valueB;
            valueB = valueA % valueB;
            valueA = tempValue;
        }

        return valueA;
    }

    /**
     * Calculates the Bezout coefficients for two user specified integers.
     *
     * @param valueA - the first user specified integer.
     * @param valueB - the second user specified integer.
     * @return - an array of integers representing the
     *           Bezout coefficients of the user specified
     *           integers.
     */
    public static int[] BezoutCoefficients(int valueA, int valueB) {

        int[] bezout = new int[2];
        int quotient, remainder,
                tempFirstCoefficient, tempSecondCoefficient;
        int firstCoefficient = 1;
        int secondCoefficient = 0;
        int firstRemainder = 0;
        int secondRemainder = 1;

        while (valueB != 0) {
            quotient = valueA / valueB;
            remainder = valueA % valueB;
            tempFirstCoefficient =
                    firstCoefficient - quotient * secondCoefficient;
            tempSecondCoefficient =
                    firstRemainder - quotient * secondRemainder;
            valueA = valueB;
            valueB = remainder;
            firstCoefficient = secondCoefficient;
            secondCoefficient = tempFirstCoefficient;
            firstRemainder = secondRemainder;
            secondRemainder = tempSecondCoefficient;
        }

        bezout[0] = firstCoefficient;
        bezout[1] = firstRemainder;

        return bezout;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int valueA, valueB, gcd;
        int[] coefficients;
        boolean repeat = true;

        while(repeat) {
            System.out.println("Please enter the first integer value: ");
            valueA = scanner.nextInt();

            System.out.println("Please enter the second integer value: ");
            valueB = scanner.nextInt();

            gcd = GCD(valueA, valueB);
            coefficients = BezoutCoefficients(valueA, valueB);

            System.out.println("RESULT: " + gcd + " = (" + coefficients[0] +
                    ")*" + valueA + " + (" + coefficients[1] + ")*" + valueB);

            System.out.println("Would you like to compute another GCD?" +
                    " Y/N:");

            if (scanner.next().equalsIgnoreCase("n")) {
                repeat = false;
                System.out.println("Goodbye.");
            }
        }
    }
}