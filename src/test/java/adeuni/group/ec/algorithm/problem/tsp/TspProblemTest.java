package adeuni.group.ec.algorithm.problem.tsp;

import org.junit.Test;

/**
 * Created by qianminming on 20/08/15.
 */
public class TspProblemTest {

    @Test
    public void testGetCityDistance() throws Exception {

    }

    @Test
    public void testParseTspFile() throws Exception {
        TspProblem tspProblem = new TspProblem("resource/tsp/eil10.tsp");
        for (int i = 0; i < 10; i++) {
            String temp="";
            for (int j = 0; j < 10; j++) {
                temp += "["+tspProblem.getCityDistance(i,j)+"]";
            }
            System.out.println(temp);
        }
    }
}