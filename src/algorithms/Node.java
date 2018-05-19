/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;

public class Node {

    String Id;
    ArrayList<String> messages;
    Point pos;
    int battery;
    int id = 0;
    private ArrayList<Edge> neighborhood;
    public static int count = 1;

    public Node(Point pos) {
        this.Id = "" + count++;
        this.messages = new ArrayList<String>();
        this.neighborhood = new ArrayList<Edge>();
        this.pos = pos;
        this.battery = 20;

    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public String getId() {
        return Id;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public void addNeighbor(Edge edge) {

        if (this.neighborhood.contains(edge)) {

            return;
        }

        this.neighborhood.add(edge);

    }

    public boolean containsNeighbor(Edge other) {

        return this.neighborhood.contains(other);

    }

    public Edge getNeighbor(int index) {

        return this.neighborhood.get(index);

    }

    Edge removeNeighbor(int index) {

        return this.neighborhood.remove(index);

    }

    public void removeNeighbor(Edge e) {

        this.neighborhood.remove(e);

    }

    public int getNeighborCount() {

        return this.neighborhood.size();

    }

    public String toString() {

        return "Vertex " + Id;

    }

    public int hashCode() {

        return this.Id.hashCode();

    }

    public boolean equals(Object other) {

        if (!(other instanceof Node)) {

            return false;

        }

        Node v = (Node) other;

        return this.Id.equals(v.Id);

    }

    public ArrayList<Edge> getNeighbors() {

        return new ArrayList<Edge>(this.neighborhood);

    }

}
