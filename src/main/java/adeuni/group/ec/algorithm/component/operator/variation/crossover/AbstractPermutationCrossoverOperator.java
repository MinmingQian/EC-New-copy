package adeuni.group.ec.algorithm.component.operator.variation.crossover;

import adeuni.group.ec.algorithm.component.operator.variation.AbstractCrossoverOperator;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.component.solution.Solution;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qianminming on 16/08/15.
 */
public abstract class AbstractPermutationCrossoverOperator extends AbstractCrossoverOperator<PermutationRepresentation> {


    private static final long serialVersionUID = 1046279800317803264L;

    /**
     * Cross over the solution
     * @param selectedSolutions
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Override
    public List<Solution<PermutationRepresentation>> solutionCrossover(List<Solution<PermutationRepresentation>> selectedSolutions)
            throws
            InvocationTargetException,
            NoSuchMethodException,
            InstantiationException,
            IllegalAccessException {

        List<Solution<PermutationRepresentation>> outputSolutions = new ArrayList<>(OUTPUT_SOLUTION_NUMBER);

        Solution<PermutationRepresentation> fatherSolution = selectedSolutions.get(0);
        Solution<PermutationRepresentation> motherSolution = selectedSolutions.get(1);

        //TODO: Is here requires a copy of the parents or totally new solution?
        Solution<PermutationRepresentation> childSolution1 = new Solution<PermutationRepresentation>(PermutationRepresentation.class);
        Solution<PermutationRepresentation> childSolution2 = new Solution<PermutationRepresentation>(PermutationRepresentation.class);

        //Crossover happenes inside, this is not hard copy, the result will shown out.
        genomeCrossover(fatherSolution.getRepresentation(),
                        motherSolution.getRepresentation(),
                        childSolution1.getRepresentation(),
                        childSolution2.getRepresentation());

        outputSolutions.add(childSolution1);
        outputSolutions.add(childSolution2);

        return outputSolutions;
    }

    /**
     * The genoe crossover function will be implemented by each operator
     * @param fatherGenome
     * @param motherGenome
     * @param childGenome1
     * @param childGenome2
     */
    public abstract void genomeCrossover(PermutationRepresentation fatherGenome,
                                         PermutationRepresentation motherGenome,
                                         PermutationRepresentation childGenome1,
                                         PermutationRepresentation childGenome2);
}
