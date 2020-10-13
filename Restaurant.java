package Assignment2;

import java.util.HashMap;

public class Restaurant implements User{
    private final String name;
    private final String address;
    private int no_orders;
    private int r_disc;
    private HashMap<Integer,food_item> items;
    private float rewards;

    Restaurant(){
        this.name = "n/a";
        this.address = "n/a";
    }
    Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        this.no_orders =0;
        this.items = new HashMap<Integer,food_item>();
        this.rewards =0;
        this.r_disc=0;
    }
    public int getNo_orders() {
        return no_orders;
    }

    public void setNo_orders(int no_orders) {
        this.no_orders += no_orders;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }


    public float getRewards() {
        return rewards;
    }

    public void setRewards(float rewards) {
        this.rewards += rewards;
    }
    public int getR_disc() {
        return r_disc;
    }

    public void setR_disc(int r_disc) {
        this.r_disc = r_disc;
    }

    public HashMap<Integer, food_item> getItems() {
        return items;
    }

    public void setItems(HashMap<Integer, food_item> items) {
        this.items = items;
    }
    @Override
    public void login(User u, User obj){
        ((Restaurant)obj).menu();
        int query = Executor.in.nextInt();
        Executor.in.nextLine();
        while(query!=5){
            ((Restaurant)obj).query_selector(query);
            ((Restaurant)obj).menu();
            query = Executor.in.nextInt();
            Executor.in.nextLine();
        }

    }

    @Override
    public void displayDets() {
        System.out.println(this.name+this.type()+ " "+ this.address+ " No. of orders taken:" + this.no_orders  );
    }

    @Override
    public void menu(){
        System.out.println("Welcome "+ this.name);
        System.out.println("1) Add Item");
        System.out.println("2) Edit Item");
        System.out.println("3) Print Rewards");
        System.out.println("4) Discount on bill value");
        System.out.println("5) Exit");
    }

    @Override
    public void query_selector(int q){
        if (q==1){
            AddItem();
        }
        if (q==2){
            edit1();
        }
        if (q==3){
            System.out.println(this.rewards + " reward points have been claimed by customers. ");
        }
        if (q==4){
            System.out.println("Enter offer on bill value:");
            this.setR_disc(Executor.in.nextInt());
            Executor.in.nextLine();
        }

    }
    public void printItems(){
        System.out.println("Choose item by code:");
        for(food_item f : items.values()){
            f.print();
        }
    }
    public float rdiscount(float p){
        return p;
    }
    public int rewardc(float p){
        return ((int)p/100)*5;
    }


    public void AddItem(){
        System.out.println("Enter food item details");
        System.out.println("Food name:");
        String n = Executor.in.nextLine();
        System.out.println("item price:");
        int p = Executor.in.nextInt();
        Executor.in.nextLine();
        System.out.println("item quantity:");
        int qn = Executor.in.nextInt();
        Executor.in.nextLine();
        System.out.println("item category:");
        String c = Executor.in.next();
        Executor.in.nextLine();
        System.out.println("Offer:");
        int o = Executor.in.nextInt();
        Executor.in.nextLine();
        food_item f = new food_item(n,p,qn,o,c,this);
        f.print();
        items.put(f.getId(),f);
    }
    public void edit1(){
        System.out.println("Choose item by code:");
        for(food_item f : items.values()){
          f.print();}
          int next = Executor.in.nextInt();
            Executor.in.nextLine();
            items.get(next).food_editor();
            items.get(next).print();
        }
    public String type(){
        return "";
    }
    }


class fastfood_r extends Restaurant{

    fastfood_r(String name, String address) {
        super(name, address);
    }
    @Override
    public float rdiscount(float p){
        float pp = p - (p*((float)getR_disc()/100));
        return pp;
    }
    @Override
    public int rewardc(float p){
        return ((int)p/150)*10;
    }
    @Override
    public String type(){
        return "(fast food)";
    }
}

class authentic_r extends Restaurant{

    authentic_r(String name, String address) {
        super(name, address);
    }
    @Override
    public float rdiscount(float p){
        float pp = p - (p*((float)getR_disc()/100));
        if (pp>100){
            pp = pp - 50;
        }
        return pp;
    }
    @Override
    public int rewardc(float p){
        return ((int)p/200)*25;
    }
    @Override
    public String type(){
        return "(Authentic)";
    }

}
