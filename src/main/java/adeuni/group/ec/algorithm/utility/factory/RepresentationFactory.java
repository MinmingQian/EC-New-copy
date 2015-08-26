package adeuni.group.ec.algorithm.utility.factory;

import adeuni.group.ec.algorithm.component.representation.InterfaceRepresentation;
import adeuni.group.ec.algorithm.component.representation.permutation.PermutationRepresentation;

import java.util.ArrayList;

/**
 * Created by qianminming on 18/08/15.
 */
public class RepresentationFactory<T extends InterfaceRepresentation> {
    // the class used to construct the representation
    final Class<T> t;

    // the input list
    protected ArrayList<Number> inputList = new ArrayList<>();

    /**
     * The representation facotry construction factory
     * @param t
     * @param inputNumbers
     */
    public RepresentationFactory(Class<T> t, Number... inputNumbers) {
        this.t = t;
        for (int i = 0; i < inputNumbers.length; i++) {
            inputList.add(i, inputNumbers[i]);
        }
    }

    /**
     * To create the represention
     * @return the representation
     */
    public InterfaceRepresentation createRepresentation() {
        if (t == PermutationRepresentation.class) {
            return new PermutationRepresentation((int)inputList.get(0));
        }

        return null;
    }
}
