import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Controller {

    private int currentCardIndex;
    private ArrayList<Card> cards;

    private HomePage homePage;
    private EditCardPage editPage;
    private LearnPage learnPage;

    private CardLayout cardLayout;
    private JPanel root;

    public Controller(CardLayout c, JPanel r){
        cardLayout = c;
        root = r;

        currentCardIndex = 0;
    }

    public void setPages(HomePage home, EditCardPage edit, LearnPage learn){
        homePage = home;
        editPage = edit;
        learnPage = learn;
    }

    public void switchToHome(){
        currentCardIndex = 0;
        homePage.resetAndReloadCards();
        cardLayout.show( root,"home");
    }

    public void switchToEdit(){
        currentCardIndex = 0;
        editPage.loadCard();
        cardLayout.show( root,"edit");
    }

    public void switchToLearn(){
        currentCardIndex = 0;
        learnPage.loadCurrentCard();
        cardLayout.show( root,"learn");
    }

    public int getCurrentIndex(){
        return currentCardIndex;
    }

    public int getCurrentCardsLength(){
        return cards.size();
    }

    // switch current card index to next card. 
    public void nextCardIndex(){
        currentCardIndex += 1;
    }

    public Card getCurrentCard(){
        return cards.get(currentCardIndex);
    }
    

    public void loadCurrentCardProperties(){
        cards.set(currentCardIndex, DB.getCardProperties(getCurrentCard().getID()));
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
            titles[i] = String.format("%s - %s",cards.get(i).getSet(), cards.get(i).getTitle());
        }
        return titles;
    }
    

    // home page - set filtered current cards for displaying (titles,sets,ids only) - ordered by rating or random
    public void loadCardHeaders(String[] tags, String[] sets, String keyword, boolean orderByKnowledge){
        //order by rating score or by RAND()
        cards = DB.getSomeCards(tags, sets, keyword, orderByKnowledge);
        
    }

    // delete cards at selected indices in Cards list
    public void deleteCardsAtIndices(int[] indices){
        
        // get card IDs
        int[] ids = new int[indices.length];
        
        for (int j = 0; j< ids.length; j ++){
            ids[j] = cards.get(indices[j]).getID();
        }

        // delete cards
        DB.deleteCards(ids);        
    }


    // insert new card and switch to edit page
    public void addNewCard(){

        int id = DB.insertCard();
        Card c = DB.getCard(id);

        cards = new ArrayList<Card>();
        cards.add(c);

        switchToEdit();
        
    }


    // load card at selected index and switch to edit page
    public void editCardAtIndex(int index){

        Card c = DB.getCard(cards.get(index).getID());

        cards = new ArrayList<Card>();
        cards.add(c);
        switchToEdit();
        
    }


    // save edits for current card
    public void updateCard(String title, String set, ArrayList<String> addedTags, ArrayList<String> removedTags, String front, String back){
        DB.updateCard(getCurrentCard().getID(), title, set, addedTags, removedTags, front, back);
        
    }

    // update card rating
    public void updateCurrentCardRating(int rating){
        Card currentCard = getCurrentCard();
        float newRating = (rating + currentCard.getRating())/2;
        DB.setCardRating(currentCard.getID(), newRating);
    }

    
   

    // UI HELPER METHODS: 


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
