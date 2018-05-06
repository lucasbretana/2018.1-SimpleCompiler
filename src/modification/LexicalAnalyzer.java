package modification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import modification.Token;
import modification.TokenType;

/** 
 * Reads the file, and return token by token, as the #getNextToken() is called
 */
public class LexicalAnalyzer {

  private String curLex = null;
  private BufferedReader file = null;

  /**
   * Creates a LexicalAnalyzer
   * @param filename the file to read
   */
  public LexicalAnalyzer(String filename) throws IOException, IllegalArgumentException {
    if((filename == null) || (filename.trim().equals(""))) throw new IllegalArgumentException("Cannot have an empty string as filename.");
    this.file = new BufferedReader(new FileReader(filename));
  }

  /**
   * Reads a token from the file, classifies, and return it
   * @return a valid token
   */
  public Token getNextToken() throws IOException, Exception { 
    int eof = -1;
    Character currchar = null;
    int currchar1 = 0;

    if(this.curLex != null) {
      Token t = makeToken(this.curLex);
      this.curLex = null;
      return t;
    } 

    do{
      currchar1 =  this.file.read();
      currchar = (char) currchar1;
    } while (currchar == '\n' || currchar == ' ' || currchar =='\t' || currchar == '\r');

    if(currchar1 != eof && currchar1 !=10) {

      if (currchar >= '0' && currchar <= '9'){
        String number = "";
        do{
          number += currchar.toString();
          do{
            currchar1 =  this.file.read();
            currchar = (char) currchar1;
          } while (currchar == '\n' || currchar == ' ' || currchar =='\t' || currchar == '\r');
        }while(currchar >= '0' && currchar <= '9');
        this.curLex = currchar.toString();
        return new Token(number, TokenType.NUM);
      } else {
        return makeToken(currchar.toString());
      }
    }
    file.close();

    return (new Token(currchar.toString(), TokenType.EOF));
  }

  /**
   * Creates a token based on the lexema
   * @param lex the lexema
   */
  private Token makeToken(String lex) throws Exception {
    switch (lex){
      case "(":
        return new Token(lex, TokenType.OPar);
      case ")":
        return new Token(lex, TokenType.CPar);
      case "+":
        return new Token(lex, TokenType.SUM);
      case "*":
        return new Token(lex, TokenType.MUL);
      case "-":
        return new Token(lex, TokenType.MIN);
      case "/":
        return new Token(lex, TokenType.DIV);

      default: throw new Exception("Caractere inválido: " + lex);
    }

  }
}
