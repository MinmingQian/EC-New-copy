package adeuni.group.ec.algorithm.algorithms;

import adeuni.group.ec.algorithm.algorithms.stateandanalysis.AlgorithmState;
import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.solution.SolutionSpace;
import adeuni.group.ec.algorithm.component.termination.TerminationCriteriaPool;
import adeuni.group.ec.algorithm.configuration.Configuration;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by qianminming on 16/08/15.
 * Modified by Feifei Xiong on 21/08/15
 *
 *The abstract algorithm
 */
public abstract class AbstractAlgorithm<T extends InterfaceRepresentation> implements InterfaceAlgorithm {

    private static final long serialVersionUID = 1148679300841432321L;
    protected  Configuration configuration;
    protected AlgorithmState algorithmState;

    /**
     * Consatrthe abstract algorithm
     * @param configuration
     */
    public   AbstractAlgorithm (Configuration configuration) {
        this.configuration = configuration;
        algorithmState = new AlgorithmState(this);
    }

    /**
     * The start point of the algorithm
     * @return the result of the algorithm
     * @throws Exception
     */
    public AlgorithmState run() throws Exception {
        init();

        execute();

        return algorithmState;
    }

    /**
     * Init the algorithm
     *
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void init() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        //init at the very beginning of the evalutionary update
        //here also init the algorithm result
        algorithmState.init();

        //the initSolutionSpace will implemented by
        SolutionSpace<T> initialSolutionSpace = initSolutionSpace();

        //evaluate the initial evaluation
        configuration.getEvaluationFunction().evaluateSolutionSpace(initialSolutionSpace);

        //set up the algorithm stateandanalysis,
        algorithmState.update(initialSolutionSpace);
    }


    /**
     * Execute the algorithm
     * @throws Exception
     */
    public void execute() throws Exception {
        TerminationCriteriaPool terminationCriteriaPool = configuration.getTerminationCriteriaPool();

        while (!terminationCriteriaPool.meetAnyone(this,algorithmState)) {
            SolutionSpace<T> newSolutionSpace = iteration(algorithmState);
            algorithmState.update(newSolutionSpace);
        }
    }

    /**
     * Get configuration
     * @return configuration
     */
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Get algorithm stateandanalysis
     * @return algorithmState
     */
    public AlgorithmState getAlgorithmState() {
        return algorithmState;
    }
}
