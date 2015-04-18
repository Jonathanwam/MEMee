package com.gamagames.yo_d.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamagames.yo_d.MEMee;
import com.gamagames.yo_d.ui.Texts;


// Show the score after the game. Click again to return to main menu.
public class ScoreState extends State{
	
	private Texts endScore;
	
	public ScoreState(GSM gsm, int score){
		super(gsm);
		endScore = new Texts(Integer.toString(score), MEMee.WIDTH/2, MEMee.HEIGHT/2);
	}
	
	public void handleInput(){
		if(Gdx.input.justTouched()){
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
		endScore.render(sb);
		sb.end();
	}
	
	

}
