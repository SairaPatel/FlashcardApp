
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class EditCardPage extends JPanel{
    
    private UIController controller;
    private EditCardPropertiesPanel sidePanel;
    private EditCardContentPanel cardsPanel;
    
    JFrame f;

    EditCardPage(UIController c){
        controller = c;
        setLayout(new GridBagLayout());

        // quit button
        JButton quitButton = new JButton("Quit");

        GridBagConstraints quitGBC = UIController.getGBC(0, 0);
        quitGBC.anchor = GridBagConstraints.LINE_START;
        quitGBC.fill = GridBagConstraints.NONE;
        add(quitButton, quitGBC);
        
        // quit button click action listener
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                controller.switchToHome();
            }
        });
        

        // side panel
        sidePanel = new EditCardPropertiesPanel();

        // cards panel
        cardsPanel = new EditCardContentPanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        GridBagConstraints gbc = UIController.getGBC(0,1, 1, 1);
        gbc.fill = GridBagConstraints.BOTH;

        tabbedPane.add("Properties", sidePanel);
        tabbedPane.add("Content", cardsPanel);
        
        add(tabbedPane, gbc);


         // save button
         JButton saveButton = new JButton("Save");

         GridBagConstraints saveGBC = UIController.getGBC(0, 2);
         add(saveButton, saveGBC);
         
         // save button click action listener
         saveButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e){

                controller.updateCard(sidePanel.getTitle(), sidePanel.getSet(), sidePanel.getAddedTags(), sidePanel.getRemovedTags(), cardsPanel.getFront(), cardsPanel.getBack());
                controller.switchToHome();
             }
         });


        // set layout
        setSize(400, 300);
        setVisible(true);

    }

    public void loadCard(){
        Card c = controller.getCurrentCard();

        sidePanel.setProperties(c.getTitle(), c.getSet(), c.getTags(), controller.getSets(), controller.getTags());
        cardsPanel.setContent(c.getFront(), c.getBack());
    }

}
