import javax.swing.*;

public class App extends JFrame{
    JFrame f;
    App(){
        JButton b = new JButton("Hi");
        b.setBounds(200, 200, 50, 50);

        add(b);
        setSize(400, 400);
        setLayout(null);
        setVisible(true);
    }
    public static void main(String[] args) throws Exception {
        new EditCardPage();
    }
}
