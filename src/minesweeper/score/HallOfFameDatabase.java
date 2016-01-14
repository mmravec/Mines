package minesweeper.score;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HallOfFameDatabase extends HallOfFame {
	//CREATE TABLE score (name VARCHAR(64) NOT NULL, seconds INT NOT NULL)
	static final String URL = "jdbc:mysql://localhost/test";
	static final String USER = "root";
	static final String PASSWORDD = "root";
	static final String SELECT_SCORES_QUERY = "SELECT username, seconds FROM score ORDER BY seconds";
	static final String INSERT_SCORE_UPDATE = "INSERT INTO score (username, seconds) VALUES ('%s', %d)";
	
	public void addScore(String name, int time) throws SQLException {		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORDD);
				Statement statement = connection.createStatement()) {
			
			String update = String.format(INSERT_SCORE_UPDATE, name, time);
			statement.executeUpdate(update);
		}		
	}
	
	public List<UserScore> loadScore() throws SQLException {
		List<UserScore> scores = new ArrayList<>();
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORDD);
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(SELECT_SCORES_QUERY)
				) {
			while (rs.next()) {
				scores.add(new UserScore(rs.getString(1), rs.getInt(2)));
			}			
		}
		
		return scores;
	}
}
