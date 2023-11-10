package dal;

import java.util.List;

public interface PoemProcessorDAL {

	void addVerse(List<String> verses, String newVerse);
	void assignRootsToVerses(String poemTitle, List<String> verses, List<String> roots);

}
