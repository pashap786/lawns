package com.lawn.pojo;

import com.lawn.enums.TileType;

public class Position {

	public Position(int x, int y, TileType tileType) {
		this.x = x;
		this.y = y;
		this.tileType = tileType;
	}
	
	private int x;
	private int y;
	private TileType tileType;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public TileType getTileType() {
		return tileType;
	}

	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}

	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + ", tileType=" + tileType + "]";
	}



}
