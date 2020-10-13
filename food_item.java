package Assignment2;

import java.util.HashMap;

public class food_item {
        private final int id;
        private String name;
        private int price;
        private int quantity;
        private int offer;
        private String food_type;
        static int id_help=0;
        private final Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    food_item(food_item i, int q){
        this.id = i.id;
        this.name = i.name;
        this.quantity = q;
        this.food_type= i.food_type;
        this.price = i.price;
        this.offer = i.offer;
        this.restaurant = i.restaurant;
    }
        food_item(String name, int price, int quantity, int disc, String food_type,Restaurant r) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.offer = disc;
            this.food_type = food_type;
            this.id = id_help+1;
            id_help++;
            this.restaurant =r;
        }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getFood_type() {
        return food_type;
    }
    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public void setQuantity(int quantity) {
        this.quantity =this.quantity- quantity;
    }

    public int getDisc() {
        return offer;
    }

    public void setDisc(int disc) {
        this.offer = disc;
    }

    public int getPrice() {
        return price;
    }
    public float finalp(int q){
            float p =price - (price*((float)offer/100));
            return p*q;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void print(){
      System.out.println(this.id + " "+this.restaurant.getName()+" " + this.name + " Rs."+ this.price+" "+this.quantity + "  "+ this.offer+"% off "+this.food_type);
    }
    public void printer2(){
        System.out.println("Item id:"+ this.getId()+ " Item name:"+ this.getName()+" Quantity:"+ this.quantity+ " for Rs."+ this.price+ " from "+ this.restaurant.getName() + " with "+ this.getOffer()+ "% off");
    }
    public void printer(int dd){
        System.out.println("Item id:"+ this.getId()+ " Item name:"+ this.getName()+" Quantity:"+ this.quantity+ " for Rs."+ this.finalp(this.quantity) + "(after item offer) from "+ this.getRestaurant().getName() + " and Delivery charge: "+ dd);
    }

    public void food_editor(){
        String[] temp = {"name","price", "quantity", "category", "offer"};
        System.out.println("Choose an attribute to edit:");
        System.out.println("1 Name");
        System.out.println("2 Price");
        System.out.println("3 Quantity");
        System.out.println("4 Category");
        System.out.println("5 Offer");
        int next = Executor.in.nextInt();
        System.out.println("Enter the new "+ temp[next-1]);
        if (next==1){
            String n = Executor.in.nextLine();
            setName(n);
        }
        if (next==2){
            int n = Executor.in.nextInt();
            Executor.in.nextLine();
            setPrice(n);
        }
        if (next==3){
            int n = Executor.in.nextInt();
            Executor.in.nextLine();
            setQuantity(n);
        }
        if (next==4){
            String n = Executor.in.next();
            Executor.in.nextLine();
            setFood_type(n);
        }
        if (next==5){
            int n = Executor.in.nextInt();
            Executor.in.nextLine();
            setOffer(n);
        }

    }
}

