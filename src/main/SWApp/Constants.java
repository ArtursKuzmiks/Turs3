package SWApp;

import java.util.ArrayList;

class Constants {

    private ArrayList<Drink> drinkList = new Drink().drinkList();
    private ArrayList<Food> foodList = new Food().foodList();
    private ArrayList<Event> eventList = new Event().eventList();
    private ArrayList<Employees> employeesList = new Employees().employeesList();

    ArrayList<Drink> getDrinkList() {
        return drinkList;
    }

    ArrayList<Food> getFoodList() {
        return foodList;
    }

    ArrayList<Event> getEventList() {
        return eventList;
    }

    ArrayList<Employees> getEmployeesList() {
        return employeesList;
    }

    int getReplacing() {
        return 90;
    }
}

class Employees {

    private String position;
    private int time;
    private int salary;
    private int count;
    private double moneyPerMin;

    Employees() {
    }

    Employees(String position, int time, int salary, int count) {
        this.position = position;
        this.time = time;
        this.salary = salary;
        this.count = count;
        this.moneyPerMin = salary/(double)time;
    }

    ArrayList<Employees> employeesList() {
        ArrayList<Employees> employeesList = new ArrayList<>();

        employeesList.add(new Employees("Junior Dev",420, 15,1));
        employeesList.add(new Employees("Middle Dev",420, 37,1));
        employeesList.add(new Employees("Senior Dev",420, 73,1));
        employeesList.add(new Employees("Project Manager",420, 41,1));
        employeesList.add(new Employees("Designer",420, 34,1));
        employeesList.add(new Employees("HR",420, 0,1));
        employeesList.add(new Employees("Accountant",420, 0,1));
        employeesList.add(new Employees("Marketing specialist",420, 39,1));
        employeesList.add(new Employees("QA",420, 26,1));
        employeesList.add(new Employees("Sys Admin",420, 44,1));
        employeesList.add(new Employees("Support Dev",420, 50,1));

        return employeesList;
    }

    double getMoneyPerMin() {
        return moneyPerMin;
    }

    void setMoneyPerMin(double moneyPerMin) {
        this.moneyPerMin = moneyPerMin;
    }

    String getPosition() {
        return position;
    }

    void setPosition(String position) {
        this.position = position;
    }

    int getTime() {
        return time;
    }

    void setTime(int time) {
        this.time = time;
    }

    int getSalary() {
        return salary;
    }

    void setSalary(int salary) {
        this.salary = salary;
    }

    int getCount() {
        return count;
    }

    void setCount(int count) {
        this.count = count;
    }
}

class Food {

    private String food;
    private double cost;
    private int time;

    Food(){
    }

    private Food(String food, double cost, int time) {
        this.food = food;
        this.cost = cost;
        this.time = time;
    }

    ArrayList<Food> foodList() {
        ArrayList<Food> foodList = new ArrayList<>();

        foodList.add(new Food("Lunch at Rimi", 2, 30));
        foodList.add(new Food("Lunch at Delisnack", 3, 40));
        foodList.add(new Food("Lunch at Bento", 4, 50));

        return foodList;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

class Drink {
    private String drink;
    private double cost;
    private int time;

    Drink(){
    }

    private Drink(String drink, double cost, int time) {
        this.drink = drink;
        this.cost = cost;
        this.time = time;
    }

    ArrayList<Drink> drinkList() {
        ArrayList<Drink> drinkList = new ArrayList<>();

        drinkList.add(new Drink("Coffee", 1, 3));
        drinkList.add(new Drink("Fresh Orange juice", 2, 2));
        drinkList.add(new Drink("Tea", 1, 5));

        return drinkList;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

class Event {

    private String event;
    private double cost;
    private int time;
    private int minPart;
    private int maxPart;

    Event(){
    }

    private Event(String event, double cost, int time, int minPart, int maxPart) {
        this.event = event;
        this.cost = cost;
        this.time = time;
        this.minPart = minPart;
        this.maxPart = maxPart;
    }

    ArrayList<Event> eventList(){
        ArrayList<Event> eventsList = new ArrayList<>();

        eventsList.add(new Event("Meet Magento conference",50,420,4,7));
        eventsList.add(new Event("Friday workshop",10,120,50,100));
        eventsList.add(new Event("Summer office",100,420,7,20));
        eventsList.add(new Event("Atomic - A-Eiropa concert",5,180,7,14));
        eventsList.add(new Event("Atomic - Steak masterclass",10,120,7,14));
        eventsList.add(new Event("Atomic - Airsoft",7,120,7,14));
        eventsList.add(new Event("Atomic - Race cars",12,90,7,20));
        eventsList.add(new Event("Atomic - Theatre",6,180,7,14));

        return eventsList;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    int getMinPart() {
        return minPart;
    }

    public void setMinPart(int minPart) {
        this.minPart = minPart;
    }

    int getMaxPart() {
        return maxPart;
    }

    public void setMaxPart(int maxPart) {
        this.maxPart = maxPart;
    }
}

