public class UnitInfo {
  
  int count;
  int teamNumber;
  int rank;
  int move;
  String name;
  boolean canDefuseBombs;
  boolean canKillMarshal;
  
  /** Pieces:  
      10 Marshal: 1
      9 General: 1
      8 Colonel: 1
      7 Major: 1
      6 Captain: 2
      5 Lieutenant: 2
      4 Sergeant: 2
      3 Miner: 5
      2 Scout 5
      1 Spotter: 2
      
      Spy: 1
      Bomb: 6
      Flag: 1**/
    
  
  public UnitInfo (int x, int y) {
    teamNumber = y;
    move = 1;
    canDefuseBombs = false;
    canKillMarshal = false;
    initializeUnits(x);
  }
  private void initializeUnits (int x) {
    switch (x) {
      case 0:
        name = "Spotter";
        count = 2;
        rank = 1;
      break;
      case 1:
        name = "Scout";
        count = 5;
        rank = 2;
        move = Integer.MAX_VALUE;
      break;
      case 2:
        name = "Miner";
        count = 5;
        rank = 3;
        canDefuseBombs = true;
      break;
      case 3:
        name = "Sergeant";
        count = 2;
        rank = 4;
      break;
      case 4:
        name = "Lieutenant";
        count = 2;
        rank = 5;
      break;
      case 5:
        name = "Captain";
        count = 2;
        rank = 6;
      break;
      case 6:
        name = "Major";
        count = 1;
        rank = 7;
      break;
      case 7:
        name = "Colonel";
        count = 1;
        rank = 8;
      break;
      case 8:
        name = "General";
        count = 1;
        rank = 9;
      break;
      case 9:
        name = "Marshal";
        count = 1;
        rank = 10;
      break;
       case 10:
        name = "Spy";
        count = 1;
        rank = 0;
        canKillMarshal = true;
      break;
      case 11:
        name = "Bomb";
        count = 6;
        rank = 11;
      break;
      case 12:
        name = "Flag";
        count = 1;
        rank = 0;
      break;
    }
  }
}