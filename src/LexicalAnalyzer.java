import java.util.ArrayList;
import java.util.List;

import TOKEN.Token;
import TOKEN.TokenType;

public class LexicalAnalyzer {
    private String input;
    private int currentPosition;
    private List<Token> tokens;

    public LexicalAnalyzer(String input) {
        this.input = input;
        this.currentPosition = 0;
        this.tokens = new ArrayList<>();
    }

    public List<Token> analyze() throws LexicalException {
        while (currentPosition < input.length()) {
            char currentChar = input.charAt(currentPosition);

            // ignore whitespace
            if (Character.isWhitespace(currentChar)) {
                currentPosition++;
                continue;
            }

            // check for single-character tokens
            switch (currentChar) {
            case ',':
                tokens.add(new Token(TokenType.CARACTERE_SPECIAL, ","));
                currentPosition++;
                break;
            case '(':
                tokens.add(new Token(TokenType.CARACTERE_SPECIAL, "("));
                currentPosition++;
                break;
            case ')':
                tokens.add(new Token(TokenType.CARACTERE_SPECIAL, ")"));
                currentPosition++;
                break;
            case ';':
                tokens.add(new Token(TokenType.CARACTERE_SPECIAL, ";"));
                currentPosition++;
                break;
            case ':':
                if (currentPosition < input.length() - 1 && input.charAt(currentPosition + 1) == '=') {
                    tokens.add(new Token(TokenType.CARACTERE_SPECIAL, ":="));
                    currentPosition += 2;
                } else {
                    throw new LexicalException("Invalid token at position " + currentPosition, currentPosition);
                }
                break;
            case '0':
                tokens.add(new Token(TokenType.CONSTANTE, "0"));
                currentPosition++;
                break;
            case '1':
                tokens.add(new Token(TokenType.CONSTANTE, "1"));
                currentPosition++;
                break;
            case '+':
                tokens.add(new Token(TokenType.CARACTERE_SPECIAL, "+"));
                currentPosition++;
                break;
            case '=':
                tokens.add(new Token(TokenType.CARACTERE_SPECIAL, "="));
                currentPosition++;
                break;
            case 'n':
                tokens.add(new Token(TokenType.IDENTIFIER, "n"));
                currentPosition++;
                break;  
            
            case 'i':
                tokens.add(new Token(TokenType.IDENTIFIER, "i"));
                currentPosition++;
                break;
                
            case 'j':
                tokens.add(new Token(TokenType.IDENTIFIER, "j"));
                currentPosition++;
                break; 
           
                
                // check for keywords and identifiers
                default:
                    if (Character.isLetter(currentChar)) {
                        int identifierStart = currentPosition;
                        while (currentPosition < input.length() && Character.isLetterOrDigit(input.charAt(currentPosition))) {
                            currentPosition++;
                        }
                        String identifier = input.substring(identifierStart, currentPosition);
                        if (identifier.equals("PROGRAM")) {
                            tokens.add(new Token(TokenType.MOT_CLES, "PROGRAM"));
                        } else if (identifier.equals("VAR")) {
                            tokens.add(new Token(TokenType.MOT_CLES, "VAR"));
                        } else if (identifier.equals("FUNCTION")) {
                            tokens.add(new Token(TokenType.MOT_CLES, "FUNCTION"));
                        } else if (identifier.equals("BEGIN")) {
                            tokens.add(new Token(TokenType.MOT_CLES, "BEGIN"));
                        } else if (identifier.equals("END")) {
                            tokens.add(new Token(TokenType.MOT_CLES, "END"));
                        } else if (identifier.equals("IF")) {
                            tokens.add(new Token(TokenType.MOT_CLES, "IF"));
                        } else if (identifier.equals("THEN")) {
                            tokens.add(new Token(TokenType.MOT_CLES, "THEN"));
                        } else if (identifier.equals("ELSE")) {
                            tokens.add(new Token(TokenType.MOT_CLES, "ELSE"));
                        } else if (identifier.equals("RETURN")) {
                            tokens.add(new Token(TokenType.MOT_CLES, "RETURN"));
                        } else if (identifier.equals("INT")) {
                            tokens.add(new Token(TokenType.MOT_CLES, "INT"));
                        } else if (identifier.equals("test")) {
                            tokens.add(new Token(TokenType.MOT_CLES, "test"));
                        } else if (identifier.equals("fib")) {
                            tokens.add(new Token(TokenType.MOT_CLES, "fib"));
                        } else {
                            tokens.add(new Token(TokenType.IDENTIFIER, identifier));
                        }
                    } else {
                        throw new LexicalException("Invalid token at position " + currentPosition, currentPosition);
                    }
                    break;
            }
        }

        return tokens;
    }


    public static void main(String[] args) {
      String input = "PROGRAM n 1 0";
      
    		  		

        try {
            LexicalAnalyzer analyzer = new LexicalAnalyzer(input);
            List<Token> tokens = analyzer.analyze();

            for (Token token : tokens) {
                System.out.println(token);
            }
        } catch (LexicalException e) {
            System.out.println("Lexical error: " + e.getMessage());
            
        }
    }
    }

