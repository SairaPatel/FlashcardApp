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
    private JPanel root;

    public UIController(CardLayout c, JPanel r){
        cardLayout = c;
        root = r;

        currentCardIndex = 0;

        loadCardHeaders();
    }
    public void switchToHome(){
        loadCardHeaders();
        cardLayout.show( root,"home");
    }

    public void switchToEdit(){
        cardLayout.show( root,"edit");
    }

    public void switchToLearn(){
        cardLayout.show( root,"learn");
    }

    public void loadCardHeaders(){
        // select card titles, sets, tags and ids
        cards = DB.getAllCards();
    }


    public void setPages(HomePage home, EditCardPage edit, LearnPage learn){
        homePage = home;
        editPage = edit;
        learnPage = learn;
    }

    public Card getCurrentCard(){
        return cards.get(currentCardIndex);
    }


    // home page  - get sets
    public String[] getSets(){
        return DB.getAllSets();
    }
    // home page - get tags
    public String[] getTags(){
        return DB.getAllTags();
    }

    // home page - get card titles with their sets
    public String[] getCurrentCardTitlesWithSets(){
        String[] titles = new String[cards.size()];

        for (int i = 0; i < cards.size(); i++){
            titles[i] = String.format("%s: %s",cards.get(i).getTitle() , cards.get(i).getSet());
        }
        return titles;
    }
    
    // home page - filter current cards
    public void filterCurrentCards(String[] tags, String[] sets, String keyword){
        cards = DB.getSomeCards(tags, sets, keyword);
    }
    
    // home page click card
    public void editCardAtIndex(int index){
        Card c = DB.getCard(cards.get(index).getID());

        cards = new ArrayList<Card>();
        cards.add(c);
        currentCardIndex = 0;

        switchToEdit();
        editPage.loadCard();
        // EditPage.loadCurrentCard()
        
    }


    // save current edit card
    public void updateCard(String title, String set, String[] tags, String front, String back){
        DB.updateCard(title, set, tags, front, back);
        System.out.println(title);
        System.out.println(set);
        System.out.println(String.format("%s %d", tags.toString(), tags.length));
        System.out.println(front);
        System.out.println(back);
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
