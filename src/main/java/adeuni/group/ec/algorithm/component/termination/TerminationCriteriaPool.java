package adeuni.group.ec.algorithm.component.termination;

import adeuni.group.ec.algorithm.algorithms.stateandanalysis.AlgorithmState;
import adeuni.group.ec.algorithm.algorithms.InterfaceAlgorithm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qianminming on 16/08/15.
 */
public class TerminationCriteriaPool<A extends InterfaceAlgorithm> {
    // the criterion element of the criteria pool
    List<InterfaceTerminationCriterion> elementCriterion;

    /**
     * The constrution function
     */
    public TerminationCriteriaPool() {
        elementCriterion = new ArrayList<>();
    }

    /**
     * Add criterion to the criteria pool
     * @param criterion
     */
    public void add(InterfaceTerminationCriterion criterion) {
        elementCriterion.add(criterion);
    }

    /**
     * Iterate all criteria in the pool, if anyone meet, return ture, finish the execution.
     *
     * @param algorithm
     * @param algorithmState
     * @return true: if any of the criteria meet, false: no one meet.
     */
    public boolean meetAnyone(A algorithm, AlgorithmState algorithmState) {
        for (InterfaceTerminationCriterion criterion: elementCriterion) {
            if (criterion.meet(algorithm, algorithmState)) {
                return true;
            }
        }

        return false;
    }
}
