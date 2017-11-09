public interface StackInterface
{

/** Get the top item of this stack without removing the item */
  public char peek();

/** Get the top item, removing it from this stack */
  public char pop();

/** Push a new item onto this stack. The new item may be the null reference. */
  public void push(char value);

/** Determine whether this stack is empty. */
  public boolean isEmpty();

/** Return the number of items in this stack. */
  public int size();

}
