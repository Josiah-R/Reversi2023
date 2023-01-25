import javax.swing.*;  

public class GUISample {  
    public static void main(String[] args) {  
        JFrame window;
        window = new JFrame();
          
        JButton button;
        button = new JButton("New Game"); 
        button.setBounds(130,100,100, 40); //x axis, y axis, width, height  
          
        JLabel title;
        title = new JLabel("Reversi");
        title.setBounds(130,50,100, 40);

        window.add(button);//adding button in JFrame  
        window.add(title);
          
        window.setSize(400,500);//400 width and 500 height  
        window.setLayout(null);//using no layout managers  
        window.setVisible(true);//making the frame visible  
    }  
}  
