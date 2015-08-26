package adeuni.group.ec.algorithm.component.operator.variation.crossover.permutation;

import adeuni.group.ec.algorithm.component.operator.variation.crossover.AbstractPermutationCrossoverOperator;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;

import java.util.Objects;

/**
 * Created by qianminming on 14/08/15.
 */
public class PermutationPMXCrossover extends AbstractPermutationCrossoverOperator {


    private static final long serialVersionUID = -237943367376866346L;

    /**
     * The genome crossover function
     * @param fatherGenome
     * @param motherGenome
     * @param childGenome1
     * @param childGenome2
     */
    @Override
    public void genomeCrossover(PermutationRepresentation fatherGenome,
                                PermutationRepresentation motherGenome,
                                PermutationRepresentation childGenome1,
                                PermutationRepresentation childGenome2) {
        executeCrossover(fatherGenome,motherGenome,childGenome1);
        executeCrossover(fatherGenome,motherGenome,childGenome2);
    }

    public void executeCrossover(PermutationRepresentation fatherGenome,PermutationRepresentation motherGenome,PermutationRepresentation childGenome){
        for (int i = 0; i < fatherGenome.size(); i++) {
            childGenome.add(null);
        }

        boolean isEqual = true;
        int pos1 = 0;
        int pos2 = 0;
        int startPos;
        int endPos;
        while(isEqual) {
            pos1 = (int) (Math.random() * fatherGenome.size());
            pos2 = (int) (Math.random() * fatherGenome.size());
            if(pos1 != pos2)
                isEqual = false;
        }
        startPos = pos1 < pos2 ? pos1 : pos2;
        endPos = pos1 < pos2 ? pos2 : pos1;
        //copy randomly selected segment from first parent into offspring
        for (int i = startPos; i <= endPos ; i++) {
            childGenome.set(i, fatherGenome.get(i));
        }
        //consider in turn the placement of the elements that occur in the middle segment of parent 2 but not parent 1.
        for (int i = startPos; i <= endPos; i++) {

            if(!childGenome.contains(motherGenome.get(i)))
            {
                int tempPos = i;
                boolean isFlag = false;
                while(!isFlag)
                {
                    for (int j = 0; j < motherGenome.size(); j++) {
                        if(Objects.equals(childGenome.get(tempPos), motherGenome.get(j)))
                        {
                            if((j < startPos) || (j > endPos))
                            {
                                childGenome.set(j, motherGenome.get(i));
                                isFlag = true;
                            }else
                            {
                                tempPos = j;
                                break;
                            }
                        }
                    }
                }
            }
        }

        //copy remaining elements from second parent into same positions in offspring
        for (int i = 0; i < motherGenome.size(); i++) {
            if(!childGenome.contains(motherGenome.get(i)))
            {
                childGenome.set(i, motherGenome.get(i));
            }
        }
    }
}
