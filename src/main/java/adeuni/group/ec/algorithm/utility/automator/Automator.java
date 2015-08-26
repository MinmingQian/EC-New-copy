package adeuni.group.ec.algorithm.utility.automator;

import adeuni.group.ec.algorithm.configuration.yml.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by qianminming on 22/08/15.
 */
public class Automator {



    protected ArrayList<Integer> populationSizes;
    protected ArrayList<String> filenames;
    protected ArrayList<Integer> generations;
    protected ArrayList<String> variations;
    protected ArrayList<String> parentSelections;
    protected ArrayList<String> survivorSelections;
    protected ArrayList<Boolean> parentSurvivorJudges;
    protected ArrayList<Boolean> eliteExistJudges;

    protected ArrayList<ArrayList<HashMap<String, Object>>> possibleParentSelectionRoulettes;
    protected ArrayList<ArrayList<HashMap<String, Object>>> possibleSurvivorSelectionRoulettes;
    protected ArrayList<ArrayList<HashMap<String, Object>>> possibleVariationRoulettes;



    protected int generation;
    protected String filename;
    protected int eliteSize;
    protected int survivorSize;
    protected boolean parentAllowToSurvive;
    protected int offspringSize;
    protected ArrayList<HashMap<String,Object>> variationRouletteCellList;
    protected ArrayList<HashMap<String,Object>> parentSelectionRouletteCellList;
    protected ArrayList<HashMap<String,Object>> survivorSelectionRouletteCellList;

    protected YamlConfiguration yamlConfiguration;

    long count = 0;


    /**
     * construct the automater
     */
    public Automator(){
    }

    /**
     * init inver ove algortihm configurations
     * if don't need some element, ensure it includes one exit value of the arraylist
     */
    public void initInverOverAlgorithm() {
        populationSizes = new ArrayList<>(Arrays.asList(50));
        filenames = new ArrayList<>(Arrays.asList("EIL51", "EIL76", "EIL101", "ST70", "KROA100", "KROC100", "KROD100", "LIN105", "PCB442", "PR2392"));
        generations = new ArrayList<>(Arrays.asList(10000));
        variations = new ArrayList<>(Arrays.asList("PermutationCycleCrossover"));
        parentSelections = new ArrayList<>(Arrays.asList("ElitismSelection"));
        survivorSelections = new ArrayList<>(Arrays.asList("BestSelection"));
        parentSurvivorJudges = new ArrayList<>(Arrays.asList(true));
        eliteExistJudges = new ArrayList<>(Arrays.asList(true));
        yamlConfiguration = new YamlConfiguration();
        yamlConfiguration.algorithm = "InverOverEA";
    }

    /**
     * init qxyz algorithm
     */
    public void initQxyzAlgorithm() {
        populationSizes = new ArrayList<>(Arrays.asList(10, 20, 50, 100));
        filenames = new ArrayList<>(Arrays.asList("EIL51", "EIL76", "EIL101", "ST70", "KROA100", "KROC100", "KROD100", "LIN105", "PCB442", "PR2392"));
        generations = new ArrayList<>(Arrays.asList(20000));
        variations = new ArrayList<>(Arrays.asList("PermutationCycleCrossover"));
        parentSelections = new ArrayList<>(Arrays.asList("ElitismSelection"));
        survivorSelections = new ArrayList<>(Arrays.asList("BestSelection"));
        parentSurvivorJudges = new ArrayList<>(Arrays.asList(true));
        eliteExistJudges = new ArrayList<>(Arrays.asList(true));
        yamlConfiguration = new YamlConfiguration();
        yamlConfiguration.algorithm = "QxyzAlgorithm";
    }

    /**
     * int evolutionary algority
     */
    public void initEvolutionaryAlgorithm () {
        populationSizes = new ArrayList<>(Arrays.asList(10, 20, 50, 100));
        filenames = new ArrayList<>(Arrays.asList("EIL51", "EIL76", "EIL101", "ST70", "KROA100", "KROC100", "KROD100", "LIN105", "PCB442", "PR2392"));
//        filenames = new ArrayList<>(Arrays.asList("EIL51"));
        generations = new ArrayList<>(Arrays.asList(20000));
        variations = new ArrayList<>(Arrays.asList("PermutationCycleCrossover", "PermutationEdgeRecombinationCrossover",
                "PermutationOrderCrossover", "PermutationPMXCrossover",
                "PermutationInsertMutation", "PermutationInversionMutation",
                "PermutationScrambleMutation", "PermutationSwapMutation"));
        parentSelections = new ArrayList<>(Arrays.asList("ElitismSelection", "FitnessProportionalSelection", "TournamentSelection"));
        survivorSelections = new ArrayList<>(Arrays.asList("BestSelection", "FitnessProportionalSelection"));
        parentSurvivorJudges = new ArrayList<>(Arrays.asList(true));
        eliteExistJudges = new ArrayList<>(Arrays.asList(true));
        yamlConfiguration = new YamlConfiguration();
        yamlConfiguration.algorithm = "EvolutionaryAlgorithm";

    }

