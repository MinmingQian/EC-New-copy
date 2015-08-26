package adeuni.group.ec.algorithm.component.solution;

import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.solution.comparator.SolutionFitnessComparator;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by qianminming on 15/08/15.
 * TODO: to find out why here need the comparator, and what is the comparator
 */
public class SolutionSpace<T extends InterfaceRepresentation> implements Serializable{


    private static final long serialVersionUID = -1960941583833227715L;
    // the solution list
    List<Solution<T>> solutionList;

    /** The comparator. */
    protected Comparator<? super Solution<T>> comparator;

    /**
     * Create an empty solution space, for new generation use.
     */
    public SolutionSpace () {
        solutionList= new ArrayList<>();
        comparator = new SolutionFitnessComparator();
    }

    /**
     * Create solution space with exist solution list
     * @param solutionList
     */
    public SolutionSpace (List<Solution<T>> solutionList) {
        this.solutionList = solutionList;
        comparator = new SolutionFitnessComparator();
    }

    /**
     * Construct the solution space
     * @param size
     */
    public SolutionSpace (int size, Class<T> c) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        solutionList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            T presentation = c.getConstructor().newInstance();
            solutionList.add(new Solution<T>((T)presentation));
        }
        comparator = new SolutionFitnessComparator();
    }

    /**
     * Get the size of the solution space
     * @return
     */
    public int size() {
        return solutionList.size();
    }


    /**
     * Need deep copy, use the add function, should not just pass the reference.
     * @param solutionSection
     */
    public void add(List<Solution<T>> solutionSection) {
        solutionSection.forEach(solutionList::add);
    }

    /**
     * Remove the element with index of the solution list
     * @param index the index of the element
     * @return the removed element
     */
    public Solution<T> remove(int index) {
        return solutionList.remove(index);
    }

    /**
     * Remove the solution of the solution list
     * @param solution the solution
     */
    public void remove(Solution solution) {
        solutionList.remove(solution);
    }

    /**
     * remove the sublist of the solution space
     * @param solutionSection
     */
    public void remove(List<Solution<T>> solutionSection) {
        solutionSection.forEach(solutionList::remove);
    }

    /**
     * Remove all the element in the solution list
     */
    public void removeAll() {
        solutionList.clear();
    }

    /**
     * Sort the solution space
     */
    public void sort() {
        Collections.sort(solutionList, comparator);
    }

    /**
     * Set the element of the solution
     * @param index
     * @param solution
     */
    public void set(int index, Solution solution) {
        solutionList.set(index, solution);
    }

    /**
     * Add element to the solution to index
     * @param index
     * @param solution
     */
    public void add(int index, Solution solution) {
        solutionList.add(index, solution);
    }

    /**
     * Add  the solution to the solution space
     * @param solution
     */
    public void add(Solution solution) {
        solutionList.add(solution);
    }

    /**
     * Get the element of index
     * @param index
     * @return
     */
    public Solution<T> get(int index) {
        return solutionList.get(index);
    }

    /**
     * Get solution list of the solution space
     * @return
     */
    public List<Solution<T>> getSolutionList() {
        return solutionList;
    }

    /**
     * Get the highest sublist
     * @param subListSize
     * @return
     */
    public List<Solution<T>> getHighestSubList(int subListSize) {
        List<Solution<T>> subList = new ArrayList<>();

        this.sort();
        solutionList.subList(size()-subListSize,size()).forEach(subList::add);

        return subList;
    }

    /**
     * Get the lowest sublist
     * @param subListSize
     * @return
     */
    public List<Solution<T>> getLowestSubList(int subListSize) {
        List<Solution<T>> subList = new ArrayList<>();

        this.sort();
        solutionList.subList(0,subListSize).forEach(subList::add);

        return subList;
    }

    /**
     * Check if the solution space contains the solution
      * @param solution
     * @return
     */
    public boolean contains(Solution<T> solution) {
        for (Solution<T> eachSolution: solutionList) {
            if (eachSolution.getRepresentation().getRepresentationString().equals(solution.getRepresentation().getRepresentationString())) {
                return true;
            }
        }
        return false;
    }
}
