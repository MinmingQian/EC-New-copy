package adeuni.group.ec.algorithm.algorithms.ea;

import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.configuration.Configuration;
import adeuni.group.ec.algorithm.problem.tsp.TspPermutationEvaluationFunction;
import adeuni.group.ec.algorithm.problem.tsp.TspProblem;
import adeuni.group.ec.algorithm.utility.factory.ConfigurationFactory;
import adeuni.group.ec.algorithm.utility.factory.RepresentationFactory;
import adeuni.group.ec.algorithm.utility.fitnessconverter.ReciprocalConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Joe on 21/08/15.
 */
public class InverOverEATest {




    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testIteration() throws Exception {

        ConfigurationFactory factory = new ConfigurationFactory("resource/configuration.yml");
        Configuration configuration = factory.createConfiguration();
        InverOverEA<PermutationRepresentation> algorithm= new InverOverEA<>(configuration);
        TspProblem tspProblemInstance = new TspProblem("resource/tsp/eil51.tsp");

        ReciprocalConverter fitnessConverter = new ReciprocalConverter();

        configuration.setRepresentationFactory(new
                RepresentationFactory<PermutationRepresentation>(PermutationRepresentation.class, tspProblemInstance.getCitySize()));
        configuration.setFitnessConverter(fitnessConverter);
        configuration.setEvaluationFunction(new TspPermutationEvaluationFunction(tspProblemInstance,fitnessConverter));

        algorithm.run();
    }

    @Test
    public void testRun() throws Exception {
    }
}