package adeuni.group.ec.algorithm.component.operator.selection.survivor;

import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.operator.selection.InterfaceSelection;
import adeuni.group.ec.algorithm.component.solution.Solution;
import adeuni.group.ec.algorithm.component.solution.SolutionSpace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qianminming on 17/08/15.
 */
public class BestSelection<R extends InterfaceRepresentation> implements InterfaceSelection<R> {
    /**
     * The main body of the selection function
     * @param numberOfSelectedSolutions
     * @param solutionSpace
     * @return
     */
    @Override
    public List<Solution<R>> selection(int numberOfSelectedSolutions, SolutionSpace<R> solutionSpace) {
        List<Solution<R>> selectedSolutionList = new ArrayList<>();
        solutionSpace.getHighestSubList(numberOfSelectedSolutions).forEach(selectedSolutionList::add);
        return selectedSolutionList;
    }

}
