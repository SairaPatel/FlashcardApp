import javax.swing.*;
import java.awt.*;

public class EditCardContentPanel extends JPanel{

    private JTextArea frontInput;
    private JTextArea backInput;

    EditCardContentPanel(){

        setLayout(new GridBagLayout());


        // front label
        JLabel frontLabel = new JLabel("Front:");

        GridBagConstraints frontLabelGBC = UIController.getGBC(0, 0);
        add(frontLabel, frontLabelGBC);
        
        // front input
        frontInput = new JTextArea();      
        JScrollPane frontPane = new JScrollPane(frontInput);  

        GridBagConstraints frontInputGBC = UIController.getGBC(0, 1, 1, 1);
        frontInputGBC.fill = GridBagConstraints.BOTH;
        add(frontPane, frontInputGBC);

        // back label
        JLabel backLabel = new JLabel("Back:");

        GridBagConstraints backLabelGBC = UIController.getGBC(0, 2);
        add(backLabel, backLabelGBC);
        
        // back input
        backInput = new JTextArea();        
        JScrollPane backPane = new JScrollPane(backInput);

        GridBagConstraints backInputGBC = UIController.getGBC(0, 3, 1, 1);
        backInputGBC.fill = GridBagConstraints.BOTH;
        add(backPane, backInputGBC);


    }

    public void setContent(String f, String b){
        frontInput.setText(f);
        backInput.setText(b);
    }

    public String getFront(){
        return frontInput.getText();
    }

    public String getBack(){
        return backInput.getText();
    }
}
