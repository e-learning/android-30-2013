package com.example.Calculator;

public class BaseToken implements TokenInterface {
    // Token type
    private TOKEN_ID TokId;

    // Default class constructor
    public BaseToken() {
    }

    // User class constructor
    public BaseToken(TOKEN_ID Id) {
        TokId = Id;
    }

    // Get token ID function
    public TOKEN_ID GetTokenId() {
        return TokId;
    }

    // Print token function
    public String Print() {
        return "!!!ERROR!!!Base token";
    }
}
