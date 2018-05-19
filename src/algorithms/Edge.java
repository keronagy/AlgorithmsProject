/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

public class Edge implements Comparable<Edge> {

    private Node one, two;

    private int weight;

    public Edge(Node one, Node two) {

        this(one, two, 1);

    }

    public Edge(Node one, Node two, int weight) {

        this.one = (one.getId().compareTo(two.getId()) <= 0) ? one : two;

        this.two = (this.one == one) ? two : one;

        this.weight = weight;

    }

    public Node getNeighbor(Node current) {

        if (!(current.equals(one) || current.equals(two))) {

            return null;

        }

        return (current.equals(one)) ? two : one;

    }

    public Node getOne() {

        return this.one;

    }

    public Node getTwo() {

        return this.two;

    }

    public int getWeight() {

        return this.weight;

    }

    public void setWeight(int weight) {

        this.weight = weight;

    }

    public int compareTo(Edge other) {

        return this.weight - other.weight;

    }

    public String toString() {

        return "({" + one + ", " + two + "}, " + weight + ")";

    }

    public int hashCode() {

        return (one.getId() + two.getId()).hashCode();

    }

    public boolean equals(Object other) {

        if (!(other instanceof Edge)) {

            return false;

        }

        Edge e = (Edge) other;

        return e.one.equals(this.one) && e.two.equals(this.two);

    }

}
