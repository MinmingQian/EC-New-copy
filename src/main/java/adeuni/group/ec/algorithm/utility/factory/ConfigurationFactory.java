package adeuni.group.ec.algorithm.utility.factory;

import adeuni.group.ec.algorithm.algorithms.ea.EvolutionaryAlgorithm;
import adeuni.group.ec.algorithm.algorithms.ea.InverOverEA;
import adeuni.group.ec.algorithm.component.operator.selection.InterfaceSelection;
import adeuni.group.ec.algorithm.component.operator.selection.parent.ElitismSelection;
import adeuni.group.ec.algorithm.component.operator.selection.parent.FitnessProportionalSelection;
import adeuni.group.ec.algorithm.component.operator.selection.parent.TournamentSelection;
import adeuni.group.ec.algorithm.component.operator.selection.survivor.BestSelection;
import adeuni.group.ec.algorithm.component.operator.variation.InterfaceVariationOperator;
import adeuni.group.ec.algorithm.component.operator.variation.crossover.permutation.PermutationCycleCrossover;
import adeuni.group.ec.algorithm.component.operator.variation.crossover.permutation.PermutationEdgeRecombinationCrossover;
import adeuni.group.ec.algorithm.component.operator.variation.crossover.permutation.PermutationOrderCrossover;
import adeuni.group.ec.algorithm.component.operator.variation.crossover.permutation.PermutationPMXCrossover;
import adeuni.group.ec.algorithm.component.operator.variation.mutation.permutation.PermutationInsertMutation;
import adeuni.group.ec.algorithm.component.operator.variation.mutation.permutation.PermutationInversionMutation;
import adeuni.group.ec.algorithm.component.operator.variation.mutation.permutation.PermutationScrambleMutation;
import adeuni.group.ec.algorithm.component.operator.variation.mutation.permutation.PermutationSwapMutation;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;
import adeuni.group.ec.algorithm.component.termination.TerminationCriteriaPool;
import adeuni.group.ec.algorithm.component.termination.criteria.ExecutionTimeTerminationCriterion;
import adeuni.group.ec.algorithm.component.termination.criteria.IterationTerminationCriterion;
import adeuni.group.ec.algorithm.configuration.Configuration;
import adeuni.group.ec.algorithm.configuration.yml.YamlConfiguration;
import adeuni.group.ec.algorithm.utility.logger.Logger;
import adeuni.group.ec.algorithm.utility.roulette.RouletteException;
import adeuni.group.ec.algorithm.utility.roulette.operator.VariationCell;
import adeuni.group.ec.algorithm.utility.roulette.operator.VariationRoulette;
import adeuni.group.ec.algorithm.utility.roulette.selection.SelectionCell;
import adeuni.group.ec.algorithm.utility.roulette.selection.SelectionRoulette;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by qianminming on 19/08/15.
 * The configuration factory used the yamconfiguration to generate the configuration for the algorithm
 */
public class ConfigurationFactory {

    // the yaml configuration
    YamlConfiguration yamlConfiguration;

    // the configuration file name
    String configurationFileName;

    /**
     * Consturction function
     * @param yamlConfiguration
     */
    public ConfigurationFactory(YamlConfiguration yamlConfiguration) {
        this.yamlConfiguration = yamlConfiguration;
    }

