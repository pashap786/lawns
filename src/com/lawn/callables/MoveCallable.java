package com.lawn.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import com.lawn.enums.TileType;
import com.lawn.pojo.Mower;
import com.lawn.pojo.Position;

public class MoveCallable implements Callable<List<Position>>{
	
	private int width;
	private int height;
	private Mower mower;
	private Position[][] scanned;
	
	public MoveCallable(int width, int height, Mower mower, Position[][] scanned) {
		this.width = width;
		this.height = height;
		this.mower = mower;
		this.scanned = scanned;
	}

	@Override
	public List<Position> call() throws Exception {
		// TODO execute cutting logic
		List<Position> report = new ArrayList<>();
		
		while(!lawnIsCut(width, height)) {
			
			Position position = mower.scanRandom(scanned);
			
			
			
		}
		
		return null;
	}

	public static boolean lawnIsCut(int width, int height) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (scanned[x][y].getTileType() == TileType.GRASS) {
					return false;
				}
			}
		}
		return true;
	}
}
