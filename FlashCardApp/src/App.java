import java.awt.*;
import javax.swing.*;

public class App extends JFrame{
    JFrame f;

    public App(){

        // card layout panel
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        Controller controller = new Controller(cardLayout, mainPanel);
        
        // cards
        HomePage home = new HomePage(controller);
        mainPanel.add("home", home);
        EditCardPage edit = new EditCardPage(controller);
        mainPanel.add("edit", edit);
        LearnPage learn = new LearnPage(controller);
        mainPanel.add("learn", learn);

        controller.setPages(home, edit, learn);

        add(mainPanel, BorderLayout.CENTER);

        // set layout
        setSize(420, 500);
        setVisible(true);
    }   
    
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        new App();
    }
}
