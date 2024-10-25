
import java.sql.*;
import java.util.ArrayList;


public class DB {

    static final String DB_URL =  "jdbc:mysql://localhost:3306/flashcarddb";
    static final String USER = "root";
    static final String PASS = "root";

    /*
     * Generates a string  containing a tuple of n question marks/wildcards
     * i.e. 2 -> '(?, ?)'
     * @param length the number of wildcards to be in the tuple 
     */
    public static String generateWildcardStr(int length){
        if (length == 0){
            return "()";
        }

        String out = "(";
        for (int i = 0; i < length -1; i++){
            out += "?, ";
        }
        out += "?)";
        return out;
    }

    /*
     * Generates a string  containing a list of comma-separated tuples - each of n question marks/wildcards
     * i.e. 2, 3 -> '(?, ?), (?, ?), (?, ?)'
     * @param length - the number of wildcards to be in each tuple 
     * @param count - the number of tuples to be in the string
     */
    public static String generateWildcardStr(int length, int count){
        if (count == 0){
            return "";
        }
        
        String out = "";
        for (int i = 0; i < count -1; i ++){
            out += generateWildcardStr(length) + ", ";
        }
        out += generateWildcardStr(length);
        return out;
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
        String qry = "SELECT DISTINCT Tag FROM CardTags;";
        ArrayList<String> tags= new ArrayList<String>();

        try(
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(qry);
        )
        {
            while (rs.next()){
                tags.add(rs.getString("Tag"));
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

    public static ArrayList<Card> getSomeCards(String[] tags, String[] sets, String keyword, boolean orderByKnowledge){
        
        // build condition
        
        String tagCondition = "CardTags.Tag IN " + generateWildcardStr(tags.length);
        if (tags.length == 0){
            tagCondition =  "TRUE";
        }

        String setCondition = "CardSet IN " + generateWildcardStr(sets.length);
        if (sets.length == 0){
            setCondition = "TRUE";
        }
       
        String keywordCondition = "Cards.Title LIKE ? OR Cards.Front LIKE ? OR Cards.Back LIKE ?";
        
        String order = "ORDER BY rand()";
        if (orderByKnowledge){
            order = "ORDER BY Rating ASC";
        }

        // build query
        String qry = String.format("""
                    SELECT DISTINCT Cards.CardID, Cards.Title, Cards.CardSet, Cards.Rating
    
                                FROM CardTags
                                RIGHT OUTER JOIN Cards ON Cards.CardID = CardTags.CardID
                                
                                WHERE (%s) AND (%s) AND (%s)
    
                                
                            
                            %s
                            ;
                    """, tagCondition, setCondition, keywordCondition, order);

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
        Card c = new Card(id);;

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
        String qry = "SELECT Tag FROM CardTags WHERE CardID = ? ;";

        ArrayList<String> tags = new ArrayList<String>();
        try(
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement st = conn.prepareStatement(qry);
        )
        {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                tags.add( rs.getString("Tag"));
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

    // add 'default' card with empty/default values
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
        return id;
    }


    public static void deleteCards(int[] ids){
        // delete all cards with listed IDs
        String qry = String.format("DELETE FROM Cards WHERE CardID IN %s", generateWildcardStr(ids.length));
        
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


    // update card editable properties and tags
    public static void updateCard(int id, String title, String set, ArrayList<String> addedTags, ArrayList<String> removedtags, String front, String back){
        
        // UPDATE CARD PROPERTIES in Cards table
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

        


        // INSERT ADDED TAGS
        if (addedTags.size() > 0){
            String addTagsQry = String.format("INSERT INTO CardTags (CardId, Tag) VALUES %s;", generateWildcardStr( 2, addedTags.size()));
            try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement st = conn.prepareStatement(addTagsQry);
            )
            {
                int paramCount = 1;

                // bind id's and tag names
                for (String t: addedTags){
                    st.setInt(paramCount, id);
                    paramCount ++;
                    st.setString(paramCount, t);
                    paramCount ++;
                }

                st.executeUpdate();

                st.close();
                conn.close();
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
        
        
        // DELETE REMOVED TAGS
        if (removedtags.size() > 0){
            String deleteTagsQry ="DELETE FROM CardTags WHERE CardID = ? AND Tag = ?";
            int paramCount = 1;
            for (String t: removedtags){
    
                try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement st = conn.prepareStatement(deleteTagsQry);
                )
                {
                    
                    st.setInt(paramCount, id);
                    paramCount ++;
                    st.setString(paramCount, t);
                    paramCount ++;
    
                    st.executeUpdate();
                    st.close();
                    conn.close();
                }
                catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
    
            }
        }
       
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
