package adeuni.group.ec.algorithm.algorithms.ea;

import adeuni.group.ec.algorithm.algorithms.AbstractAlgorithm;
import adeuni.group.ec.algorithm.algorithms.stateandanalysis.AlgorithmState;
import adeuni.group.ec.algorithm.component.evaluationfunction.InterfaceEvaluationFunction;
import adeuni.group.ec.algorithm.component.operator.OperatorException;
import adeuni.group.ec.algorithm.component.operator.variation.InterfaceVariationOperator;
import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.solution.Solution;
import adeuni.group.ec.algorithm.component.solution.SolutionSpace;
import adeuni.group.ec.algorithm.configuration.Configuration;
import adeuni.group.ec.algorithm.utility.factory.RepresentationFactory;
import adeuni.group.ec.algorithm.utility.roulette.operator.VariationRoulette;
import adeuni.group.ec.algorithm.utility.roulette.selection.SelectionRoulette;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qianminming on 16/08/15.
 * Modified by feifeixiong on 21/08/15.
 *
 * The main body of Evolutionary Algorithm
 *
 */
public class EvolutionaryAlgorithm<T extends InterfaceRepresentation> extends AbstractAlgorithm {

    private static final long serialVersionUID = 1672124688695016729L;

    /**
     * Construct the evolutionary algorithm
     * @param configuration
     */
    public EvolutionaryAlgorithm(Configuration configuration) {
        super(configuration);
    }

    /**
     * Init solution space(population)
     * @return solutionSpace
     */
    public SolutionSpace initSolutionSpace() {
        int solutionSpaceSize = configuration.getSolutionSpaceSize();
        RepresentationFactory<T> representationFactory = configuration.getRepresentationFactory();
        SolutionSpace<T> solutionSpace = new SolutionSpace<>();

        //init the population(solution space)
        for (int i = 0; i < solutionSpaceSize; i++) {
            solutionSpace.add(new Solution(representationFactory.createRepresentation()));
        }

        return solutionSpace;
    }

    /**
     * Produce the new generation
     * @param algorithmState
     * @return newGeneration
     * @throws Exception
     */
    public SolutionSpace iteration(AlgorithmState algorithmState) throws Exception {
        int offspringSize;
        int eliteSize;
        int survivorSize;

        SolutionSpace<T> newGeneration = new SolutionSpace<>();
        SolutionSpace<T> currentSolutionSpace = algorithmState.getCurrentSolutionSpace();
        InterfaceEvaluationFunction<T> evaluationFunction = configuration.getEvaluationFunction();
        List<Solution<T>> eliteList;
        List<Solution<T>> offspringList;


        //choose the elite, the elite is not to be chosen
        eliteSize = configuration.getEliteSize();
        eliteList = elitism(eliteSize, currentSolutionSpace);
        eliteList.forEach(newGeneration::add);

        //reproduce offspring here
        offspringSize = configuration.getOffspringSize();
        offspringList = reproduce(offspringSize, currentSolutionSpace);

        //compete to get survivors
        survivorSize = configuration.getSurvivorSize();
        SolutionSpace<T> competeSolutionSpace = new SolutionSpace();
        if (configuration.isParentAllowToSurvive()) {
            currentSolutionSpace.remove(eliteList);
            competeSolutionSpace.add(currentSolutionSpace.getSolutionList());
        }
        competeSolutionSpace.add(offspringList);
        compete(survivorSize, competeSolutionSpace).forEach(newGeneration::add);


        //migrate new population to help increase the varify of the population
        migrate().forEach(newGeneration::add);

        //evaluate new generation
        evaluationFunction.evaluateSolutionSpace(newGeneration);

        return newGeneration;
    }

    /**
     * Get the highest sublist of current solution space
     * @param eliteSize
     * @param currentSolutionSpace
     * @return highest sublist of current solution space
     */
    private List<Solution<T>> elitism(int eliteSize, SolutionSpace<T> currentSolutionSpace) {
        return currentSolutionSpace.getHighestSubList(eliteSize);
    }

    /**
     * Select the assigned size of survivors in the parent solution space. we call this compete because it is a competetion between all parents
     * @param survivorSize
     * @param competeSolutionSpace
     */
    protected List<Solution<T>> compete(int survivorSize, SolutionSpace<T> competeSolutionSpace) {
        SelectionRoulette survivorSelectionRoulette = configuration.getSurvivorSelectionRoulette();

        return survivorSelectionRoulette.selectOperator().selection(survivorSize, competeSolutionSpace);
    }


    /**
     * Re-produce the solution space(population)
     * @param offspringSize
     * @param currentSolutionSpace
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws OperatorException
     */
    protected List<Solution<T>> reproduce(int offspringSize, SolutionSpace<T> currentSolutionSpace) throws Exception {
        int currentOffspingNumber;
        int inputSolutionNumber;

        InterfaceVariationOperator variationOperator;
        InterfaceEvaluationFunction<T> evaluationFunction = configuration.getEvaluationFunction();

        VariationRoulette variationRoulette = configuration.getVariationRoulette();
        SelectionRoulette parentSelectionRoulette = configuration.getParentSelectionRoulette();

        List<Solution<T>> candidateList;
        List<Solution<T>> variationList;
        List<Solution<T>> offspringList = new ArrayList<>();

        //offspring until meet the requirement offspring size
        currentOffspingNumber = 0;
        while (currentOffspingNumber < offspringSize) {
            variationOperator = variationRoulette.selectOperator();

            inputSolutionNumber = variationOperator.getInputSolutionNumber();

            //solution list selected for variation
            candidateList = parentSelectionRoulette.selectOperator().selection(inputSolutionNumber, currentSolutionSpace);

            //solution list after variation, these are newly created solutions, should evaluate these functions to set the fitnees
            variationList = variationOperator.apply(candidateList);
            variationList.forEach(evaluationFunction::evaluateSolution);

            //System.out.println(variationOperator.getClass().toString()+" after apply");

            //added to the total list, gather all candidates(parents), gather all variations
            variationList.forEach(offspringList::add);
            currentOffspingNumber += variationOperator.getOutputSolutionNumber();
        }

        //select the offspring according to the set selection
        return offspringList;
    }


    /**
     * Produce the list of immigrate
     * @return list of immigrate
     */
    public List<Solution<T>> migrate() {
        RepresentationFactory<T> representationFactory = configuration.getRepresentationFactory();
        List<Solution<T>> immigrateList = new ArrayList<>();
        for (int i = 0; i < configuration.getImmigrateSize(); i++) {
            immigrateList.add(new Solution(representationFactory.createRepresentation()));
        }

        return immigrateList;
    }
}
