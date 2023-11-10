package bll;

import dal.PoemProcessorDAL;

import java.util.List;

public class PoemProcessorBO {
    private PoemProcessorDAL poemProcessorDAL;

    public PoemProcessorBO(PoemProcessorDAL poemProcessorDAL) {
        this.poemProcessorDAL = poemProcessorDAL;
    }

    public void addVerse(List<String> verses, String newVerse) {
        poemProcessorDAL.addVerse(verses, newVerse);
    }

    public void assignRootsToVerses(List<String> verses, List<String> roots) {
        poemProcessorDAL.assignRootsToVerses(null, verses, roots);
    }
}
