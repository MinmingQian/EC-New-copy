package adeuni.group.ec.algorithm.component.solution;

import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import org.junit.Test;

/**
 * Created by qianminming on 15/08/15.
 */
public class SolutionSpaceTest {
    SolutionSpace<PermutationRepresentation> solutionSpace;

    @Test
    public void testSize() throws Exception {
        int size = 20;
        solutionSpace = new SolutionSpace<PermutationRepresentation>(size, PermutationRepresentation.class);
        for (int i = 0; i < size; i++) {
            solutionSpace.get(i).getRepresentation().encode(101);
            System.out.println(solutionSpace.get(i).getRepresentation().getElementData().toString());
        }
    }


}