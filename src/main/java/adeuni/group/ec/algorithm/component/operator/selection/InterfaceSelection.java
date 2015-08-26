package adeuni.group.ec.algorithm.component.operator.selection;

import adeuni.group.ec.algorithm.component.operator.InterfaceOperator;
import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.solution.Solution;
import adeuni.group.ec.algorithm.component.solution.SolutionSpace;

import java.util.List;

/**
 * Created by qianminming on 17/08/15.
 */
public interface InterfaceSelection<R extends InterfaceRepresentation> extends InterfaceOperator {
    /**
     * The main body of the selection function
     * @param numberOfSelectedSolutions
     * @param solutionSpace
     * @return
     */
    List<Solution<R>> selection(int numberOfSelectedSolutions, SolutionSpace<R> solutionSpace);
}