    /**
     * set up the yaml configration
     */
    public void setupYamlConfiguration() throws IOException {
        possibleVariationRoulettes = calculatePossibleCombination(variations, "variation");
        possibleSurvivorSelectionRoulettes =  calculateOnePossibleCombination(survivorSelections, "selection");
        possibleParentSelectionRoulettes = calculateOnePossibleCombination(parentSelections, "selection");
        combinationGenerator();

        System.out.println(count);
    }


    /**
     * dump the yaml configuration file
     * @throws IOException
     */
    public void dumpYamlConfiguration(String filename) throws IOException {
        Yaml yaml = new Yaml();
        FileWriter writer = new FileWriter(filename);

        /* start configure the yaml configuration file */
        yamlConfiguration.representation = "PermutationRepresentation";

        yamlConfiguration.eliteSize = this.eliteSize;
        yamlConfiguration.offspringSize = this.offspringSize;
        yamlConfiguration.survivorSize = this.survivorSize;
        yamlConfiguration.immigrateSize = 0;

        yamlConfiguration.parentAllowToSurvive = true;

        yamlConfiguration.filename = this.filename;

        yamlConfiguration.variationRouletteCellList = variationRouletteCellList;

        yamlConfiguration.parentSelectionRouletteCellList = parentSelectionRouletteCellList;

        yamlConfiguration.survivorSelectionRouletteCellList = survivorSelectionRouletteCellList;


        /* set up the terminationCriteriaPool */
        HashMap<String, Object> map;
        ArrayList<HashMap<String, Object>> terminationCriteriaPool = new ArrayList<>();
        map = new HashMap<>();
        map.put("criterion", "IterationTerminationCriterion");
        map.put("maxIterationRound", this.generation);
        terminationCriteriaPool.add(map);
        map = new HashMap<>();
        map.put("criterion", "ExecutionTimeTerminationCriterion");
        map.put("maxExecutionTime", 14400000);
        terminationCriteriaPool.add(map);
        yamlConfiguration.terminationCriteriaPool = terminationCriteriaPool;


        yaml.dump(yamlConfiguration, writer);
        writer.close();
    }


    /**
     * generate all the possible combinations
     */
    public void combinationGenerator() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();


