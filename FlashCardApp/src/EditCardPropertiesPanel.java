import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EditCardPropertiesPanel extends JPanel{


    private JTextField titleInput;
    private JComboBox<String> setInput;
    private DefaultListModel<String> tagsListModel;
    private DefaultComboBoxModel<String> tagsComboModel;
    private DefaultComboBoxModel<String> setsComboModel;

    private ArrayList<String> addedTags;
    private ArrayList<String> removedTags;
    EditCardPropertiesPanel(){

        
        // added Tags list
        addedTags = new ArrayList<String>();
        removedTags = new ArrayList<String>();


        setLayout(new GridBagLayout());

        // TITLE ---------------------
        // title label
        JLabel titleLabel = new JLabel("Title:");

        GridBagConstraints titleLabelGBC = UIController.getGBC(0, 0);
        add(titleLabel, titleLabelGBC);
        
        // title input
        titleInput = new JTextField();        

        GridBagConstraints titleInputGBC = UIController.getGBC(0, 1);
        add(titleInput, titleInputGBC);


        // SET -------------------------
        // set label
        JLabel setLabel = new JLabel("Set:");

        GridBagConstraints setLabelGBC = UIController.getGBC(0, 2);
        add(setLabel, setLabelGBC);

        // set input
        setsComboModel = new DefaultComboBoxModel<String>();
        setInput = new JComboBox<String>(setsComboModel);
        setInput.setEditable(true);

        GridBagConstraints setInputGBC = UIController.getGBC(0, 3);
        add(setInput, setInputGBC);
        

        // TAGS -------------------------
        // String[] tags = {"Storage", "Secondary Storage and Primary Storage", "HDD", "Memory", "HDD", "Memory", "HDD", "Memory"};
        
        // add tag label
        JLabel addLabel = new JLabel("Add Tag:");

        GridBagConstraints addLabelGBC = UIController.getGBC(0, 4 );
        add(addLabel, addLabelGBC);

        // add input
        tagsComboModel = new DefaultComboBoxModel<String>();
        JComboBox<String> addInput = new JComboBox<String>(tagsComboModel);
        
        addInput.setEditable(true);

        GridBagConstraints addInputGBC = UIController.getGBC(0, 5);
        add(addInput, addInputGBC);

        // add button
        JButton addButton = new JButton("Add");

        GridBagConstraints addButtonGBC = UIController.getGBC(0, 6);
        add(addButton, addButtonGBC);


        // add button click action listener
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                // add tag (if not already added)
                if (addInput.getSelectedItem() != null){
                    String item = addInput.getSelectedItem().toString();
                    if (tagsListModel.contains(item)){
                        JOptionPane.showMessageDialog(titleInput, "This tag has already been added.");
                    }
                    else{
                        tagsListModel.addElement(item);

                        // update removedTags/addedTags lists
                        if (removedTags.contains(item)){
                            removedTags.remove(item);
                        }
                        else{
                            addedTags.add(item);
                        }
                    
                    }
                    addInput.setSelectedIndex(-1);
    
                }
                
            }
        });


        // tags label
        JLabel tagsLabel = new JLabel("Tags:");
        
        GridBagConstraints tagsLabelGBC = UIController.getGBC(0, 7);
        add(tagsLabel, tagsLabelGBC);


        tagsListModel  = new DefaultListModel<String>();
        // tags list
        JList<String> tagsList = new JList<String>(tagsListModel);
        JScrollPane tagsListPane = new JScrollPane(tagsList);

        

        tagsListPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tagsListPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        GridBagConstraints tagsGBC = UIController.getGBC(0, 8,1,1);
        tagsGBC.fill = GridBagConstraints.BOTH;
        add(tagsListPane, tagsGBC);

        
        
        // tags list delete button pressed listener
        tagsList.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e){
                if (tagsList.getSelectedIndex() > -1 && e.getKeyCode() == KeyEvent.VK_DELETE){
                    for (String s: tagsList.getSelectedValuesList()){

                        // update removedTags/addedTags lists
                        if (addedTags.contains(s)){
                            addedTags.remove(s);
                        }
                        else{
                            removedTags.add(s);
                        }

                        tagsListModel.removeElement(s);
                    }
                }
            }
        });

    }

    public void setProperties(String title, String set, String[] tags, String[] allSets, String[] allTags){
        
        setsComboModel.removeAllElements();
        tagsComboModel.removeAllElements();
        tagsListModel.removeAllElements();

        for (String s: allSets){
            setsComboModel.addElement(s);
        }

        for(String t: allTags){
            tagsComboModel.addElement(t);
        }
        
        for (String tag: tags){
            tagsListModel.addElement(tag);
        }
        titleInput.setText(title);
        setInput.setSelectedItem(set);
    }


    public String getTitle(){
        return titleInput.getText();
    }

    public String getSet(){
        if (setInput.getSelectedItem() == null){
            return "My Set";
        }
        
        return setInput.getSelectedItem().toString();
    }
    // public String[] getTags(){
    //     String[] ts = new String[tagsListModel.size()];
    //     tagsListModel.copyInto(ts);
    //     return ts;
    // }

    public ArrayList<String> getAddedTags(){
        return addedTags;
    }

    public ArrayList<String> getRemovedTags(){
        return removedTags;
    }


}
