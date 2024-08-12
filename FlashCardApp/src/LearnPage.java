import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class LearnPage extends JFrame{



    LearnPage(){

        setLayout(new GridBagLayout());

        // Title label
        JLabel titleLabel = new JLabel("Title of card");
        
        GridBagConstraints titleLabelGBC = UIController.getGBC(0, 0);
        titleLabelGBC.gridwidth = 2;
        add(titleLabel, titleLabelGBC);
        


        // front content
        JTextArea frontContent = new JTextArea("Here is the front of the card");
        frontContent.setEditable(false);
        JScrollPane frontPane = new JScrollPane(frontContent);

        GridBagConstraints frontGBC = UIController.getGBC(0, 1, 1, 1);
        frontGBC.gridwidth = 2;
        frontGBC.fill = GridBagConstraints.BOTH;
        add(frontPane, frontGBC);

        // back label
        JLabel backLabel = new JLabel("Back:");
        
        GridBagConstraints backLabelGBC = UIController.getGBC(0, 2);
        add(backLabel, backLabelGBC);

        // back panel
        JLayeredPane backPane = new JLayeredPane();
        
        JTextArea backContent = new JTextArea("This is the actual back content of the flashcard");
        backContent.setEditable(false);
        JScrollPane backContentPanel = new JScrollPane(backContent);
        backPane.add(backContentPanel, 0);

        JButton revealButton = new JButton("Reveal Content");
        backPane.add(revealButton, 1);

        GridBagConstraints backGBC = UIController.getGBC(0, 3, 1,1);
        backGBC.fill = GridBagConstraints.BOTH;
        add(backPane, backGBC);
        
        
        // answer label 
        JLabel answerLabel = new JLabel("What you know:");
        
        GridBagConstraints answerLabelGBC = UIController.getGBC(1, 2);
        add(answerLabel, answerLabelGBC);

        // answer input
        JTextArea userInput = new JTextArea();
        JScrollPane inputPane = new JScrollPane(userInput);

        GridBagConstraints userInputGBC = UIController.getGBC(1, 3, 1, 1);
        userInputGBC.fill = GridBagConstraints.BOTH;
        add(inputPane, userInputGBC);


        // rating label
        JLabel ratingLabel = new JLabel("Do you know it?");
        
        GridBagConstraints ratingLabelGBC = UIController.getGBC(0, 4);
        ratingLabelGBC.gridwidth = 2;
        add(ratingLabel, ratingLabelGBC);

        // rating input pane
        JPanel ratingPanel = new JPanel();

        GridBagConstraints ratingPanelGBC = UIController.getGBC(0, 5, 1, 0);
        ratingPanelGBC.gridwidth = 2;
        add(ratingPanel, ratingPanelGBC);

        ratingPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        ButtonGroup ratingBG = new ButtonGroup();

        for (String s: new String[] {"Absolutely", "Yes","Mostly", "Kind Of", "A little", "Barely"}){
            JRadioButton r = new JRadioButton(s);
            ratingBG.add(r);
            ratingPanel.add(r);
        }
        


        
        // set layout
        setSize(400, 300);
        setVisible(true);

    }
}
