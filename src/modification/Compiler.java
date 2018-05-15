package modification;

import modification.Parser;
import modification.LexicalAnalyzer;

/**
 * This class is responsable for compiling the code to a stach machine code
 */
class Compiler{

  /**
   * Do it all
   * @param args should conatain the filename with path, in the first argument
   */
  public static void main(String...args) throws IllegalArgumentException {
    if(args.length < 1) throw new IllegalArgumentException("Invalid file name.");

    ParseTree arv = null;  
    try{

      LexicalAnalyzer al = new LexicalAnalyzer(args[0]);
      Parser as = new Parser(al);
    
      arv = as.parseProg();
    
      
      CodeGen backend = new CodeGen();
      String codigo = backend.generate(arv);
      System.out.println(codigo);

    }catch(Exception e) {     
      e.printStackTrace();
      System.out.println("Erro de compilação:\n" + e);
    }



  }
}
