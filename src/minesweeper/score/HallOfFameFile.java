package minesweeper.score;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HallOfFameFile extends HallOfFame {
	private final String SCORES_FILE = System.getProperty("user.home") + "/mnsw.scores";

	public void addScore(String name, int time) {
		try {
			List<UserScore> scores = loadScore();
			scores.add(new UserScore(name, time));
			Collections.sort(scores);
			save(scores);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<UserScore> loadScore() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(SCORES_FILE);
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				return (List) ois.readObject();
			}
		}
		return new ArrayList<UserScore>();
	}

	private void save(List<UserScore> scores) throws IOException {
		File file = new File(SCORES_FILE);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(scores);
		}
	}
}
