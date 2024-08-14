import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UIController {

    private int currentCardIndex;
    private ArrayList<Card> cards;

    private HomePage homePage;
    private EditCardPage editPage;
    private LearnPage learnPage;

    private CardLayout cardLayout;

    public UIController(){
        currentCardIndex = 0;

        cards = new ArrayList<Card>();
        cards.add(new Card(0, "CSS", "Write some stule tags", "<style></style>",0.3));
        cards.add(new Card(2, "HTMl", "write some basic semantic tags", "<p><p><h1></h1>",5.5));
        cards.add(new Card(3, "Security", "what is a security threat?", "SQL injection",2));
        cards.add(new Card(5, "JQuery", "What is jquery for?", "easier to read/write than pure javascript",1));

    }

    public Card getCurrentCard(){
        return cards.get(currentCardIndex);
    }


    
    
    
    // home page click card
    public void editCardAtIndex(){
        //cardID = this.cardList[index]
        // c = DB.select(cardID)
        // cardList = {c}
        //currentCardIndex = 0;
        // EditPage.loadCurrentCard()
        // go to edit page
        
    }

    public void learnCards(String[] filters){
        // this.cardList = DB.selectCards
        // randomise cardList if needed
        // this.cardIndex = 0
        // learnPage.loadCurrentCards()
        // go to learn page
    }

    // Edit Page save button
    public void updateCurrentCard(){
        // c = EditCardPage.getCard()
        // DB.UpdateCard(c)
    }

   
    /**
     * Returns a GridBagConstraints object with: ipadx = 2 ipady = 2, weightx and weighty = 0.
     * And gridx, gridy, values specified by params
     *  
     * @param gridx the column of the component
     * @param gridy the row of the component
     * @return the GridBagConstraints object with certain values set
     */
    static public GridBagConstraints getGBC(int gridx, int gridy){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.ipadx = 3;
        gbc.ipady = 3;
        gbc.insets = new Insets(3,3,3,3);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    /**
     * Returns a GridBagConstraints object with: ipadx = 2 ipady = 2. 
     * And gridx, gridy, weightx and weighty values specified by params
     *  
     * @param gridx the column of the component
     * @param gridy the row of the component
     * @param weightx the weight of the component's column
     * @param weighty the weight of the component's row
     * @return the GridBagConstraints object with certain values set
     */
    static public GridBagConstraints getGBC(int gridx, int gridy, double weightx, double weighty){
        GridBagConstraints gbc = getGBC(gridx, gridy);
        gbc.weightx = weightx;
        gbc.weighty = weighty;

        return gbc;
    }

    static public Font getFont(int fontsize){
        return new Font("sans serif", Font.BOLD, fontsize);
    }
}
