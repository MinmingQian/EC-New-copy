package adeuni.group.ec.algorithm.component.operator.variation.mutation.permutation;

import adeuni.group.ec.algorithm.component.operator.variation.mutation.AbstractPermutationMutationOperator;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.utility.randomnumbergenerator.ECRandom;

/**
 * Created by Yang Lu on 20/08/15.
 */
public class PermutationInsertMutation extends AbstractPermutationMutationOperator {


    private static final long serialVersionUID = 1351864917984390070L;

    /**
     * The genome mutate function
     * @param mutantGenome
     */
    @Override
    public void genomeMutate(PermutationRepresentation mutantGenome) {

        mutantGenome.add((int)(mutantGenome.size() * ECRandom.nextDouble()),
                         mutantGenome.remove((int)(mutantGenome.size() * ECRandom.nextDouble())));
    }
}
