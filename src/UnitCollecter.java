import java.util.*;

public class UnitCollecter {
  
  Unit[] units;
  
  public UnitCollecter () {
    units = new Unit[4];
    units[0] = new Unit(5);
    units[1] = new Unit(4);
    units[2] = new Unit(3);
    units[3] = new Unit(2);
  }
  public void sort () {
    Arrays.sort(units);
  }
}