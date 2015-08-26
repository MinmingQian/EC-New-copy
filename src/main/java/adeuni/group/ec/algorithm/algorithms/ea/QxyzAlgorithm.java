package adeuni.group.ec.algorithm.algorithms.ea;

import adeuni.group.ec.algorithm.algorithms.AbstractAlgorithm;
import adeuni.group.ec.algorithm.algorithms.stateandanalysis.AlgorithmState;
import adeuni.group.ec.algorithm.component.evaluationfunction.InterfaceEvaluationFunction;
import adeuni.group.ec.algorithm.component.operator.variation.crossover.permutation.PermutationOrderCrossover;
import adeuni.group.ec.algorithm.component.operator.variation.mutation.permutation.PermutationInversionMutation;
import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.component.solution.Solution;
import adeuni.group.ec.algorithm.component.solution.SolutionSpace;
import adeuni.group.ec.algorithm.configuration.Configuration;
import adeuni.group.ec.algorithm.utility.deepcopy.DeepCopy;
import adeuni.group.ec.algorithm.utility.factory.RepresentationFactory;
import adeuni.group.ec.algorithm.utility.randomnumbergenerator.ECRandom;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Joe on 21/08/15.
 */
public class QxyzAlgorithm<T extends InterfaceRepresentation> extends AbstractAlgorithm<T>{


    private static final long serialVersionUID = 8418821980998613063L;

    public QxyzAlgorithm(Configuration configuration) {
        super(configuration);
    }

    @Override
    public SolutionSpace initSolutionSpace() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int solutionSpaceSize = configuration.getSolutionSpaceSize();
        RepresentationFactory<T> representationFactory = configuration.getRepresentationFactory();
        SolutionSpace<T> solutionSpace = new SolutionSpace<>();

        //init the population(solution space)
        for (int i = 0; i < solutionSpaceSize; i++) {
            solutionSpace.add(new Solution(representationFactory.createRepresentation()));
        }
        return solutionSpace;
    }

    @Override
    public SolutionSpace iteration(AlgorithmState algorithmState) throws Exception {
        SolutionSpace<T> currentSolutionSpace = algorithmState.getCurrentSolutionSpace();
        InterfaceEvaluationFunction<T> evaluationFunction = configuration.getEvaluationFunction();

        for (int i = 0; i < currentSolutionSpace.size(); i++) {
            PermutationOrderCrossover permutationOrderCrossover = new PermutationOrderCrossover();
            Solution<PermutationRepresentation> childGenome1 = new Solution(new PermutationRepresentation());
            Solution<PermutationRepresentation> childGenome2 = new Solution(new PermutationRepresentation());
            permutationOrderCrossover.genomeCrossover((PermutationRepresentation) currentSolutionSpace.get((int)(ECRandom.nextDouble()*currentSolutionSpace.size())).getRepresentation(),
                    (PermutationRepresentation) currentSolutionSpace.getHighestSubList(1).get(0).getRepresentation(),
                    childGenome1.getRepresentation(),
                    childGenome2.getRepresentation());
            evaluationFunction.evaluateSolution((Solution<T>) childGenome1);
            evaluationFunction.evaluateSolution((Solution<T>) childGenome2);
            evaluationFunction.evaluateSolution(currentSolutionSpace.get(i));
            childGenome1 = (childGenome1.getFitness() < childGenome2.getFitness() ? childGenome1 : childGenome2);
            currentSolutionSpace.set(i, (childGenome1.getFitness() < currentSolutionSpace.get(i).getFitness() ? childGenome1 : currentSolutionSpace.get(i)));
        }

        for (int i = 0; i < currentSolutionSpace.size(); i++) {
            PermutationInversionMutation permutationInversionMutation = new PermutationInversionMutation();
            Solution childGenome = (Solution) DeepCopy.copy(currentSolutionSpace.get(i));
            permutationInversionMutation.genomeMutate((PermutationRepresentation) childGenome.getRepresentation());
            evaluationFunction.evaluateSolution((Solution<T>) childGenome);
            evaluationFunction.evaluateSolution(currentSolutionSpace.get(i));
            currentSolutionSpace.set(i, (childGenome.getFitness() < currentSolutionSpace.get(i).getFitness() ? childGenome : currentSolutionSpace.get(i)));
        }

        return currentSolutionSpace;

    }
}
