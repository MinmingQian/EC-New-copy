package adeuni.group.ec.algorithm.algorithms.stateandanalysis;

import adeuni.group.ec.algorithm.algorithms.InterfaceAlgorithm;
import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.solution.SolutionSpace;
import adeuni.group.ec.algorithm.configuration.Configuration;

import java.util.GregorianCalendar;

/**
 * Created by qianminming on 16/08/15.
 * Modified by Feifei xiong on 21/08/15
 *
 * algorithm state and analysis
 */
public class AlgorithmState<T extends InterfaceRepresentation> {


    /** This value is used for recording current iteration number  */
    protected int currentIterationNumber;

    /** This value is used for recording last iteration duration  */
    protected long lastIterationDuration;

    /** This value is used for recording current time  */
    protected long currentTime;

    /** This value is used for recording total iteration duration */
    protected long totalIterationDuration;

    /* this is the current solution space list*/
    protected SolutionSpace<T> currentSolutionSpace;

    // the configuration reference
    protected Configuration configuration;

    // the algorithm point
    protected InterfaceAlgorithm<T> algorithm;

    // the algorithm analysis
    protected  AlgorithmAnalysis<T> algorithmAnalysis;

    // the algorithm state queue
    protected AlgorithmStateQueue<T> algorithmStateQueue;

    /**
     * get the algorithm analysis
     * @return the algorithm analysis
     */
    public AlgorithmAnalysis<T> getAlgorithmAnalysis() {
        return algorithmAnalysis;
    }

    /**
     * set algorithm analysis
     * @param algorithmAnalysis
     */
    public void setAlgorithmAnalysis(AlgorithmAnalysis<T> algorithmAnalysis) {
        this.algorithmAnalysis = algorithmAnalysis;
    }


    /**
     * Get algorithm state queue
     * @return
     */
    public AlgorithmStateQueue<T> getAlgorithmStateQueue() {
        return algorithmStateQueue;
    }

    /**
     * Set algorithm state queue
     * @param algorithmStateQueue
     */
    public void setAlgorithmStateQueue(AlgorithmStateQueue<T> algorithmStateQueue) {
        this.algorithmStateQueue = algorithmStateQueue;
    }

    /**
     * Get the algorithm
     * @return
     */
    public InterfaceAlgorithm<T> getAlgorithm() {
        return algorithm;
    }

    /**
     * Get last iteration duration
     * @return
     */
    public long getLastIterationDuration() {
        return lastIterationDuration;
    }

    /**
     * Get current time
     * @return
     */
    public long getCurrentTime() {
        return currentTime;
    }

    /**
     * Get total iteration duration
     * @return
     */
    public long getTotalIterationDuration() {
        return totalIterationDuration;
    }


    /**
     * Get configuration
     * @return
     */
    public Configuration getConfiguration() {
        return configuration;
    }


    /**
     * Construction function
     * @param algorithm
     */
    public  AlgorithmState(InterfaceAlgorithm<T> algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Construction function
     * @param algorithmState
     */
    public AlgorithmState(AlgorithmState algorithmState) {
        lastIterationDuration = algorithmState.getLastIterationDuration();
        currentTime = algorithmState.getCurrentTime();
        totalIterationDuration = algorithmState.getTotalIterationDuration();
        currentSolutionSpace = algorithmState.getCurrentSolutionSpace();
        currentIterationNumber = algorithmState.getCurrentIterationNumber();
        configuration = algorithmState.getConfiguration();
        algorithm = algorithmState.getAlgorithm();
        algorithmStateQueue = algorithmState.getAlgorithmStateQueue();
        algorithmAnalysis = algorithmState.getAlgorithmAnalysis();
    }


    /**
     * init all the stateandanalysis information
     */
    public void init() {
        currentIterationNumber = 0;
        lastIterationDuration = 0;
        currentTime = GregorianCalendar.getInstance().getTimeInMillis();
        totalIterationDuration = 0;
        currentSolutionSpace = null;
        configuration = algorithm.getConfiguration();
        algorithmAnalysis = new AlgorithmAnalysis<>(this);
        algorithmStateQueue = new AlgorithmStateQueue<>(20);
    }



    /**
     * Get the cuurent solution space (population)
     * @returncurrent solution space (population)
     */
    public SolutionSpace<T> getCurrentSolutionSpace() {
        return currentSolutionSpace;
    }

    /**
     * Set current solution space (population)
     * @param currentSolutionSpace
     */
    public void setCurrentSolutionSpace(SolutionSpace<T> currentSolutionSpace) {
        this.currentSolutionSpace = currentSolutionSpace;
    }

    /**
     * Update solution space (population)
     * @param solutionSpace
     */
    public void update(SolutionSpace<T> solutionSpace) {
        long tempCurrentTime = GregorianCalendar.getInstance().getTimeInMillis();
        lastIterationDuration = tempCurrentTime - currentTime;
        currentTime = tempCurrentTime;
        totalIterationDuration += lastIterationDuration;
        currentSolutionSpace = solutionSpace;

        algorithmStateQueue.update(this);
        algorithmAnalysis = new AlgorithmAnalysis<>(this);
        algorithmAnalysis.analyseAndStore();

        currentIterationNumber ++;
    }

    /**
     * Get current iteration number
     * @return current iteration number
     */
    public int getCurrentIterationNumber() {
        return currentIterationNumber;
    }

}

