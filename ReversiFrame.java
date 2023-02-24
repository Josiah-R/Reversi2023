/********************************************************
    * Author: Josiah Railton
	* Course: CSC 321
	* Assignment: Reversi Game GUI Sample
    * Purpose: Frame extension made for the Reversi Game
********************************************************/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ReversiFrame extends JFrame{

    /* buttons */
    private JButton newGameButton;
    
    private JLabel messageLabel;    
    private PlayListener myListener;   
    public JButton[][] buttonTable; 
    private Color GREEN;
    
    public class PlayListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command;
            JButton button;
            Icon icon;

            icon = new ImageIcon("blackPiece.PNG");
            
            //rewrite
            command = event.getActionCommand();
            button = (JButton) event.getSource();
            button.setIcon(icon);
            messageLabel.setText(command);
        }
    }

    public class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            for (int row = 0; row < buttonTable.length; row++) {
                for (int col = 0; col < buttonTable[row].length; col++) {
                    buttonTable[row][col].setIcon(null);
                }
            }
        }
    }

    //Modify if size of board can change.
    public ReversiFrame () {
        buttonTable = new JButton[8][8];
        GREEN = new Color(5, 117, 52);
        setGraphics();
    }
    // Change to change size?
    private void addBoard(JPanel panel) {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                JButton newButton;
                newButton = new JButton(); 
                newButton.setBorder(
                    BorderFactory.createLineBorder(Color.black));
                newButton.setSize(10,10);
                newButton.setBackground(GREEN);
                newButton.setOpaque(true);
                panel.add(newButton);
                newButton.addActionListener(myListener);
                buttonTable[i][j] = newButton;
            } 
        }
    }

    public void setGraphics() {
        Container myPane;
        JPanel gamePanel, messagePanel;
        
        myPane = getContentPane();
        myPane.setLayout(new GridLayout(2,1));
        
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(8,8,0,0));
        gamePanel.setBorder(
            BorderFactory.createLineBorder(Color.black));
        messagePanel = new JPanel();

        myPane.add(gamePanel);
        myPane.add(messagePanel);

        newGameButton = new JButton("New Game");
        newGameButton.setBounds(130,100,100, 40);


        messageLabel = new JLabel("Message");
        myListener = new PlayListener();

        newGameButton.addActionListener(new ResetListener());
        newGameButton.setEnabled(true);

        addBoard(gamePanel);

        /*
        JButton saveButton;
        saveButton = new JButton("Save Game");
        saveButton.setBounds(130,100,100, 40);
        */ 

        messagePanel.add(newGameButton);
        messagePanel.add(messageLabel);
        //messagePanel.add(saveButton);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,500);
        setVisible(true);
    }
}