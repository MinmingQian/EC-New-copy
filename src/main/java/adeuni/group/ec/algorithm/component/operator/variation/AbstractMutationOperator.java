package adeuni.group.ec.algorithm.component.operator.variation;

import adeuni.group.ec.algorithm.component.operator.OperatorException;
import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.solution.Solution;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by qianminming on 16/08/15.
 */
public abstract class AbstractMutationOperator<T extends InterfaceRepresentation> implements InterfaceVariationOperator<T> {

    // the input solution number of mutation is set to 1
    protected static final int INPUT_SOLUTION_NUMBER = 1;

    // the output solution number of mutation is set to 2
    protected static final int OUTPUT_SOLUTION_NUMBER = INPUT_SOLUTION_NUMBER;
    private static final long serialVersionUID = 1275335585003751734L;

    /**
     * Apply the mutation operator to the selected solution
     * @param selectedSolutions
     * @return
     * @throws OperatorException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public List<Solution<T>> apply(List<Solution<T>> selectedSolutions) throws OperatorException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        OperatorException inputException = new OperatorException("Invalid number of mutation input solutions: " + selectedSolutions.size() +
                ". Should be: "+INPUT_SOLUTION_NUMBER+".");
        if (selectedSolutions.size() != INPUT_SOLUTION_NUMBER) {
            throw inputException;
        }

        List<Solution<T>> outputSolutions;
        outputSolutions = solutionMutate(selectedSolutions);

        OperatorException outputException = new OperatorException("Invalid number of mutation output solutions: " + outputSolutions.size() +
                ". Should be: "+OUTPUT_SOLUTION_NUMBER+".");
        if (outputSolutions.size() != OUTPUT_SOLUTION_NUMBER) {
            throw outputException;
        }

        return outputSolutions;
    }

    /**
     * The solution mutate function is set by the abstract function
     * @param selectedSolutions
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public abstract List<Solution<T>> solutionMutate(List<Solution<T>> selectedSolutions) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    /**
     * Get the input solution number
     * @return input solution number
     */
    @Override
    public int getInputSolutionNumber() {
        return INPUT_SOLUTION_NUMBER;
    }

    /**
     * Get the output solution number
     * @return output solution number
     */
    @Override
    public int getOutputSolutionNumber() {
        return OUTPUT_SOLUTION_NUMBER;
    }
}
