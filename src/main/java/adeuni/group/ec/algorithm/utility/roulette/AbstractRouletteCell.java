package adeuni.group.ec.algorithm.utility.roulette;

import adeuni.group.ec.algorithm.component.operator.InterfaceOperator;

/**
 * Created by qianminming on 17/08/15.
 * The abstract roulette cell class
 */
public abstract class AbstractRouletteCell<T extends InterfaceOperator> implements InterfaceRouletteCell{
    private static final long serialVersionUID = -6305667134534420264L;
    // the probability of the cell
    protected double probability;
    // the operator
    protected T operator;

    /**
     * The construction function
     * @param probability
     * @param operator
     */
    public AbstractRouletteCell(double probability, T operator) {
        this.probability = probability;
        this.operator = operator;
    }

    /**
     * Set the probability of the cell
     * @param probability
     */
    public  void setProbability(double probability) {
        this.probability = probability;
    }

    /**
     * Get the probability of the cell
     * @return
     */
    public double getProbability() {
        return probability;
    }

    /**
     * Get the operator
     * @return
     */
    public T getOperator() {
        return operator;
    }

    /**
     * Set the operator
     * @param operator
     */
    public void setOperator(T operator) {
        this.operator = operator;
    }
}
