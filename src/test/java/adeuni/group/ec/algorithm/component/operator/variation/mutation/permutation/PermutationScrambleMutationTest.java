package adeuni.group.ec.algorithm.component.operator.variation.mutation.permutation;

import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by qianminming on 23/08/15.
 */
public class PermutationScrambleMutationTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGenomeMutate() throws Exception {
        for (int i = 0; i < 1000; i++) {
            PermutationRepresentation testMutant=new PermutationRepresentation(10);
            System.out.println(testMutant.getRepresentationString());
            PermutationScrambleMutation permutationScrambleMutation = new PermutationScrambleMutation();
            permutationScrambleMutation.genomeMutate(testMutant);

            System.out.println(testMutant.getRepresentationString());
        }

    }
}