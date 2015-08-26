package adeuni.group.ec.algorithm.component.termination;

import adeuni.group.ec.algorithm.algorithms.stateandanalysis.AlgorithmState;
import adeuni.group.ec.algorithm.algorithms.InterfaceAlgorithm;

import java.io.Serializable;

/**
 * Created by qianminming on 16/08/15.
 */
public interface InterfaceTerminationCriterion<A extends InterfaceAlgorithm> extends Serializable{
    /**
     * Check if the termination criterion is met
     * @param algorithm
     * @param algorithmState
     * @return
     */
    boolean meet(A algorithm, AlgorithmState algorithmState);
}
