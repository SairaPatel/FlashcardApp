
import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

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
    

    public static String arrayToWildcardString(int length){
        return arrayToWildcardString(length, 0);
    }
    /* generate a string containing wildcards for each column in list of given length
     *  e.g. len 3 -> '(?, ?, ?)'
     * 
     * where a column > 1 is given: each row is a list of wildcards
     * e.g. len 3, cols 2 -> '((?, ?), (?, ?), (?, ?))'
     * used for inserting multiple rows (of multiple column values) into a table
     */
    public static String arrayToWildcardString(int length, int columns){
        // generate a string containing wildcards for each item in list of given length
        // e.g. 3-> '(?, ?, ?)'

        // for separate rows, each wildcard is placed within brackets: (?) instead of ?


        if (length == 0){
            return "()";
        }
        String out = "(";
        for (int i = 0; i < length -1; i ++){
            if (columns == 0){
                out += "?, ";
            }
            else{
                out += arrayToWildcardString(columns) + ", ";
            }
            
        }

        if (columns == 0){
            out += "?)";
        }
        else{
            out += arrayToWildcardString(columns) + ")";
        }

        return out;
    }

    public static ArrayList<Card> getAllCards(){
        
        // get all cards' id, title and set

        String qry = "SELECT CardID, Title, CardSet FROM Cards ;";
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
        String qry = "SELECT DISTINCT CardSet FROM Cards;";
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

            
            rs.close();
            st.close();
            conn.close();
        }
        catch (SQLException e){
            
            System.out.println(e.getMessage());
            sets.add("Could not load sets");
        }
        return sets.toArray(new String[sets.size()]);
    }

    public static String[] getAllTags(){
        String qry = "SELECT Name FROM Tags;";
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

            rs.close();
            st.close();
            conn.close();
        }
        catch (SQLException e){
            
            System.out.println(e.getMessage());
            tags.add("Could not load tags");
        }
        return tags.toArray(new String[tags.size()]);
    }

    public static ArrayList<Card> getSomeCards(String[] tags, String[] sets, String keyword, boolean orderRandomly){
        
        // build condition
        
        String tagCondition = "Tags.Name IN " + arrayToWildcardString(tags.length);
        if (tags.length == 0){
            tagCondition =  "TRUE";
        }
        

        String setCondition = "CardSet IN " + arrayToWildcardString(sets.length);
        if (sets.length == 0){
            setCondition = "TRUE";
        }
       
        String keywordCondition = "Cards.Title LIKE ? OR Cards.Front LIKE ? OR Cards.Back LIKE ?";
        
        String order = "ORDER BY Rating ASC";
        if (orderRandomly){
            order = "ORDER BY rand()";
        }

        // build query
        String qry = String.format("""
                SELECT CardID, Title, CardSet FROM Cards WHERE CardID IN
                        (

                        SELECT DISTINCT Cards.CardID

                            FROM CardTags
                            INNER JOIN Cards ON Cards.CardID = CardTags.CardID
                            INNER JOIN Tags ON Tags.TagID = CardTags.TagID
                            
                            WHERE (%s) AND (%s) AND (%s)

                            
                        )
                        %s
                        ;
                """, tagCondition, setCondition, keywordCondition, order);

        System.out.println(qry);
        ArrayList<Card> cards = new ArrayList<Card>();

        // get all cards with given tags, sets and keywords
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement st = conn.prepareStatement(qry);
            
        )
        {
            int paramCount = 1;

            for (String t: tags){
                st.setString(paramCount, t);
                paramCount ++;
            }
            for (String s: sets){
                st.setString(paramCount, s);
                paramCount ++;
            }
            for (int i = paramCount; i <paramCount + 3; i++){
                st.setString(i, "%" + keyword + "%");
            }

            ResultSet rs = st.executeQuery();
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
        catch (SQLException e){
            
            System.out.println(e.getMessage());
            cards.add(new Card(0, "Could not load Cards", ""));
        }

        return cards;
        
    }

    public static Card getCardProperties(int id){

        // get card content and ratings 
        String qry = "SELECT Title, CardSet, Front, Back, Rating FROM Cards WHERE CardID = ?;";
        Card c = new Card(id, "", "", new String[0], "", "", 0);;

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement st = conn.prepareStatement(qry);
            
        )
        {
            st.setInt(1,id);

            ResultSet rs = st.executeQuery();
            while (rs.next()){
                String title = ( rs.getString("Title"));
                String set = rs.getString("CardSet");
                String front = rs.getString("Front");
                String back = rs.getString("Back");
                Float rating = rs.getFloat("Rating");
                
                
                c.setProperties(title, set, front, back, rating);
            }

            rs.close();
            st.close();
            conn.close();
        }
        catch(SQLException e){
            
            System.out.println(e.getMessage());
            c.setProperties("Could not load card", "", "", "",0);
        }

        return c;

    }

    public static Card getCard(int id){

        // get properties
        Card c = getCardProperties(id);

        // get tags
        String qry = """
                SELECT Tags.Name

                    FROM CardTags
                    INNER JOIN Cards ON Cards.CardID = CardTags.CardID
                    INNER JOIN Tags ON Tags.TagID = CardTags.TagID

                    WHERE Cards.CardID = ?
                    ;
                """;
        ArrayList<String> tags = new ArrayList<String>();
        try(
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement st = conn.prepareStatement(qry);
        )
        {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                tags.add( rs.getString("Name"));
            }
            rs.close();
            st.close();
            conn.close();
        }
        catch( SQLException e){
            tags.add("Could not load tags");
            System.out.println(e.getMessage());
        }
        
        c.setTags(tags.toArray(new String[tags.size()]));
        
        return c;

    }

    public static int insertCard(){
        String qry = "INSERT INTO Cards (Title, CardSet, Front, Back, Rating) VALUES ('Untitled', 'My Set', '','', 0 );";
        String qry2 = "SELECT LAST_INSERT_ID();";

        int id = -1;

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();

        )
        {
            // insert card
            st.executeUpdate(qry);

            // get id
            ResultSet rs = st.executeQuery(qry2);
            while (rs.next()){
                id = rs.getInt(1);
            }

            st.close();
            conn.close();
            
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            
        }
        System.out.println(id);
        return id;
    }

    public static void deleteCards(int[] ids){
        // delete all cards with listed IDs
        String qry = String.format("DELETE FROM Cards WHERE CardID IN %s", arrayToWildcardString(ids.length));
        
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement st = conn.prepareStatement(qry);
        )
        {
            // bind ids to query
            for (int i = 0; i < ids.length; i++){
                st.setInt(i+1, ids[i]);
            }

            st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public static void updateCard(int id, String title, String set, ArrayList<String> addedTags, ArrayList<String> removedtags, String front, String back){
        // UPDATE CARD
        String qry = "UPDATE Cards SET Title = ?, CardSet = ?, Front = ?, Back = ? WHERE CardID = ?";

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement st = conn.prepareStatement(qry);
        )
        {
            st.setString(1, title);
            st.setString(2, set);
            st.setString(3, front);
            st.setString(4, back);
            st.setInt(5,id);

            st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }


        // INSERT ADDED TAGS (to Tags and CardTags tables)
        


        // DELETE REMOVED TAGS (from Tags and CardTags tables)
        

    }

    public static void setCardRating(int id, float rating){
       
        // set card rating to equal rating.
        String qry = "UPDATE Cards SET rating = ? WHERE CardID = ?";

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement st = conn.prepareStatement(qry);
        )
        {
            st.setDouble(1, rating);
            st.setInt(2, id);

            st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


}
