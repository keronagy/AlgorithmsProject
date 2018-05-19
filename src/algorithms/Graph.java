/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.*;

public class Graph {

    private HashMap<String, Node> vertices;

    private HashMap<Integer, Edge> edges;

    public Graph() {

        this.vertices = new HashMap<String, Node>();

        this.edges = new HashMap<Integer, Edge>();

    }

    public Graph(ArrayList<Node> vertices) {

        this.vertices = new HashMap<String, Node>();

        this.edges = new HashMap<Integer, Edge>();

        for (Node v : vertices) {

            this.vertices.put(v.getId(), v);

        }

    }

    public boolean addEdge(Node one, Node two) {

        return addEdge(one, two, 1);

    }

    public boolean addEdge(Node one, Node two, int weight) {

        if (one.equals(two)) {

            return false;

        }

        Edge e = new Edge(one, two, weight);

        if (edges.containsKey(e.hashCode())) {

            return false;

        } else if (one.containsNeighbor(e) || two.containsNeighbor(e)) {

            return false;

        }

        edges.put(e.hashCode(), e);

        one.addNeighbor(e);

        two.addNeighbor(e);

        return true;

    }

    public boolean containsEdge(Edge e) {

        if (e.getOne() == null || e.getTwo() == null) {

            return false;

        }

        return this.edges.containsKey(e.hashCode());

    }

    public Edge removeEdge(Edge e) {

        e.getOne().removeNeighbor(e);

        e.getTwo().removeNeighbor(e);

        return this.edges.remove(e.hashCode());

    }

    public boolean containsVertex(Node vertex) {

        return this.vertices.get(vertex.getId()) != null;

    }

    public Node getVertex(String label) {

        return vertices.get(label);

    }

    public boolean addVertex(Node vertex, boolean overwriteExisting) {

        Node current = this.vertices.get(vertex.getId());

        if (current != null) {

            if (!overwriteExisting) {

                return false;

            }

            while (current.getNeighborCount() > 0) {

                this.removeEdge(current.getNeighbor(0));

            }

        }

        vertices.put(vertex.getId(), vertex);

        return true;

    }

    public Node removeVertex(String label) {

        Node v = vertices.remove(label);

        while (v.getNeighborCount() > 0) {

            this.removeEdge(v.getNeighbor((0)));

        }

        return v;

    }

    public Set<String> vertexKeys() {

        return this.vertices.keySet();

    }

    public Set<Edge> getEdges() {

        return new HashSet<Edge>(this.edges.values());

    }

}
