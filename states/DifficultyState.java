package com.gamagames.yo_d.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.gamagames.yo_d.MEMee;
import com.gamagames.yo_d.states.PlayState.Difficulty;
import com.gamagames.yo_d.ui.Texts;

public class DifficultyState extends State{
	
	private Array<Texts> diffs;
	private Texts back;
	
	
	public DifficultyState(GSM gsm){
		super(gsm);
		
		String [] texts = {"easy", "normal", "hard", "insane"};
		diffs = new Array<Texts>();
		for(int i = 0; i < texts.length; i++){
			diffs.add(new Texts(texts[i], MEMee.WIDTH/2, MEMee.HEIGHT/2 + 100 - 70*i));			
		}
		
		back = new Texts("back", MEMee.WIDTH/2, MEMee.HEIGHT/2 - 250);
		
	}

	
	public void handleInput(){
		if(Gdx.input.justTouched()){
			touchPos.x = Gdx.input.getX();
			touchPos.y = Gdx.input.getY();
			cam.unproject(touchPos);
			for(int i = 0; i < diffs.size; i++){
				if(diffs.get(i).contains(touchPos.x, touchPos.y)){
					Difficulty myDiff = Difficulty.values()[i];
					gsm.set(new PlayState(gsm, myDiff));
				}
			}
			
			if(back.contains(touchPos.x, touchPos.y)){
				gsm.set(new MenuState(gsm));
			}
		}
	}
	@Override
	public void hangInput() {

	}

	@Override
	public void update(float dt) {
		handleInput();
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		for(int i = 0; i < diffs.size; i++){
			diffs.get(i).render(sb);
		}
		back.render(sb);
		sb.end();
	}

}
