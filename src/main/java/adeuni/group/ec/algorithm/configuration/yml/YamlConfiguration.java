package adeuni.group.ec.algorithm.configuration.yml;

import java.util.HashMap;
import java.util.List;

/**
 * Created by qianminming on 19/08/15.
 * The java been for the yaml configuration file
 */
public class YamlConfiguration {

    // the termination criteria pool
    public List<HashMap<String,Object>> terminationCriteriaPool;

    // the representation
    public String representation;

    // the variation roulette cell list
    public List<HashMap<String,Object>> variationRouletteCellList;
    // the parent selection roulette cell list
    public List<HashMap<String,Object>> parentSelectionRouletteCellList;
    // the survivor selection rouletter cell list
    public List<HashMap<String,Object>> survivorSelectionRouletteCellList;

    // the elite size
    public int eliteSize;
    // the offspring size
    public int offspringSize;
    // the survivor size
    public int survivorSize;
    // the immigrate size
    public int immigrateSize;

    // the flag show if parent allow to survive
    public boolean parentAllowToSurvive;

    // the algorithm name
    public String algorithm;
    // the file name
    public String filename;

}
