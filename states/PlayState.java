package com.gamagames.yo_d.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.gamagames.yo_d.MEMee;
import com.gamagames.yo_d.ui.Score;
import com.gamagames.yo_d.ui.Tile;
import com.gamagames.yo_d.ui.TileClicked;

public class PlayState extends State {
	
	//An enum type is a special data type that enables for a variable to be a set of predefined constants.
	public enum Difficulty {
		EASY,
		NORMAL,
		HARD,
		INSANE,
		REGULAR;
	}
	
	// easy (level 1) 3x3, etc.
	private int level = 1;
	private int maxLevel;
	public static Difficulty diff;
	private int [] args;
	
	private static Score score;
	private float scoreTimer;
	
	private Tile [][] tiles;
	private int tileSize, boardHeight;
	private float boardOffset; // used to calculate where user is able to send input and creating grid
	private final int MAX_FINGERS = 2; // user can not use more than 2 fingers
	private boolean showSol; // indicates that game is showing the solution to user
	private float timer; // only show solution for 5 seconds
	
	public static boolean failed;
	
	private TextureRegion light, dark; // used to indicate which level player is on
	
	private Array<Tile> userSelection; // player's attempt to copy solution
	private Array<Tile> solution; // the solution
	private Array<TileClicked> userClicks;
	
	private float wrongTimer;
	private boolean wrongDone;

	public PlayState(GSM gsm, Difficulty diff){
		super(gsm);
		PlayState.diff = diff;
		
		userSelection = new Array<Tile>();
		solution = new Array<Tile>();
		
		args = getArgs();
		// Create board
		createGrid(args[0], args[1]);
		createSol(args[2]);
		
		score = new Score(MEMee.WIDTH/2, MEMee.HEIGHT - 115);
		
		light = MEMee.res.getAtlas("pack").findRegion("light");
		dark = MEMee.res.getAtlas("pack").findRegion("dark");
		
		userClicks = new Array<TileClicked>();
		

	}
	
	
	// Defines difficulties and sends it off to other methods
	private int[] getArgs(){
		
		// indexes 0 and 1 represent ixi grid to render
		// index 2 represents the number of tiles to lite.
		int [] lev = new int[3];
		
		// 3x3 for EASY levels
		if (diff == Difficulty.EASY){
			lev[0] = 3;
			lev[1] = 3;
			if (level == 1 || level == 2){
				lev[2] = 3;
			}
			if (level == 3 || level == 4){
				lev[2] = 4;
			}
			if (level == 5 || level == 6){
				lev[0] = 4;
				lev[1] = 4;
				lev[2] = 4;
			}
			if (level == 7 || level == 8){
				lev[0] = 4;
				lev[1] = 4;
				lev[2] = 5;
			}
			
			maxLevel = 8;
		}
		
		if (diff == Difficulty.NORMAL){
			lev[0] = 4;
			lev[1] = 4;
			
			if (level == 1 || level == 2){
				lev[2] = 5;
			}
			if (level == 3 || level == 4){
				lev[2] = 6;
			}
			if (level == 5 || level == 6){
				lev[0] = 5;
				lev[1] = 5;
				lev[2] = 6;
			}
			if (level == 7 || level == 8){
				lev[0] = 5;
				lev[1] = 5;
				lev[2] = 7;
			}
			if (level == 9 || level == 10){
				lev[0] = 6;
				lev[1] = 6;
				lev[2] = 7;
			}
			maxLevel = 10;
		}
		
		if (diff == Difficulty.HARD){
			lev[0] = 6;
			lev[1] = 6;
			
			if(level == 1 || level == 2){
				lev[2] = 6;
			}
			if(level == 3 || level == 4){
				lev[2] = 7;
			}
			if (level == 5 || level == 6){
				lev[2] = 8;
			}
			if (level == 7 || level == 8){
				lev[2] = 9;
			}
			if (level == 9 || level == 10){
				lev[0] = 7;
				lev[1] = 7;
				lev[2] = 10;
			}
			
			maxLevel = 10;

		}
		
		if (diff == Difficulty.INSANE){
			lev[0] = 7;
			lev[1] = 7;
			if(level == 1 || level == 2){
				lev[2] = 10;
			}
			if(level == 3 || level == 4){
				lev[0] = 5; 
				lev[1] = 10; 
				lev[2] = 10;
			}
			if (level == 5 || level == 6){
				lev[0] = 12;
				lev[1] = 5;
				lev[2] = 12;
			}
			if (level == 7 || level == 8){
				lev[0] = 9;
				lev[1] = 7;
				lev[2] = 14;
			}
			if (level == 9 || level == 10){
				lev[0] = 9;
				lev[1] = 9;
				lev[2] = 16;
			}
			
			maxLevel = 10;
		}
		
		if (diff == Difficulty.REGULAR){
			lev[0] = 2 + level;
			lev[1] = 2 + level;
			lev[2] = 3*level;
			
			maxLevel = 10;
			
		}
		
		return lev;
	}
	
	
	// Doesn't actually check if it's done. Should rename this eventually.
	public void checkIfDone(float dt){
		if(showSol){
			timer += dt;
			
			// Adds flicker for presentation value
			if (timer > 3){
				if(timer % 0.13f < 0.05f){
					for(int i = 0; i < solution.size; i++){
						solution.get(i).setSolution(true);
					}
				}
				else{
					for (int i = 0; i < solution.size; i++){
						solution.get(i).setSolution(false);
					}
				}
			}
			if(timer > 4){
				showSol = false;
				for (int i = 0; i < solution.size; i++){
					solution.get(i).setSolution(false);
				}
			}
		}
	}
	