    /**
     * Construction funcntion
     * @param configurationFileName the configuration filename
     * @throws FileNotFoundException
     */
    public ConfigurationFactory(String configurationFileName) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream input = new FileInputStream(new File(configurationFileName));
        this.configurationFileName = configurationFileName;
        this.yamlConfiguration = (YamlConfiguration) yaml.load(input);
    }

    /**
     * The configuration create function
     * @return
     * @throws FileNotFoundException
     * @throws RouletteException
     */
    public Configuration createConfiguration() throws FileNotFoundException, RouletteException {
        Configuration configuration = null;

        //construct configuration
        if (yamlConfiguration.representation.equals("PermutationRepresentation")) {
            configuration = new Configuration<PermutationRepresentation>();
        }


        //configure algorithm
        if (yamlConfiguration.algorithm.equals("EvolutionaryAlgorithm")) {
            configuration.setAlgorithm(new EvolutionaryAlgorithm<>(configuration));
        } else if (yamlConfiguration.algorithm.equals("InverOverEA")) {
            configuration.setAlgorithm(new InverOverEA<>(configuration));
        }

        //configure population structure
        int population = yamlConfiguration.eliteSize +
                         yamlConfiguration.survivorSize +
                         yamlConfiguration.immigrateSize;
        configuration.setSolutionSpaceSize(population);
        configuration.setEliteSize(yamlConfiguration.eliteSize);
        configuration.setOffspringSize(yamlConfiguration.offspringSize);
        configuration.setSurvivorSize(yamlConfiguration.survivorSize);
        configuration.setImmigrateSize(yamlConfiguration.immigrateSize);
        configuration.setParentAllowToSurvive(yamlConfiguration.parentAllowToSurvive);
        configuration.setFileName(yamlConfiguration.filename);


        //configure variation roulette section
        String variationString = "";
        double totalVariationRouletteWeight = 0;
        for (Map<String, Object> variationCellMap : yamlConfiguration.variationRouletteCellList) {
            totalVariationRouletteWeight += (double)variationCellMap.get("weight");
        }
        VariationRoulette variationRoulette = new VariationRoulette();
        for (Map<String, Object> variationCellMap : yamlConfiguration.variationRouletteCellList) {
            double probability = (double)variationCellMap.get("weight") / totalVariationRouletteWeight;
            VariationCell variationCell = null;

            //construct each variation operator type
            String variationCellType = (String) variationCellMap.get("variation");
            InterfaceVariationOperator variationOperator ;
            switch (variationCellType) {
                case "PermutationPMXCrossover":
                    variationOperator = new PermutationPMXCrossover();
                    break;
                case "PermutationOrderCrossover":
                    variationOperator = new PermutationOrderCrossover();
                    break;
                case "PermutationSwapMutation":
                    variationOperator = new PermutationSwapMutation();
                    break;
                case "PermutationEdgeRecombinationCrossover":
                    variationOperator = new PermutationEdgeRecombinationCrossover();
                    break;
                case "PermutationCycleCrossover":
                    variationOperator = new PermutationCycleCrossover();
                    break;
                case "PermutationInsertMutation":
                    variationOperator = new PermutationInsertMutation();
                    break;
                case "PermutationInversionMutation":
                    variationOperator = new PermutationInversionMutation();
                    break;
                case "PermutationScrambleMutation":
                    variationOperator = new PermutationScrambleMutation();
                    break;
                default:
                    throw new IllegalStateException("variation operator was not implemented: " + variationCellType);
            }

            variationString += variationCellType + ":" + probability + " ";

            variationCell = new VariationCell(probability, variationOperator);
            variationRoulette.addCell(variationCell);
        }
        configuration.setVariationRoulette(variationRoulette);



        //configure parent selection roulette
        double totalParentSelectionRouletteWeight = 0;
        for (Map<String, Object> parentSelectionCellMap: yamlConfiguration.parentSelectionRouletteCellList) {
            totalParentSelectionRouletteWeight += (double)parentSelectionCellMap.get("weight");
        }
        String parentSelectionString = "";
        SelectionRoulette parentSelectionRoulette = new SelectionRoulette();
        for (Map<String, Object> parentSelectionCellMap: yamlConfiguration.parentSelectionRouletteCellList) {
            double probability = (double)parentSelectionCellMap.get("weight")/totalParentSelectionRouletteWeight;
            SelectionCell selectionCell = null;

            //construct each selection operator type
            String selectionCellType = (String) parentSelectionCellMap.get("selection");
            InterfaceSelection selectionOperator;
            switch (selectionCellType) {
                case "BestSelection":
                    selectionOperator = new BestSelection<>();
                    break;
                case "TournamentSelection":
                    selectionOperator = new TournamentSelection<>(7);
                    break;
                case "FitnessProportionalSelection":
                    selectionOperator = new FitnessProportionalSelection<>();
                    break;
                case "ElitismSelection":
                    selectionOperator = new ElitismSelection<>();
                    break;
                default:
                    throw new IllegalStateException("variation operator was not implemented: " + selectionCellType);
            }

            parentSelectionString += selectionCellType + ":" + probability + " ";

            selectionCell = new SelectionCell(probability,selectionOperator);
            parentSelectionRoulette.addCell(selectionCell);
        }
        configuration.setParentSelectionRoulette(parentSelectionRoulette);



        //configure survivor selection roulette
        String survivorString = "";
        double totalSurvivorSelectionRouletteWeight = 0;
        for (Map<String, Object> survivorSelectionCellMap: yamlConfiguration.survivorSelectionRouletteCellList) {
            totalSurvivorSelectionRouletteWeight += (double)survivorSelectionCellMap.get("weight");
        }
        SelectionRoulette survivorSelectionRoulette = new SelectionRoulette();
        for (Map<String, Object> survivorSelectionCellMap: yamlConfiguration.survivorSelectionRouletteCellList) {
            double probability = (double)survivorSelectionCellMap.get("weight")/totalSurvivorSelectionRouletteWeight;
            SelectionCell selectionCell = null;

            //construct each selection operator type
            String selectionCellType = (String) survivorSelectionCellMap.get("selection");
            InterfaceSelection selectionOperator;
            switch (selectionCellType) {
                case "BestSelection":
                    selectionOperator = new BestSelection<>();
                    break;
                case "TournamentSelection":
                    selectionOperator = new TournamentSelection<>(7);
                    break;
                case "FitnessProportionalSelection":
                    selectionOperator = new FitnessProportionalSelection<>();
                    break;
                case "ElitismSelection":
                    selectionOperator = new ElitismSelection<>();
                    break;
                default:
                    throw new IllegalStateException("variation operator was not implemented: " + selectionCellType);
            }

            survivorString += selectionCellType + ":" + probability + " ";

            selectionCell = new SelectionCell(probability,selectionOperator);
            survivorSelectionRoulette.addCell(selectionCell);
        }
        configuration.setSurvivorSelectionRoulette(survivorSelectionRoulette);



        //configure termination criteria pool
        TerminationCriteriaPool terminationCriteriaPool = new TerminationCriteriaPool();
        for (Map<String,Object> criterionDrop: yamlConfiguration.terminationCriteriaPool) {
            if (criterionDrop.get("criterion").equals("IterationTerminationCriterion")){
                int maxIterationRound = (int) criterionDrop.get("maxIterationRound");
                IterationTerminationCriterion iterationTerminationCriterion = new IterationTerminationCriterion(maxIterationRound);
                terminationCriteriaPool.add(iterationTerminationCriterion);
            } else if(criterionDrop.get("criterion").equals("ExecutionTimeTerminationCriterion")) {
                int maxExecutionTime = (int) criterionDrop.get("maxExecutionTime");
                ExecutionTimeTerminationCriterion executionTimeTerminationCriterion = new ExecutionTimeTerminationCriterion(maxExecutionTime);
                terminationCriteriaPool.add(executionTimeTerminationCriterion);
            }
        }
        configuration.setTerminationCriteriaPool(terminationCriteriaPool);


        // create the loger
        Path p = Paths.get(yamlConfiguration.filename);
        String theFilename = p.getFileName().toString();
        String problemName = theFilename.substring(0, theFilename.lastIndexOf('.'));

        Path p2 = Paths.get(configurationFileName);
        String configFilename = p2.getFileName().toString();
        String configFileNumber = configFilename.split("-")[0];

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();


        String logFirstLine = problemName + "," +
                              yamlConfiguration.algorithm + "," +
                              population + "," +
                              yamlConfiguration.eliteSize + "," +
                              yamlConfiguration.parentAllowToSurvive + "," +
                              variationString + "," +
                              parentSelectionString + "," +
                              survivorString;

        String logFilenamePath = "resource/logfile/" + yamlConfiguration.algorithm + "/" + problemName;
        String logFilename = configFileNumber + "-log-" + dateFormat.format(date) + ".log";
        File  f = new File(logFilenamePath);

        f.mkdirs();
        Logger logger = new Logger(logFilenamePath + "/" + logFilename);
        logger.writeLog(logFirstLine);

        configuration.setLogger(logger);


        return configuration;
    }
}
