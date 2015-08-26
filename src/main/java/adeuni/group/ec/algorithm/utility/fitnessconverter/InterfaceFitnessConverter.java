package adeuni.group.ec.algorithm.utility.fitnessconverter;

import java.io.Serializable;

/**
 * Created by qianminming on 21/08/15.
 */
public interface InterfaceFitnessConverter  {
    /**
     * Convert the original value to fitness value, to make sure fitness is the bigger the better.
     * @param original
     * @return
     */
    double encode(double original);

    /**
     * To show the best original information, need to decode back
     * @param fitness
     * @return
     */
    double decode(double fitness);
}
