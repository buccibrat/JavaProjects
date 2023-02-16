package hr.fer.zemris.java.hw03.prob1;

public enum LexerState {
    /**
     *Basic state where lexer only reads strings
     */
    BASIC, 
    /**
     * extended state triggerd by # symbol, separates Words by white spaces
     */
    EXTENDED;
}
