import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HomePage extends JPanel{
    private UIController controller;

    private JList<String> setsList;
    private JList<String> tagsList;
    private JList<String> cardsList;
    private JTextField keywordInput;

    HomePage(UIController c){
        controller = c;

        setLayout(new GridBagLayout());


        // Sets label
        JLabel setsLabel = new JLabel("Sets:");

        GridBagConstraints setsLabelGBC = UIController.getGBC(0, 0);
        add(setsLabel, setsLabelGBC);
        
        // sets input
        
        setsList = new JList<String>();    
        JScrollPane setsListPanel = new JScrollPane(setsList);  

        GridBagConstraints setsGBC = UIController.getGBC(0, 1, 1, 0.5);
        setsGBC.fill = GridBagConstraints.BOTH;
        add(setsListPanel, setsGBC);


        // Tags label
        JLabel tagsLabel = new JLabel("Tags:");

        GridBagConstraints tagsLabelGBC = UIController.getGBC(1, 0);
        add(tagsLabel, tagsLabelGBC);
        
        // tags input
        tagsList = new JList<String>();      
        JScrollPane tagsListPanel = new JScrollPane(tagsList);  

        GridBagConstraints tagsGBC = UIController.getGBC(1, 1, 1, 0.5);
        tagsGBC.fill = GridBagConstraints.BOTH;
        add(tagsListPanel, tagsGBC);

        

        // keyword label
        JLabel keywordLabel = new JLabel("Keyword:");

        GridBagConstraints keywordLabelGBC = UIController.getGBC(0, 2);
        add(keywordLabel, keywordLabelGBC);
        
        // keyword input
        keywordInput = new JTextField();        

        GridBagConstraints keywordInputGBC = UIController.getGBC(0, 3, 1,0);
        keywordInputGBC.gridwidth = 2;
        add(keywordInput, keywordInputGBC);


        // apply button
        JButton applyButton = new JButton("Apply Filters");        

        GridBagConstraints applyButtonGBC = UIController.getGBC(0, 4, 1,0);
        add(applyButton, applyButtonGBC);

        // apply button click action listener
        applyButton.addActionListener(applyClickedListener);


        // clear button
        JButton clearButton = new JButton("Clear Filters");        

        GridBagConstraints clearButtonGBC = UIController.getGBC(1, 4, 1,0);
        add(clearButton, clearButtonGBC);

        // clear button click action listener
        clearButton.addActionListener(clearClickedListener);

        // cards list
        cardsList = new JList<String>();
        JScrollPane cardsPane = new JScrollPane(cardsList);

        // cards gbc
        GridBagConstraints tableGBC = UIController.getGBC(0, 5, 1, 1);
        tableGBC.fill = GridBagConstraints.BOTH;
        tableGBC.gridwidth = 2;
        add(cardsPane, tableGBC);

        // card item click action listener
        cardsList.addMouseListener(cardClickedListener);



        // order radio buttons
        JPanel orderPanel = new JPanel();

        GridBagConstraints orderPanelGBC = UIController.getGBC(0, 6, 1, 0);
        orderPanelGBC.gridwidth = 2;
        add(orderPanel, orderPanelGBC);

        orderPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        ButtonGroup orderBG = new ButtonGroup();

        JRadioButton knowledgeRadio = new JRadioButton("Order by Knowledge");
        knowledgeRadio.setSelected(true);
        orderBG.add(knowledgeRadio);
        orderPanel.add(knowledgeRadio);

        JRadioButton randomRadio = new JRadioButton("Order Randomly");
        orderBG.add(randomRadio);
        orderPanel.add(randomRadio);



        // study button
        JButton studyButton = new JButton("Study Selected Cards Only");        

        GridBagConstraints studyButtonGBC = UIController.getGBC(0, 7, 1,0);
        studyButtonGBC.gridwidth = 2;
        add(studyButton, studyButtonGBC);

        // studyAll button
        JButton studyAllButton = new JButton("Study All Displayed Cards");        

        GridBagConstraints studyAllButtonGBC = UIController.getGBC(0, 8, 1,0);
        studyAllButtonGBC.gridwidth = 2;
        add(studyAllButton, studyAllButtonGBC);



        resetInputs();


        // set layout
        setSize(400, 300);
        setVisible(true);

    }


    public void reloadCards(){
        cardsList.setListData(controller.getCurrentCardTitlesWithSets());
    }
    

    // apply button click
    public ArrayList<String> getAllCards(){
        return new ArrayList<String>();
    }

    public ActionListener applyClickedListener = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            String[] tags = tagsList.getSelectedValuesList().toArray(new String[tagsList.getSelectedIndices().length]);
            String[] sets = tagsList.getSelectedValuesList().toArray(new String[tagsList.getSelectedIndices().length]);
            controller.filterCurrentCards(tags,sets, keywordInput.getText());
            
            reloadCards();
        }
    };


    // clear button click

    public ActionListener clearClickedListener = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            resetInputs();
        }
    };

    public void resetInputs(){
        tagsList.setListData(controller.getTags());
        setsList.setListData(controller.getSets());
        keywordInput.setText("");

        controller.loadCardHeaders();

        reloadCards();
    }

    // card list item clicked listener
    MouseAdapter cardClickedListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent e){
            if (e.getClickCount() == 2 ){
                controller.editCardAtIndex(cardsList.getSelectedIndex());
            }
        }
    };
    

    // study button
        // get all filters
        // controller.learnCards(filters)

}
