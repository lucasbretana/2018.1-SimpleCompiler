package modification;

import modification.TokenType;

public class Token{
  protected String lexeme;
  protected TokenType token;

  public Token (String l, TokenType t) {
    lexeme = l;
    token = t;
  }

}
