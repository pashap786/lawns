package com.lawn.pojo;

import static com.lawn.enums.Compass.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lawn.enums.Compass;
import com.lawn.enums.TileType;

public class Lawn {

	private static int widthOfLawn;
	private static int heightOfLawn;
	private int numberOfMowers;
	private static List<Mower> bots = new ArrayList<>();
	private static Position[][] positions;
	private int numberOfCraters;
	private List<Position> craterPositions;
	private int maximumTurns;

	public Lawn(int width, int height) {
		this.heightOfLawn = height;
		this.widthOfLawn = width;
	}

	public int getWidthOfLawn() {
		return widthOfLawn;
	}

	public void setWidthOfLawn(int widthOfLawn) {
		this.widthOfLawn = widthOfLawn;
	}

	public int getHeightOfLawn() {
		return heightOfLawn;
	}

	public void setHeightOfLawn(int heightOfLawn) {
		this.heightOfLawn = heightOfLawn;
	}

	public int getNumberOfMowers() {
		return numberOfMowers;
	}

	public void setNumberOfMowers(int numberOfMowers) {
		this.numberOfMowers = numberOfMowers;
	}

	public int getNumberOfCraters() {
		return numberOfCraters;
	}

	public void setNumberOfCraters(int numberOfCraters) {
		this.numberOfCraters = numberOfCraters;
	}

	public List<Position> getCraterPositions() {
		return craterPositions;
	}

	public void setCraterPositions(List<Position> craterPositions) {
		this.craterPositions = craterPositions;
	}

	public int getMaximumTurns() {
		return maximumTurns;
	}

	public void setMaximumTurns(int maximumTurns) {
		this.maximumTurns = maximumTurns;
	}

	public static Lawn readScenario(List<String> scenario) {
		int width = Integer.parseInt(scenario.get(0));
		int height = Integer.parseInt(scenario.get(1));
		widthOfLawn = width;
		heightOfLawn = height;
		setGrassOnBoard(width, height);
		int mowerIndex = 2;
		List<Mower> mowers = new ArrayList<>();
		int numberOFMowers = Integer.parseInt(scenario.get(2));
		// set mowers to the list
		for (int x = 0; x < numberOFMowers; x++) {
			mowerIndex = mowerIndex + 1;
			String mowerParts = scenario.get(mowerIndex);
			Mower lwb = Mower.setupMower(mowerParts, width, height);
			Position position = new Position(lwb.getX(), lwb.getY(), TileType.MOWER);
			positions[lwb.getX()][lwb.getY()] = position;
			lwb.setCurrentPosition(position);
			lwb.setId(x);
			bots.add(lwb);
		}
		mowerIndex = mowerIndex + 1;
		int numberOFCraterss = Integer.parseInt(scenario.get(mowerIndex));
		int craterIndex = mowerIndex;
		// set craters to list
		for (int x = 0; x < numberOFCraterss; x++) {
			craterIndex = craterIndex + 1;
			String crateInstrucitons = scenario.get(craterIndex);
			String[] craterParts = crateInstrucitons.split(",");
			int craterX = Integer.parseInt(craterParts[0]);
			int craterY = Integer.parseInt(craterParts[1]);
			positions[craterX][craterY] = new Position(craterX, craterY, TileType.CRATER);

		}
		printBoard();
		return new Lawn(width, height);
	}
	//Steps
	// Each mower does a scan.
	// each mower clears their block
	// mower returns their cut portions and board is updated
	// mower 'moves' to next block
	public void mow() {
		while (!lawnIsCut(widthOfLawn, heightOfLawn)) {
			System.out.println("Mowing");
			for (Mower mower : bots) {
				List<Position> scanResults = new ArrayList<>();

				scanResults = mower.scan(positions, mower.getCompass());
				for(Position p : scanResults) {
					System.out.println(p);
				}
				printBoard();

				Scanner s = new Scanner(System.in);
				String in = s.nextLine();
				if (in.equalsIgnoreCase("y")) {
					continue;
				}
				
			}
		}
		// update direction

	}

	public Compass getNewDirection(int x) {
		switch (x) {
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

	public static void setGrassOnBoard(int width, int height) {
		positions = new Position[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Position p = new Position(x, y, TileType.GRASS);
				positions[x][y] = p;
			}
		}

	}

	public static boolean lawnIsCut(int width, int height) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (positions[x][y].getTileType() == TileType.GRASS) {
					return false;
				}
			}
		}
		return true;
	}

	public static void printBoard() {
		for (int x = 0; x < widthOfLawn; x++) {
			for (int y = 0; y < heightOfLawn; y++) {
				System.out.print(positions[x][y]);
			}
			System.out.println();
		}
	}

}
