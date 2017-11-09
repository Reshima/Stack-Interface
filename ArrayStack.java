public class ArrayStack implements StackInterface
{

  private char[] data;
  private int top;

  public ArrayStack()
  {
    data = new char[10];
    top = 0;
  }

  public char peek()
  {
    if (top == 0)
    {
      throw new RuntimeException("Cannot peek an empty stack.");
    }
    return data[top];
  }

  public char pop()
  {
    if (top == 0)
    {
      System.out.println("Cannot pop element from an empty stack.");
    }
    char value = data[top];
    top--;
    return value;
  }

  public void push(char value)
  {
    if (top >= data.length)
    {
      increaseArrayCapacity();
    }
    data[top + 1] = value;
    top++;
  }

  private void increaseArrayCapacity()
  {
    char[] newData = new char[data.length * 2];
    for(int i = 0; i < data.length; i++)
    {
      newData[i] = data[i];
    }
    data = newData;
  }

  public boolean isEmpty()
  {
    if(top == 0)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  public int size()
  {
    return top;
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("[");
    for(int i = 1; i < top; i++)
    {
      sb.append(data[i]);
      if (i < top - 1)
      {
        sb.append(",");
      }
    }
    sb.append("]");
    return sb.toString();
  }
}
