package com.gamagames.yo_d.handler;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


// Simple content handler used in many libgdx games
public class Content {
	
	private HashMap<String, TextureAtlas> atlases;
	
	public Content(){
		atlases = new HashMap<String, TextureAtlas>();
	}

	
	public void loadAtlas(String path, String key){
		atlases.put(key, new TextureAtlas(Gdx.files.internal(path)));
	}
	
	public TextureAtlas getAtlas(String key){
		return atlases.get(key);
	}
	
	
}