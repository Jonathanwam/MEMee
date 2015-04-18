package com.gamagames.yo_d.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class TileClicked extends Tile {
	private float alphaLevel, startWidth, startHeight;
	private boolean remove;
	protected float maxAnimeTime = .5f;
	
	public TileClicked(float x, float y, float height, float width) {
		super(x, y, height, width);
		this.width = width;
		this.height = height;
	}
	
	public boolean remove(){
		return remove;
	}
	
	public void update(float dt){
		timer += dt;
		width += dt*150;
		height += dt*150; // change number to make square animation bigger or smaller
		if (timer >= maxAnimeTime){
			remove = true;
		}
	}
	
	public void render(SpriteBatch sb){
		alphaLevel = 1 - timer/ maxTime;
		sb.setColor(1, 1, 1, alphaLevel);
		sb.draw(light, x-width/2, y-height/2, width, height);
		sb.setColor(1, 1, 1, 1);
	}
}
