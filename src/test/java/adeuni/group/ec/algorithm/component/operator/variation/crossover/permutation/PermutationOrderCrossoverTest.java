package adeuni.group.ec.algorithm.component.operator.variation.crossover.permutation;

import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Joe on 23/08/15.
 */
public class PermutationOrderCrossoverTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGenomeCrossover() throws Exception {
        for (int i = 0; i < 100000; i++) {
            PermutationRepresentation testCrossover1=new PermutationRepresentation(5);
            PermutationRepresentation testCrossover2=new PermutationRepresentation(5);
            PermutationRepresentation childCrossover1=new PermutationRepresentation();
            PermutationRepresentation childCrossover2=new PermutationRepresentation();
//            System.out.println(testant.getRepresentationString());
//            PermutationInsertMutation permutationInsertMutation = new PermutationInsertMutation();
//            permutationInsertMutation.genomeMutate(testMu
            PermutationOrderCrossover permutationOrderCrossover = new PermutationOrderCrossover();
            permutationOrderCrossover.genomeCrossover(testCrossover1,testCrossover2,childCrossover1,childCrossover2);
            System.out.println(childCrossover1.getRepresentationString());
            System.out.println(childCrossover2.getRepresentationString());
            if(childCrossover1.size()!=5||childCrossover2.size()!=5)
                break;
        }
    }
}