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
        currentCardIndex = 0;
        homePage.resetPage();
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

    public void loadCardHeaders(){
        // select all card titles, sets, tags and ids
        cards = DB.getAllCards();
    }


    public void setPages(HomePage home, EditCardPage edit, LearnPage learn){
        homePage = home;
        editPage = edit;
        learnPage = learn;
    }

    public void loadCurrentCardProperties(){
        cards.set(currentCardIndex, DB.getCardProperties(getCurrentCard().getID()));
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
    
    // home page - set filtered current cards for displaying (titles,sets,ids only)
    public void loadFilteredCardHeaders(String[] tags, String[] sets, String keyword){
        cards = DB.getSomeCards(tags, sets, keyword, false);
    }

    // home page - set filtered current cards for learning(all card properties). ordered by rating or random
    public void loadFilteredCards(String[] tags, String[] sets, String keyword, boolean orderRandomly){
        //order by rating score or by RAND()
        cards = DB.getSomeCards(tags, sets, keyword, orderRandomly);
        
    }

    // insert new card and switch to edit page
    public void addNewCard(){
        int id = DB.insertCard();

        Card c = DB.getCard(id);

        cards = new ArrayList<Card>();
        cards.add(c);
        System.out.println(c.toString());
        switchToEdit();
        
    }

    public void deleteCardsAtIndices(int[] indices){
        
        // get card IDs
        int[] ids = new int[indices.length];

        for (int j = 0; j< ids.length; j ++){
            ids[j] = cards.get(indices[j]).getID();
        }

        // delete cards
        DB.deleteCards(ids);
        
        
    }

    
    // home page click card
    public void editCardAtIndex(int index){
        

        Card c = DB.getCard(cards.get(index).getID());

        cards = new ArrayList<Card>();
        cards.add(c);
        switchToEdit();
        
    }


    // save current edit card
    public void updateCard(String title, String set, ArrayList<String> addedTags, ArrayList<String> removedTags, String front, String back){
        DB.updateCard(getCurrentCard().getID(), title, set, addedTags, removedTags, front, back);
        
    }

    

    //update card rating
    public void updateCurrentCardRating(int rating){
        Card currentCard = getCurrentCard();
        // FIX THIS FORMULA _--------------------------------------------------------------
        float newRating = (rating + currentCard.getRating())/2;
        DB.setCardRating(currentCard.getID(), newRating);
    }

    // switch current card to next card. 
    public void nextCardIndex(){
        
        currentCardIndex += 1;

    }

    public int getCurrentIndex(){
        return currentCardIndex;
    }

    public int getCurrentCardsLength(){
        return cards.size();
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
