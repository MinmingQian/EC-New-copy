package adeuni.group.ec.algorithm.component.operator.variation.mutation.permutation;

import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import org.junit.Test;

/**
 * Created by young on 20/08/15.
 */
public class PermutationInsertMutationTest {

    @Test
    public void testGenomeMutate() throws Exception {
        for (int i = 0; i < 1000; i++) {
            PermutationRepresentation testMutant=new PermutationRepresentation(8);
            System.out.println(testMutant.getRepresentationString());
            PermutationInsertMutation permutationInsertMutation = new PermutationInsertMutation();
            permutationInsertMutation.genomeMutate(testMutant);

            System.out.println(testMutant.getRepresentationString());
        }
    }
}