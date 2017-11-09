import java.util.Scanner;

public class ExprConverterTest
{

  public static void main(String[] args)
  {
    ArrayStack stack = new ArrayStack();
    Scanner input = new Scanner(System.in);
    System.out.println("What Infix equation would you like to convert?");
    String intfix = input.nextLine();
    System.out.println("Postfix: " + ExprConverter.toPostfix(intfix));
    System.out.println("Prefix: " + ExprConverter.toPrefix(intfix));
  }

}
