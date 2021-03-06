package mySortedIntegerList.src;

import java.util.LinkedList;
import java.util.ListIterator;


public class SortedIntegerList {
  protected LinkedList<Integer> list;
  protected boolean allowsEqualElements;

  public SortedIntegerList(boolean allowsEqualElements) {
    this.list = new LinkedList<Integer>();
    this.allowsEqualElements = allowsEqualElements;
  }

  public int get(int index) {
    return this.list.get(index);
  }

  public boolean add(int value) {
    if (this.list.size() == 0) {
      this.list.add(value);
      return true;
    }

    ListIterator<Integer> itr = this.getIterator();
    for (int i = 0; i < this.getSize(); i++) {
      int current = itr.next();
      if (current == value && !this.allowsEqualElements()) {
        return false;
      }
      if (!(value > current)) {
        itr.previous();
        itr.add(value);
        // this.list.add(i, value);
        return true;
      }
    }
    this.list.addLast(value);
    return true;
  }

  public boolean remove(int value) {
    boolean success = false;
    ListIterator<Integer> itr= this.getIterator();
    int current;
    while (itr.hasNext()) {
      current = itr.next();
      if (current == value) {
        itr.remove();
        success = true;
      }
    }

    return success;
    // int idx = this.list.indexOf(value);
    // if (idx != -1) {
    //   this.list.remove(idx);
    //   if (this.allowsEqualElements()) {
    //     idx = this.list.indexOf(value);
    //     while (idx != -1) {
    //       // this.list.remove(idx);

    //       idx = this.list.indexOf(value);
    //     }
    //   }
    //   return true;
    // }
    // return false;
  }

  public int getSize() {
    return this.list.size();
  }

  public ListIterator<Integer> getIterator() {
    return this.list.listIterator(0);
  }

  public boolean allowsEqualElements() {
    return this.allowsEqualElements;
  }

  @Override
  public boolean equals(Object op) {
    if (!(op instanceof SortedIntegerList)) {
      return false;
    }
    if (this == op) {
      return true;
    }

    SortedIntegerList opCast = (SortedIntegerList)op;

    if (this.getSize() == opCast.getSize()) {
      if (this.getSize() == 0) {
        return true;
      }
      ListIterator<Integer> thisItr = this.getIterator();
      ListIterator<Integer> opItr = opCast.getIterator();
      while (thisItr.hasNext() && opItr.hasNext()) {
        if (thisItr.next() != opItr.next()) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  @Override
  public String toString(){
    if (this.list.size() == 0) {
      return "";
    }
    ListIterator<Integer> itr = this.getIterator();
    StringBuilder string = new StringBuilder();
    string.append("[ ");
    string.append(itr.next());
    while (itr.hasNext()) {
      string.append(", ");
      string.append(itr.next());
    }
    string.append(" ]");
    return string.toString();
  }

  public void log() {
    System.out.println(this.toString());
  }

  /* Additional task */

  public SortedIntegerList remove(SortedIntegerList op) {
    ListIterator<Integer> thisItr = this.getIterator();
    ListIterator<Integer> opItr;
    SortedIntegerList result = new SortedIntegerList(this.allowsEqualElements);
    int current, currentOp;
    boolean currentElementIsInOpList = false;

    while (thisItr.hasNext()) {
      current = thisItr.next();
      opItr = op.getIterator();
      currentElementIsInOpList = false;

      while (opItr.hasNext()) {
        currentOp = opItr.next();
        if (current < currentOp) {
          break;
        }
        if (currentOp == current) {
          currentElementIsInOpList = true;
          break;
        }
      }

      if (!currentElementIsInOpList) {
        result.add(current);
      }
    }
    return result;
  }

}
