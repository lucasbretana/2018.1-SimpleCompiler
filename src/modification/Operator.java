package modification;

import modification.Exp;

public class Operator extends Exp{
  Exp arg1;
  Exp arg2;
  public Operator(Exp a1, Exp a2) { 
    arg1 = a1;
    arg2 = a2;
  }
}
