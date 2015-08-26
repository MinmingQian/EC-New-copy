package adeuni.group.ec.algorithm.component.operator.variation.crossover.permutation;

import adeuni.group.ec.algorithm.component.operator.variation.crossover.AbstractPermutationCrossoverOperator;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;

/**
 * Created by Joe on 20/08/15.
 * Modified by Yang Lu ob 21/08/2015
 * The class is used to implement the cycle crossover for the permutation representation
 */

public class PermutationCycleCrossover extends AbstractPermutationCrossoverOperator {
    private static final long serialVersionUID = 4599534397392814893L;

    /**
     * excute cyclecrossover
     * @param fatherGenome
     * @param motherGenome
     * @param childGenome1
     * @param childGenome2
     */
    @Override
    public void genomeCrossover(PermutationRepresentation fatherGenome, PermutationRepresentation motherGenome, PermutationRepresentation childGenome1, PermutationRepresentation childGenome2) {

        for (int i = 0; i < fatherGenome.size(); i++) {
            childGenome1.add(null);
            childGenome2.add(null);
        }
        int cycleCount=1;
        for (int i = 0; i < fatherGenome.size() ; i++) {
            int tempInt;
            int tempPos = i;
            int tempParent = 1;
            int startInt = fatherGenome.get(i);
            tempInt = startInt;
            boolean isFlag = false;

            while(!isFlag)
            {
                //excute odd cycle crossover
                if(cycleCount%2==1)
                {

                    if(!childGenome1.contains(tempInt))
                    {
                        if(tempParent==1)
                        {
                            childGenome1.set(tempPos,tempInt);
                            tempInt=motherGenome.get(tempPos);
                            childGenome2.set(tempPos,tempInt);
                            tempParent++;

                            if(tempInt==startInt)
                            {
                                isFlag=true;
                                cycleCount++;
                            }

                        } else {
                            for (int j = 0; j < fatherGenome.size(); j++) {
                                if(fatherGenome.get(j)==tempInt)
                                {
                                    tempPos=j;
                                }
                            }
                            tempInt=fatherGenome.get(tempPos);
                            childGenome1.set(tempPos, tempInt);
                            tempInt=motherGenome.get(tempPos);
                            childGenome2.set(tempPos, tempInt);
                            tempParent++;

                            if(tempInt==startInt)
                            {
                                isFlag=true;
                                cycleCount++;
                            }
                        }
                    }else
                    {
                        isFlag=true;
                    }
                }else //excute even cycle crossover
                {
                    if(!childGenome2.contains(tempInt))
                    {
                        if(tempParent==1)
                        {
                            childGenome2.set(tempPos, tempInt);
                            tempInt=motherGenome.get(tempPos);
                            childGenome1.set(tempPos, tempInt);
                            tempParent++;

                            if(tempInt==startInt)
                            {
                                isFlag=true;
                                cycleCount++;
                            }

                        }else
                        {
                            for (int j = 0; j < fatherGenome.size(); j++) {
                                if(fatherGenome.get(j)==tempInt)
                                {
                                    tempPos=j;
                                }
                            }
                            tempInt=fatherGenome.get(tempPos);
                            childGenome2.set(tempPos, tempInt);
                            tempInt=motherGenome.get(tempPos);
                            childGenome1.set(tempPos, tempInt);


                            tempParent++;

                            if(tempInt==startInt)
                            {
                                isFlag=true;
                                cycleCount++;
                            }
                        }

                    }else
                    {
                        isFlag=true;
                    }
                }
            }
        }
    }

}

