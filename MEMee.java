package com.gamagames.yo_d;

/*****************
 * TODO 
 * Options State - themes, tile graphics, audio, font choices, about
 * Fix font
 * Change difficulty around
 * Possibly add cheat code
 * High score tracker
 * Change 'practice' to 'play'
 * Change 'play' to 'challenge'
 * Custom shapes. Triforce mode, circle mode, etc.
 * 
 * TODO BUGS
 * Crash in challenge - nullpointerexception - 7
 * level++ occurs on each click on wrong tiles after level is done. 
 * Disable input on level completion
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamagames.yo_d.handler.Content;
import com.gamagames.yo_d.states.GSM;
import com.gamagames.yo_d.states.MenuState;

public class MEMee extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	public static final String title = "MEMee";
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	
	public static Content res;
	
	private SpriteBatch sb;
	private GSM gsm;
	
	public void create () {
		
		// with photoshop, divide RGB values by 255 and set to float with 'f'
		//Gdx.gl.glClearColor(213/255f, 222/255f, 217/255f, 1); // light
		
		 Gdx.gl.glClearColor(1f, 1f, 1f, 1); // white
		
	
		
		res = new Content();
		res.loadAtlas("pack.pack", "pack");
		
		sb = new SpriteBatch();
		gsm = new GSM();
		// gsm.push(new PlayState(gsm, Difficulty.INSANE));
		gsm.push(new MenuState(gsm));
		
		
	}

	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
		
	}
}
