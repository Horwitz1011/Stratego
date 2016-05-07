public class Unit implements Comparable<Unit> {
  
  private int movementPoints;
  private int rank;
  private String name;
  private int teamNumber;
  private boolean canDefuseBombs;
  private boolean canKillMarshal;
  
  public Unit (int movementPoints, int rank, String name, int teamNumber) {
    this.movementPoints = movementPoints;
    this.rank = rank;
    this.name = name;
    this.teamNumber = teamNumber;
  } 
  public Unit (String name) {
	  switch (name) {
	  case "Spotter":
	        name = "Spotter";
	        rank = 1;
	      break;
	      case "Scout":
	        name = "Scout";
	        rank = 2;
	        movementPoints = Integer.MAX_VALUE;
	      break;
	      case "Miner":
	        name = "Miner";
	        rank = 3;
	        canDefuseBombs = true;
	      break;
	      case "Sgt.":
	        name = "Sgt.";
	        rank = 4;
	      break;
	      case "Lt.":
	        name = "Lt.";
	        rank = 5;
	      break;
	      case "Captain":
	        name = "Captain";
	        rank = 6;
	      break;
	      case "Major":
	        name = "Major";
	        rank = 7;
	      break;
	      case "Colonel":
	        name = "Colonel";
	        rank = 8;
	      break;
	      case "General":
	        name = "General";
	        rank = 9;
	      break;
	      case "Marshal":
	        name = "Marshal";
	        rank = 10;
	      break;
	       case "Spy":
	        name = "Spy";
	        rank = 0;
	        canKillMarshal = true;
	      break;
	      case "Bomb":
	        name = "Bomb";
	        rank = 11;
	      break;
	      case "Flag":
	        name = "Flag";
	        rank = 0;
	      break;
	  }
  }
  public Unit (int rank) {
  this(1, rank, "", 0);
  }
  public int getMovementPoints () {
    return movementPoints;
  }
  public int getRank () {
    return rank;
  }
  public String getName () {
    return name;
  }
  public int getTeamNumber () {
  return teamNumber;
  }
  public int compareTo (Unit u) {
    if (u == null) {
      return 1;
    }
    if (this.rank > u.getRank()) {
      return 1;
    }
    else if (this.rank < u.getRank()) {
    return -1;
    }
    else {
    return 0;
    }
  } 
}