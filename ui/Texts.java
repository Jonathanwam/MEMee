package com.gamagames.yo_d.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamagames.yo_d.MEMee;


// 5 characters per row and each are 50x50 pixels
public class Texts extends Box {
	private String text;
	private TextureRegion[][] font;
	private int size;
	
	public Texts(String text, float x, float y){
		this.text = text;
		this.x = x;
		this.y = y;
		size = 50;
		setText(text);
		
		TextureRegion fontUnpacked = MEMee.res.getAtlas("pack").findRegion("font");
		font = fontUnpacked.split(size, size);
		
		/* ALL CODE HERE IS REPLACED WITH AMAZING SPLIT FEATURE
		int numRows = fontUnpacked.getRegionWidth() / size;
		int numCols = fontUnpacked.getRegionWidth() / size;
		font = new TextureRegion[numRows][numCols];
		for (int row = 0; row < numRows; row++){
			for(int col = 0; col < numCols; col++){
				font[row][col] = new TextureRegion(fontUnpacked, size*col, size*row, size, size);
			}
		}
		*/
		
	}
	
	public void setText(String text){
		this.text = text;
		width = size * text.length();
		height = size;
		this.text=text.toUpperCase();

	}
	

	
	
	// Remember - they draw from bottom left corner and x,y is centered
	// so need to start at x-width/2, ...
	public void render(SpriteBatch sb){
		
		
		for (int i = 0; i < text.length(); i++){
			char c = text.charAt(i);
			int index=(int)c;
			if (index < '0'){
				index = '0';
			}
			if(index>='0'&&index<='9'){
				index-='0';
				index+=27;
			}
			else{
				index-='A';
			}
			int row = index / font[0].length;
			int col = index % font[0].length;
			sb.draw(font[row][col], x-width/2 + 50*i, y-height/2);
		}
		
	}
}
