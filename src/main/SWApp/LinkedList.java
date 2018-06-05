package SWApp;

public class LinkedList {
    class Node {
        private double data;
        private Node next;
        private double[][] food;
        private String event;

        Node(double data, Node next, double[][] food,String event) {
            this.data = data;
            this.next = next;
            this.food = food;
            this.event = event;
        }

        double getData() {
            return data;
        }

        public void setData(double data) {
            this.data = data;
        }

        Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }

        double[][] getFood() {
            return food;
        }

        public void setFood(double[][] food) {
            this.food = food;
        }

        String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }
    }
    private Node start;
    private Node tail;
    private int size;

    LinkedList() {
        this.start = null;
        this.tail = null;
        this.size = 0;

    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return start == null;
    }


    void insert(double data, double[][] food, String event) {
       if(start == null){
           start = new Node(data,null,food,event);
           tail = start;
       } else{
           tail.setNext(new Node(data,null,food,event));
           tail = tail.getNext();
       }
    }

    Node getStart() {
        return start;
    }


}
