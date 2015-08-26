package adeuni.group.ec.algorithm.algorithms.stateandanalysis;

import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qianminming on 22/08/15.
 */
public class AlgorithmStateQueue<T extends InterfaceRepresentation> {
    /**
     * the size of the algorithm stateandanalysis queue
     */
    protected int queueSize;

    /**
     * the queue list to save the algorithm states
     */
    protected List<AlgorithmState<T>> algorithmStateList;

    /**
     * Construction function of the queue
     * @param queueSize
     */
    public AlgorithmStateQueue(int queueSize) {
        this.queueSize = queueSize;
        algorithmStateList = new ArrayList<>();
    }

    /**
     *
     * @param algorithmState
     */
    public void update(AlgorithmState algorithmState) {
        if (algorithmStateList.size() == queueSize) {
            algorithmStateList.remove(0);
        }
        algorithmStateList.add(new AlgorithmState<T>(algorithmState));
    }

    /**
     * get the algorithm stateandanalysis according to the index
     * @param index
     * @return
     */
    public AlgorithmState<T> get(int index) {
        return algorithmStateList.get(index);
    }


    /**
     * get the latest element of the algorithm queue
     * @return the latest element
     */
    public AlgorithmState<T> getLatest() {
        return algorithmStateList.get(algorithmStateList.size()-1);
    }
}
