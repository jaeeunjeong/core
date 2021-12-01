package hello.core.singleton;

public class StatefulService {
    private int price;
    private String name;

    public void order(String userID, int price){
        this.price = price;
        this.name = userID;
    }

    public int getPrice(){
        return price;
    }

    public String getName() {
        return name;
    }
}
