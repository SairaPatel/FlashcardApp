import javax.swing.*;
import java.awt.*;

public class EditCardContentPanel extends JPanel{



    EditCardContentPanel(){

        setLayout(new GridBagLayout());


        // front label
        JLabel frontLabel = new JLabel("Front:");

        GridBagConstraints frontLabelGBC = UIController.getGBC(0, 0);
        add(frontLabel, frontLabelGBC);
        
        // front input
        JTextArea frontInput = new JTextArea();      
        JScrollPane frontPane = new JScrollPane(frontInput);  

        GridBagConstraints frontInputGBC = UIController.getGBC(0, 1, 1, 1);
        frontInputGBC.fill = GridBagConstraints.BOTH;
        add(frontPane, frontInputGBC);

        // back label
        JLabel backLabel = new JLabel("Back:");

        GridBagConstraints backLabelGBC = UIController.getGBC(0, 2);
        add(backLabel, backLabelGBC);
        
        // back input
        JTextArea backInput = new JTextArea();        
        JScrollPane backPane = new JScrollPane(backInput);

        GridBagConstraints backInputGBC = UIController.getGBC(0, 3, 1, 1);
        backInputGBC.fill = GridBagConstraints.BOTH;
        add(backPane, backInputGBC);


    }
}
