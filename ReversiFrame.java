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

    public ReversiFrame () {
        setGraphics();
    }
    //Probably add a new button class that extends JButton, 
    // and stores row and column as field variable
    // Change to change size?
    private void addBoard(JPanel panel) {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                JButton newButton;
                newButton = new JButton( "" + i + ", " + j); 
                newButton.setBorder(
                    BorderFactory.createLineBorder(Color.black));
                newButton.setSize(10,10);
                panel.add(newButton);
                newButton.addActionListener(myListener);
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

        newGameButton.addActionListener(myListener);
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