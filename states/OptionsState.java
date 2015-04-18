package com.gamagames.yo_d.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamagames.yo_d.MEMee;
import com.gamagames.yo_d.ui.Texts;
import com.gamagames.yo_d.ui.Tile;

public class OptionsState extends State {
	
	private Texts lightBG, darkBG, back, style;
	
	protected OptionsState(GSM gsm) {
		super(gsm);
		lightBG = new Texts("Light", MEMee.WIDTH/2 - 110, MEMee.HEIGHT - 100);
		darkBG = new Texts("Dark", MEMee.WIDTH - 110, MEMee.HEIGHT - 100);
		back = new Texts("back", MEMee.WIDTH/2, MEMee.HEIGHT/2 - 300);
		style = new Texts("Change Style", MEMee.WIDTH/2, MEMee.HEIGHT/2);
	}

	public void handleInput(){
		
		if(Gdx.input.justTouched()){
			touchPos.x = Gdx.input.getX();
			touchPos.y = Gdx.input.getY();
			cam.unproject(touchPos);
		
			if (lightBG.contains(touchPos.x, touchPos.y)){
				Gdx.gl.glClearColor(1f, 1f, 1f, 1);
			}
		
			if (darkBG.contains(touchPos.x,  touchPos.y)){
				Gdx.gl.glClearColor(.2f, .2f, .2f, 1);
			}
			if (back.contains(touchPos.x, touchPos.y)){
				// new state to menu
				gsm.set(new MenuState(gsm));
			}
			
			/* Change pack.pack
			if (style.contains(touchPos.x,  touchPos.y)){
				// change this
				Tile.light = MEMee.res.getAtlas("pack").findRegion("light");
				Tile.dark = MEMee.res.getAtlas("pack").findRegion("dark");
			}
			
			*/
		}
		
	}
	
	public void hangInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		back.render(sb);
		lightBG.render(sb);
		darkBG.render(sb);
		
		sb.end();
	}

}
