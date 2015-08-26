package adeuni.group.ec.algorithm.algorithms;

import adeuni.group.ec.algorithm.algorithms.stateandanalysis.AlgorithmState;
import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.solution.SolutionSpace;
import adeuni.group.ec.algorithm.configuration.Configuration;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by qianminming on 16/08/15.
 *odified by Feifei Xiong on 21/08/15
 *
 * Interface algorithm
 */
public interface InterfaceAlgorithm<T extends InterfaceRepresentation> {
    /**
     * Init the algorithm
     *
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    void init() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * Init the solution space (population)
     *
     * @return the initialized solution space(population)
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    SolutionSpace<T> initSolutionSpace() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;


    /**
     * Strat point of the algorithm
     * @return
     * @throws Exception
     */
    AlgorithmState run() throws Exception;


    /**
     * the execute function
     * @throws Exception
     */
    void execute() throws Exception;

    /**
     * The main iteration
     * @param algorithmState
     * @return
     * @throws Exception
     */
    SolutionSpace<T> iteration(AlgorithmState<T> algorithmState) throws Exception;


    /**
     * Get configuration
     * @return
     */
    Configuration getConfiguration();

    /**
     * Get algorithm state
     * @return
     */
    AlgorithmState<T> getAlgorithmState();
}
