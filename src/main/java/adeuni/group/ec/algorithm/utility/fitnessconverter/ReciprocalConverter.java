package adeuni.group.ec.algorithm.utility.fitnessconverter;

/**
 * Created by qianminming on 21/08/15.
 */
public class ReciprocalConverter implements InterfaceFitnessConverter {

    private static final long serialVersionUID = -2070771658391166032L;

    /**
     * Convert the original value to fitness value, to make sure fitness is the bigger the better.
     *
     * @param original
     * @return
     */
    @Override
    public double encode(double original) {
        return 1/original;
    }

    /**
     * To show the best original information, need to decode back
     *
     * @param fitness
     * @return
     */
    @Override
    public double decode(double fitness) {
        return 1/fitness;
    }
}
