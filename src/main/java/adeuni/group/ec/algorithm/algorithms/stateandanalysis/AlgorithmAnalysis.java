package adeuni.group.ec.algorithm.algorithms.stateandanalysis;

import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.solution.Solution;
import adeuni.group.ec.algorithm.utility.fitnessconverter.InterfaceFitnessConverter;

/**
 * Created by qianminming on 16/08/15.
 * Modified by Feifei Xiong on 21/08/15
 *
 *lgorithm analysis
 */
public class AlgorithmAnalysis<T extends InterfaceRepresentation> {
    AlgorithmState<T> algorithmState;


    /**
     * Construct function of the algorithm analysis
     *
     * @param algorithmState
     */
    public AlgorithmAnalysis(AlgorithmState<T> algorithmState) {
        this.algorithmState = algorithmState;
    }

    /**
     * analyse and store the algorithm stateandanalysis
     *
     */
    public void analyseAndStore() {
        String outputString = "";
        String doubleWithDigital ="";
//                = String.format("%.2f",23.59004);
        outputString += "IT:" + algorithmState.getCurrentIterationNumber()+"  ";
        outputString += "TIME:" + algorithmState.getTotalIterationDuration()+"  ";

        double result = algorithmState.getConfiguration().getFitnessConverter().decode(algorithmState.getCurrentSolutionSpace().getHighestSubList(1).get(0).getFitness());
        doubleWithDigital = String.format("%.4f",result);
        outputString += "RES:" + doubleWithDigital + "  ";
        doubleWithDigital = String.format("%.4f",algorithmState.getConfiguration().getFitnessConverter().decode(algorithmState.getCurrentSolutionSpace().getLowestSubList(1).get(0).getFitness()));
        outputString += "WORST:" + doubleWithDigital + "  ";

        String currentTour = "";
        if (algorithmState.getCurrentIterationNumber() == 0) {
            currentTour ="TOUR0" + algorithmState.getCurrentSolutionSpace().getHighestSubList(1).get(0).getRepresentation().getRepresentationString();
            algorithmState.getConfiguration().getLogger().writeLog(currentTour);
        } else if (algorithmState.getCurrentIterationNumber() == 5000) {
            currentTour ="TOUR5000:" + algorithmState.getCurrentSolutionSpace().getHighestSubList(1).get(0).getRepresentation().getRepresentationString();
            algorithmState.getConfiguration().getLogger().writeLog(currentTour);
        } else if (algorithmState.getCurrentIterationNumber() == 10000) {
            currentTour ="TOUR10000:" + algorithmState.getCurrentSolutionSpace().getHighestSubList(1).get(0).getRepresentation().getRepresentationString();
            algorithmState.getConfiguration().getLogger().writeLog(currentTour);
        } else if (algorithmState.getCurrentIterationNumber() == 20000) {
            currentTour ="TOUR20000:" + algorithmState.getCurrentSolutionSpace().getHighestSubList(1).get(0).getRepresentation().getRepresentationString();
            algorithmState.getConfiguration().getLogger().writeLog(currentTour);
        }

        double mean = calculateMean();
        doubleWithDigital = String.format("%.4f",mean);
        outputString += "MEAN:" + doubleWithDigital + "  ";

        double standardDeviation = calculateStandardDeviation(mean);
        doubleWithDigital = String.format("%.4f",standardDeviation);
        outputString += "STD:" + doubleWithDigital;

        if (algorithmState.getCurrentIterationNumber() % 40 == 0) {
            algorithmState.getConfiguration().getLogger().writeLog(outputString);
        }
        System.out.println(outputString);
    }


    /**
     * calcuate mean value of all the results
     * @return
     */
    public double calculateMean() {
        double totalResult = 0;
        InterfaceFitnessConverter converter = algorithmState.getConfiguration().getFitnessConverter();
        for (Solution solution : algorithmState.getCurrentSolutionSpace().getSolutionList()) {
            totalResult += converter.decode(solution.getFitness());
        }

        return  totalResult/algorithmState.getCurrentSolutionSpace().size();
    }


    /**
     * calculate the standard deviation of all the existing results
     * @param mean
     * @return
     */
    public double calculateStandardDeviation(double mean) {
        double totalSquareSum = 0;
        InterfaceFitnessConverter converter = algorithmState.getConfiguration().getFitnessConverter();
        for (Solution solution : algorithmState.getCurrentSolutionSpace().getSolutionList()) {
            totalSquareSum += Math.pow(converter.decode(solution.getFitness()) - mean, 2);
        }

        return  Math.sqrt(totalSquareSum/algorithmState.getCurrentSolutionSpace().size());
    }
}
