public class Card {

    private int id;
    private String title;
    private String front;
    private String back;
    private String set;
    private String[] tags;

    private float rating;

    // constructors
    Card(int ID, String t, String s){
        this.id= ID;
        title = t;
        set = s;
        tags = new String[0]; 
        rating = 0;
    }

    Card(int id){
        this.id = id;
    }

    // setters
    public void updateRating(int val){
        if (rating == -1){
            rating = val;
        }
        else{
            rating = (rating + val)/2;
        }
    }
        
    public void setProperties(String t, String s, String f, String b, float r){
        title = t;
        front = f;
        back = b;
        set = s;
        rating = r;
    }

    public void setTags(String[] tags){
        this.tags=  tags;
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
    public float getRating(){
        return rating;
    }

    public String toString(){
        return String.format("T: %s \nS: %s \nF: %s\nB:%s \nR:%f", title, set, front, back, rating);
    }
    
}
