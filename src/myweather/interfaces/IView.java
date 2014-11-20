package myweather.interfaces;

/**
 * 
 * All the views should implement this interface in order for the controller to know how to interact
 */
public interface IView {

    /**
     * On message received from controller
     *
     */
    public void onMessage();
}