package Assignment2;


import java.util.*;

public class Customer implements User {
    private final String name;
    private final String address;
    private float balance;
    private float reward;
    private HashMap<Integer,food_item> orders;
    private HashMap<Integer,food_item> in_cart;
    Customer(){
        this.name = "n/a";
        this.address = "n/a";
    }

    Customer(String name, String address) {
        this.name = name;
        this.address = address;
        this.orders = new HashMap<Integer, food_item>();
        this.in_cart = new HashMap<Integer, food_item>();
        this.balance =1000;
        this.reward =0;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }


    public float getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public HashMap<Integer, food_item> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<Integer, food_item> orders) {
        this.orders = orders;
    }

    public float getReward() {
        return reward;
    }

    public void setReward(float reward) {
        this.reward += reward;
    }
    @Override
    public void login(User u, User obj){
        ((Customer)obj).menu();
        int query = Executor.in.nextInt();
        Executor.in.nextLine();
        while(query!=5){
            ((Customer)obj).query_selector(query);
            ((Customer)obj).menu();
            query = Executor.in.nextInt();
            Executor.in.nextLine();
        }

    }

    @Override
    public void query_selector(int q) {;
        int current=0;
        if (q==1){
         Application.printr();
            int rr = Executor.in.nextInt();
            Executor.in.nextLine();
            if(current==0 || current==rr){
            current =rr;
            Restaurant sel_r = Application.getRestaurants().get(rr);
            sel_r.printItems();
            int selected_item = Executor.in.nextInt();
            Executor.in.nextLine();
            food_item f = sel_r.getItems().get(selected_item);
            System.out.println("Enter item quantity:");
            int quan = Executor.in.nextInt();
            Executor.in.nextLine();
            if (quan<=f.getQuantity()){
            System.out.println("Items added to cart.");
                food_item sel = new food_item(f,quan);
                in_cart.put(f.getId(),sel);
            }
            else{System.out.println("this quantity in not available,order again.");}}
            else{System.out.println("You can only select from one restaurant in a single order");}

        }
        if (q==2){
            Restaurant selrr = null;
            int number=0;
            for (food_item f :in_cart.values()){
                selrr = f.getRestaurant();
                number += f.getQuantity();
            }
            if(in_cart.size()>0) {
                float ft = cart(selrr);
                while ((ft+ this.delivery()) > (this.reward + this.balance)) {
                    System.out.println("You are out of balance so please enter the ID of the product you would like to delete");
                    int del = Executor.in.nextInt();
                    Executor.in.nextLine();
                    in_cart.remove(del);
                    if (in_cart.size() > 0) {
                        ft = cart(selrr);
                    } else {
                        break;
                    }
                }
                if (in_cart.size() > 0) {
                    System.out.println("1) Proceed to Checkout");
                    if (Executor.in.nextInt() == 1) {
                        orders.putAll(in_cart);
                        in_cart.clear();
                        float rew = selrr.rewardc(ft);
                        float ft_d = ft + this.delivery();
                        System.out.println(number + " items successfully bought for Rs." + ft_d);
                        if (this.reward < ft_d) {
                            float leftover = Math.abs(this.reward - (ft_d));
                            this.reward = 0;
                            this.balance = this.balance - leftover;
                        } else if (this.reward >= ft_d) {
                            this.reward = this.reward - (ft_d);
                        }
                        Application.setCompany_bal(ft * (0.01));
                        Application.setC_delivery(this.delivery());
                        selrr.setNo_orders(1);
                        selrr.setRewards(rew);
                        this.setReward(rew);
                        current = 0;

                    } else {
                        System.out.println("Error while checking out");
                    }
                } else {
                    System.out.println("Cart is empty.");
                }
            }
            else {
                System.out.println("Cart is empty.");
            }

        }
        if (q==3){
            System.out.println("Reward :" + this.reward);
        }
        if (q==4){
            Set<Map.Entry<Integer, food_item>> entrySet = orders.entrySet();
            List<Map.Entry<Integer,food_item>> list = new ArrayList<Map.Entry<Integer,food_item>>(entrySet);
            for (int i= list.size()-1;i>=(list.size()<10?0:list.size()-10) ;i--){
                list.get(i).getValue().printer(this.delivery());
            }

        }
    }

    @Override
    public void displayDets() {

        System.out.println(this.name+ this.type() + " "+ this.address+ " Account balance:"+ this.balance);
    }

    public float cart(Restaurant sres){

        System.out.println("Items in cart: ");
        float tamt=0;
        for(food_item p : in_cart.values()){
            p.printer2();
            tamt = tamt+ p.finalp(p.getQuantity());
        }
        tamt = sres.rdiscount(tamt);
        tamt = cdiscount(tamt);
        System.out.println("Delivery Charge: "+ this.delivery());
        float ft = tamt+ this.delivery();
        System.out.println("Total order value: "+ ft);

        return tamt;
    }
    public float cdiscount(float p){
        return p;
    }

    @Override
    public void menu() {
        System.out.println("Welcome "+ this.name);
        System.out.println("1) Select Restaurant");
        System.out.println("2) Checkout Cart");
        System.out.println("3) Reward won");
        System.out.println("4) Print the recent orders");
        System.out.println("5) Exit");
    }
    public int delivery(){
        return 40;
    }
    public String type(){
        return "";
    }
}
class elite_c extends Customer{

    elite_c(String name, String address) {
        super(name, address);
    }
    @Override
    public float cdiscount(float p){
        if (p>200){
            p = p -50;
        }
        return p;
    }
    @Override
    public int delivery(){
        return 0;
    }
    @Override
    public String type(){
        return "(elite)";
    }
}
class special_c extends Customer{

    special_c(String name, String address) {
        super(name, address);
    }
    @Override
    public float cdiscount(float p){
        if (p>200){
            p = p -25;
        }
        return p;
    }
    @Override
    public int delivery(){
        return 20;
    }
    @Override
    public String type(){
        return "(special)";
    }
}

//class order{
//    private food_item f;
//    private int quantity;
//    //private float amount;
//    private int delivery;
//    private int reward;
//
//
//    public order(food_item f, int quantity, float amount, int r) {
//        this.f = f;
//        this.quantity = quantity;
//        //this.amount = amount;
//        this.delivery = 0;
//        this.reward =r;
//    }
//
//    public food_item getF() {
//        return f;
//    }
//
//    public void setF(food_item f) {
//        this.f = f;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public float getAmount() {
//        return amount;
//    }
//
//    public void setAmount(float amount) {
//        this.amount = amount;
//    }
//
//    public int getDelivery() {
//        return delivery;
//    }
//
//    public void setDelivery(int delivery) {
//        this.delivery = delivery;
//    }
//
//    public int getReward() {
//        return reward;
//    }
//
//
//
//    public void setReward(int reward) {
//        this.reward = reward;
//    }
//    public void printer(){
//        System.out.println("Item id:"+ f.getId()+ " Item name:"+ f.getName()+" Quantity:"+ this.quantity+ " for Rs."+ this.amount+ " from"+ f.getRestaurant().getName() + " and Delivery charge: "+ this.delivery);
//    }
////    public void printer2(){
////        System.out.println("Item id:"+ f.getId()+ " Item name:"+ f.getName()+" Quantity:"+ this.quantity+ " for Rs."+ this.amount+ " from"+ this.res.getName() + " with "+ this.f.getOffer()+ "% off");
////    }
//}
