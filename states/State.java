package com.gamagames.yo_d.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.gamagames.yo_d.MEMee;


public abstract class State {

	protected static GSM gsm;
	protected OrthographicCamera cam;
	protected Vector3 touchPos;
	
	protected State(GSM gsm){
		State.gsm = gsm;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, MEMee.WIDTH, MEMee.HEIGHT);
		touchPos = new Vector3();
	}
	


	public abstract void hangInput();
	public abstract void update(float dt);
	public abstract void render(SpriteBatch sb);
}
