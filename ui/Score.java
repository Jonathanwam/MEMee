package com.gamagames.yo_d.ui;

import com.gamagames.yo_d.states.PlayState;
import com.gamagames.yo_d.states.PlayState.Difficulty;




public class Score extends Texts{
	
	public int score;
	public int realScore;

	public Score(float x, float y) {
		super("0", x, y);
	}

	public void incrementScore(int i){
		score+=i;
		realScore +=i ;
		if (realScore < 0 && PlayState.diff == Difficulty.REGULAR){
			PlayState.failed = true;
			// System.out.println("Set failed to true");
			PlayState.gameDone();
		}
		if  (score < 0){
			score = 0;
		}
		setText(Integer.toString(score));
	}
	
	
	public int getScore(){
		return score;
	}
}
