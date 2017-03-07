package back_end.model.scene;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import back_end.commands.custom.CustomVariable;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class TurtleMaster {
	public static final String IMAGE_DIRECTORY = "resources/images/";
	public static final String DEFAULT_TURTLE = "turtle.gif";

	private Point2D home;
	private Map<Integer, Turtle> turtleContainer;
	private List<Integer> activeTurtleIDs;
	private List<Integer> tempActiveTurtleIDs;
	private boolean tempActiveTurtles;
	private Integer activeTurtleID;

	public TurtleMaster() {
		turtleContainer = new HashMap<Integer, Turtle>();
		activeTurtleIDs = new ArrayList<Integer>();
		tempActiveTurtleIDs = new ArrayList<Integer>();
		tempActiveTurtles = false;
		activeTurtleID = 0;
	}

	void setHome(Point2D home) {
		this.home = home;

	}

	private void cycleThroughActive(List<Integer> turtleIDs, Consumer<? super Turtle> action) {
		turtleIDs.stream().filter(elt -> elt != null).forEach(id -> {
			activeTurtleID = id;
			Turtle turtle = turtleContainer.get(id);
			action.accept(turtle);
		});

	}

	private double operationOnLastTurtle(List<Integer> turtleIDs, Function<Turtle, Double> action) {
		return action.apply(turtleContainer.get(turtleIDs.size() - 1));
	}

	private List<Integer> getListeningTurtleIDs() {
		if (tempActiveTurtles) {
			return Collections.unmodifiableList(tempActiveTurtleIDs);
		} else {
			System.out.println("getting active list");
			return Collections.unmodifiableList(activeTurtleIDs);
		}
	}

	void moveForward(double mag) {
		cycleThroughActive(getListeningTurtleIDs(), turtle -> {
			turtle.moveForward(mag);
		});
	}

	void rotate(double angle) {
		cycleThroughActive(getListeningTurtleIDs(), turtle -> {
			turtle.setAngle(turtle.getAngle() + angle);
		});
	}

	void setVisible(boolean b) {
		cycleThroughActive(getListeningTurtleIDs(), turtle -> turtle.setVisible(b));
	}

	double sendHome() {
		return sendHome(getListeningTurtleIDs());
	}

	private double sendHome(List<Integer> turtleIDs) {
		double value = operationOnLastTurtle(turtleIDs, turtle -> turtle.setPosition(home));
		cycleThroughActive(turtleIDs, turtle -> {
			turtle.setPosition(home);
			turtle.dontDrawLine();
		});
		return value;
	}

	double setPos(double inX, double inY) {
		double distance = operationOnLastTurtle(getListeningTurtleIDs(),
				turtle -> turtle.setPosition(new Point2D(inX, inY)));
		cycleThroughActive(getListeningTurtleIDs(), turtle -> {
			turtle.setPosition(inX, inY);
		});
		return distance;
	}

	boolean isVisible() {
		return isVisible(getListeningTurtleIDs());
	}

	private boolean isVisible(List<Integer> pickTurtles) {
		return turtleContainer.get(pickTurtles.get(pickTurtles.size() - 1)).isVisible();
	}

	void setPenDown() {
		cycleThroughActive(getListeningTurtleIDs(), turtle -> {
			turtle.setPenDown();
		});
	}

	void setPenUp() {
		cycleThroughActive(getListeningTurtleIDs(), turtle -> turtle.setPenUp());
	}

	void setAngle(double angle) {
		cycleThroughActive(getListeningTurtleIDs(), turtle -> {
			turtle.setAngle(angle);
		});
	}

	boolean isPenDown() {
		return isPenDown(getListeningTurtleIDs());
	}

	private boolean isPenDown(List<Integer> pickTurtles) {
		return turtleContainer.get(pickTurtles.get(pickTurtles.size() - 1)).isPenDown();
	}

	double setTowards(double ox, double oy) {
		double newX = home.getX() + ox;
		double newY = home.getY() - oy;
		double angleChange = operationOnLastTurtle(getListeningTurtleIDs(), turtle -> turtle.setTowards(newX, newY));
		cycleThroughActive(getListeningTurtleIDs(), turtle -> {
			turtle.setTowards(newX, newY);
		});
		return angleChange;
	}

	double clearScreen() {
		// TODO remove lines
		List<Integer> allTurtleIDs = new ArrayList<Integer>(turtleContainer.keySet());
		double value = operationOnLastTurtle(allTurtleIDs, turtle -> turtle.setPosition(home));
		sendHome(allTurtleIDs);
		return value;
	}

	public List<Integer> getAllTurtleIDs() {
		return Collections.unmodifiableList(new ArrayList<Integer>(turtleContainer.keySet()));
	}

	double getCoordinate(Integer coordinate) {
		return getCoordinate(getListeningTurtleIDs(), coordinate);
	}

	private double getCoordinate(List<Integer> pickTurtles, Integer coordinate) {
		Point2D lastTurtleCenterPos = turtleContainer.get(pickTurtles.get(pickTurtles.size() - 1)).getCenterPosition();
		double[] pos = new double[] { lastTurtleCenterPos.getX(), lastTurtleCenterPos.getY() };
		double[] homePos = new double[] { home.getX(), home.getY() };
		return homePos[coordinate] - (coordinate.equals(0) ? 0 : -1) * pos[coordinate];
	}

	Collection<Turtle> getTurtles() {
		return turtleContainer.values();
	}

	void breedTurtle(int newTurtleID) {
		if (newTurtleID == -1) {
			newTurtleID = findLowestIDnotTaken();
		}
		turtleContainer.put(newTurtleID, new Turtle(getDefaultTurtleImage(), home));

	}

	private int findLowestIDnotTaken() {
		int newID = 1;
		while (turtleContainer.keySet().contains(newID)) {
			newID += 1;
		}
		return newID;
	}

	private Image getDefaultTurtleImage() {
		String imageLocation = IMAGE_DIRECTORY + DEFAULT_TURTLE;
		Image imageTurtle = new Image(getClass().getClassLoader().getResourceAsStream(imageLocation));
		return imageTurtle;
	}

	void changeTurtleImage(File newImageFile) {
		Image newTurtleImage = new Image(
				getClass().getClassLoader().getResourceAsStream(IMAGE_DIRECTORY + newImageFile.getName()));
		cycleThroughActive(getListeningTurtleIDs(), turtle -> {
			turtle.changeImage(newTurtleImage);
		});
	}

	public void setActiveTurtles(List<Integer> newActives) {

		newActives.stream().forEach(id -> {
			if (!turtleContainer.containsKey(id)) {
				this.breedTurtle(id);
			}
		});

		this.activeTurtleIDs = newActives;
	}

	public void setTempActiveTurtles(List<Integer> newTempActives) {
		this.tempActiveTurtleIDs = newTempActives;
		this.tempActiveTurtles = true;
	}

	double getActiveTurtleID() {
		return this.activeTurtleID;
	}
}