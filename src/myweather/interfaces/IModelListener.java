package myweather.interfaces;

/**
 *
 * The interface made public to the controller (observer pattern) - goes both ways
 */
public interface IModelListener {

    /**
     * Notifies the listeners that an update occurred in the model
     */
    public void onUpdate();
}