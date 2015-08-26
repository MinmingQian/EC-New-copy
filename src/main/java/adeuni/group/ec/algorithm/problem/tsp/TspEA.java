package adeuni.group.ec.algorithm.problem.tsp;

import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.configuration.Configuration;
import adeuni.group.ec.algorithm.utility.factory.ConfigurationFactory;
import adeuni.group.ec.algorithm.utility.factory.RepresentationFactory;
import adeuni.group.ec.algorithm.utility.fitnessconverter.ReciprocalConverter;

/**
 * Created by qianminming on 19/08/15.
 *
 * The TspEA instance
 */
public class TspEA {

    /**
     * the start point of the problem
     * @param args
     */
    public static void main(String [ ] args) throws Exception {
        String configurationFilename = args[0];

        ConfigurationFactory factory = new ConfigurationFactory(args[0]);
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
