package com.gamagames.yo_d.ui;

//Basically same as lib's rectangle but centered around x and y instead of bottom left corner.
public class Box {
	protected float x, y, width, height;
	
	public boolean contains(float x, float y){
		return x > this.x - width /2 &&
				x < this.x + width /2 &&
				y > this.y - height /2 &&
				y < this.y + height /2;
		
	}
	
	public float tileX(){
		return x;
	}
	
	public float tileY(){
		return y;
	}
	
	public float tileWidth(){
		return width;
	}
	
	public float tileHeight(){
		return height;
	}
}