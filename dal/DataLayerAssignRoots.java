package dal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DataLayerAssignRoots implements PoemProcessorDAL {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	public void addVerse(List<String> verses, String newVerse) {
        verses.add(newVerse);
    }

	@Override
    public void assignRootsToVerses(String poemTitle, List<String> verses, List<String> roots) {
        int minSize = Math.min(verses.size(), roots.size());
        for (int i = 0; i < minSize; i++) {
            String verse = verses.get(i);
            String root = roots.get(i);

            int verseId = getVerseIdByTitleAndPoem(poemTitle, verse);
            int rootId = getRootIdByName(root);

            if (verseId != -1 && rootId != -1) {
                associateRootWithVerse(verseId, rootId);
            } else {
                System.out.println("Error: Unable to associate Root with Verse in the database.");
            }
        }
    }

    private int getVerseIdByTitleAndPoem(String poemTitle, String verse) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String selectQuery = "SELECT verse_id FROM Verses WHERE p_id = (SELECT pid FROM Poems WHERE p_title = ?) AND verse = ?";
            try (PreparedStatement statement = con.prepareStatement(selectQuery)) {
                statement.setString(1, poemTitle);
                statement.setString(2, verse);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("verse_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getRootIdByName(String root) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String selectQuery = "SELECT root_id FROM Roots WHERE root_name = ?";
            try (PreparedStatement statement = con.prepareStatement(selectQuery)) {
                statement.setString(1, root);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("root_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void associateRootWithVerse(int verseId, int rootId) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO VerseRootAssociation (verse_id, root_id) VALUES (?, ?)";
            try (PreparedStatement statement = con.prepareStatement(insertQuery)) {
                statement.setInt(1, verseId);
                statement.setInt(2, rootId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}