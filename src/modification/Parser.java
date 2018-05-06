package modification;

import modification.LexicalAnalyzer;
import modification.Operator;
import modification.ParseTree;
import modification.TokenType;
import modification.Token;

public class Parser {

  LexicalAnalyzer scanner;


  Parser(LexicalAnalyzer s) throws IllegalArgumentException {
    if(s == null) throw new IllegalArgumentException("LexicalAnalyzer cannot be null");
    scanner = s;
  }

  ParseTree parseProg() throws Exception {

    ParseTree code = exp();
    Token current = scanner.getNextToken();

    if(current.token != TokenType.EOF)
      throw (new Exception("Last token must be an EOF"));

    return code;

  }

  private Exp exp() throws Exception {
    Exp exp1, exp2;
    Token tokenCur =  scanner.getNextToken();

    if(tokenCur.token == TokenType.NUM)
      return new Num(Integer.parseInt(tokenCur.lexeme));


    if(tokenCur.token == TokenType.OPar)
    {
      exp1 = exp();
      if(exp1 == null)
        throw (new Exception("Expected an expression (1)!"));

      Operator op = op();

      if (op == null)
        throw (new Exception("Expected an operator!"));

      exp2 = exp();
      if(exp2 == null)
        throw (new Exception("Expected an operation (2)!"));

      op.arg1 = exp1;
      op.arg2 = exp2;
      tokenCur =  scanner.getNextToken();
      if(tokenCur.token != TokenType.CPar)
        throw (new Exception("Expected : \')\'"));
      return op;

    } else throw (new Exception ("Expected: \'(\' ou <NUM>"));

  }

  Operator op() throws Exception {

    Token tokenCur = scanner.getNextToken();
    switch(tokenCur.token){
      case SUM:
        return new Sum(null,null);
      case MUL:
        return new Mult(null,null);
      default: 
    }
    return null;


  }

}
