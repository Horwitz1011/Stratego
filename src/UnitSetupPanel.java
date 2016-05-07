import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.basic.*;

public class UnitSetupPanel extends JPanel {

  JButton[][] unitButtons;
  String[] unitNames;
  String unitSelectedName;
  int unitSelectedRemaining;
  int[] unitCounts;
  JButton buttonClicked;
  
   /** Pieces:  
      10 Marshal: 1
      9 General: 1
      8 Colonel: 1
      7 Major: 1
      6 Captain: 2
      5 Lieutenant: 2
      4 Sergeant: 2
      3 Miner: 5
      2 ScoutL 5
      1 Spotter: 2
      
      Spy: 1
      Bomb: 6
      Flag: 1**/
  
//Constructor
  public UnitSetupPanel (int x, int y) {
    
  setLayout(new GridLayout(x, y));  
  unitButtons = new JButton[x][y];
  //  unitNames = new String[] {"Spy","Spotter","Scout","Miner","Sergeant","Lieautenant","Captain","Major","Colonel","General","Marshal","Bomb","Flag"}; 
  unitNames = new String[] {"Spy","Spotter","Scout","Miner","Sgt.","Lt.","Captain","Major","Colonel","General","Marshal","Bomb","Flag"}; 
  unitCounts = new int[] {1,2,5,5,2,2,2,1,1,1,1,6,1};
  fillUnitSetupPanel();
  }

  public void fillUnitSetupPanel () {
    for (int i=0; i<((GridLayout)getLayout()).getRows(); i++) {
      for (int j=0; j<((GridLayout)getLayout()).getColumns(); j++) {
        unitButtons[i][j] = new JButton(unitNames[i] + " : " + unitCounts[i]);
      this.add(unitButtons[i][j]);
      unitButtons[i][j].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
          JButton buttonClicked = (JButton)e.getSource();
          UnitSetupPanel.this.buttonClicked = buttonClicked;
         String[] unitInfo = buttonClicked.getText().split(":");
          unitSelectedName = unitInfo[0];
          unitSelectedRemaining = Integer.parseInt(unitInfo[1].trim());
        System.out.println(unitSelectedName);
        }
      });
      
      }
    }
  }
  
  private String buttonName (int x, int y) {
  return null;
  }
  
  public String getUnitSelectedName() {
  return unitSelectedName;
  }

public int getUnitSelectedRemaining() {
	return unitSelectedRemaining;
}

public void decrementUnitRemaining() {
	buttonClicked.setText(unitSelectedName + " : " + (unitSelectedRemaining - 1));
}

  
}