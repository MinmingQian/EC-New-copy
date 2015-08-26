package adeuni.group.ec.algorithm.component.operator.variation.mutation.permutation;

import adeuni.group.ec.algorithm.component.operator.variation.mutation.AbstractPermutationMutationOperator;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.utility.randomnumbergenerator.ECRandom;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Yang Lu on 20/08/15.
 * Modified by qianminming for the code format
 *
 * Implementation fo the inversion mutation.
 *
 */
public class PermutationInversionMutation  extends AbstractPermutationMutationOperator {


    private static final long serialVersionUID = 4986664334326859176L;
    /**
     * The genome mutate function
     * @param mutantGenome
     */
    @Override
    public void genomeMutate(PermutationRepresentation mutantGenome) {


        int pos1 = (int)(mutantGenome.size() * ECRandom.nextDouble());
        int pos2 = (int)(mutantGenome.size() * ECRandom.nextDouble());

        while(pos1 == pos2){
            pos2 = (int)(mutantGenome.size() * ECRandom.nextDouble());
        }

        int startPos = pos1 > pos2 ? pos2 : pos1;
        int endPos = pos1 > pos2 ? pos1 : pos2;

        ArrayList<Integer> tempCity = new ArrayList<>();

        for (int i = startPos; i <= endPos; i++) {
            tempCity.add(mutantGenome.get(i));
        }

        Collections.reverse(tempCity);

        for (int i = startPos; i <= endPos; i++) {
            mutantGenome.set(i, tempCity.get(i - startPos));
        }

    }
}
