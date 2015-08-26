package adeuni.group.ec.algorithm.component.operator.selection.parent;

import adeuni.group.ec.algorithm.component.operator.selection.InterfaceSelection;
import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.solution.Solution;
import adeuni.group.ec.algorithm.component.solution.SolutionSpace;

import java.util.List;

/**
 * Created by young on 21/08/15.
 */
public class ElitismSelection<R extends InterfaceRepresentation> implements InterfaceSelection<R> {

    /**
     * The main body of the selection function
     * @param numberOfSelectedSolutions
     * @param solutionSpace
     * @return
     */
    @Override
    public List<Solution<R>> selection(int numberOfSelectedSolutions, SolutionSpace<R> solutionSpace) {

        return solutionSpace.getHighestSubList(numberOfSelectedSolutions);
    }
}