        for (String filename: filenames) {
            count = 0;
            for (int populationSize: populationSizes) {
                for (int generation: generations) {
                    for (boolean eliteExist: eliteExistJudges) {
                        for (boolean isParentSurvive: parentSurvivorJudges) {
                            for (ArrayList<HashMap<String, Object>> parentSelectionRoulette: possibleParentSelectionRoulettes) {
                                for (ArrayList<HashMap<String, Object>> survivorSelectionRoulette: possibleSurvivorSelectionRoulettes) {
                                    for (ArrayList<HashMap<String, Object>> variationRoulette: possibleVariationRoulettes) {
                                        if (eliteExist) {
                                            this.eliteSize = 1;
                                        } else {
                                            this.eliteSize = 0;
                                        }
                                        this.survivorSize = populationSize - this.eliteSize;
                                        this.offspringSize= this.survivorSize + 10;
                                        this.generation = generation;
                                        this.filename = "resource/tsp/" + filename.toLowerCase() + ".tsp";
                                        this.parentAllowToSurvive = isParentSurvive;
                                        this.parentSelectionRouletteCellList = parentSelectionRoulette;
                                        this.survivorSelectionRouletteCellList = survivorSelectionRoulette;
                                        this.variationRouletteCellList = variationRoulette;

//                                        System.out.println(this.eliteSize + " " +
//                                                this.survivorSize + " " +
//                                                this.generation + " " +
//                                                this.filename + " " +
//                                                this.parentAllowToSurvive + " " +
//                                                this.variationRouletteCellList.toString() + " " +
//                                                this.parentSelectionRouletteCellList.toString() +" " +
//                                                this.survivorSelectionRouletteCellList.toString());
                                        String configFileName = filename + "," +
                                                yamlConfiguration.algorithm + "," +
                                                populationSize + "," +
                                                yamlConfiguration.eliteSize + "," +
                                                yamlConfiguration.parentAllowToSurvive + "," +
                                                variationRoulette.toString() + "," +
                                                parentSelectionRoulette.toString() + "," +
                                                survivorSelectionRoulette.toString();



                                        count ++;
                                        String path = "resource/autoconfig/" + yamlConfiguration.algorithm + "/"  + filename.toLowerCase();
                                        File  f = new File(path);
                                        f.mkdirs();
                                        dumpYamlConfiguration(path + "/" + count + "-autoconfig"  +".yml");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public ArrayList<ArrayList<HashMap<String, Object>>> calculateOnePossibleCombination(ArrayList<String> inputOperators, String operatorType) {
        ArrayList<ArrayList<HashMap<String, Object>>> allPossibleCombinations = new ArrayList<>();

        ArrayList<HashMap<String, Object>> possibleCombination ;
        HashMap<String, Object> operatorMap = null;

        // C 8 ,1
        for (String operator: inputOperators) {
            operatorMap = new HashMap<>();
            operatorMap.put(operatorType, operator);
            operatorMap.put("weight", 1.0);
            possibleCombination = new ArrayList<>();
            possibleCombination.add(operatorMap);
            allPossibleCombinations.add(possibleCombination);
        }

        return allPossibleCombinations;
    }



    /**
     * calculate possibale combinations of each list
     * @param inputOperators
     * @param operatorType
     * @return
     */
    public ArrayList<ArrayList<HashMap<String, Object>>> calculatePossibleCombination(ArrayList<String> inputOperators, String operatorType) {
        ArrayList<ArrayList<HashMap<String, Object>>> allPossibleCombinations = new ArrayList<>();



        ArrayList<HashMap<String, Object>> possibleCombination ;
        HashMap<String, Object> operatorMap = null;

        // C 8 ,1
        for (String operator: inputOperators) {
            operatorMap = new HashMap<>();
            operatorMap.put(operatorType, operator);
            operatorMap.put("weight", 1.0);
            possibleCombination = new ArrayList<>();
            possibleCombination.add(operatorMap);
            allPossibleCombinations.add(possibleCombination);
        }



        double maxWeight = 1.0;
        // C 8 , 2
        for (int i = 0; i < inputOperators.size(); i++) {
            for (int j = i+1; j < inputOperators.size(); j++) {
                for (double k = 1.0; k <= maxWeight; k++) {
                    for (double l = 1.0; l <= maxWeight; l++) {
                        if (k == 1.0 || k != l) {
                            possibleCombination = new ArrayList<>();
                            operatorMap = new HashMap<>();
                            operatorMap.put(operatorType, inputOperators.get(i));
                            operatorMap.put("weight", k);
                            possibleCombination.add(operatorMap);
                            operatorMap = new HashMap<>();
                            operatorMap.put(operatorType, inputOperators.get(j));
                            operatorMap.put("weight", l);
                            possibleCombination.add(operatorMap);
                            allPossibleCombinations.add(possibleCombination);
                        }
                    }

                }
            }
        }

        // C 8, 3
        for (int i = 0; i < inputOperators.size(); i++) {
            for (int j = i+1; j < inputOperators.size(); j++) {
                for (int k = j+1; k < inputOperators.size(); k++) {
                    for (double l = 1.0; l <= maxWeight; l++) {
                        for (double m = 1.0; m <= maxWeight; m++) {
                            for (double n = 1.0; n <= maxWeight; n++) {
                                if (l == 1.0 || !(l == m && m == n)) {
                                    possibleCombination = new ArrayList<>();
                                    operatorMap = new HashMap<>();
                                    operatorMap.put(operatorType, inputOperators.get(i));
                                    operatorMap.put("weight", l);
                                    possibleCombination.add(operatorMap);
                                    operatorMap = new HashMap<>();
                                    operatorMap.put(operatorType, inputOperators.get(j));
                                    operatorMap.put("weight", m);
                                    possibleCombination.add(operatorMap);
                                    operatorMap = new HashMap<>();
                                    operatorMap.put(operatorType, inputOperators.get(k));
                                    operatorMap.put("weight", n);
                                    possibleCombination.add(operatorMap);
                                    allPossibleCombinations.add(possibleCombination);
                                }
                            }
                        }
                    }
                }
            }
        }


        return  allPossibleCombinations;
    }


    /**
     * the main entrance of the automator
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
        Automator automator = new Automator();

//        automator.initInverOverAlgorithm();
        automator.initEvolutionaryAlgorithm();

        automator.setupYamlConfiguration();
        automator.dumpYamlConfiguration("resource/autoconfig/test.yml");
    }
}
