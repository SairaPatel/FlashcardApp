import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.lang.Math;

public class LearnCardPanel extends JPanel{

    private JLabel titleLabel;
    private JTextArea frontContent;
    private JTextArea userInput;
    private JTextArea backContent;
    private JRadioButton[] ratingButtons;

    private JToggleButton revealButton;
    private JScrollPane backContentPanel;

    LearnCardPanel(){

        setLayout(new GridBagLayout());

        // Title label
        titleLabel = new JLabel("Title of card", SwingConstants.CENTER);
        
        GridBagConstraints titleLabelGBC = Controller.getGBC(0, 0);
        titleLabelGBC.gridwidth = 2;
        add(titleLabel, titleLabelGBC);
        


        // front content
        frontContent = new JTextArea("Here is the front of the card");
        frontContent.setEditable(false);
        frontContent.setLineWrap(true);
        JScrollPane frontPane = new JScrollPane(frontContent);

        GridBagConstraints frontGBC = Controller.getGBC(0, 1, 1, 1);
        frontGBC.gridwidth = 2;
        frontGBC.fill = GridBagConstraints.BOTH;
        add(frontPane, frontGBC);


        // back panel
        backContent = new JTextArea("card's back content");
        backContent.setEditable(false);
        backContent.setLineWrap(true);

        backContentPanel = new JScrollPane(backContent);
        backContentPanel.setVisible(false);
        
        GridBagConstraints backGBC = Controller.getGBC(0, 3, 1,1);
        backGBC.fill = GridBagConstraints.BOTH;
        add(backContentPanel, backGBC);
        
        
        // answer label 
        JLabel answerLabel = new JLabel("What you know:", SwingConstants.CENTER);
        
        GridBagConstraints answerLabelGBC = Controller.getGBC(1, 2);
        answerLabelGBC.anchor = GridBagConstraints.CENTER;
        answerLabelGBC.fill = GridBagConstraints.NONE;
        add(answerLabel, answerLabelGBC);

        // answer input
        userInput = new JTextArea();
        userInput.setLineWrap(true);
        JScrollPane inputPane = new JScrollPane(userInput);


        GridBagConstraints userInputGBC = Controller.getGBC(1, 3, 1, 1);
        userInputGBC.fill = GridBagConstraints.BOTH;
        add(inputPane, userInputGBC);


        // reveal back button
        revealButton = new JToggleButton("Show Content");
                
        GridBagConstraints revealButtonGBC = Controller.getGBC(0, 4);
        revealButtonGBC.gridwidth = 2;

        revealButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e){
                if (revealButton.getText() == "Show Content"){
                    revealButton.setText("Hide Content");
                    backContentPanel.setVisible(true);
                }
                else{
                    revealButton.setText("Show Content");
                    backContentPanel.setVisible(false);
                }
            }
            
        });

        add(revealButton, revealButtonGBC);


        // rating label
        JLabel ratingLabel = new JLabel("Do you know it?", SwingConstants.CENTER);
        
        GridBagConstraints ratingLabelGBC = Controller.getGBC(0, 5);
        ratingLabelGBC.anchor = GridBagConstraints.PAGE_END;
        ratingLabelGBC.gridwidth = 2;
        add(ratingLabel, ratingLabelGBC);

        // rating input pane
        JPanel ratingPanel = new JPanel();

        GridBagConstraints ratingPanelGBC = Controller.getGBC(0, 6, 1, 0);
        ratingPanelGBC.gridwidth = 2;
        add(ratingPanel, ratingPanelGBC);

        ratingPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        ButtonGroup ratingBG = new ButtonGroup();

        String[] ratings = new String[] { "Barely", "A little",  "Kind Of", "Mostly", "Yes","Absolutely"};
        
        ratingButtons = new JRadioButton[6];
        for (int i =0; i<6; i++){

            ratingButtons[i] = new JRadioButton(ratings[i]);
            ratingBG.add(ratingButtons[i]);
            ratingPanel.add(ratingButtons[i]);

        }
        
    }


    public void setValues(String title, String front, String back, float rating){
        titleLabel.setText(title);
        frontContent.setText(front);
        backContent.setText(back);
        userInput.setText("");

        ratingButtons[(int)rating].setSelected(true);

        revealButton.setText("Show Content");
        backContentPanel.setVisible(false);
    }

    public int getSelectedRating(){
        for (int i =0; i<6;i++){
            if (ratingButtons[i].isSelected()){
                return i;
            }
        }
        return 0;
    }

    
}
