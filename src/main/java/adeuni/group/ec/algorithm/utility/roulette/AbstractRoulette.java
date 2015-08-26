package adeuni.group.ec.algorithm.utility.roulette;

import adeuni.group.ec.algorithm.component.operator.InterfaceOperator;
import adeuni.group.ec.algorithm.utility.randomnumbergenerator.ECRandom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qianminming on 17/08/15.
 */
public abstract class AbstractRoulette<T extends InterfaceOperator> implements InterfaceRoulette {
    private static final long serialVersionUID = -3875306205006077044L;
    List<AbstractRouletteCell<T>> cellList;

    // the total probability
    protected double totalProbability;

    /**
     * the construction function for the abstract roulette
     */
    public  AbstractRoulette() {
        cellList = new ArrayList<>();
    }

    /**
     * Select one operator
     * @return the selecte operator
     */
    public T selectOperator() {
        double random = ECRandom.nextDouble();
        for (AbstractRouletteCell<T> cell: cellList) {
            random -= cell.getProbability();
            if (random <= 0 ) {
                //System.out.println(random + cell.getOperator().getClass().toString());
                return cell.getOperator();
            }
        }

        return null;
    }

    /**
     * Add cell to the roulette
     * @param cell the cell to be added
     * @throws RouletteException
     */
    public void addCell(AbstractRouletteCell<T> cell) throws RouletteException {
        totalProbability += cell.getProbability();
        checkProbability();
        cellList.add(cell);
    }

    /**
     * Check if the probability exist 1
     * @throws RouletteException
     */
    public void checkProbability() throws RouletteException {
        if (totalProbability > 1) throw new RouletteException("The total probability is bigger than one, current is: "+totalProbability);
    }
}
