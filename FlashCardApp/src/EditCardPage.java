import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class EditCardPage extends JFrame{
    JFrame f;

    EditCardPage(){

        setLayout(new GridBagLayout());

        // title label
        JLabel titleLabel = new JLabel("Title:");

        GridBagConstraints titleLabelGBC = UIController.getGBC(0, 0);
        add(titleLabel, titleLabelGBC);
        
        // title input
        JTextField titleInput = new JTextField();        

        GridBagConstraints titleInputGBC = UIController.getGBC(0, 1);
        add(titleInput, titleInputGBC);


        // set label
        JLabel setLabel = new JLabel("Set:");

        GridBagConstraints setLabelGBC = UIController.getGBC(0, 2);
        add(setLabel, setLabelGBC);

        // set input
        JComboBox<String> setInput = new JComboBox<String>();
        setInput.setEditable(true);

        GridBagConstraints setInputGBC = UIController.getGBC(0, 3);
        add(setInput, setInputGBC);
        
        
        // tags label
        JLabel tagsLabel = new JLabel("Tags:");
        
        GridBagConstraints tagsLabelGBC = UIController.getGBC(0, 4);
        add(tagsLabel, tagsLabelGBC);


        // tags list
        String[] tags = {"Storage", "Secondary Storage and Primary Storage", "HDD", "Memory", "HDD", "Memory", "HDD", "Memory"};
        JList<String> tagsList = new JList<String>(tags);
        JScrollPane tagsListPane = new JScrollPane(tagsList);
        tagsListPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        GridBagConstraints tagsGBC = UIController.getGBC(0, 5, 0, 1);
        tagsGBC.fill = GridBagConstraints.BOTH;
        add(tagsListPane, tagsGBC);
        
        // add tag label
        JLabel addLabel = new JLabel("Add Tag:");

        GridBagConstraints addLabelGBC = UIController.getGBC(0, 6 );
        add(addLabel, addLabelGBC);

        // add input
        JComboBox<String> addInput = new JComboBox<String>(tags);
        addInput.setEditable(true);

        GridBagConstraints addInputGBC = UIController.getGBC(0, 7);
        add(addInput, addInputGBC);


        // add button
        JButton addButton = new JButton("Add");

        GridBagConstraints addButtonGBC = UIController.getGBC(0, 8);
        add(addButton, addButtonGBC);


        // Cards Frame ----------------
        JPanel cardsPanel = new JPanel(new GridBagLayout());



        // front label
        JLabel frontLabel = new JLabel("Front:");

        GridBagConstraints frontLabelGBC = UIController.getGBC(0, 0);
        cardsPanel.add(frontLabel, frontLabelGBC);
        
        // front input
        JTextArea frontInput = new JTextArea();      
        JScrollPane frontPane = new JScrollPane(frontInput);  

        GridBagConstraints frontInputGBC = UIController.getGBC(0, 1, 1, 1);
        frontInputGBC.fill = GridBagConstraints.BOTH;
        cardsPanel.add(frontPane, frontInputGBC);

        // back label
        JLabel backLabel = new JLabel("Back:");

        GridBagConstraints backLabelGBC = UIController.getGBC(0, 2);
        cardsPanel.add(backLabel, backLabelGBC);
        
        // back input
        JTextArea backInput = new JTextArea();        
        JScrollPane backPane = new JScrollPane(backInput);

        GridBagConstraints backInputGBC = UIController.getGBC(0, 3, 1, 1);
        backInputGBC.fill = GridBagConstraints.BOTH;
        cardsPanel.add(backPane, backInputGBC);



        // cards panel gbc
        GridBagConstraints cardsPanelGBC = UIController.getGBC(1,0,1,1);
        cardsPanelGBC.fill = GridBagConstraints.BOTH;
        cardsPanelGBC.gridheight = 9;
        add(cardsPanel, cardsPanelGBC);


        // set layout
        setSize(400, 400);
        setVisible(true);

    }

}
