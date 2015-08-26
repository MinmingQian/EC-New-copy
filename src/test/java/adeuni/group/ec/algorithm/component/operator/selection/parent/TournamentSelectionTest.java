package adeuni.group.ec.algorithm.component.operator.selection.parent;

import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.component.solution.Solution;
import adeuni.group.ec.algorithm.component.solution.SolutionSpace;
import adeuni.group.ec.algorithm.problem.tsp.TspPermutationEvaluationFunction;
import adeuni.group.ec.algorithm.problem.tsp.TspProblem;
import adeuni.group.ec.algorithm.utility.factory.RepresentationFactory;
import org.junit.Test;

/**
 * Created by young on 21/08/15.
 */
public class TournamentSelectionTest {

    @Test
    public void testSelection() throws Exception {
        int size = 15;
        SolutionSpace<PermutationRepresentation> solutionSpace
                = new SolutionSpace<PermutationRepresentation>();
        int length = 10;
        RepresentationFactory<PermutationRepresentation> factory = new RepresentationFactory<>(PermutationRepresentation.class, length);
        for (int i = 0; i < size; i++) {
            solutionSpace.add(i, new Solution(factory.createRepresentation()));
        }

        TspProblem tspProblemInstance = new TspProblem("resource/tsp/eil10.tsp");
        TspPermutationEvaluationFunction evaluationFunction = new TspPermutationEvaluationFunction(tspProblemInstance);
        evaluationFunction.evaluateSolutionSpace(solutionSpace);

        TournamentSelection<PermutationRepresentation> selection = new TournamentSelection<>(10);
        selection.selection(10,solutionSpace).forEach(solution -> System.out.println(solution.getRepresentation().getRepresentationString()));
    }
}