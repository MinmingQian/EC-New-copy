package adeuni.group.ec.algorithm.component.operator.selection.parent;

import adeuni.group.ec.algorithm.component.operator.selection.InterfaceSelection;
import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.solution.Solution;
import adeuni.group.ec.algorithm.component.solution.SolutionSpace;
import adeuni.group.ec.algorithm.utility.randomnumbergenerator.ECRandom;

import java.util.List;

/**
 * Created by young on 20/08/15.
 */
public class TournamentSelection<R extends InterfaceRepresentation> implements InterfaceSelection<R>{
    //the tournament size
    protected int k;

    /**
     * The construction of the tournament selection function
     * @param k
     */
    public TournamentSelection(int k) {
        this.k = k;
    }


    /**
     * The main body of the selection function
     * @param numberOfSelectedSolutions
     * @param solutionSpace
     * @return
     */
    @Override
    public List<Solution<R>> selection(int numberOfSelectedSolutions, SolutionSpace<R> solutionSpace) {
        SolutionSpace<R> tournamentSolutionSpace = new SolutionSpace<>();
        SolutionSpace<R> resultTournamentSolutionSpace = new SolutionSpace<>();
        SolutionSpace<R> originalSolutionSpace = new SolutionSpace<>();
        SolutionSpace<R> originalSelectedSolutionSpace = new SolutionSpace<>();



        solutionSpace.getSolutionList().forEach(originalSolutionSpace::add);

        Solution<R> championSolution;
        while (resultTournamentSolutionSpace.size() < numberOfSelectedSolutions)
        {
            originalSelectedSolutionSpace.add(originalSolutionSpace.getSolutionList());

            if (k >= originalSelectedSolutionSpace.size()){
                tournamentSolutionSpace.add(originalSelectedSolutionSpace.getSolutionList());
            } else {
                for (int i = 0; i < k; i++) {
                    tournamentSolutionSpace.add(originalSelectedSolutionSpace.remove((int) (ECRandom.nextDouble() * originalSelectedSolutionSpace.size())));
                }
            }

            championSolution =  tournamentSolutionSpace.getHighestSubList(1).get(0);

//            if(!resultTournamentSolutionSpace.contains(championSolution))
            {
                resultTournamentSolutionSpace.add(championSolution);
                originalSolutionSpace.remove(championSolution);

            }
            tournamentSolutionSpace.removeAll();
            originalSelectedSolutionSpace.removeAll();
        }

        return resultTournamentSolutionSpace.getSolutionList();
    }
}
