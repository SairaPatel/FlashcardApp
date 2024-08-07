
import java.awt.*;

import javax.swing.*;

public class EditCardPage extends JFrame{
    JFrame f;

    EditCardPage(){

        setLayout(new GridBagLayout());

        // // side panel --------------------------
        // JPanel sidePanel = new JPanel(new GridBagLayout());

        // // title label
        // JLabel titleLabel = new JLabel("Title:");

        // GridBagConstraints titleLabelGBC = UIController.getGBC(0, 0);
        // sidePanel.add(titleLabel, titleLabelGBC);
        
        // // title input
        // JTextField titleInput = new JTextField();        

        // GridBagConstraints titleInputGBC = UIController.getGBC(0, 1);
        // sidePanel.add(titleInput, titleInputGBC);


        // // set label
        // JLabel setLabel = new JLabel("Set:");

        // GridBagConstraints setLabelGBC = UIController.getGBC(0, 2);
        // sidePanel.add(setLabel, setLabelGBC);

        // // set input
        // JComboBox<String> setInput = new JComboBox<String>();
        // setInput.setEditable(true);

        // GridBagConstraints setInputGBC = UIController.getGBC(0, 3);
        // sidePanel.add(setInput, setInputGBC);
        
        
        // // tags label
        // JLabel tagsLabel = new JLabel("Tags:");
        
        // GridBagConstraints tagsLabelGBC = UIController.getGBC(0, 4);
        // sidePanel.add(tagsLabel, tagsLabelGBC);


        // // tags list
        // String[] tags = {"Storage", "Secondary Storage and Primary Storage", "HDD", "Memory", "HDD", "Memory", "HDD", "Memory"};
        // JList<String> tagsList = new JList<String>(tags);
        // JScrollPane tagsListPane = new JScrollPane(tagsList);

        // tagsListPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // tagsListPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // GridBagConstraints tagsGBC = UIController.getGBC(0, 5,1,1);
        // tagsGBC.fill = GridBagConstraints.BOTH;
        // sidePanel.add(tagsListPane, tagsGBC);
        
        // // add tag label
        // JLabel addLabel = new JLabel("Add Tag:");

        // GridBagConstraints addLabelGBC = UIController.getGBC(0, 6 );
        // sidePanel.add(addLabel, addLabelGBC);

        // // add input
        // JComboBox<String> addInput = new JComboBox<String>(tags);
        // addInput.setEditable(true);

        // GridBagConstraints addInputGBC = UIController.getGBC(0, 7);
        // sidePanel.add(addInput, addInputGBC);


        // // add button
        // JButton addButton = new JButton("Add");

        // GridBagConstraints addButtonGBC = UIController.getGBC(0, 8);
        // sidePanel.add(addButton, addButtonGBC);



        // // side panel gbc
        // GridBagConstraints sidePanelGBC = UIController.getGBC(0, 0);
        // sidePanelGBC.fill = GridBagConstraints.BOTH;
        // // add(sidePanel, sidePanelGBC);

        EditCardPropertiesPanel sidePanel = new EditCardPropertiesPanel();


        // Cards Frame ----------------
        // JPanel cardsPanel = new JPanel(new GridBagLayout());


        // // front label
        // JLabel frontLabel = new JLabel("Front:");

        // GridBagConstraints frontLabelGBC = UIController.getGBC(0, 0);
        // cardsPanel.add(frontLabel, frontLabelGBC);
        
        // // front input
        // JTextArea frontInput = new JTextArea();      
        // JScrollPane frontPane = new JScrollPane(frontInput);  

        // GridBagConstraints frontInputGBC = UIController.getGBC(0, 1, 1, 1);
        // frontInputGBC.fill = GridBagConstraints.BOTH;
        // cardsPanel.add(frontPane, frontInputGBC);

        // // back label
        // JLabel backLabel = new JLabel("Back:");

        // GridBagConstraints backLabelGBC = UIController.getGBC(0, 2);
        // cardsPanel.add(backLabel, backLabelGBC);
        
        // // back input
        // JTextArea backInput = new JTextArea();        
        // JScrollPane backPane = new JScrollPane(backInput);

        // GridBagConstraints backInputGBC = UIController.getGBC(0, 3, 1, 1);
        // backInputGBC.fill = GridBagConstraints.BOTH;
        // cardsPanel.add(backPane, backInputGBC);


        EditCardContentPanel cardsPanel = new EditCardContentPanel();
        
        // cards panel gbc
        GridBagConstraints cardsPanelGBC = UIController.getGBC(1,0,1,1);
        cardsPanelGBC.fill = GridBagConstraints.BOTH;
        cardsPanelGBC.gridheight = 9;
        // add(cardsPanel, cardsPanelGBC);


        JTabbedPane tabbedPane = new JTabbedPane();
        GridBagConstraints gbc = UIController.getGBC(0,0, 1, 1);
        gbc.fill = GridBagConstraints.BOTH;

        tabbedPane.add("Properties", sidePanel);
        tabbedPane.add("Content", cardsPanel);
        
        add(tabbedPane, gbc);


        // set layout
        setSize(400, 300);
        setVisible(true);

    }

}
