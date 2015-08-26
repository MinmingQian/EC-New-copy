package adeuni.group.ec.algorithm.utility.randomnumbergenerator;

import java.util.Random;

/**
 * Created by qianminming on 17/08/15.
 * The random generator for all the random numbers in the algorithm
 */
public class ECRandom {

    /**
     * The random number generator holder
     */
    private static final class RandomNumberGeneratorHolder {
        static final Random randomNumberGenerator = new Random();
    }

    /**
     * Generate next int
     * @return
     */
    public static int nextInt() {
        return RandomNumberGeneratorHolder.randomNumberGenerator.nextInt();
    }

    /**
     * Generate next gaussian number
     * @return
     */
    public static double nextGaussian() {
        return RandomNumberGeneratorHolder.randomNumberGenerator.nextGaussian();
    }

    /**
     * Generate next double
     * @return
     */
    public static double nextDouble() {
        return RandomNumberGeneratorHolder.randomNumberGenerator.nextDouble();
    }
}
