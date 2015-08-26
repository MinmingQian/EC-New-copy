package adeuni.group.ec.algorithm.algorithms.ea;

import adeuni.group.ec.algorithm.component.operator.selection.survivor.BestSelection;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.component.solution.SolutionSpace;
import adeuni.group.ec.algorithm.component.termination.criteria.IterationTerminationCriterion;
import adeuni.group.ec.algorithm.component.termination.TerminationCriteriaPool;
import adeuni.group.ec.algorithm.configuration.Configuration;
import adeuni.group.ec.algorithm.utility.factory.RepresentationFactory;
import adeuni.group.ec.algorithm.utility.roulette.selection.SelectionCell;
import adeuni.group.ec.algorithm.utility.roulette.selection.SelectionRoulette;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by qianminming on 18/08/15.
 */
public class EvolutionaryAlgorithmTest {
    public Configuration<PermutationRepresentation> configuration;
    public EvolutionaryAlgorithm<PermutationRepresentation> evolutionaryAlgorithm;

    @Test
    public void testCompete() throws Exception {
        SolutionSpace<PermutationRepresentation> solutionSpace = evolutionaryAlgorithm.initSolutionSpace();
        solutionSpace.getSolutionList().forEach(solution -> System.out.println(solution.getRepresentation().getRepresentationString()));

    }


    @Before
    public void setUp() throws Exception {
        int size = 200;
        configuration = new Configuration<>();
        configuration.setRepresentationFactory(new RepresentationFactory<PermutationRepresentation>(PermutationRepresentation.class, 10));
        configuration.setSolutionSpaceSize(size);

        TerminationCriteriaPool<EvolutionaryAlgorithm> terminationCriteriaPool = new TerminationCriteriaPool<>();
        terminationCriteriaPool.add(new IterationTerminationCriterion<>(200));
        configuration.setTerminationCriteriaPool(terminationCriteriaPool);


        BestSelection<PermutationRepresentation> bestSelection = new BestSelection<>();
        SelectionCell  selectionSelectionCell = new SelectionCell(1, bestSelection);
        SelectionRoulette selectionRoulette = new SelectionRoulette();
        selectionRoulette.addCell(selectionSelectionCell);


        configuration.setParentSelectionRoulette(selectionRoulette);
        configuration.setSurvivorSelectionRoulette(selectionRoulette);

        //TspPermutationEvaluationFunction permutationEvaluationFunction = new TspPermutationEvaluationFunction();
        //configuration.setEvaluationFunction(permutationEvaluationFunction);

        evolutionaryAlgorithm = new EvolutionaryAlgorithm<>(configuration);


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRun() throws Exception {
        evolutionaryAlgorithm.run();
    }
}