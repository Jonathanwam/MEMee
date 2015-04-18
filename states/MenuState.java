package com.gamagames.yo_d.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamagames.yo_d.MEMee;
import com.gamagames.yo_d.states.PlayState.Difficulty;
import com.gamagames.yo_d.ui.Graphic;
import com.gamagames.yo_d.ui.Texts;

public class MenuState extends State {
	
	private Graphic title;
	private Texts practice, play, options;
	
	// Every game state needs a reference to GSM...
	public MenuState(GSM gsm) {
		super(gsm);
		title = new Graphic(MEMee.res.getAtlas("pack").findRegion("logo"), MEMee.WIDTH/2, MEMee.HEIGHT/2 + 100);
		practice = new Texts("play", MEMee.WIDTH/2, MEMee.HEIGHT/2 - 100);
		play = new Texts("challenge", MEMee.WIDTH/2, MEMee.HEIGHT/2 - 250);
		options =  new Texts("options", MEMee.WIDTH/2, MEMee.HEIGHT - 100);
	}
	
	// multitouch is pointless in the main menu
	public void handleInput(){
		if(Gdx.input.justTouched()){
			// System.out.println("Im being touched in the main menu");
			touchPos.x = Gdx.input.getX();
			touchPos.y = Gdx.input.getY();
			cam.unproject(touchPos);
			if (practice.contains(touchPos.x, touchPos.y)){
				gsm.set(new DifficultyState(gsm));
			}
			if(play.contains(touchPos.x, touchPos.y)){
				gsm.set(new PlayState(gsm, Difficulty.REGULAR));
			}
			if (options.contains(touchPos.x,  touchPos.y)){
				gsm.set(new OptionsState(gsm));
			}
		}
	}
	
	public void update(float dt){
		handleInput();
	}
	
	public void render(SpriteBatch sb){
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		
		options.render(sb);
		title.render(sb);
		practice.render(sb);
		play.render(sb);
		sb.end();
		
	}

	@Override
	public void hangInput() {
		// TODO Auto-generated method stub
		
	}
	
}
