import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JPanel{
    private Controller controller;

    private JList<String> setsList;
    private JList<String> tagsList;
    private JList<String> cardsList;
    private JTextField keywordInput;
    private JRadioButton byKnowledgeRadio;


    HomePage(Controller c){
        controller = c;

        setLayout(new GridBagLayout());


        // Sets label
        JLabel setsLabel = new JLabel("Sets:");

        GridBagConstraints setsLabelGBC = Controller.getGBC(0, 0);
        add(setsLabel, setsLabelGBC);
        
        // sets input
        
        setsList = new JList<String>();    
        JScrollPane setsListPanel = new JScrollPane(setsList);  

        GridBagConstraints setsGBC = Controller.getGBC(0, 1, 1, 0.5);
        setsGBC.fill = GridBagConstraints.BOTH;
        add(setsListPanel, setsGBC);


        // Tags label
        JLabel tagsLabel = new JLabel("Tags:");

        GridBagConstraints tagsLabelGBC = Controller.getGBC(1, 0);
        add(tagsLabel, tagsLabelGBC);
        
        // tags input
        tagsList = new JList<String>();      
        JScrollPane tagsListPanel = new JScrollPane(tagsList);  

        GridBagConstraints tagsGBC = Controller.getGBC(1, 1, 1, 0.5);
        tagsGBC.fill = GridBagConstraints.BOTH;
        add(tagsListPanel, tagsGBC);

        

        // keyword label
        JLabel keywordLabel = new JLabel("Keyword:");

        GridBagConstraints keywordLabelGBC = Controller.getGBC(0, 2);
        add(keywordLabel, keywordLabelGBC);
        
        // keyword input
        keywordInput = new JTextField();        

        GridBagConstraints keywordInputGBC = Controller.getGBC(0, 3, 1,0);
        keywordInputGBC.gridwidth = 2;
        add(keywordInput, keywordInputGBC);


        // apply button
        JButton applyButton = new JButton("Apply Filters");        

        GridBagConstraints applyButtonGBC = Controller.getGBC(0, 4, 1,0);
        add(applyButton, applyButtonGBC);

        // apply button click action listener
        applyButton.addActionListener(applyClickedListener);


        // clear button
        JButton clearButton = new JButton("Clear Filters");        

        GridBagConstraints clearButtonGBC = Controller.getGBC(1, 4, 1,0);
        add(clearButton, clearButtonGBC);

        // clear button click action listener
        // NOTE - Clearing filters may show different results than Applying Filter with none selected (since applying no filters will exclude cards that are not tagged - COULD ADD A 'not tagged filter')
        clearButton.addActionListener(clearClickedListener);

        // cards list
        cardsList = new JList<String>();
        JScrollPane cardsPane = new JScrollPane(cardsList);

        // cards gbc
        GridBagConstraints tableGBC = Controller.getGBC(0, 5, 1, 1);
        tableGBC.fill = GridBagConstraints.BOTH;
        tableGBC.gridwidth = 2;
        add(cardsPane, tableGBC);

        // card item click action listener
        cardsList.addMouseListener(cardClickedListener);
        cardsList.addKeyListener(deleteKeyListener);


        // New card button
        JButton newButton = new JButton("Add New Card");        

        GridBagConstraints newButtonGBC = Controller.getGBC(0, 6, 1,0);
        newButtonGBC.gridwidth = 2;
        add(newButton, newButtonGBC);

        // add card button click action listener
        newButton.addActionListener(newClickedListener);


        // order radio buttons
        JPanel orderPanel = new JPanel();

        GridBagConstraints orderPanelGBC = Controller.getGBC(0, 7, 1, 0);
        orderPanelGBC.gridwidth = 2;
        add(orderPanel, orderPanelGBC);

        orderPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        ButtonGroup orderBG = new ButtonGroup();

        byKnowledgeRadio = new JRadioButton("Order by Knowledge");
        orderBG.add(byKnowledgeRadio);
        orderPanel.add(byKnowledgeRadio);

        JRadioButton randomlyRadio = new JRadioButton("Order Randomly");
        orderBG.add(randomlyRadio);
        orderPanel.add(randomlyRadio);


        // study button
        JButton studyButton = new JButton("Study These Cards");        

        GridBagConstraints studyButtonGBC = Controller.getGBC(0, 8, 1,0);
        studyButtonGBC.gridwidth = 2;
        add(studyButton, studyButtonGBC);

        studyButton.addActionListener(studyAllClickedListener);

        resetAndReloadCards();

        // set layout
        setSize(400, 300);
        setVisible(true);

    }

    // PAGE/CARD UPDATE FUNCS

    // reloads all current card titles and their sets
    public void reloadCards(){
        String[] tags = tagsList.getSelectedValuesList().toArray(new String[tagsList.getSelectedIndices().length]);
        String[] sets = setsList.getSelectedValuesList().toArray(new String[setsList.getSelectedIndices().length]);
        
        controller.loadCardHeaders(tags,sets, keywordInput.getText(), byKnowledgeRadio.isSelected());

        cardsList.setListData(controller.getCurrentCardTitlesWithSets());
    }

    // reset filters and reload cards
    public void resetAndReloadCards(){
        tagsList.setListData(controller.getTags());
        setsList.setListData(controller.getSets());
        byKnowledgeRadio.setSelected(true);

        keywordInput.setText("");
        reloadCards();
    }

    // EVENT LISTENERS

    // New button click listener
    public ActionListener newClickedListener = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            controller.addNewCard();
        }
    };

    // card double-click listener
    MouseAdapter cardClickedListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent e){
            if (e.getClickCount() == 2 ){
                controller.editCardAtIndex(cardsList.getSelectedIndex());
            }
        }
    };

    // Delete key pressed - delete selected cards
    KeyAdapter deleteKeyListener = new KeyAdapter() {
        public void keyPressed(KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_DELETE){
                controller.deleteCardsAtIndices(cardsList.getSelectedIndices());
                resetAndReloadCards();
            }
        };
    };

    // Apply button click listener - get selected filters and display filtered cards
    public ActionListener applyClickedListener = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            reloadCards();
        }
    };

    // Clear button click listener - reset page
    public ActionListener clearClickedListener = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            resetAndReloadCards();
        }
    };

    // Study button click listener - reset page
    public ActionListener studyAllClickedListener = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            
            reloadCards();
            controller.switchToLearn();
            
        }
    };

}
