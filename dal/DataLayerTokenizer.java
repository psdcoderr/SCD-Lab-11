package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class DataLayerTokenizer implements TokenizerDAL {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";

	@Override
    public void tokenizeVerseAndSave(String verse) {
        StringTokenizer tokenizer = new StringTokenizer(verse, " ");
        System.out.println("Tokens of the verse:");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO VerseTokens (token) VALUES (?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    System.out.println(token);
                    preparedStatement.setString(1, token);
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
