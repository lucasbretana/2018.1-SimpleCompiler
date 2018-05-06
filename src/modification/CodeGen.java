package modification;

public class CodeGen{

  public String generate(ParseTree arv) {
    return (this.recGen(arv) + "PRINT");
  }


  public String recGen(ParseTree arv) {

    if (arv instanceof Mult)
      return (recGen(((Mult) arv).arg1) + 
          recGen(((Mult) arv).arg2) +
          "MULT\n");

    if (arv instanceof Sum)
      return (recGen((((Sum) arv).arg1)) + 
          recGen(((Sum) arv).arg2) +
          "SUM\n");

    if (arv instanceof Sub)
      return (recGen(((Sub) arv).arg1) + recGen(((Sub) arv).arg2) + "SUB\n");

    if (arv instanceof Div)
      return (recGen(((Div) arv).arg1) + recGen(((Div) arv).arg2) + "DIV\n");

    if (arv instanceof Num)
      return ("PUSH "  + ((Num) arv).num + "\n");

    return "";
  }
}
