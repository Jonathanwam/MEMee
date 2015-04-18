package com.gamagames.yo_d.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamagames.yo_d.MEMee;
import com.gamagames.yo_d.ui.Texts;

public class FailureState extends State {
	
	private Texts failMsg;

	public FailureState(GSM gsm) {
		super(gsm);
		failMsg = new Texts("failure", MEMee.WIDTH/2, MEMee.HEIGHT/2);

	}
	
	public void handleInput(){
		if(Gdx.input.justTouched()){
			PlayState.failed = false;
			gsm.set(new MenuState(gsm));
		}
	}

	public void hangInput() {
		
	}

	public void update(float dt) {
		handleInput();
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		failMsg.render(sb);
		sb.end();
	}

}
