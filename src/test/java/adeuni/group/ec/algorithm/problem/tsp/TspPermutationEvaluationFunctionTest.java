package adeuni.group.ec.algorithm.problem.tsp;

import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.utility.factory.RepresentationFactory;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by qianminming on 20/08/15.
 */
public class TspPermutationEvaluationFunctionTest {

    @Test
    public void testEvaluate() throws Exception {
        int length = 51;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(i);
        }

        TspProblem tspProblem = new TspProblem("resource/tsp/eil51.tsp");
        RepresentationFactory<PermutationRepresentation> factory = new RepresentationFactory<>(PermutationRepresentation.class, length);
        PermutationRepresentation permutationRepresentation = (PermutationRepresentation) factory.createRepresentation();

        PermutationRepresentation representation = new PermutationRepresentation(list);
        TspPermutationEvaluationFunction function = new TspPermutationEvaluationFunction(tspProblem);

        System.out.println(permutationRepresentation.getRepresentationString());
        System.out.println(function.evaluate(permutationRepresentation));


        System.out.println(representation.getRepresentationString());
        System.out.println(function.evaluate(representation));
    }
}