package myweather.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import myweather.interfaces.IController;
import myweather.interfaces.IView;
import myweather.model.WeatherModel;


public class WeatherController implements IController {
	
	private WeatherModel model;
	
	private List<IView> views;
	
	/**
     * Constructor
     */
	public WeatherController()
	{
	}

	
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        if (event.getActionCommand().equals(ACTION_UPDATE))
            updateWeather();   
    }
        
    /**
     * Update model with new temperature and wind speed.
     */
    private void updateWeather()
    {
    	if (this.model != null)
    	{
    		this.model.setTemperature(randInt(0,15));
    		this.model.setWindSpeed(randInt(0,20));
    	}
    }
    
    /**
     * Generate a random integer between min and max.
     *
     * @param min
     * @param max
     */
    public int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    /**
     * Adds a reference to the model, so it can update it
     *
     * @param model The data model reference
     */
    public void addModel(WeatherModel model) {
        this.model = model;
    }

    /**
     * Adds a view reference in order to interact with it
     *
     * @param view The view from the controller will receive events and send messages
     */
    public void addView(IView view) {
        if (views == null) {
            views = new ArrayList<IView>();
        }
        views.add(view);
    }
}
