import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class App extends JFrame{
    JFrame f;

    public App(){
        // setLayout(new GridBagLayout());
        

        // card layout panel
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        UIController controller = new UIController(cardLayout, mainPanel);
        

        // cards
        HomePage home = new HomePage(controller);
        mainPanel.add("home", home);
        EditCardPage edit = new EditCardPage(controller);
        mainPanel.add("edit", edit);
        LearnPage learn = new LearnPage(controller);
        mainPanel.add("learn", learn);

        controller.setPages(home, edit, learn);

        
        add(mainPanel, BorderLayout.CENTER);

        addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e){
                System.out.println(getSize());
            }
        });

        // set layout
        
        setSize(420, 500);
        setVisible(true);



        
    }   
    
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        
        // new EditCardPage();
        // new LearnPage();
        // new HomePage();
        new App();

        




        
    }
}
