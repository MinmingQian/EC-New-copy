package adeuni.group.ec.algorithm.component.operator.variation;

import adeuni.group.ec.algorithm.component.operator.OperatorException;
import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.solution.Solution;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by qianminming on 16/08/15.
 */
public abstract class AbstractCrossoverOperator<T extends InterfaceRepresentation> implements InterfaceVariationOperator<T>{

    // the input solution number of crossover is set to 2
    protected static final int INPUT_SOLUTION_NUMBER = 2;

    // the out solution number of crossover is set to 2 too
    protected static final int OUTPUT_SOLUTION_NUMBER = INPUT_SOLUTION_NUMBER;
    private static final long serialVersionUID = 1085772289515620009L;

    /**
     * Apply the operator
     * @param selectedSolutions
     * @return
     * @throws Exception
     */
    public List<Solution<T>> apply(List<Solution<T>> selectedSolutions) throws Exception {
        OperatorException inputException = new OperatorException("Invalid number of crossover input solutions: " + selectedSolutions.size() +
                                                                 ". Should be: "+INPUT_SOLUTION_NUMBER+".");
        if (selectedSolutions.size() != INPUT_SOLUTION_NUMBER) {
            throw inputException;
        }

        List<Solution<T>> outputSolutions;
        outputSolutions = solutionCrossover(selectedSolutions);

        OperatorException outputException = new OperatorException("Invalid number of crossover output solutions: " + outputSolutions.size() +
                                                                  ". Should be: "+OUTPUT_SOLUTION_NUMBER+".");
        if (outputSolutions.size() != OUTPUT_SOLUTION_NUMBER) {
            throw outputException;
        }

        return outputSolutions;
    }

    /**
     * Cross the solution, implemented in the abstract function
     * @param selectedSolutions
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public abstract List<Solution<T>> solutionCrossover(List<Solution<T>> selectedSolutions) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    /**
     * Get the input solution number
     * @return the solution number
     */
    @Override
    public int getInputSolutionNumber() {
        return INPUT_SOLUTION_NUMBER;
    }


    /**
     * Get output solution number
     * @return the solution number
     */
    @Override
    public int getOutputSolutionNumber() {
        return OUTPUT_SOLUTION_NUMBER;
    }
}
