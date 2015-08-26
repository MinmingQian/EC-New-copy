package adeuni.group.ec.algorithm.problem.tsp;

import adeuni.group.ec.algorithm.component.evaluationfunction.AbstractEvaluationFunction;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.utility.fitnessconverter.InterfaceFitnessConverter;

/**
 * Created by qianminming on 17/08/15.
 */
public class TspPermutationEvaluationFunction extends AbstractEvaluationFunction<PermutationRepresentation> {


    // the tsp problem instance
    protected  TspProblem tspProblemInstance;

    // the fitness converter
    protected InterfaceFitnessConverter fitnessConverter;

    /**
     * The construction function
     * @param tspProblemInstance
     */
    public TspPermutationEvaluationFunction (TspProblem tspProblemInstance) {
        this.tspProblemInstance = tspProblemInstance;
    }

    /**
     * Construction function with the fitness converter
     * @param tspProblemInstance
     * @param fitnessConverter
     */
    public TspPermutationEvaluationFunction (TspProblem tspProblemInstance, InterfaceFitnessConverter fitnessConverter) {
        this.tspProblemInstance = tspProblemInstance;
        this.fitnessConverter = fitnessConverter;

    }

    /**
     * The implemented evaluate function
     * @param representation
     * @return
     */
    @Override
    public double evaluate(PermutationRepresentation representation) {
        double totalDistance = 0;

//        System.out.println(representation);
//        System.out.println(representation.getElementData());
//        System.out.println(representation.getRepresentationString());

        for (int i = 0; i < representation.size(); i++) {
            totalDistance += tspProblemInstance.getCityDistance(representation.get(i),
                                                                representation.get((i+1)%representation.size()));
        }


        if (fitnessConverter == null) {
            return totalDistance;
        } else {
            return fitnessConverter.encode(totalDistance);
        }
    }

}
