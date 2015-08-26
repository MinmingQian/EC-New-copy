package adeuni.group.ec.algorithm.component.operator.variation.mutation.permutation;

import adeuni.group.ec.algorithm.component.operator.variation.mutation.AbstractPermutationMutationOperator;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.utility.randomnumbergenerator.ECRandom;

import java.util.Collections;

/**
 * Created by qianminming on 20/08/15.
 * Modified by qianminming, modify the code format
 *
 * This class is the implementation of scramble mutation
 */
public class PermutationScrambleMutation extends AbstractPermutationMutationOperator {


    private static final long serialVersionUID = -7012207137444726913L;

    /**
     * The genone mutate function
     * @param mutantGenome
     */
    @Override
    public void genomeMutate(PermutationRepresentation mutantGenome) {
        int pos1;
        int pos2;

        do{
            pos1 = (int)(mutantGenome.size()* ECRandom.nextDouble());
            pos2 = (int)(mutantGenome.size()* ECRandom.nextDouble());
        }while (pos1 == pos2);

        int startPos = pos1 > pos2 ? pos2 : pos1;
        int endPos = pos1 > pos2 ? pos1 : pos2;

        Collections.shuffle(mutantGenome.subList(startPos,endPos));
    }
}
