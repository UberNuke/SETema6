package myweather.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import myweather.interfaces.IController;
import myweather.interfaces.IModelListener;
import myweather.interfaces.IView;
import myweather.model.WeatherModel;

public class WeatherView extends JFrame implements IModelListener, IView{
	
	private static final long serialVersionUID = 1L;
	
	private JButton updateButton = new JButton("Update");
	private JLabel tempLabel = new JLabel("Temperature: ");
	private JLabel windSpeedLabel = new JLabel("Wind Speed:");
	
	private WeatherModel model;
	
	public WeatherView()
	{
		// Layout the components.
		JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(this.tempLabel);
        content.add(this.windSpeedLabel);
        content.add(this.updateButton);
        
        // Finalize layout
        this.setContentPane(content);
        this.pack();

        this.setTitle("Weather App");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	
    /**
     * Sets the view's reference to the model - Only get operations allowed
     *
     * @param model The Weather model
     */
    public void addModel(WeatherModel model) {
        this.model = model;
        this.tempLabel.setText("Temperature: " + this.model.getTemperature() + "km/h.");
        this.windSpeedLabel.setText("Wind Speed: " + this.model.getWindSpeed() + "C.");
        this.pack();
    }
    
    /**
     * Sets the view's event listener - the controller - so that the changes made by the user in the view, can be reflected in the model
     *
     * @param controller The controller (event listener)
     */
    public void addController(IController controller) {
    	updateButton.setActionCommand(IController.ACTION_UPDATE);
    	updateButton.addActionListener(controller);
    }
	
	@Override
    public void onMessage() {
      
    }

    @Override
    public void onUpdate() {
    	this.tempLabel.setText("Temperature: " + this.model.getTemperature() + "km/h.");
        this.windSpeedLabel.setText("Wind Speed: " + this.model.getWindSpeed() + "C.");
        this.pack();
    }
}
