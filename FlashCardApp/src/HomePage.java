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
    private JRadioButton randomRadio;


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
        // NOTE - Clearing filters may show different results than Applying Filter with none selected (since applying no filters will exclude cards that are not tagged - COULD ADD A 'not tagged filter')
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
        cardsList.addKeyListener(deleteKeyListener);


        // New card button
        JButton newButton = new JButton("Add New Card");        

        GridBagConstraints newButtonGBC = UIController.getGBC(0, 6, 1,0);
        newButtonGBC.gridwidth = 2;
        add(newButton, newButtonGBC);

        // add card button click action listener
        newButton.addActionListener(newClickedListener);


        // order radio buttons
        JPanel orderPanel = new JPanel();

        GridBagConstraints orderPanelGBC = UIController.getGBC(0, 7, 1, 0);
        orderPanelGBC.gridwidth = 2;
        add(orderPanel, orderPanelGBC);

        orderPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        ButtonGroup orderBG = new ButtonGroup();

        JRadioButton knowledgeRadio = new JRadioButton("Order by Knowledge");
        knowledgeRadio.setSelected(true);
        orderBG.add(knowledgeRadio);
        orderPanel.add(knowledgeRadio);

        randomRadio = new JRadioButton("Order Randomly");
        orderBG.add(randomRadio);
        orderPanel.add(randomRadio);



        // study button
        JButton studyButton = new JButton("Study These Cards");        

        GridBagConstraints studyButtonGBC = UIController.getGBC(0, 8, 1,0);
        studyButtonGBC.gridwidth = 2;
        add(studyButton, studyButtonGBC);

        studyButton.addActionListener(studyAllClickedListener);

        resetPage();

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
            String[] sets = setsList.getSelectedValuesList().toArray(new String[setsList.getSelectedIndices().length]);
            
            controller.loadFilteredCardHeaders(tags,sets, keywordInput.getText());
            reloadCards();
        }
    };


    // clear button click

    public ActionListener clearClickedListener = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            resetPage();
        }
    };


    public void resetInputs(){
        tagsList.setListData(controller.getTags());
        setsList.setListData(controller.getSets());
        keywordInput.setText("");
    }

    public void resetPage(){
        resetInputs();
        controller.loadCardHeaders();
        reloadCards();
    }

    // new button clicked listener
    public ActionListener newClickedListener = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            controller.addNewCard();
        }
    };


    // card list item clicked listener
    MouseAdapter cardClickedListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent e){
            if (e.getClickCount() == 2 ){
                controller.editCardAtIndex(cardsList.getSelectedIndex());
            }
        }
    };

    // delete key pressed - delete selected cards
    KeyAdapter deleteKeyListener = new KeyAdapter() {
        public void keyPressed(KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_DELETE){
                controller.deleteCardsAtIndices(cardsList.getSelectedIndices());
                resetPage();
            }
        };
    };
    


    public ActionListener studyAllClickedListener = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            String[] tags = tagsList.getSelectedValuesList().toArray(new String[tagsList.getSelectedIndices().length]);
            String[] sets = setsList.getSelectedValuesList().toArray(new String[setsList.getSelectedIndices().length]);
            controller.loadFilteredCards(tags,sets, keywordInput.getText(), randomRadio.isSelected());
            
            controller.switchToLearn();
            
        }
    };

}
