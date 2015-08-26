package adeuni.group.ec.algorithm.utility.factory;

import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.configuration.Configuration;
import adeuni.group.ec.algorithm.problem.tsp.TspPermutationEvaluationFunction;
import adeuni.group.ec.algorithm.problem.tsp.TspProblem;
import adeuni.group.ec.algorithm.utility.fitnessconverter.ReciprocalConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by qianminming on 19/08/15.
 */
public class ConfigurationFactoryTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateConfiguration() throws Exception {
        ConfigurationFactory factory = new ConfigurationFactory("resource/configuration.yml");
        Configuration configuration = factory.createConfiguration();
        TspProblem tspProblemInstance = new TspProblem(configuration.getFileName());
        ReciprocalConverter fitnessConverter = new ReciprocalConverter();

        configuration.setRepresentationFactory(new
                RepresentationFactory<>(PermutationRepresentation.class, tspProblemInstance.getCitySize()));
        configuration.setFitnessConverter(fitnessConverter);
        configuration.setEvaluationFunction(new TspPermutationEvaluationFunction(tspProblemInstance, fitnessConverter));

        configuration.getAlgorithm().run();
    }
}