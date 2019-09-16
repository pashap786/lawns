package com.lawn.service;

import static com.lawn.enums.Compass.EAST;
import static com.lawn.enums.Compass.NORTH;
import static com.lawn.enums.Compass.NORTHEAST;
import static com.lawn.enums.Compass.NORTHWEST;
import static com.lawn.enums.Compass.SOUTH;
import static com.lawn.enums.Compass.SOUTHEAST;
import static com.lawn.enums.Compass.SOUTHWEST;
import static com.lawn.enums.Compass.WEST;

import java.util.Random;

import com.lawn.enums.Compass;

public class DirectionService {

	public static Compass changeMowerDirection() {
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
