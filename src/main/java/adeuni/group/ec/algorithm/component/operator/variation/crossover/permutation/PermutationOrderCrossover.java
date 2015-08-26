package adeuni.group.ec.algorithm.component.operator.variation.crossover.permutation;

import adeuni.group.ec.algorithm.component.operator.variation.crossover.AbstractPermutationCrossoverOperator;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.utility.randomnumbergenerator.ECRandom;

/**
 * Created by Joe on 19/08/15.
 *
 * This class is used to implement the order crossover for the permutation representation
 */
public class PermutationOrderCrossover extends AbstractPermutationCrossoverOperator {

    private static final long serialVersionUID = -5977309327371040475L;

    /**
     * The genome crossover function
     * @param fatherGenome
     * @param motherGenome
     * @param childGenome1
     * @param childGenome2
     */
    @Override
    public void genomeCrossover(PermutationRepresentation fatherGenome, PermutationRepresentation motherGenome, PermutationRepresentation childGenome1, PermutationRepresentation childGenome2) {
        executeCrossover(fatherGenome, motherGenome, childGenome1);
        executeCrossover(fatherGenome, motherGenome, childGenome2);
    }

    /**
     *  Returns a permutation representation of the result after executing the order crossover with the given parent
     *
     * @param fatherGenome  The permutation representation of father's genome
     * @param motherGenome  The permutation representation of mother's genome
     * @param childGenome   The permutation representation of child's genome
     */
    private void executeCrossover(PermutationRepresentation fatherGenome,PermutationRepresentation motherGenome,PermutationRepresentation childGenome){

        for (int i = 0; i < fatherGenome.size(); i++) {
            childGenome.add(null);
        }

        //Generate two random position
        int startPos = (int) (ECRandom.nextDouble() * fatherGenome.size());
        int endPos = startPos;
        while (endPos == startPos) {
            endPos = (int) (ECRandom.nextDouble() * fatherGenome.size());
        }

        //Set the start and end position of the father's genome
        int posStart = startPos < endPos ? startPos : endPos;
        int posEnd = startPos > endPos?startPos : endPos;

        //Copy part of the genome from father into child
        for (int i = posStart; i <= posEnd; i++) {
            childGenome.set(i, fatherGenome.get(i));
        }

        //Set the copy position in the child genome
        int addPosition = (posEnd + 1) % motherGenome.size();

        //Loop the mother genome from the next position of end position
        for (int i = (posEnd+1) % motherGenome.size(); i < posEnd + 1 + motherGenome.size(); i++)
        {
            // If child doesn't have the gene add it
            if (!childGenome.getElementData().contains(motherGenome.get(i % motherGenome.size())))
            {
                // Loop to find a spare position in the child's genome
                childGenome.set(addPosition, motherGenome.get(i % motherGenome.size()));
                addPosition = (addPosition + 1) % motherGenome.size();
            }
        }
    }
}
