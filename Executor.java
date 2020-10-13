package Assignment2;
import java.util.HashMap;
import java.util.Scanner;

public class Executor {
    public static Scanner in= new Scanner(System.in);

    Executor(){
        in = new Scanner(System.in);
    }

    public static void main(String[] args){
        //hard coding customer
    User u1 = new elite_c("Ram","Delhi");
    User u2 = new elite_c("Sam","Delhi");
    User u3 = new special_c("Tim","Bombay");
    User u4 = new Customer("Kim","Dadra");
    User u5 = new Customer("Jim","Delhi");
        HashMap<Integer,Customer> c = new HashMap<>();
        c.put(1, (Customer) u1);
        c.put(2, (Customer) u2);
        c.put(3, (Customer) u3);
        c.put(4, (Customer) u4);
        c.put(5, (Customer) u5);

    // hard coding restaurants
        User u6 = new authentic_r("Shah","Delhi");
        User u7 = new Restaurant("Ravi's","Dadra");
        User u8 = new authentic_r("Chinese","Delhi");
        User u9 = new fastfood_r("Wang's","Delhi");
        User u10 = new Restaurant("Paradise","Bombay");
        HashMap<Integer,Restaurant> r = new HashMap<>();
        r.put(1, (Restaurant) u6);
        r.put(2, (Restaurant) u7);
        r.put(3, (Restaurant) u8);
        r.put(4, (Restaurant) u9);
        r.put(5, (Restaurant) u10);
        Application start = new Application(c,r);

    }
}

class Application{
    private static double company_bal;
    private static int c_delivery;
    private static HashMap<Integer,Customer> customers;
    private static HashMap<Integer,Restaurant> restaurants;


    public static int getC_delivery() {
        return c_delivery;
    }

    public static void setC_delivery(int c) {
        //System.out.println(c);
        c_delivery += c;
    }

    Application(HashMap<Integer, Customer> c, HashMap<Integer,Restaurant> r ){
        customers = c;
        restaurants = r;
        company_bal=0;
        c_delivery=0;
        System.out.println("Zotato App Started");
        printMenu();
        int app_query = Executor.in.nextInt();
        Executor.in.nextLine();
        while (app_query!=5){
            select(app_query);
            printMenu();
            app_query = Executor.in.nextInt();
            Executor.in.nextLine();
        }

        }
        void printMenu(){
            System.out.println("Welcome to Zotato!");
            System.out.println("1) Enter as Restaurant Owner");
            System.out.println("2) Enter as Customer");
            System.out.println("3) Check User Details");
            System.out.println("4) Company Account details");
            System.out.println("5) Exit");
        }

        public static double getCompany_bal() {
            return company_bal;
        }

        public static void setCompany_bal(double c) {
//        System.out.println(c);
            company_bal += c;
        }

        void select(int q){
        User user;
        if (q==1){
            user = new Restaurant();
            printr();
            int e = Executor.in.nextInt();
            Executor.in.nextLine();
            user.login(new Restaurant(),restaurants.get(e));
        }
        if (q==2){
            user = new Customer();
            printc();
            int e = Executor.in.nextInt();
            Executor.in.nextLine();
            user.login(new Customer(),customers.get(e));
        }
        if (q==3){
            System.out.println("1) Customer List");
            System.out.println("2) Restaurant List");
            int choice = Executor.in.nextInt();
            Executor.in.nextLine();
            if (choice==1){
                Application.printc();
                int i = Executor.in.nextInt();
                Executor.in.nextLine();
                customers.get(i).displayDets();

            }
            else if (choice==2){
                Application.printr();
                int i = Executor.in.nextInt();
                Executor.in.nextLine();
                restaurants.get(i).displayDets();
            }
        }
        if (q==4){
            System.out.println("Total charges collected by Zotato:" + company_bal);
            System.out.println("Total delivery charges collected:"+ c_delivery);

        }
        }


        static void printc(){
            System.out.println("Choose customer:");
            customers.forEach((key,value) -> System.out.println(key + " " + value.getName() + value.type()));
        }

        public static void printr(){
        System.out.println("Choose Restaurant:");
        getRestaurants().forEach((key,value) -> System.out.println(key + " " + value.getName()+value.type()));
    }

    public static HashMap<Integer, Restaurant> getRestaurants() {
        return restaurants;
    }

    public static void setRestaurants(HashMap<Integer, Restaurant> restaurants) {
        Application.restaurants = restaurants;
    }
}


    interface User{
    public void login(User u,User obj);
    public void query_selector(int q);
    public void menu();
    public void displayDets();
    }

    