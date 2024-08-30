import java.util.ArrayList;

public class DB {


    private static ArrayList<Card> getCards(){

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(0, "CSS", "WebDev", "Write some stule tags", "<style></style>",0.3));
        cards.add(new Card(2, "HTMl","WebDev", "write some basic semantic tags", "<p><p><h1></h1>",5.5));
        cards.add(new Card(3, "Security","WebDev", "what is a security threat?", "SQL injection",2));
        cards.add(new Card(5, "JQuery", "WebDev","What is jquery for?", "easier to read/write than pure javascript",1));

        return cards;

    }

    public static ArrayList<Card> getAllCards(){
        // select card titles, sets, tags and ids ONLYYYYYYYYYYYYYYYYYYYYYYYYYYY
        return getCards();
    }

    public static String[] getAllSets(){
        String[] s= {"Maths", "WebDev", "Func prog", "Architecture"};
        return s;
    }

    public static String[] getAllTags(){
        String[] t= {"Memory", "Hardware", "Software", "Security", "Logic",};
        return t;
    }

    public static ArrayList<Card> getSomeCards(String[] tags, String[] sets, String keyword){
        ArrayList<Card> c =  getCards();
        c.remove(0);
        c.remove(0);
        return c;
    }


    public static Card getCard(int id){
        return new Card(id, "HTML and CSS", "Web Dev", "What are the main html tags?", "p, h1-h6, br, div, span, a", 0);

    }

    public static void updateCard(String title, String set, String[] tags, String front, String back){
        // DB UPDATE CARD
        // DB UPDATE TAGS
    }
}
