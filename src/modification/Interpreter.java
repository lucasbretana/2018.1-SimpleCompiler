package modification;

import modification.ParseTree;
import modification.Mult;
import modification.Div;
import modification.Sum;
import modification.Sub;
import modification.Num;

public class Interpreter{

  public String interpret(ParseTree arv) throws Exception{
    return this.recGen(arv).toString();
  }


  private Float recGen(ParseTree arv) throws Exception{
    if (arv instanceof Mult)
      return recGen(((Mult)arv).arg1) * recGen(((Mult)arv).arg2);

    if (arv instanceof Sum)
      return recGen(((Sum)arv).arg1) + recGen(((Sum)arv).arg2);

    if (arv instanceof Sub)
      return recGen(((Sub)arv).arg1) - recGen(((Sub)arv).arg2);

    if (arv instanceof Div)
      return recGen(((Div)arv).arg1) / recGen(((Div)arv).arg2);

    if (arv instanceof Num)
      return new Float(((Num)arv).num);
  
    throw new Exception("Interpreting error, mal formed tree. Aborting.");

  }

}
