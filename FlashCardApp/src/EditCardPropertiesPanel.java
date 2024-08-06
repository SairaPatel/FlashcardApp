import javax.swing.*;
import java.awt.*;

public class EditCardPropertiesPanel extends JPanel{



    EditCardPropertiesPanel(){

        setLayout(new GridBagLayout());

        // TITLE ---------------------
        // title label
        JLabel titleLabel = new JLabel("Title:");

        GridBagConstraints titleLabelGBC = UIController.getGBC(0, 0);
        add(titleLabel, titleLabelGBC);
        
        // title input
        JTextField titleInput = new JTextField();        

        GridBagConstraints titleInputGBC = UIController.getGBC(0, 1);
        add(titleInput, titleInputGBC);


        // SET -------------------------
        // set label
        JLabel setLabel = new JLabel("Set:");

        GridBagConstraints setLabelGBC = UIController.getGBC(0, 2);
        add(setLabel, setLabelGBC);

        // set input
        JComboBox<String> setInput = new JComboBox<String>();
        setInput.setEditable(true);

        GridBagConstraints setInputGBC = UIController.getGBC(0, 3);
        add(setInput, setInputGBC);
        

        // TAGS -------------------------
        String[] tags = {"Storage", "Secondary Storage and Primary Storage", "HDD", "Memory", "HDD", "Memory", "HDD", "Memory"};
        
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



        // tags label
        JLabel tagsLabel = new JLabel("Tags:");
        
        GridBagConstraints tagsLabelGBC = UIController.getGBC(0, 4);
        add(tagsLabel, tagsLabelGBC);


        // tags list
        JList<String> tagsList = new JList<String>(tags);
        JScrollPane tagsListPane = new JScrollPane(tagsList);

        tagsListPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tagsListPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        GridBagConstraints tagsGBC = UIController.getGBC(0, 5,1,1);
        tagsGBC.fill = GridBagConstraints.BOTH;
        add(tagsListPane, tagsGBC);
        
        

    }
}