	public boolean isDone(){
		System.out.println("Checking to see if done");
		
		for (int i = 0; i < solution.size; i++){
			Tile partOfSol = solution.get(i);
			
			// if there's a part of the solution that isn't selected, player ain't done yet.
			if(!userSelection.contains(partOfSol, true)){
				return false;
			}
		}
		
		level++; // user is done. Increase level.
		
		return true;
	}
	
	// Handles what to do when player finished a difficulty
	public static void gameDone(){
		if (failed == true){
			System.out.println("Going to fail state");
			gsm.set(new FailureState(gsm));
		}
		else {
		gsm.set(new ScoreState(gsm, score.getScore()));
		}
	}

	public void handleInput() {
		// Deals with multitouch. Don't want like 20 fingers and toes allowed...
		for (int i = 0; i < MAX_FINGERS; i++){
			if (!showSol && Gdx.input.isTouched(i)){
				touchPos.x = Gdx.input.getX(i);
				touchPos.y = Gdx.input.getY(i);
				cam.unproject(touchPos);
				
				// Check to make sure user clicked in game board before doing anything...
				if (touchPos.x > 0 && touchPos.x < MEMee.WIDTH && touchPos.y > boardOffset && touchPos.y < boardOffset + boardHeight) {
					int row = (int) ((touchPos.y - boardOffset) / tileSize);
					int col = (int) (touchPos.x / tileSize);
					// System.out.println(row + ", " + col);
					if(!tiles[row][col].userSelected()){
						tiles[row][col].setSolution(true);
						userSelection.add(tiles[row][col]);
						userClicks.add(new TileClicked(tiles[row][col].tileX(), tiles[row][col].tileY(), tileSize, tileSize));
						if(isDone()){
							wrongDone = true;
							
							// calculate score here. Faster = more points
							int higher = (int) (scoreTimer * 10);
							// Wrong tiles lessen points
							int lower = 5*(userSelection.size - solution.size);
							
							// if user got nothing wrong then go to the next level
							if (lower == 0) wrongTimer = 1;
							
							for (int j = 0; j < userSelection.size; j++){
								Tile tile = userSelection.get(j);
								if(!solution.contains(tile, true)){
									tile.wrongTile();
								}
							}
							
							score.incrementScore(higher - lower);
							
						}
					}
				}
			}
		}
	}
	
	// Randomly chooses some tiles in the grid as the solution
	public void createSol(int numToLite){
		showSol = true;
		timer = 0;
		wrongTimer = 0;
		scoreTimer = 5;
		
		userSelection.clear();
		solution.clear();
		
		for (int i = 0; i < numToLite; i++){
			int row = 0, col = 0;
			do {
				row = MathUtils.random(tiles.length - 1);
				col = MathUtils.random(tiles[0].length - 1);
			} while(solution.contains(tiles[row][col], true));
			
			solution.add(tiles[row][col]);
			tiles[row][col].setSolution(true);
		}
	}
	
	// Create the grid!
	private void createGrid(int numRows, int numCols){
		// create a new tile grid in 2D array
		// width - 480, height - 800
		tiles = new Tile [numRows][numCols];
		tileSize = MEMee.WIDTH / tiles[0].length;
		boardHeight = tileSize * tiles.length;
		boardOffset = (MEMee.HEIGHT - (tileSize * tiles.length)) / 2;
				
		for (int row=0; row < tiles.length; row++){
			for (int col=0; col < tiles[0].length; col++){
				tiles[row][col] = new Tile(col*tileSize + tileSize/2, row*tileSize + boardOffset + tileSize/2, tileSize, tileSize);
				
				// change the number to change the speed of the corner2corner animation
				tiles[row][col].setTimer((-(tiles.length - row) - col) * 0.05f); 
			}
		}
	}
	


	public void update(float dt) {
		handleInput();
		checkIfDone(dt);
		
		if(wrongDone){
			wrongTimer += dt;
			
			// go to next level after showing user's attempt
			if (wrongTimer >= 1){
				args = getArgs();
				createGrid(args[0], args[1]);
				createSol(args[2]);
				wrongDone = false;
			
				// check if done
				if(level > maxLevel){
					gameDone();
				}
			}
		}
		
		
		// animation for when tile is clicked in game
		for (int i = 0; i < userClicks.size; i++){
			userClicks.get(i).update(dt);
			
			// remove animation after certain period of time
			if (userClicks.get(i).remove()){
				userClicks.removeIndex(i);
				i--;
			}
		}
		
		if(!showSol){
			scoreTimer -= dt;
		}
		
		for (int row=0; row < tiles.length; row++){
			for (int col=0; col < tiles[0].length; col++){
				tiles[row][col].update(dt);
			}
		}
	}

	public void render(SpriteBatch sb) {
		// System.out.println("Begin rendering..." + sb);
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		

		
		score.render(sb);
		
		// draws level indicator
		for (int i = 0; i < maxLevel; i++){
			if(i < level){
				sb.draw(light, (MEMee.WIDTH / 2 - (10*maxLevel-5)) + 20*i, MEMee.HEIGHT - 157, 10, 10);
			}
			else{
				sb.draw(dark, (MEMee.WIDTH / 2 - (10*maxLevel-5))+ 20*i, MEMee.HEIGHT - 157, 10, 10);
			}
		}
		

		
		for (int row=0; row < tiles.length; row++){
			for (int col=0; col < tiles[0].length; col++){
				tiles[row][col].render(sb);	
			}
		}
		
		// draw animation for clicks
		for (int i = 0; i < userClicks.size; i++){
			userClicks.get(i).render(sb);
		}
		sb.end();
	}
	
	public void onBackPressed() {
		gsm.set(new MenuState(gsm));
		}
	
	@Override
	public void hangInput() {
		// TODO Auto-generated method stub
		
	}

}