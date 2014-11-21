package myweather.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


import myweather.interfaces.IController;
import myweather.interfaces.IView;
import myweather.model.WeatherModel;

import org.json.*;



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
        	getApiWeatherInfo();   
    }
    
    /**
     * Reads weather information from the internet and calls updateWeather
     */
    private void getApiWeatherInfo()
    {
    	String input;
        URL url = null;
        URLConnection urlConn = null;
        InputStreamReader  inStream = null;
        BufferedReader buff = null;
    	
    	try
    	{
    		// Create the URL obect that points
			// at the default file index.html
			url  = new URL("http://api.openweathermap.org/data/2.5/weather?lat=45&lon=26" );
			urlConn = url.openConnection();
			inStream = new InputStreamReader(urlConn.getInputStream());
			buff= new BufferedReader(inStream);  

			input = buff.readLine();  			
			JSONObject jsonObject = new JSONObject(input);
			
			JSONObject mainJSON = jsonObject.getJSONObject("main");			
			float absoluteTemp = Float.parseFloat(mainJSON.get("temp").toString());
			float newTemp = (float) (absoluteTemp - 273.15);
			newTemp = (float) Math.round(newTemp * 100) /100;
					
			
			JSONObject windJSON = jsonObject.getJSONObject("wind");	
			float speed = Float.parseFloat(windJSON.get("speed").toString());
			speed = (float) Math.round(speed * 100)/100;
			
			JSONObject locationJSON = jsonObject.getJSONObject("sys");	
			String location = locationJSON.get("country").toString();
			
			updateWeather(newTemp, speed, location);
			return;
        } 
    	catch(MalformedURLException e)
    	{
    		System.out.println("Please check the URL:" + e.toString() );
        }
    	catch(IOException  e1)
    	{
    		System.out.println("Can't read  from the Internet: " + e1.toString() ); 
    	}
    	
    	//If this code is reached the system threw an error and could not read the weather information.
    	//This means the weather info will be updated with random values.
    	updateWeather(randInt(0,30), randInt(0,5), "??");
    }
        
    /**
     * Update model with new temperature and wind speed.
     */
    private void updateWeather(float temp, float speed, String location)
    {
    	if (this.model != null)
    	{
    		this.model.setTemperature(temp);
    		this.model.setWindSpeed(speed);
    		this.model.setLocation(location);
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
