/**
 * 
 */
package myweather;


import myweather.view.*;
import myweather.model.*;
import myweather.controller.*;

/**
 * @author Razvan
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		WeatherModel model = new WeatherModel();
		WeatherView view = new WeatherView();
		WeatherController controller = new WeatherController();
		
		// Attach the view to the model
        model.addModelListener(view);

        // Tell the view about the model and the controller
        view.addModel(model);
        view.addController(controller);

        // Tell the controller about the model and the view
        controller.addModel(model);
        controller.addView(view);
        
		// Display the view
		view.setVisible(true);
	}

}
