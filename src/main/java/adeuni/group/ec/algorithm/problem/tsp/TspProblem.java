package adeuni.group.ec.algorithm.problem.tsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by qianminming on 17/08/15.
 */
public class TspProblem {


    //The distance between two cities
    protected double[][] cityDistance;

    /**
     * Get the size of the cities
     *
     * @return
     */
    public int getCitySize() {
        return cityDistance.length;
    }

    /**
     * Build the TSP problem model with specific data source
     *
     * @param filename
     */
    public TspProblem (String filename) {
        parseTspFile(filename);
    }

    /**
     * Get the distance between two cities
     *
     * @param x
     * @param y
     * @return
     */
    public double getCityDistance(int x, int y) {
        return cityDistance[x][y];
    }

    /**
     * Parse the data file to the TSP problem model
     *
     * @param filename
     */
    public void parseTspFile(String filename){

        //Build the coordinate of the cities
        ArrayList<Double> xCor = new ArrayList<>();
        ArrayList<Double> yCor = new ArrayList<>();
        BufferedReader bufferedReader = null;

        try
        {
            String singleLine;
            bufferedReader = new BufferedReader(new FileReader(filename));
            while ((singleLine = bufferedReader.readLine()) != null)
            {
                String[] tempData = singleLine.split(" ");

                //Read the data when the first element is Integer
                try
                {
                    Integer.parseInt(tempData[0]);
                    xCor.add(Double.valueOf(tempData[1]));
                    yCor.add(Double.valueOf(tempData[2]));
                } catch (NumberFormatException e)
                {
                }
            }

            cityDistance = new double[xCor.size()][xCor.size()];

            //Calculate the distance between the cities
            for (int i = 0; i < xCor.size(); i++) {
                for (int j = 0; j < xCor.size(); j++) {
                    double temp = Math.pow(xCor.get(i) - xCor.get(j), 2) + Math.pow(yCor.get(i) - yCor.get(j), 2);
                    cityDistance[i][j] = Math.sqrt(temp);
                }
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}