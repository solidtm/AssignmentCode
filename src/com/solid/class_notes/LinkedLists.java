package com.solid.class_notes;

public class LinkedLists {
    Node head;
    Node tail;
    int size;
    public LinkedLists() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public void addFirst(int data) {
        Node node = new Node(data);
        node.next = head;
        head = node;

        if(tail == null){
            tail = head;
        }
        size++;
    }

    //    insert at tail
    public void addLast(int val) {
        if(tail == null){
            addFirst(val);
            return;
        }

        Node node = new Node(val);
        tail.next = node;
        tail = node;
        size++;
    }

    public void traverseList(){
        Node temp = head;

        while(temp != null){
            System.out.print("Visited node with data -> " + temp.data);
            System.out.println();
            temp = temp.next;
        }
        System.out.println("\nEND OF TRAVERSAL");
    }

    public void search(int key){
        Node temp = head;

        while(temp != null){
            if(temp.data != key){
                System.out.print("Visited node with data -> " + temp.data + " and it is not our key");
            }else{
                System.out.print("Visited node with data -> " + temp.data + " and it is equal to our key");
                break;
            }
            System.out.println();
            temp = temp.next;
        }
        System.out.println("\nEND OF SEARCH");
    }

    public void display(){
        Node temp = head;

        while(temp != null){
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("END");
    }

    public int deleteFirst(){
        int val = head.data;
        head = head.next;

        if(head == null){
            tail = null;
        }
        size--;

        return val;
    }

    public Node get(int index){   //gets the node at any index.
        Node node = head;

        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    public int deleteLast(){
        if(size <= 1) return deleteFirst();

        Node secondLast = get(size - 2);   //node points to the second last item in the ist
        int val = tail.data;
        tail = secondLast;
        tail.next = null;

        return val;
    }

    public static class Node{
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" + "data=" + data + ", next=" + next + '}';
        }
    }
}
