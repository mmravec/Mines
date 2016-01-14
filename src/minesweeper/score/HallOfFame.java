package minesweeper.score;

import java.util.ArrayList;
import java.util.List;

public abstract class HallOfFame {
	public abstract void addScore(String name, int time) throws Exception;
	
	public abstract List<UserScore> loadScore() throws Exception;
	
	@Override
	public String toString() {
		List<UserScore> scores;
		try {
			scores = loadScore();
		} catch (Exception e) {
			scores = new ArrayList<>();
		}
		
		StringBuilder sb = new StringBuilder();
		int index = 1;
		for (UserScore score : scores) {
			sb.append(index + ". " + score.getName() + " " + score.getTime() + "\n");
			index++;
		}
		return sb.toString();
	}
}