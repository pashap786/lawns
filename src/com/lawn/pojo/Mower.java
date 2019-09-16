package com.lawn.pojo;

import static com.lawn.enums.Compass.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.lawn.enums.Compass;
import com.lawn.enums.TileType;

public class Mower {
	private static final Map<String, Compass> COMPASS_MAP = compassMap();
	private int x;
	private int y;
	private Compass compass;
	private Compass previousFace;
	private final int lawnWidth;
	private final int lawnHeight;
	private Position currentPosition;
	private Position previousPosition;
	private List<Position> scannedPositions;
	private int id;

	private static HashMap<String, Compass> compassMap() {
		HashMap<String, Compass> map = new HashMap<>();

		map.put("north", NORTH);
		map.put("south", SOUTH);
		map.put("west", WEST);
		map.put("east", EAST);
		map.put("northeast", NORTHEAST);
		map.put("southeast", SOUTHEAST);
		map.put("northwest", NORTHWEST);
		map.put("southwest", SOUTHWEST);

		return map;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Compass getCompass() {
		return compass;
	}

	public void setCompass(Compass compass) {
		this.compass = compass;
	}

	public Compass getPreviousFace() {
		return previousFace;
	}

	public void setPreviousFace(Compass previousFace) {
		this.previousFace = previousFace;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	public List<Position> getScannedPositions() {
		return scannedPositions;
	}

	public void setScannedPositions(List<Position> scannedPositions) {
		this.scannedPositions = scannedPositions;
	}

	public Mower(int x, int y, Compass compass, int lawnWidth, int lawnHeight) {
		this.x = x;
		this.y = y;
		this.compass = compass;
		this.lawnWidth = lawnWidth;
		this.lawnHeight = lawnHeight;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Position getPreviousPosition() {
		return previousPosition;
	}

	public void setPreviousPosition(Position previousPosition) {
		this.previousPosition = previousPosition;
	}

	public static Mower setupMower(String instructions, int lawnWidth, int lawnHeight) {

		String[] config = instructions.split(",");

		String d = config[2].toLowerCase();
		Compass r = COMPASS_MAP.get(d);
		return new Mower(Integer.parseInt(config[0]), Integer.parseInt(config[1]), r, lawnWidth, lawnHeight);
	}

	public List<Position> scan(Position[][] positions, Compass face) {

		List<Position> surroundings = new ArrayList<>();

		int x = currentPosition.getX();
		int y = currentPosition.getY();
		// scan positions above
		for (int p = (x - 1); p < 3; p++) {
			try {
				Position position = positions[p][y + 1];
				surroundings.add(position);
			} catch (Exception e) {
				Position fence = new Position(p, y + 1, TileType.FENCE);
				surroundings.add(fence);
			}
		}
		// scan positions below
		for (int p = (x - 1); p < 3; p++) {
			try {
				Position position = positions[p][y - 1];
				surroundings.add(position);
			} catch (Exception e) {
				Position fence = new Position(p, y - 1, TileType.FENCE);
				surroundings.add(fence);
			}
		}
		// scan left
		for (int p = (y - 1); p < 3; p++) {
			try {
				Position position = positions[x + 1][p];
				surroundings.add(position);
			} catch (Exception e) {
				Position fence = new Position(x + 1, p, TileType.FENCE);
				surroundings.add(fence);
			}
		}
		// scan right
		for (int p = (y - 1); p < 3; p++) {
			try {
				Position position = positions[x - 1][p];
				surroundings.add(position);
			} catch (Exception e) {
				Position fence = new Position(x - 1, p, TileType.FENCE);
				surroundings.add(fence);
			}
		}

		return surroundings;

	}

	public Position scanRandom(Position[][] positions) {

		Position scanResults = null;

		int y = 0;
		int x = 0;
		Compass f = changeMowerDirection();
		System.out.println(lawnWidth + " " + lawnHeight + " current facing " + f);
		switch (f) {
		case SOUTH:
			x = currentPosition.getX() + 1;
			if (x < lawnWidth) {
				System.out.println("move north");
				Position p = positions[x][currentPosition.getY()];
				if (addPosition(p)) {
					scanResults = p;
				}
			}
			return scanResults;
		case NORTH:
			x = currentPosition.getX() - 1;
			if (x >= 0) {
				System.out.println("move north");
				Position p = positions[x][currentPosition.getY()];
				if (addPosition(p)) {
					scanResults = p;
				}
			}
			return scanResults;
		case EAST:
			y = currentPosition.getY() + 1;
			if (y < lawnHeight) {
				System.out.println(y + " x= " + currentPosition.getX());
				Position p = positions[currentPosition.getX()][y];
				if (addPosition(p)) {
					scanResults = p;
				}
			}
			return scanResults;
		case WEST:
			System.out.println("move west");
			y = currentPosition.getY() - 1;
			if (y >= 0) {
				System.out.println(y + " x= " + currentPosition.getX());
				Position p = positions[currentPosition.getX()][y];
				if (addPosition(p)) {
					scanResults = p;
				}
			}
			return scanResults;
		case NORTHWEST:
			System.out.println("move nort west");
			y = currentPosition.getY() - 1;
			x = currentPosition.getX() - 1;
			if (y >= 0 && x > 0) {
				System.out.println(y + " x= " + currentPosition.getX());
				Position p = positions[x][y];
				if (addPosition(p)) {
					scanResults = p;
				}
			}
			return scanResults;
		case NORTHEAST:
			System.out.println("move nort east");
			y = currentPosition.getY() + 1;
			x = currentPosition.getX() - 1;
			if (y < lawnHeight && x > 0) {
				System.out.println(y + " x= " + currentPosition.getX());
				Position p = positions[x][y];
				if (addPosition(p)) {
					scanResults = p;
				}
			}
			return scanResults;
		case SOUTHWEST:
			System.out.println("move south west");
			y = currentPosition.getY() - 1;
			x = currentPosition.getX() + 1;
			if (y >= 0 && (x < lawnHeight)) {
				System.out.println(y + " x= " + currentPosition.getX());
				Position p = positions[x][y];
				if (addPosition(p)) {
					scanResults = p;
				}
			}
			return scanResults;
		case SOUTHEAST:
			System.out.println("move south east");
			y = currentPosition.getY() + 1;
			x = currentPosition.getX() + 1;
			if (x < lawnWidth && (y < lawnHeight)) {
				System.out.println(y + " x= " + currentPosition.getX());
				Position p = positions[x][y];
				if (addPosition(p)) {
					scanResults = p;
				}
			}
			return scanResults;
		default:

			System.out.println("move west");
			y = currentPosition.getY() - 1;
			if (y >= 0) {
				System.out.println(y + " x= " + currentPosition.getX());
				Position p = positions[currentPosition.getX()][y];
				if (addPosition(p)) {
					scanResults = p;
				}
			}
			return scanResults;
		}

	}

	private boolean addPosition(Position p) {
		if (p.getTileType() == TileType.CRATER || p.getTileType() == TileType.MOWER
				|| p.getTileType() == TileType.FENCE) {
			return false;
		}
		if (p.getTileType() == TileType.GRASS || p.getTileType() == TileType.EMPTY) {
			return true;
		}
		return true;
	}

	public Compass changeMowerDirection() {
		Random r = new Random();
		int directions = r.nextInt((8 - 0) + 1) + 0;

		switch (directions) {
		case 0:
			return NORTH;
		case 1:
			return EAST;
		case 2:
			return SOUTH;
		case 3:
			return WEST;
		case 4:
			return SOUTHWEST;
		case 5:
			return NORTHWEST;
		case 6:
			return NORTHEAST;
		case 7:
			return SOUTHEAST;
		default: {
			return WEST;
		}
		}
	}
}
