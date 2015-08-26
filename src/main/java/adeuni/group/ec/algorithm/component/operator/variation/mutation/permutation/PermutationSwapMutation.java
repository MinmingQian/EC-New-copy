package adeuni.group.ec.algorithm.component.operator.variation.mutation.permutation;

import adeuni.group.ec.algorithm.component.operator.variation.mutation.AbstractPermutationMutationOperator;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.utility.randomnumbergenerator.ECRandom;

/**
 * Created by Joe on 19/08/15.
 * Modified by qianminming 23/08/15 change the name of the format of the code
 *
 * Implementation of  of the swap mutation
 */
public class PermutationSwapMutation extends AbstractPermutationMutationOperator {


    private static final long serialVersionUID = -1822072850476331558L;

    /**
     * The genome mutate function
     *
     * @param mutantGenome
     */
    @Override
    public void genomeMutate(PermutationRepresentation mutantGenome) {
        int genomePos1 = (int)(mutantGenome.size()* ECRandom.nextDouble());
        int genomePos2 = (int)(mutantGenome.size()* ECRandom.nextDouble());

        while (genomePos1 == genomePos2){
            genomePos2 = (int)(mutantGenome.size()* ECRandom.nextDouble());
        }

        int Element1 = mutantGenome.get(genomePos1);
        int Element2 = mutantGenome.get(genomePos2);

        mutantGenome.set(genomePos1, Element2);
        mutantGenome.set(genomePos2, Element1);
    }
}
