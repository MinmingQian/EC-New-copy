package adeuni.group.ec.algorithm.component.operator.variation.crossover.permutation;

import adeuni.group.ec.algorithm.component.operator.variation.crossover.AbstractPermutationCrossoverOperator;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.utility.randomnumbergenerator.ECRandom;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joe on 20/08/15.
 *
 * This class is used to implement the edge recombination crossover for permutation representation
 */
public class PermutationEdgeRecombinationCrossover extends AbstractPermutationCrossoverOperator {


    @Override
    public void genomeCrossover(PermutationRepresentation fatherGenome, PermutationRepresentation motherGenome, PermutationRepresentation childGenome1, PermutationRepresentation childGenome2) {

        executeCrossover(fatherGenome, motherGenome, childGenome1);
        executeCrossover(fatherGenome, motherGenome, childGenome2);
    }

    /**
     * Execute the edge recombination
     *
     * @param fatherGenome
     * @param motherGenome
     * @param childGenome
     */
    private void executeCrossover(PermutationRepresentation fatherGenome,PermutationRepresentation motherGenome,PermutationRepresentation childGenome){

        //Build the edge table with HashMap
        HashMap<Integer, ArrayList<Integer>> edgeTable = new HashMap<>();
        for (int i = 0; i < fatherGenome.size(); i++) {
            ArrayList<Integer> edges = new ArrayList<>();
            int pos1 = fatherGenome.getElementData().indexOf(i);
            int pos2 = motherGenome.getElementData().indexOf(i);
            ArrayList<Integer> edgeElement1 = new ArrayList<>();
            ArrayList<Integer> edgeElement2 = new ArrayList<>();


            edgeElement1.add(fatherGenome.get((pos1+fatherGenome.size() + 1) % fatherGenome.size()));
            edgeElement1.add(fatherGenome.get((pos1+fatherGenome.size() - 1) % fatherGenome.size()));

            edgeElement2.add(motherGenome.get((pos2+motherGenome.size() + 1) % motherGenome.size()));
            edgeElement2.add(motherGenome.get((pos2+motherGenome.size() - 1) % motherGenome.size()));


            for (Integer anEdgeElement1 : edgeElement1) {
                edges.add(anEdgeElement1);
            }

            //Remove the repeat element
            for (Integer anEdgeElement2 : edgeElement2) {
                if (edges.contains(String.valueOf(anEdgeElement2))) {
                    edges.set(edges.indexOf(anEdgeElement2), anEdgeElement2*-1);
                } else {
                    edges.add(anEdgeElement2);
                }
            }
            edgeTable.put(i, edges);
        }


        //Build the child
        childGenome.add((int) (ECRandom.nextDouble() * fatherGenome.size()));
        removeExist(edgeTable, childGenome.get(0), fatherGenome.size());

        while(childGenome.size() < fatherGenome.size()){
            int lastNode = childGenome.get(childGenome.size() - 1);
            //The edge near the lastnode
            ArrayList<Integer> lastNodeEdges = edgeTable.get(lastNode);
            int bestResult = -1;
            ArrayList<Integer> commonEdgeNodes = new ArrayList<>();
            ArrayList<Integer> normalEdgeNodes = new ArrayList<>();
            for (int i = 0; i < lastNodeEdges.size(); i++) {
                commonEdgeNodes = new ArrayList<>();
                normalEdgeNodes = new ArrayList<>();
                int currentNodeIndex;

                //Build the commonedge and normal edge
                if(lastNodeEdges.get(i) < 0){
                    currentNodeIndex = Math.abs(lastNodeEdges.get(i));
                    if(edgeTable.get(currentNodeIndex).size() == 0)
                        continue;
                    if(commonEdgeNodes.size() == 0){
                        commonEdgeNodes.add(currentNodeIndex);
                    }else if(edgeTable.get(commonEdgeNodes.get(0)).size() == edgeTable.get(currentNodeIndex).size()){
                        commonEdgeNodes.add(currentNodeIndex);
                    }else if(edgeTable.get(commonEdgeNodes.get(0)).size() > edgeTable.get(currentNodeIndex).size()){
                        commonEdgeNodes = new ArrayList<>();
                        commonEdgeNodes.add(currentNodeIndex);
                    }
                }else{
                    currentNodeIndex = Integer.valueOf(lastNodeEdges.get(i));
                    if(edgeTable.get(currentNodeIndex).size()==0)
                        continue;
                    if(normalEdgeNodes.size() == 0){
                        normalEdgeNodes.add(currentNodeIndex);
                    }else{
                        if(edgeTable.get(currentNodeIndex).size() == edgeTable.get(normalEdgeNodes.get(0)).size()){
                            normalEdgeNodes.add(currentNodeIndex);
                        }
                        if(edgeTable.get(currentNodeIndex).size() < edgeTable.get(normalEdgeNodes.get(0)).size()){
                            normalEdgeNodes = new ArrayList<>();
                            normalEdgeNodes.add(currentNodeIndex);
                        }
                    }
                }
            }

            //Random choosing when have the same value
            if(commonEdgeNodes.size() > 0 || normalEdgeNodes.size() > 0 ){
                if(commonEdgeNodes.size() > 0){
                    bestResult = commonEdgeNodes.get((int)(ECRandom.nextDouble()*commonEdgeNodes.size()));
                }else{
                    bestResult = normalEdgeNodes.get((int)(ECRandom.nextDouble()*normalEdgeNodes.size()));
                }
            }

            //Random pick up a point when the edge length is 0
            if(bestResult == -1){
                do {
                    bestResult = (int) (ECRandom.nextDouble() * fatherGenome.size());
                }while(childGenome.contains(bestResult));
            }

            childGenome.add(bestResult);

            //Refresh the edge table
            removeExist(edgeTable, childGenome.get(childGenome.size() - 1), fatherGenome.size());

        }

    }

    /**
     * This method is used to remove the previous element in the edge table
     *
     * @param edgeTable
     * @param cityRemove
     * @param cityCount
     * @return
     */
    private void removeExist(HashMap<Integer,ArrayList<Integer>> edgeTable,int cityRemove,int cityCount){
        //Build the remove list
        ArrayList<Integer> removeList = new ArrayList<>();
        removeList.add(cityRemove);
        removeList.add(cityRemove*-1);
        for(int i = 0; i < cityCount; i++) {
            if(edgeTable.get(i).contains(cityRemove) || edgeTable.get(i).contains(cityRemove*-1)){
                ArrayList<Integer> nodeEdge = edgeTable.get(i);
                nodeEdge.removeAll(removeList);
                edgeTable.put(i,nodeEdge);
            }
        }
    }

}
