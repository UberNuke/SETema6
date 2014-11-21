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
	
	private float windSpeed;
	private float temperature;
	private String location = "??"; 
	
	private List<IModelListener> mListeners;
	
	/**
	 * Constructor uses default values.
	 */
	public WeatherModel()
	{
		windSpeed = 10;
		temperature = 20;
	}
	
	
	public float getWindSpeed() {
		return windSpeed;
	}
	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
		notifyListeners();
	}


	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
		notifyListeners();
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
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
