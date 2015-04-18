package com.gamagames.yo_d.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamagames.yo_d.MEMee;

public class Tile extends Box {

	public static TextureRegion light;
	public static TextureRegion dark;
	
	private boolean solutionTile;
	
	protected float totalHeight;
	protected float totalWidth;
	protected float timer;
	protected float maxTime = 0.5f;
	
	private boolean wrongTile;
	
	public Tile(float x, float y, float height, float width) {
		
		this.x = x;
		this.y = y;
		this.width = 0;
		this.height = 0;
		this.totalHeight = height - 8; // squares are 8 pixels apart
		this.totalWidth = width - 8;

		light = MEMee.res.getAtlas("pack").findRegion("light");
		dark = MEMee.res.getAtlas("pack").findRegion("dark");
		
	}
	
	
	// TODO implement this
	public void TriTile(float x, float y, float height, float width){
		this.x = x;
		this.y = y;
		this.width = 0;
		this.height = 0;
		this.totalHeight = height - 8;
		this.totalWidth = width - 8;
		
		
		light = MEMee.res.getAtlas("pack").findRegion("light");
		dark = MEMee.res.getAtlas("pack").findRegion("dark");
	}
	
	public void setSolution(boolean b) {
		solutionTile = b;		
	}
	
	public boolean userSelected() {
		return solutionTile;
	}
	
	public void setTimer(float t) {
		timer = t;
	}
	
	public void wrongTile(){
		this.wrongTile = true;
	}
	public void toggleSelect(){
		solutionTile = !solutionTile;
	}
	
	public void update(float dt) {
		if (width < totalWidth && height < totalHeight) {
				timer += dt;
				width = (timer / maxTime) * totalWidth;
				height = (timer / maxTime) * totalHeight;
				if (width < 0) width = 0;
				if (height < 0) height = 0;
			if (width > totalWidth) width = totalWidth;
				if (height > totalHeight) height = totalHeight;
		}

		
	}
	public void render(SpriteBatch sb) {

		if (solutionTile) {
			if (wrongTile) {
				// should be red
				// System.out.println("Drawing wrong tile");
				sb.setColor(.85f, 0f, 0f, 1);
			}
			sb.draw(light, x-width/2, y-height/2, width, height);
			sb.setColor(1, 1, 1, 1); // set back to normal
		}
		else {
			sb.draw(dark,  x-width/2,  y-height/2,  width,  height);
		}
	}
	
}