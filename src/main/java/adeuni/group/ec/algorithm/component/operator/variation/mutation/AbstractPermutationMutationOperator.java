package adeuni.group.ec.algorithm.component.operator.variation.mutation;

import adeuni.group.ec.algorithm.component.operator.variation.AbstractMutationOperator;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.component.solution.Solution;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qianminming on 16/08/15.
 */
public abstract  class AbstractPermutationMutationOperator extends AbstractMutationOperator<PermutationRepresentation> {


    private static final long serialVersionUID = -7141931054982698764L;

    /**
     * The solution mutate function
     * @param selectedSolutions
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Override
    public List<Solution<PermutationRepresentation>> solutionMutate(List<Solution<PermutationRepresentation>> selectedSolutions) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<Solution<PermutationRepresentation>> outputSolutions = new ArrayList<>(OUTPUT_SOLUTION_NUMBER);

        Solution<PermutationRepresentation> normalSolution = selectedSolutions.get(0);
        Solution<PermutationRepresentation> mutantSolution = new Solution<PermutationRepresentation>(PermutationRepresentation.class);
        normalSolution.getRepresentation().getElementData().forEach(mutantSolution.getRepresentation()::add);

        //Crossover happens inside, this is not hard copy, the result will shown out.
        genomeMutate(mutantSolution.getRepresentation());

        outputSolutions.add(mutantSolution);

        return outputSolutions;
    }

    /**
     * The genome mutate funciont will be implemented by each mutation operator
     * @param mutantGenome
     */
    public abstract void genomeMutate(PermutationRepresentation mutantGenome);
}
