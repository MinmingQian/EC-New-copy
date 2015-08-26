package adeuni.group.ec.algorithm.utility.roulette.operator;

import adeuni.group.ec.algorithm.component.operator.variation.InterfaceVariationOperator;
import adeuni.group.ec.algorithm.utility.roulette.AbstractRouletteCell;

/**
 * Created by qianminming on 16/08/15.
 * The variation cell class
 */
public  class VariationCell extends AbstractRouletteCell<InterfaceVariationOperator> {

    private static final long serialVersionUID = 3252127751059855225L;

    /**
     * Construction fucinton
     * @param probability
     * @param operator
     */
    public VariationCell(double probability, InterfaceVariationOperator operator) {
        super(probability, operator);
    }
}
