import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class App extends JFrame{
    JFrame f;

    public App(){
        setLayout(new GridBagLayout());

        // back button
        JButton backButton = new JButton("Back");
        backButton.setVisible(false);

        GridBagConstraints backButtonGBC = UIController.getGBC(0, 0);
        backButtonGBC.anchor = GridBagConstraints.LINE_START;
        add(backButton, backButtonGBC);

        // card layout panel
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // cards
        HomePage home = new HomePage(cardLayout);
        mainPanel.add("home", home);

        EditCardPage edit = new EditCardPage(cardLayout);
        mainPanel.add("edit", edit);
        
        LearnPage learn = new LearnPage(cardLayout);
        mainPanel.add("learn", learn);

        

    }   
    
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        
        // new EditCardPage();
        // new LearnPage();
        // new HomePage();
        new App();

        




        
    }
}
