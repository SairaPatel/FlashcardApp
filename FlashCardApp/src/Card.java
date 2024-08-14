public class Card {

    private int id;
    private String title;
    private String front;
    private String back;

    private double rating;

    Card(int ID, String t, String f, String b, double r){
        updateCardContent(t, f, b);
        rating = r;
    }

    Card(int ID){
        new Card(ID, "", "", "", -1);
    }
    

    public void updateRating(int val){
        if (rating == -1){
            rating = val;
        }
        else{
            rating = (rating + val)/2;
        }
    }
    
    

    public void updateCardContent(String t, String f, String b){
        title = t;
        front = f;
        back = b;
    }


    // getters
    public int getID(){
        return id;
    }
    public String getTitle(){
        return title;
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
