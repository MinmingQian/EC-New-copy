package adeuni.group.ec.algorithm.configuration;

import adeuni.group.ec.algorithm.algorithms.InterfaceAlgorithm;
import adeuni.group.ec.algorithm.component.evaluationfunction.InterfaceEvaluationFunction;
import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.termination.TerminationCriteriaPool;
import adeuni.group.ec.algorithm.utility.factory.RepresentationFactory;
import adeuni.group.ec.algorithm.utility.fitnessconverter.InterfaceFitnessConverter;
import adeuni.group.ec.algorithm.utility.logger.Logger;
import adeuni.group.ec.algorithm.utility.roulette.operator.VariationRoulette;
import adeuni.group.ec.algorithm.utility.roulette.selection.SelectionRoulette;

/**
 * Created by qianminming on 14/08/15.
 * The configuration class used by the algorithm
 */
public class Configuration<T extends InterfaceRepresentation> {

    // the termination criteria pool
    protected TerminationCriteriaPool<? extends InterfaceAlgorithm> terminationCriteriaPool;

    // evaluation function
    protected InterfaceEvaluationFunction<T> evaluationFunction;

    // variation roulette
    private VariationRoulette variationRoulette;
    // parent selection routlette
    private SelectionRoulette parentSelectionRoulette;
    // survivor selection roulette
    private SelectionRoulette survivorSelectionRoulette;

    // elite size
    private int eliteSize;
    // offspring size
    private int offspringSize;
    // immigrate size
    private int immigrateSize;
    // survivor size
    private int survivorSize;
    // solution space size
    private int solutionSpaceSize; //population size

    // parent allow to survive
    public boolean parentAllowToSurvive;

    // the represent factory
    private RepresentationFactory<T> representationFactory;

    // the algorithm
    private InterfaceAlgorithm<InterfaceRepresentation> algorithm;
    // the fitness converter
    private InterfaceFitnessConverter fitnessConverter;
    // the fitness name
    private String fileName;
    private Logger logger;
    private String configurationFileNumber;

    /**
     * Set parent allow to survive
     * @param parentAllowToSurvive
     */
    public void setParentAllowToSurvive(boolean parentAllowToSurvive) {
        this.parentAllowToSurvive = parentAllowToSurvive;
    }

    /**
     * Check if is parent allow to survive
     * @return
     */
    public boolean isParentAllowToSurvive() {
        return parentAllowToSurvive;
    }

    /**
     * Get the termination criteria pool
     * @return
     */
    public  TerminationCriteriaPool getTerminationCriteriaPool() {
        return terminationCriteriaPool;
    }

    /**
     * Set termination criteria pool
     *
     * @param terminationCriteriaPool
     */
    public void setTerminationCriteriaPool(TerminationCriteriaPool<? extends InterfaceAlgorithm> terminationCriteriaPool) {
        this.terminationCriteriaPool = terminationCriteriaPool;
    }

    /**
     * Get evaluation function
     *
     * @return
     */
    public  InterfaceEvaluationFunction<T> getEvaluationFunction() {
        return evaluationFunction;
    }

    /**
     * Set evaluation function
     * @param evaluationFunction
     */
    public void setEvaluationFunction(InterfaceEvaluationFunction<T> evaluationFunction) {
        this.evaluationFunction = evaluationFunction;
    }

    /**
     * Get variation roulette
     * @return
     */
    public VariationRoulette getVariationRoulette() {
        return variationRoulette;
    }

    /**
     * Set variation roulette
     * @param variationRoulette
     */
    public void setVariationRoulette(VariationRoulette variationRoulette) {
        this.variationRoulette = variationRoulette;
    }

    /**
     * Get parent selection roulette
     * @return
     */
    public SelectionRoulette getParentSelectionRoulette() {
        return parentSelectionRoulette;
    }

    /**
     * Set parent selection roulette
     * @param parentSelectionRoulette
     */
    public void setParentSelectionRoulette(SelectionRoulette parentSelectionRoulette) {
        this.parentSelectionRoulette = parentSelectionRoulette;
    }

    /**
     * Get offspring size
     * @return
     */
    public int getOffspringSize() {
        return offspringSize;
    }

    /**
     * Set offspring size
     * @param offspringSize
     */
    public void setOffspringSize(int offspringSize) {
        this.offspringSize = offspringSize;
    }

    /**
     * Get elite size
     * @return
     */
    public int getEliteSize() {
        return eliteSize;
    }

    /**
     * Set elite size
     * @param eliteSize
     */
    public void setEliteSize(int eliteSize) {
        this.eliteSize = eliteSize;
    }

    /**
     * Get representation factory
     * @return
     */
    public RepresentationFactory<T> getRepresentationFactory() {
        return representationFactory;
    }

    /**
     * Set representation facotry
     * @param representationFactory
     */
    public void setRepresentationFactory(RepresentationFactory<T> representationFactory) {
        this.representationFactory = representationFactory;
    }

    /**
     * Get solution space size
     * @return
     */
    public int getSolutionSpaceSize() {
        return solutionSpaceSize;
    }

    /**
     * Set solution space size
     * @param solutionSpaceSize
     */
    public void setSolutionSpaceSize(int solutionSpaceSize) {
        this.solutionSpaceSize = solutionSpaceSize;
    }

    /**
     * Get immigrate size
     * @return
     */
    public int getImmigrateSize() {
        return immigrateSize;
    }

    /**
     * Set immigrate size
     * @param immigrateSize
     */
    public void setImmigrateSize(int immigrateSize) {
        this.immigrateSize = immigrateSize;
    }

    /**
     * Set survivor size
     * @param survivorSize
     */
    public void setSurvivorSize(int survivorSize) {
        this.survivorSize = survivorSize;
    }

    /**
     * Get survivior size
     * @return
     */
    public int getSurvivorSize() {
        return survivorSize;
    }

    /**
     * Set survivor selection roulette
     * @param survivorSelectionRoulette
     */
    public void setSurvivorSelectionRoulette(SelectionRoulette survivorSelectionRoulette) {
        this.survivorSelectionRoulette = survivorSelectionRoulette;
    }

    /**
     * Get survivor selection roulette
     * @return
     */
    public SelectionRoulette getSurvivorSelectionRoulette() {
        return survivorSelectionRoulette;
    }

    /**
     * Set algorithm
     * @param algorithm
     */
    public void setAlgorithm(InterfaceAlgorithm<InterfaceRepresentation> algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Get algorithm
     * @return
     */
    public InterfaceAlgorithm<InterfaceRepresentation> getAlgorithm() {
        return algorithm;
    }

    /**
     * Set fitness converter
     * @param fitnessConverter
     */
    public void setFitnessConverter(InterfaceFitnessConverter fitnessConverter) {
        this.fitnessConverter = fitnessConverter;
    }

    /**
     * Get fitnesss converter
     * @return
     */
    public InterfaceFitnessConverter getFitnessConverter() {
        return fitnessConverter;
    }

    /**
     * get filename
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set filename
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Set the logger for the system
     * @param logger
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Get the logger for the system
     * @return
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Set configuration file number
     * @param configurationFileNumber
     */
    public void setConfigurationFileNumber(String configurationFileNumber) {
        this.configurationFileNumber = configurationFileNumber;
    }

    /**
     * Get configuration file number
     * @return
     */
    public String getConfigurationFileNumber() {
        return configurationFileNumber;
    }
}

