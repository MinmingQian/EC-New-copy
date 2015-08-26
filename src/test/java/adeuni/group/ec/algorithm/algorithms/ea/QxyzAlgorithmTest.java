package adeuni.group.ec.algorithm.algorithms.ea;

import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.configuration.Configuration;
import adeuni.group.ec.algorithm.problem.tsp.TspPermutationEvaluationFunction;
import adeuni.group.ec.algorithm.problem.tsp.TspProblem;
import adeuni.group.ec.algorithm.utility.factory.ConfigurationFactory;
import adeuni.group.ec.algorithm.utility.factory.RepresentationFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Joe on 21/08/15.
 */
public class QxyzAlgorithmTest {

    @Before
    public void setUp() throws Exception {
        ConfigurationFactory factory = new ConfigurationFactory("resource/configuration.yml");
        Configuration configuration = factory.createConfiguration();
        QxyzAlgorithm<PermutationRepresentation> algorithm= new QxyzAlgorithm<>(configuration);
        TspProblem tspProblemInstance = new TspProblem("resource/tsp/eil51.tsp");

        configuration.setRepresentationFactory(new
                RepresentationFactory<PermutationRepresentation>(PermutationRepresentation.class, tspProblemInstance.getCitySize()));
//        ReciprocalConverter fitnessConverter = new ReciprocalConverter();

//        configuration.setFitnessConverter(fitnessConverter);
        configuration.setEvaluationFunction(new TspPermutationEvaluationFunction(tspProblemInstance));
//        configuration.setEvaluationFunction(new TspPermutationEvaluationFunction(tspProblemInstance));

        algorithm.run();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testIteration() throws Exception {

    }
}