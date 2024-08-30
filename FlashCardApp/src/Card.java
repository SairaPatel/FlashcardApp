public class Card {

    private int id;
    private String title;
    private String front;
    private String back;
    private String set;
    private String[] tags;

    private double rating;

    Card(int ID, String t, String s, String[] tags, String f, String b, double r){
        updateCardContent(t, s, f, b);
        rating = r;
        this.tags = tags;
    }

    Card(int ID, String t, String s, String f, String b, double r){
        updateCardContent(t, s, f, b);
        tags = new String[0]; 
        rating = r;
    }

    Card(int ID){
        new Card(ID, "", "", "", "", -1);
    }
    

    public void updateRating(int val){
        if (rating == -1){
            rating = val;
        }
        else{
            rating = (rating + val)/2;
        }
    }
    
    

    public void updateCardContent(String t, String s, String f, String b){
        title = t;
        front = f;
        back = b;
        set = s;
    }


    // getters
    public int getID(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getSet(){
        return set;
    }
    public String[] getTags(){
        return tags;
    }

    public String getFront(){
        return front;
    }
    public String getBack(){
        return back;
    }
    public double getRating(){
        return rating;
    }
    
}
