package adeuni.group.ec.algorithm.component.termination.criteria;

import adeuni.group.ec.algorithm.algorithms.stateandanalysis.AlgorithmState;
import adeuni.group.ec.algorithm.algorithms.InterfaceAlgorithm;
import adeuni.group.ec.algorithm.component.termination.InterfaceTerminationCriterion;

/**
 * Created by qianminming on 19/08/15.
 * The execution time termination criterion
 */
public class ExecutionTimeTerminationCriterion<A extends InterfaceAlgorithm> implements InterfaceTerminationCriterion<A> {

    private static final long serialVersionUID = 429032151169052925L;
    // the max execution time
    protected int maxExecutionTime;

    /**
     * The construction function
     * @param maxExecutionTime
     */
    public ExecutionTimeTerminationCriterion(int maxExecutionTime) {
        this.maxExecutionTime = maxExecutionTime;
    }


    /**
     * Check if the criterion is met
     * @param algorithm
     * @param algorithmState
     * @return
     */
    @Override
    public boolean meet(A algorithm, AlgorithmState algorithmState) {
        return algorithmState.getTotalIterationDuration() > maxExecutionTime ;
    }
}
