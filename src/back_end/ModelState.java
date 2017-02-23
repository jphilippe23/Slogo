package back_end;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ModelState extends Observable {
	public static final String IMAGE_DIRECTORY = "resources/images/";
	public static final String DEFAULT_TURTLE = "ball_given.gif";

	private Map<Integer, Turtle> turtleContainer = new HashMap<>();
	private Color backgroundColor;
	private Point2D home;

	public ModelState() {
		turtleContainer.put(0, new Turtle(getDefaultTurtleImage(), home));
		setBackgroundColor(Color.WHITE);
		setChangedAndNotifyObservers();
	}

	private void setChangedAndNotifyObservers() {
		setChanged();
		notifyObservers();
	}

	public void setX(int ID, double inX) {
		turtleContainer.get(ID).setX(inX);
		setChangedAndNotifyObservers();
	}

	public void setY(int ID, double inY) {
		turtleContainer.get(ID).setY(inY);
		setChangedAndNotifyObservers();
	}

	public void setAngle(int ID, double inAngle) {
		turtleContainer.get(ID).setAngle(inAngle);
		setChangedAndNotifyObservers();
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		setChangedAndNotifyObservers();
	}
	
	public void changeTurtleImage(Integer ID, File newImageFile) {
		Image newTurtleImage = new Image(
				getClass().getClassLoader().getResourceAsStream(IMAGE_DIRECTORY + newImageFile.getName()));
		turtleContainer.get(ID).setImage(newTurtleImage);
		setChangedAndNotifyObservers();
	}

	public void createTurtle() {
		Integer nextID = Collections.max(turtleContainer.keySet()) + 1;
		turtleContainer.put(nextID, new Turtle(getDefaultTurtleImage(), home));
		setChangedAndNotifyObservers();
		
	}

	public void setHome(Point2D home) {
		this.home = home;
	}

	public double getX(int ID) {
		return turtleContainer.get(ID).getX();
	}

	public double getY(int ID) {
		return turtleContainer.get(ID).getX();
	}

	public double getAngle(int ID) {
		return turtleContainer.get(ID).getAngle();
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Map<Integer, Turtle> getTurtleContainer() {
		return turtleContainer;
	}

	private Image getDefaultTurtleImage() {
		String imageLocation = IMAGE_DIRECTORY + DEFAULT_TURTLE;
		Image imageTurtle = new Image(getClass().getClassLoader().getResourceAsStream(imageLocation));
		return imageTurtle;
	}

}
