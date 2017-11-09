/* First off I want to say I am very sorry about the state of this code. No matter what I tried I couldn't get it to run right and wil be coming to office hours
with some questions. I left some commented out code in here so that you might be able to see what I was trying to do. It didn't turn out well but I hope to
learn more in the future.*/
public class ExprConverter
{
  private static char op;
  private static char leftOperand;
  private static char rightOperand;

  public static int valueOf(char op)
  {
    int value = 0;
    if(op == '+' || op == '-')
    {
      value = 1;
    }
    else if(op == '*' || op == '/')
    {
      value = 8;
    }
    else
    {
      value = 0;
    }
    return value;
  }

  public static String toPostfix(String intfix)
  {
    ArrayStack post = new ArrayStack();
    String postfix = "";
    for(int i = 0; i < intfix.length(); i++)
    {
      if(Character.isLetter(intfix.charAt(i)) == true)
      {
        postfix += intfix.charAt(i);
      }
      else if((intfix.charAt(i)=='+')||(intfix.charAt(i)=='-'))
      {
        if(post.isEmpty() == true)
        {
//          System.out.println("Empty: " + post.toString());
          post.push(intfix.charAt(i));
//          System.out.println(post.toString());
        }
        else
        {
          while(post.isEmpty() == false)
          {
            postfix += post.pop();
//            System.out.println(postfix);
          }
          post.push(intfix.charAt(i));
        }
      }
      else if((intfix.charAt(i)=='*')||(intfix.charAt(i)=='/'))
      {
        if(post.isEmpty() == true)
        {
          post.push(intfix.charAt(i));
        }
        else
        {
          while(post.peek() != '+' || post.peek() != '-')
          {
            postfix += post.pop();
//            System.out.println(postfix);
            if(post.isEmpty() == true)
            {
              break;
            }
          }
          post.push(intfix.charAt(i));
        }
      }
      else if(intfix.charAt(i)=='(')
      {
//        String temp = "";
        while(intfix.charAt(i) != ')' && i < intfix.length())
        {
          if(Character.isLetter(intfix.charAt(i)))
          {
            postfix += intfix.charAt(i);
            i++;
          }
          else
          {
            post.push(intfix.charAt(i));
            i++;
//            System.out.println(post.toString());
          }
        }
//        if(post.isEmpty() == true)
//        {
//          System.out.println("Parenthesis are unbalanced.");
//          System.exit(0);
//        }
      }
/*        if(post.isEmpty() == true)
        {
          post.push(intfix.charAt(i));
          System.out.println("here");
        }
        else
        {
          while(ExprConverter.valueOf(post.peek()) >=  ExprConverter.valueOf(intfix.charAt(i)))
          {
            postfix += post.pop();
            System.out.println("here");
          }
          System.out.println(post.toString());
          post.push(intfix.charAt(i));
          System.out.println(post.toString());
        }
      }*/
      else if(intfix.charAt(i) == ')')
      {
        while(post.peek() != '(')
        {
          if(post.isEmpty() == false)
          {
//            System.out.println(post.toString());
            postfix += post.pop();
          }
          else
          {
            System.out.println("Parenthesis are not balanced");
            System.exit(0);
          }
        }
//        System.out.println(post.toString());
        post.pop();
      }
      else
      {
        postfix = "Must use operators and variables only";
      }
    }
    for(int i = post.size(); i > 0; i--)
    {
      if(post.peek() == '(')
      {
        System.out.println("Parenthesis are not balanced");
      }
      postfix += post.pop();
    }
    return postfix;
  }

  public static String toPrefix(String infix)
  {
    String prefix = "";
    ArrayStack operand = new ArrayStack();
    ArrayStack operator = new ArrayStack();
    for(int i = 0; i < infix.length(); i++)
    {
      if(Character.isLetter(infix.charAt(i)))
      {
        operand.push(infix.charAt(i));
      }
      if(infix.charAt(i) == '(')
      {
        while(infix.charAt(i) != ')')
        {
          if(Character.isLetter(infix.charAt(i)) == true)
          {
            operand.push(infix.charAt(i));
          }
          else
          {
            operator.push(infix.charAt(i));
          }
          i++;
          if(i == infix.length())
          {
            System.out.println("Parenthesis are unbalanced");
            System.exit(0);
          }
        }
//        System.out.println(operand.toString());
      }
      if(infix.charAt(i) == ')')
      {
        if(operator.isEmpty() == true)
        {
          System.out.println("Parenthesis are unbalanced.");
          System.exit(0);
        }
        while(operator.peek() != '(' && operand.isEmpty() == false)
        {
          op = operator.pop();
          rightOperand = operand.pop();
          leftOperand = operand.pop();
          operand.push(op);
          operand.push(leftOperand);
          operand.push(rightOperand);
//          System.out.println(operand.toString());
          if(operator.isEmpty() == true)
          {
            System.out.println("Parenthesis are unbalanced");
            System.exit(0);
          }
        }
      }
      if(infix.charAt(i) == '+' || infix.charAt(i) == '-')
      {
        if(operator.isEmpty() == true)
        {
          operator.push(infix.charAt(i));
        }
        else
        {
          while(operator.isEmpty() == false)
          {
            op = operator.pop();
            rightOperand = operand.pop();
            leftOperand = operand.pop();
            operand.push(op);
            operand.push(leftOperand);
            operand.push(rightOperand);
          }
          operator.push(infix.charAt(i));
        }
      }
      if(infix.charAt(i) == '*' || infix.charAt(i) == '/')
      {
        if(operator.isEmpty() == true)
        {
          operator.push(infix.charAt(i));
        }
        else
        {
          while((operator.peek() != '+' || operator.peek() != '-') && operand.isEmpty() == false)
          {
            op = operator.pop();
            rightOperand = operand.pop();
            leftOperand = operand.pop();
            operand.push(op);
            operand.push(leftOperand);
            operand.push(rightOperand);
            if(operator.isEmpty() == true)
            {
              operator.push(infix.charAt(i));
              break;
            }
          }
        }
      }
    }
    if(operator.isEmpty() == false)
    {
      while(operator.isEmpty() == false && operand.isEmpty() == false)
      {
        op = operator.pop();
        rightOperand = operand.pop();
        leftOperand = operand.pop();
        operand.push(op);
        operand.push(leftOperand);
        operand.push(rightOperand);
      }
    }
    for(int i = 0; i < operand.size(); i++)
    {
      prefix += operand.pop();
    }
    return prefix;
  }
}
