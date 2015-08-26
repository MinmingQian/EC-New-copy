package adeuni.group.ec.algorithm.utility.roulette;

/**
 * Created by qianminming on 17/08/15.
 * The exception of the roulette
 */
public class RouletteException extends Exception {

    private static final long serialVersionUID = -1007976585354758033L;

    /**
     * Construction function
     * @param message
     */
    public RouletteException(String message) {
        super(message);
    }

    public RouletteException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
