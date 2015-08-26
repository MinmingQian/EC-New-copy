package adeuni.group.ec.algorithm.component.operator.selection.parent;

import adeuni.group.ec.algorithm.component.operator.selection.InterfaceSelection;
import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.solution.Solution;
import adeuni.group.ec.algorithm.component.solution.SolutionSpace;
import adeuni.group.ec.algorithm.utility.randomnumbergenerator.ECRandom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by young on 21/08/15.
 */
public class FitnessProportionalSelection<R extends InterfaceRepresentation> implements InterfaceSelection<R> {

    /**
     * The selection
     * @param numberOfSelectedSolutions
     * @param solutionSpace
     * @return
     */
    @Override
    public List<Solution<R>> selection(int numberOfSelectedSolutions, SolutionSpace<R> solutionSpace) {

        List<Solution<R>> resultSolutions=new ArrayList<>();
        double totalFitness=0;
        for (int i = 0; i <solutionSpace.size() ; i++) {
            totalFitness += solutionSpace.get(i).getFitness();
        }

        while (resultSolutions.size() < numberOfSelectedSolutions) {
            double randomFitness = ECRandom.nextDouble() * totalFitness;

            for (Solution<R> solution: solutionSpace.getSolutionList()) {
                randomFitness -= solution.getFitness();
                if (randomFitness < 0) {
                    resultSolutions.add(solution);
                    break;
                }
            }
        }

        return resultSolutions;
    }


}
