package SWApp;

import javax.swing.*;
import java.util.*;

class Logic {

    private Constants cons = new Constants();
    private ArrayList<Employees> tempEmpList;

    private Queue<Employees> queue = new Queue<>() {
        class Node {
            private Employees data;
            private Node next;

            Node(Employees data, Node next) {
                this.data = data;
                this.next = next;
            }

            Employees getData() {
                return data;
            }

            Node getNext() {
                return next;
            }

            void setNext(Node next) {
                this.next = next;
            }
        }

        private int size = 0;
        private Node head = null;
        private Node tail = null;

        @Override
        public boolean add(Employees employees) {
            if (head == null) {
                head = new Node(employees, null);
                tail = head;
                size++;
            } else {
                tail.setNext(new Node(employees, null));
                tail = tail.getNext();
                size++;
            }
            return true;
        }

        @Override
        public boolean offer(Employees employees) {
            return false;
        }

        @Override
        public Employees remove() {
            Node temp = head;
            head = head.getNext();
            size--;
            return temp.getData();
        }

        @Override
        public Employees poll() {
            return null;
        }

        @Override
        public Employees element() {
            return null;
        }

        @Override
        public Employees peek() {
            return head.getData();
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Employees> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Employees> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {
            while (!isEmpty())
                queue.remove();
        }
    };
    private int middle = 0;
    private int count = 0;
    private int leave = 1;
    private double income = 0;


    void predict(DefaultListModel<String> listMode, int balance) {

        double tempBalance = balance;
        double oneDayPredictBalance = tempBalance / 20.0;
        int dayCount = 20;
        int seniorCount = 3;
        int senior = 2;
        int middleCount = 3;

        while (income < tempBalance) {

            listMode.clear();
            income = 0;
            queue.clear();
            middle = 0;
            count = 0;
            leave = 1;

            for (int i = 1; i <= dayCount; i++) {

                double eventCost;
                listMode.addElement("DAY " + i + ":");
                leave(i);
                employeeHiring(seniorCount, middleCount);
                tempEmpList = tempList();
                tempEmpList.sort(Comparator.comparing(Employees::getMoneyPerMin));

                if (income == 0) {

                    eventCost = events(tempEmpList, cons.getEventList(),
                            income, listMode);
                    income = -eventCost + predictOneDay(cons.getEmployeesList());
                    tempBalance = balance + eventCost;
                    oneDayPredictBalance = tempBalance / 20.0;
                    listMode.addElement("Start-up: " + eventCost);

                } else {

                    double tempPred = predictOneDay(cons.getEmployeesList());
                    double tempOnePay = oneDayPredictBalance * i;

                    if (tempPred >= tempOnePay) {

                        eventCost = events(tempEmpList, cons.getEventList(),
                                income, listMode);
                    } else {

                        eventCost = events(tempEmpList, cons.getEventList(),
                                income - (tempOnePay - tempPred), listMode);

                    }

                    income += tempPred;
                    income -= eventCost;

                }

                listMode.addElement("Amount of workers: " + tempEmpList.size());
                listMode.addElement("Total revenue: " + Math.rint(100.0 * income) / 100.0);
                listMode.addElement("---------------------------------------");

            }
            if (income < tempBalance) {

                cons = new Constants();
                seniorCount++;
                middleCount++;
                cons.getEmployeesList().get(10).setCount(senior++);
                cons.getEmployeesList().get(0).setCount(senior++);

            } else {
                break;
            }
        }
    }

    private double predictOneDay(ArrayList<Employees> list) {
        double income = 0;
        for (Employees aList : list) {
            income += aList.getCount() *
                    (aList.getTime() * aList.getMoneyPerMin());
        }
        return income;
    }

    private ArrayList<Employees> tempList() {
        tempEmpList = new ArrayList<>();
        for (int count = 0, i = 0; i < cons.getEmployeesList().size(); i++) {
            for (int j = 0; j < cons.getEmployeesList().get(i).getCount(); j++) {
                tempEmpList.add(new Employees());
                tempEmpList.get(count).setPosition(cons.getEmployeesList().get(i).getPosition());
                tempEmpList.get(count).setTime(cons.getEmployeesList().get(i).getTime());
                tempEmpList.get(count).setSalary(cons.getEmployeesList().get(i).getSalary());
                tempEmpList.get(count).setMoneyPerMin(cons.getEmployeesList().get(i).getMoneyPerMin());
                tempEmpList.get(count++).setCount(1);
            }
        }

        return tempEmpList;
    }

    private double events(ArrayList<Employees> employ, ArrayList<Event> events, double balance,
                          DefaultListModel<String> listModel) {
        boolean found = false;
        ArrayList<LinkedList> cost = new ArrayList<>();

        for (Event event : events) {
            LinkedList list = new LinkedList();
            for (int j = 0; j < event.getMaxPart() - event.getMinPart() + 1; j++) {
                if (j + event.getMinPart() <= employ.size()) {
                    double[][] food = new double[10][cons.getDrinkList().size() * cons.getFoodList().size()];
                    for (int m = 1; m <= 10; m++) {
                        food[m - 1] = foodExpense(employ, cons.getFoodList(),
                                cons.getDrinkList(), (int) (employ.size() * (m / 10.)));
                    }
                    list.insert(Math.rint(100.0 * eventCost(event, employ, j)) / 100.0, food, event.getEvent());
                }
            }
            cost.add(list);
        }
        double expenses = 0;
        int size = listModel.getSize();
        double rest = Integer.MAX_VALUE;
        for (LinkedList list : cost) {
            for (LinkedList.Node node = list.getStart(); node != null; node = node.getNext()) {
                for (int j = 0; j < 10; j++) {
                    for (int n = 0; n < cons.getDrinkList().size() * cons.getFoodList().size(); n++) {
                        double tempExpenses = node.getData() + node.getFood()[j][n];
                        if (balance - tempExpenses >= 0) {
                            if (balance - tempExpenses < rest) {
                                found = true;
                                rest = balance - tempExpenses;
                                expenses = tempExpenses;
                                if (listModel.getSize() < size + 2) {
                                    listModel.addElement("Event (" + node.getEvent() + ") cost: " +
                                            Math.rint(100.0 * node.getData()) / 100.0);
                                    listModel.addElement("Food and drinks cost: " +
                                            Math.rint(100.0 * node.getFood()[j][n]) / 100.0);
                                } else {
                                    listModel.set(listModel.getSize() - 2,
                                            "Event (" + node.getEvent() + ") cost: " +
                                                    Math.rint(100.0 * node.getData()) / 100.0);
                                    listModel.set(listModel.getSize() - 1,
                                            "Food and drinks cost: " + Math.rint(100.0 *
                                                    node.getFood()[j][n]) / 100.0);
                                }
                            }
                        }
                    }

                }
            }

        }
        if (!found) {
            rest = Integer.MIN_VALUE;
            for (LinkedList list : cost) {
                for (LinkedList.Node node = list.getStart(); node != null; node = node.getNext()) {
                    for (int j = 0; j < 10; j++) {
                        for (int n = 0; n < cons.getDrinkList().size() * cons.getFoodList().size(); n++) {
                            double tempExpenses = node.getData() + node.getFood()[j][n];
                            if (balance - tempExpenses >= rest) {
                                if (rest < balance - tempExpenses) {
                                    rest = balance - tempExpenses;
                                    expenses = Math.abs(rest);
                                    if (listModel.getSize() < size + 2) {
                                        listModel.addElement("Event (" + node.getEvent() + ") cost: " +
                                                Math.rint(100.0 * node.getData()) / 100.0);
                                        listModel.addElement("Food and drinks cost: " +
                                                Math.rint(100.0 * node.getFood()[j][n]) / 100.0);
                                    } else {
                                        listModel.set(listModel.getSize() - 2,
                                                "Event (" + node.getEvent() + ") cost: " +
                                                        Math.rint(100.0 * node.getData()) / 100.0);
                                        listModel.set(listModel.getSize() - 1,
                                                "Food and drinks cost: " + Math.rint(100.0 *
                                                        node.getFood()[j][n]) / 100.0);
                                    }
                                }
                            }

                        }

                    }
                }

            }
        }
        return expenses;

    }

    private double eventCost(Event event, ArrayList<Employees> employ, int num) {
        double cost = 0;

        cost += event.getCost();
        for (int n = 0; n < event.getMinPart() + num; n++) {
            cost += Math.rint(100.0 * (employ.get(n).getMoneyPerMin() * event.getTime())) / 100.0;
        }

        return cost;
    }

    private double[] foodExpense(ArrayList<Employees> employ, ArrayList<Food> food,
                                 ArrayList<Drink> drink, int count) {
        double[] foodCost = new double[food.size()];
        double[] drinkCost = new double[drink.size()];

        for (int i = 0; i < foodCost.length; i++) {
            foodCost[i] += food.get(i).getCost();
            for (int n = 0; n < count; n++) {
                foodCost[i] += Math.rint(100.0 * (employ.get(n).getMoneyPerMin() * food.get(i).getCost())) / 100.0;
            }
        }

        for (int i = 0; i < drinkCost.length; i++) {
            drinkCost[i] += drink.get(i).getCost();
            for (int n = 0; n < count; n++) {
                drinkCost[i] += Math.rint(100.0 * (employ.get(n).getMoneyPerMin() * drink.get(i).getCost())) / 100.0;
            }
        }
        double[] expenses = new double[food.size() * drink.size()];
        count = 0;

        for (double aFoodCost : foodCost) {
            for (double aDrinkCost : drinkCost) {
                expenses[count++] = Math.rint(100.0 * (aFoodCost + aDrinkCost)) / 100.0;
            }
        }

        return expenses;

    }

    private void leave(int i) {
        if (i % 4 == 0) {
            switch (leave) {
                case 1:
                    if (cons.getEmployeesList().get(2).getCount() != 0)
                        cons.getEmployeesList().get(2).setCount(
                                cons.getEmployeesList().get(2).getCount() - 1);
                    leave++;
                    break;
                case 2:
                    if (cons.getEmployeesList().get(0).getCount() != 0)
                        cons.getEmployeesList().get(0).setCount(
                                cons.getEmployeesList().get(0).getCount() - 1);
                    leave++;
                    break;
                case 3:
                    if (cons.getEmployeesList().get(7).getCount() != 0)
                        cons.getEmployeesList().get(7).setCount(
                                cons.getEmployeesList().get(7).getCount() - 1);
                    leave = 1;
                    break;

            }
            income -= cons.getReplacing();
        }
    }

    private void employeeHiring(int seniorCount, int middleCount) {
        if (queue.size() - 5 == 0) {
            assert queue.peek() != null;
            if (queue.peek().getPosition().equalsIgnoreCase("Mid to Senior")) {
                cons.getEmployeesList().get(2).setCount(cons.getEmployeesList().get(2).getCount() + 1);
                cons.getEmployeesList().get(1).setCount(cons.getEmployeesList().get(1).getCount() - 1);
                count--;
            } else {
                if (Objects.requireNonNull(queue.peek()).getPosition().equalsIgnoreCase("New to Mid")) {
                    cons.getEmployeesList().get(1).setCount(cons.getEmployeesList().get(1).getCount() + 1);
                    middle--;
                } else {
                    cons.getEmployeesList().get(0).setCount(cons.getEmployeesList().get(0).getCount() + 1);
                }
            }
            queue.remove();
        }

        if (cons.getEmployeesList().get(2).getCount() < seniorCount &&
                count - cons.getEmployeesList().get(1).getCount() < 0) {
            queue.add(new Employees("Mid to Senior", 420, 37, 1));
            count++;
        } else {
            if (middle < middleCount) {
                queue.add(new Employees("New to Mid", 420, 0, 1));
                middle++;
            } else {
                queue.add(new Employees("New developer", 420, 0, 1));
            }

        }

    }

}
