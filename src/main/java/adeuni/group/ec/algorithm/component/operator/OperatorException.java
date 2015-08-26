package adeuni.group.ec.algorithm.component.operator;

/**
 * Created by qianminming on 16/08/15.
 */
public class OperatorException extends Exception {


    /**
     * the operator exception
     * @param message
     */
    public OperatorException(String message) {
        super(message);
    }

    /**
     * the operator exception
     * @param message
     * @param throwable
     */
    public OperatorException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
