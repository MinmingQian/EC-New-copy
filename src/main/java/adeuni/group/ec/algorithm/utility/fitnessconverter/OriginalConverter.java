package adeuni.group.ec.algorithm.utility.fitnessconverter;

/**
 * Created by qianminming on 24/08/15.
 */
public class OriginalConverter implements  InterfaceFitnessConverter {

    /**
     * Convert the original value to fitness value, to make sure fitness is the bigger the better.
     *
     * @param original
     * @return
     */
    @Override
    public double encode(double original) {
        return original;
    }

    /**
     * To show the best original information, need to decode back
     *
     * @param fitness
     * @return
     */
    @Override
    public double decode(double fitness) {
        return fitness;
    }
}
