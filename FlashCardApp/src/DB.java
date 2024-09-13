
import java.sql.*;
import java.util.ArrayList;

public class DB {

    static final String DB_URL =  "jdbc:mysql://localhost:3306/flashcarddb";
    static final String USER = "root";
    static final String PASS = "root";

    

    // private static ArrayList<Card> getCards(){


    //     ArrayList<Card> cards = new ArrayList<Card>();
    //     cards.add(new Card(0, "CSS", "WebDev", "Write some stule tags", "<style></style>",0.3));
    //     cards.add(new Card(2, "HTMl","WebDev", "write some basic semantic tags", "<p><p><h1></h1>",5.5));
    //     cards.add(new Card(3, "Security","WebDev", "what is a security threat?", "SQL injection",2));
    //     cards.add(new Card(5, "JQuery", "WebDev","What is jquery for?", "easier to read/write than pure javascript",1));

    //     return cards;

    // }
    public static String arrayToString(String[] words){
        
        String out = "(";
        for (int i = 0; i < words.length -1; i ++){
            out += "'" + words[i] +  "', ";
        }
        out += "'" + words[words.length -1] + "')";
        return out;
    }

    public static ArrayList<Card> getAllCards(){
        
        String qry = "SELECT CardID, Title, CardSet FROM Cards ";
        ArrayList<Card> cards = new ArrayList<Card>();
        
        try(
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(qry);
        )
        {
            while (rs.next()){
                int id = rs.getInt("CardID");
                String title = rs.getString("Title");
                String set = rs.getString("CardSet");

                cards.add(new Card(id, title, set));
            }

            rs.close();
            st.close();
            conn.close();
        }
        catch ( SQLException e){
            System.out.println(e.getMessage());
            cards.add(new Card(0, "Could not load cards", ""));
        }

        // select card titles, sets, (tagsREMOVE) and ids ONLYYYYYYYYYYYYYYYYYYYYYYYYYYY
        return cards;
    }

    public static String[] getAllSets(){
        
        String qry = "SELECT DISTINCT CardSet FROM Cards";
        ArrayList<String> sets = new ArrayList<String>();

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(qry);
        )
        {
            while (rs.next()){
                sets.add( rs.getString("CardSet"));
            }
        }
        catch (SQLException e){
            sets.add("Could not load sets");
        }
        return sets.toArray(new String[sets.size()]);
    }

    public static String[] getAllTags(){
        String qry = "SELECT name FROM Tags";
        ArrayList<String> tags= new ArrayList<String>();

        try(
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(qry);
        )
        {
            while (rs.next()){
                tags.add(rs.getString("Name"));
            }
        }
        catch (SQLException e){
            tags.add("Could not load tags");
        }
        return tags.toArray(new String[tags.size()]);
    }

    public static ArrayList<Card> getSomeCards(String[] tags, String[] sets, String keyword){
        
        // build condition
        
        String tagCondition = "";
        if (tags.length > 0){
            tagCondition = String.format("Tags.Name IN %s ", arrayToString(tags));
        }
        String setCondition = "";
        if (sets.length > 0){
            setCondition = String.format("CardSet IN %s ", arrayToString(sets));
        }
        String conditionConnector = "";
        if (tagCondition != "" && setCondition != ""){
            conditionConnector = " AND ";
        }

        String condition = String.format("%s%s%s", tagCondition, conditionConnector, setCondition);


        String keywordCondition = "";
        System.out.println(keyword.length());
        if (keyword.length() > 0){
            keywordCondition = String.format("Cards.Title LIKE '%%%s%%'", keyword);
        }

        
        String conditionConnector2 = "";
        if (condition != "" && keywordCondition != ""){
            conditionConnector2 = " AND ";
        }

        condition = String.format("%s%s%s", condition, conditionConnector2, keywordCondition);

        if (condition != ""){
            condition = "WHERE " + condition;
        }

        

        String qry = String.format("""
                SELECT * FROM Cards WHERE CardID IN
                        (

                        SELECT DISTINCT Cards.CardID

                            FROM CardTags
                            INNER JOIN Cards ON Cards.CardID = CardTags.CardID
                            INNER JOIN Tags ON Tags.TagID = CardTags.TagID
                            
                            %s
                        )
                        ;
                """, condition);
        System.out.println(qry);
        ArrayList<Card> cards = new ArrayList<Card>();

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(qry);
        )
        {
            while (rs.next()){

                int id = rs.getInt("CardID");
                String title = rs.getString("Title");
                String set = rs.getString("CardSet");

                cards.add(new Card(id, title, set));
            }
        }
        catch (SQLException e){
            cards.add(new Card(0, "Could not load Cards", ""));
        }

        return cards;
        
    }


    public static Card getCard(int id){
        return new Card(id, "HTML and CSS", "Web Dev", "What are the main html tags?", "p, h1-h6, br, div, span, a", 0);

    }

    public static void updateCard(String title, String set, String[] tags, String front, String back){
        // DB UPDATE CARD
        // DB UPDATE TAGS
    }

    public static void updateCardRating(int index, double rating){
        // set card rating to equal rating.
        System.out.println(rating);
    }


}
