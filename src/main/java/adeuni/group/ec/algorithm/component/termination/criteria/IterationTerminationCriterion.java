package adeuni.group.ec.algorithm.component.termination.criteria;

import adeuni.group.ec.algorithm.algorithms.stateandanalysis.AlgorithmState;
import adeuni.group.ec.algorithm.algorithms.InterfaceAlgorithm;
import adeuni.group.ec.algorithm.component.termination.InterfaceTerminationCriterion;

/**
 * Created by qianminming on 16/08/15.
 * The iteration termination criterion
 */
public class IterationTerminationCriterion<A extends InterfaceAlgorithm> implements InterfaceTerminationCriterion<A> {


    // the max iteration round
    protected int maxIterationRound;

    /**
     * The construction function
     * @param maxIterationRound
     */
    public IterationTerminationCriterion(int maxIterationRound) {
        this.maxIterationRound = maxIterationRound;
    }

    /**
     * Check if the termination criterion is met
     * @param algorithm
     * @param algorithmState
     * @return
     */
    @Override
    public boolean meet(A algorithm, AlgorithmState algorithmState) {
        return algorithmState.getCurrentIterationNumber() > maxIterationRound + 1;
    }
}
