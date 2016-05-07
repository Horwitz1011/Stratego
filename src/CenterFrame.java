//try to see if you can make the word spy come up on the button

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.plaf.basic.*;

public class CenterFrame extends JFrame {
  
  JTextArea welcomeTextArea;
  JTextArea turnCounter;
  JTextArea titleInGame;
  JPanel panel;
  JPanel sidePanel;
  UnitSetupPanel unitSetupPanel;
  JPanel container;
  JButton startGameButton;
  JButton[][] gridButtons;
  Unit[][] gridUnit;
  int startX;
  int startY;
  Unit startUnit;
  int turnCount;
  
  
  boolean isAlly;
  private static final int STRATEGO_BOARD_WIDTH = 8;
  private static final int STRATEGO_BOARD_HEIGHT = 10;  
  private static final int INT_NULL = -1;
  
// Constructor
  public CenterFrame () {
    
    panel = new JPanel();
    sidePanel= new JPanel();
    unitSetupPanel = new UnitSetupPanel(13, 1);
    container = new JPanel();
    welcomeTextArea = new JTextArea();
    startGameButton = new JButton("Start");
    turnCounter = new JTextArea();
    titleInGame = new JTextArea();
    turnCount = 0;
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
    
    
    //Location of button
    panel.setLayout(new GridLayout(STRATEGO_BOARD_WIDTH, STRATEGO_BOARD_HEIGHT));
    container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
    startGameButton.setBounds(200, 200, 75, 50);
    //Text Areas
    welcomeTextArea.append("Welcome To Stratego");
    welcomeTextArea.append("\n\n Click this Button to Start!");
    welcomeTextArea.setEditable(false);  
    turnCounter.setText("Stratego!\n\nTurn: "+(turnCount+1));
    turnCounter.append("\n\nIt is Player "+(turnCount%2+1)+"'s Turn.");
    turnCounter.setEditable(false);
    
    panel.add(welcomeTextArea);
    getContentPane().add(container);
    container.add(panel);
    
    
    
    startGameButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        fillBoard();
        gridButtonListeners();
        panel.remove(welcomeTextArea);
        panel.remove(startGameButton);
        container.add(sidePanel);
        sidePanel.add(turnCounter);
        sidePanel.add(unitSetupPanel);
        
        
        setSize(1100, 400);
      }
    });
    panel.add(startGameButton);
    
    
    
    this.setLayout(new BoxLayout (getContentPane(), BoxLayout.X_AXIS));
    this.add(sidePanel, 0);
    this.add(panel, 1);
    this.add(container, 2);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    panel.setSize(500, 500);
    panel.setLocation(1440/2-this.getWidth()/2, 900/2-this.getHeight()/2); 
    sidePanel.setSize(200,500);
    container.setSize(700,500);
    
    startX = INT_NULL;
    startY = INT_NULL;
  }
  
  public void setStartX (int x) {
    startX = x;
  } 
  public void setStartY (int y) {
    startY = y;
  } 
  
  public static void main(String[] args) {
    
    CenterFrame window = new CenterFrame();
    window.pack();
  }
  private void oldfillBoard () {
    gridUnit = new Unit[STRATEGO_BOARD_WIDTH][STRATEGO_BOARD_HEIGHT];
    gridButtons = new JButton[STRATEGO_BOARD_WIDTH][STRATEGO_BOARD_HEIGHT];
    for (int i=0; i<STRATEGO_BOARD_WIDTH; i++) {
      for (int j=0; j<STRATEGO_BOARD_HEIGHT; j++) {
        int teamNumber = 0;
        if (i == 0 || i == 1 || i ==2) {
          teamNumber = 0;
        }
        else if (i == 3 || i == 4) {
          teamNumber = -1;
        } 
        else if (i == 5 || i == 6 || i == 7) {
          teamNumber = 1;
        } 
        
        gridUnit[i][j] =new Unit(1, i+1, "", teamNumber); 
        gridButtons[i][j] =new JButton(""+gridUnit[i][j].getRank());
        panel.add(gridButtons[i][j]);
        
      }
      
    }
    this.pack();
  }
  
  private void fillBoard () {
    gridUnit = new Unit[STRATEGO_BOARD_WIDTH][STRATEGO_BOARD_HEIGHT];
    gridButtons = new JButton[STRATEGO_BOARD_WIDTH][STRATEGO_BOARD_HEIGHT];
    for (int i=0; i<STRATEGO_BOARD_WIDTH; i++) {
      for (int j=0; j<STRATEGO_BOARD_HEIGHT; j++) {
        int teamNumber = 0;
        if (i == 0 || i == 1 || i ==2) {
          teamNumber = 0;
        }
        else if (i == 3 || i == 4) {
          teamNumber = -1;
        } 
        else if (i == 5 || i == 6 || i == 7) {
          teamNumber = 1;
        } 
        
        gridUnit[i][j] = null;
        gridButtons[i][j] =new JButton();
        panel.add(gridButtons[i][j]);
        
        gridButtons[i][j].addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e) {
            
            if (unitSetupPanel.getUnitSelectedName() == null) {
              JOptionPane.showMessageDialog(CenterFrame.this, "Please Pick A Unit.");
            }
            JButton buttonClicked = (JButton)e.getSource();
            Unit clickedUnit = getUnitFromButton(buttonClicked);
            if (clickedUnit == null) {
            	if (unitSetupPanel.getUnitSelectedRemaining() > 0) {
            		setUnitFromButton(buttonClicked, new Unit(unitSetupPanel.getUnitSelectedName()));
            		buttonClicked.setText(unitSetupPanel.getUnitSelectedName());
            		unitSetupPanel.decrementUnitRemaining();
            		CenterFrame.this.pack();
            	}
      }
            
            if (clickedUnit != null) {
              JOptionPane.showMessageDialog(CenterFrame.this, "This Spot Has Already Been Filled!");
              return;
            }
            
          }
        });
        
      }
      
    }
    this.pack();
  }
  
  public Unit getUnitFromButton (JButton buttonClicked) { 
    for (int i=0; i<STRATEGO_BOARD_WIDTH; i++){
      for (int j=0; j<STRATEGO_BOARD_HEIGHT; j++) {
        if (buttonClicked == gridButtons[i][j]) {
          return gridUnit[i][j];
        }               
      }
    }
  return null;
  }
  
  public void setUnitFromButton (JButton buttonClicked, Unit unitToSet) {
	  for (int i=0; i<STRATEGO_BOARD_WIDTH; i++) {
		  for (int j=0; j<STRATEGO_BOARD_HEIGHT; j++) {
			  if (buttonClicked == gridButtons[i][j]) {
				  gridUnit[i][j] = unitToSet;
			  }
			  			  
		  }
	  }
	  
  }
  
  private void gridButtonListeners () {
    for (int i=0; i<STRATEGO_BOARD_WIDTH; i++) {
      for (int j=0; j<STRATEGO_BOARD_HEIGHT; j++) {
        gridButtons[i][j].addActionListener(new ActionListener(){
          
          public void actionPerformed(ActionEvent e) {
            for (int i=0; i<STRATEGO_BOARD_WIDTH; i++) {
              for (int j=0; j<STRATEGO_BOARD_HEIGHT; j++) {
                if((JButton)(e.getSource()) == gridButtons[i][j]) {
                  if(startX == INT_NULL || startY == INT_NULL) {                    
                    setStartX(i);
                    setStartY(j);
                    System.out.println("start unit set");
                  }
                  else {
                    if (isAdjacent(i, j) && !isAlly(i, j)) {
                      //attack logic
                      System.out.println("attack logic");
                      combatLogic(i, j);
                      turnCount++;
                      updateTurnCounter();
                    }
                    
                    else {
                      //System.out.println("Stop hitting yourself");
                    }
                    startX = INT_NULL;
                    startY = INT_NULL;
                  }
                }
                
              }
            }
            
          }
        });
      }
    }
  }
  
  private void updateTurnCounter () {
    turnCounter.setText("Stratego! \n\nTurn: "+(turnCount+1));
    turnCounter.append("\n\nIt is Player "+(turnCount%2+1)+"'s Turn.");
  }
  
  private boolean isAdjacent (int xCoord, int yCoord) {
    int delX = Math.abs(xCoord - startX);
    int delY = Math.abs(yCoord - startY);
    int distance = delX + delY;
    if (distance == 1) {
      return true;
    }
    else {
      System.out.println("Target is Not Adjacent");
      return false;
    }
  }
  private boolean isAlly (int xCoord, int yCoord) {
    Unit attacker = gridUnit[startX][startY];
    Unit target = gridUnit[xCoord][yCoord];  
    if (attacker == null) {
      System.out.println("Attacker is Not Your Unit");
      return true;
    }
    //Checking whos turn it is.
    if (turnCount%2 != attacker.getTeamNumber()) {
      System.out.println("It is Not Your Turn");
      return true;
    }
    if (target == null) {
      return false;
    }
    return attacker.getTeamNumber() == target.getTeamNumber();
  }
  
  private void combatLogic (int xCoord, int yCoord) {
    Unit attacker = gridUnit[startX][startY];
    Unit defender = gridUnit[xCoord][yCoord];
    int attackResult = attacker.compareTo(defender);
    if (attackResult == 1 ) {
      moveUnit(startX, startY, xCoord, yCoord);
      
    }
    if (attackResult == 0 ) {
      gridUnit[startX][startY] = null;
      gridUnit[xCoord][yCoord] = null;
      gridButtons[startX][startY].setText("");
      gridButtons[xCoord][yCoord].setText("");
    }
    if (attackResult == -1 ) {
      gridUnit[startX][startY] = null;
      gridButtons[startX][startY].setText("");
    }
  }
  
  private void moveUnit (int xFrom, int yFrom, int xTo, int yTo) {
    gridUnit[xTo][yTo] = gridUnit[xFrom][yFrom];
    gridUnit[xFrom][yFrom] = null;
    gridButtons[xTo][yTo].setText(gridButtons[xFrom][yFrom].getText());
    gridButtons[xFrom][yFrom].setText("");
  }
  
}
