/**
 * 
 */
package myweather.model;

import java.util.ArrayList;
import java.util.List;

import myweather.interfaces.*;

/**
 * @author Razvan
 *
 */
public class WeatherModel {
	
	private int windSpeed;
	private int temperature;
	
	private List<IModelListener> mListeners;
	
	/**
	 * Constructor uses default values.
	 */
	public WeatherModel()
	{
		windSpeed = 10;
		temperature = 20;
	}
	
	
	public int getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(int windSpeed) {
		this.windSpeed = windSpeed;
		notifyListeners();
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
		notifyListeners();
	}
	
    /**
     * Adds the view listener to the list
     *
     * @param listener The model event listener
     */
    public void addModelListener(IModelListener listener) {
        if (mListeners == null) {
            mListeners = new ArrayList<IModelListener>();
        }

        mListeners.add(listener);
    }

    /**
     * Notifies the views listeners of the changed state (value)
     */
    private void notifyListeners() {
        if (mListeners != null && !mListeners.isEmpty()) {
            for (IModelListener listener : mListeners)
                listener.onUpdate();
        }
    }

}
