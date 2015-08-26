package adeuni.group.ec.algorithm.algorithms.ea;

import adeuni.group.ec.algorithm.algorithms.AbstractAlgorithm;
import adeuni.group.ec.algorithm.algorithms.stateandanalysis.AlgorithmState;
import adeuni.group.ec.algorithm.component.evaluationfunction.InterfaceEvaluationFunction;
import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.component.solution.Solution;
import adeuni.group.ec.algorithm.component.solution.SolutionSpace;
import adeuni.group.ec.algorithm.configuration.Configuration;
import adeuni.group.ec.algorithm.utility.deepcopy.DeepCopy;
import adeuni.group.ec.algorithm.utility.factory.RepresentationFactory;
import adeuni.group.ec.algorithm.utility.randomnumbergenerator.ECRandom;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Joe on 20/08/15.
 * Modified by Feifei Xiong 21/08/15
 *
 * The inver-over evolutionary algorithm
 */
public class InverOverEA<T extends InterfaceRepresentation> extends AbstractAlgorithm<T> {

    private static final long serialVersionUID = -615526054464366643L;

    /**
     * Construct the inver-over EA
     * @param configuration
     */
    public InverOverEA(Configuration configuration) {
        super(configuration);
    }

    /**
     * Init solution space (population)
     * @return solution space(population)
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Override
    public SolutionSpace initSolutionSpace() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //The population size of the problem
        int solutionSpaceSize = 50;
        RepresentationFactory<T> representationFactory = configuration.getRepresentationFactory();
        SolutionSpace<T> solutionSpace = new SolutionSpace<>();

        //init the population(solution space)
        for (int i = 0; i < solutionSpaceSize; i++) {
            solutionSpace.add(new Solution(representationFactory.createRepresentation()));
        }
        return solutionSpace;
    }

    /**
     *Use iteration to produce the new generation
     * @param algorithmState
     * @return new generation
     * @throws Exception
     */
    @Override
    public SolutionSpace iteration(AlgorithmState algorithmState) throws Exception {

        SolutionSpace<T> currentSolutionSpace = algorithmState.getCurrentSolutionSpace();
        InterfaceEvaluationFunction<T> evaluationFunction = configuration.getEvaluationFunction();

        //The possibility
        double p = 0.02;

        //The city c'
        int cityEnd;
        for (int i = 0; i < currentSolutionSpace.size(); i++) {
            Solution<PermutationRepresentation> currentSolution =
                    (Solution<PermutationRepresentation>) DeepCopy.copy(currentSolutionSpace.get(i));
            //The city c
            int cityStart = (int) (ECRandom.nextDouble() * currentSolution.getRepresentation().size());
            //The previous and the next city of c
            ArrayList<Integer> nextOrPrevious = new ArrayList<>();
            Solution<PermutationRepresentation> randomSolution;
            while(true){
                if(ECRandom.nextDouble() <= p) {
                    do {
                        cityEnd =(int)(ECRandom.nextDouble()*currentSolution.getRepresentation().size());
                    }while(cityStart == cityEnd);
                }else{
                    randomSolution =
                            (Solution<PermutationRepresentation>)currentSolutionSpace.get((int)(ECRandom.nextDouble() * currentSolutionSpace.size()));
                    cityEnd =
                            randomSolution.getRepresentation().get((randomSolution.getRepresentation().getElementData().indexOf(cityStart) + 1)%
                                    randomSolution.getRepresentation().size());
                }

                //Find the position of the city c and c' in current individual
                int positionA = currentSolution.getRepresentation().getElementData().indexOf(cityStart);
                int positionB = currentSolution.getRepresentation().getElementData().indexOf(cityEnd);

                //Add the previous and next city of c in random individual into list
                nextOrPrevious.add(currentSolution.getRepresentation().get((positionA + currentSolution
                        .getRepresentation().size() - 1) % currentSolution.getRepresentation()
                        .size()));
                nextOrPrevious.add(currentSolution.getRepresentation().get((positionA + 1) % currentSolution.getRepresentation()
                        .size()));

                if(nextOrPrevious.contains(cityEnd)){
                    break;
                }

                int positionStart = positionA > positionB ? positionB : positionA;
                int positionEnd = positionA > positionB ? positionA : positionB;

                //If the index of city c smaller than c' reverse the city between them
                ArrayList<Integer> resultList = new ArrayList<>();
                ArrayList<Integer> sortList = new ArrayList<>();
                for (int j = positionStart + 1; j <= positionEnd ; j++) {
                    sortList.add(currentSolution.getRepresentation().get(j));
                }
                Collections.reverse(sortList);
                for (int j = 0; j <= positionStart; j++) {
                    resultList.add(currentSolution.getRepresentation().get(j));
                }
                for (Integer aSortList : sortList) {
                    resultList.add(aSortList);
                }
                if(positionEnd != currentSolution.getRepresentation().size() - 1){
                    for (int j = positionEnd + 1; j < currentSolution.getRepresentation().size(); j++) {
                        resultList.add(currentSolution.getRepresentation().get(j));
                    }
                }

                //If the index of city c larger than c' loop the reverse list and fix the offset
                if(positionA > positionB) {
                    Collections.reverse(resultList);
                    int resultIndex = resultList.indexOf(cityStart);
                    Collections.rotate(resultList, resultIndex - positionA);

                }

                for (int j = 0; j < resultList.size(); j++) {
                    currentSolution.getRepresentation().set(j, resultList.get(j));
                }
                cityStart = cityEnd;

            }

            //Evaluate the original individual and current individual
            evaluationFunction.evaluateSolution((Solution<T>) currentSolution);
            evaluationFunction.evaluateSolution(currentSolutionSpace.get(i));

            //If the fitness smaller than original individual replace it
            if(currentSolution.getFitness() >= currentSolutionSpace.get(i).getFitness()){
                currentSolutionSpace.set(i, currentSolution);
            }
        }
        return currentSolutionSpace;
    }
}

