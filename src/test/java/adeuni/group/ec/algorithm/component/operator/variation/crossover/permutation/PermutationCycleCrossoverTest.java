package adeuni.group.ec.algorithm.component.operator.variation.crossover.permutation;

import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Joe on 23/08/15.
 */
public class PermutationCycleCrossoverTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGenomeCrossover() throws Exception {
        for (int i = 0; i < 100000; i++) {
//            PermutationRepresentation testCrossover1 = new PermutationRepresentation(Arrays.asList(19,23,10,16,41,46,39,42,29,44,28,35,21,18,5,24,40,38,33,3,15,25,37,4,22,13,12,26,34,1,7,30,6,47,48,31,0,11,43,36,45,14,49,8,2,50,20,32,17,9,28));
//            PermutationRepresentation testCrossover2 = new PermutationRepresentation(Arrays.asList(50,2,8,49,14,45,36,43,11,0,31,48,47,6,30,7,1,34,26,12,13,22,4,37,25,15,10,16,41,46,39,42,29,44,28,35,21,18,5,24,40,38,33,3,23,19,27,9,17,32,20));
        PermutationRepresentation testCrossover1 = new PermutationRepresentation(51);
        PermutationRepresentation testCrossover2 = new PermutationRepresentation(51);
            PermutationRepresentation childCrossover1 = new PermutationRepresentation();
            PermutationRepresentation childCrossover2 = new PermutationRepresentation();
        System.out.println(testCrossover1.getRepresentationString());
            System.out.println(testCrossover2.getRepresentationString());
        PermutationCycleCrossover permutationCycleCrossover = new PermutationCycleCrossover();
            permutationCycleCrossover.genomeCrossover(testCrossover1, testCrossover2, childCrossover1, childCrossover2);
            System.out.println(childCrossover1.getRepresentationString());
            System.out.println(childCrossover2.getRepresentationString());
            System.out.println("*************"+i+"****************");
//            if(childCrossover1.contains(null) || childCrossover2.contains(null))
//                break;
        }
    }
}