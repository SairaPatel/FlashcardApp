import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class App extends JFrame{
    JFrame f;
    
    public static void main(String[] args) throws Exception {
        // UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        
        new EditCardPage();
        
    }
}
