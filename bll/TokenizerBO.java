package bll;

import dal.TokenizerDAL;

public class TokenizerBO {

    private TokenizerDAL tokenizerDAL;

    public TokenizerBO(TokenizerDAL tokenizerDAL) {
        this.tokenizerDAL = tokenizerDAL;
    }

    public void tokenizeVerse(String verse) {
        tokenizerDAL.tokenizeVerseAndSave(verse);
    }
}
