/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.*;

public class Dijkstra {

    private Graph graph;
    private String initialVertexLabel;
    private HashMap<String, String> predecessors;
    private HashMap<String, Integer> distances;
    private PriorityQueue<Node> availableVertices;
    private HashSet<Node> visitedVertices;

    public Dijkstra(Graph graph, String initialVertexLabel) {
        this.graph = graph;
        Set<String> vertexKeys = this.graph.vertexKeys();

        if (!vertexKeys.contains(initialVertexLabel)) {
            throw new IllegalArgumentException("The graph must contain the initial vertex.");
        }

        this.initialVertexLabel = initialVertexLabel;
        this.predecessors = new HashMap<String, String>();
        this.distances = new HashMap<String, Integer>();
        this.availableVertices = new PriorityQueue<Node>(vertexKeys.size(), new Comparator<Node>() {

            public int compare(Node one, Node two) {
                int weightOne = Dijkstra.this.distances.get(one.getId());
                int weightTwo = Dijkstra.this.distances.get(two.getId());
                return weightOne - weightTwo;
            }
        });

        this.visitedVertices = new HashSet<Node>();

        for (String key : vertexKeys) {
            this.predecessors.put(key, null);
            this.distances.put(key, Integer.MAX_VALUE);
        }

        this.distances.put(initialVertexLabel, 0);

        Node initialVertex = this.graph.getVertex(initialVertexLabel);
        ArrayList<Edge> initialVertexNeighbors = initialVertex.getNeighbors();
        for (Edge e : initialVertexNeighbors) {
            Node other = e.getNeighbor(initialVertex);
            this.predecessors.put(other.getId(), initialVertexLabel);
            this.distances.put(other.getId(), e.getWeight());
            this.availableVertices.add(other);
        }

        this.visitedVertices.add(initialVertex);

        processGraph();

    }

    private void processGraph() {

        while (this.availableVertices.size() > 0) {

            Node next = this.availableVertices.poll();
            int distanceToNext = this.distances.get(next.getId());

            List<Edge> nextNeighbors = next.getNeighbors();
            for (Edge e : nextNeighbors) {
                Node other = e.getNeighbor(next);
                if (this.visitedVertices.contains(other)) {
                    continue;
                }

                int currentWeight = this.distances.get(other.getId());
                int newWeight = distanceToNext + e.getWeight();

                if (newWeight < currentWeight) {
                    this.predecessors.put(other.getId(), next.getId());
                    this.distances.put(other.getId(), newWeight);
                    this.availableVertices.remove(other);
                    this.availableVertices.add(other);
                }

            }

            this.visitedVertices.add(next);
        }
    }

    public ArrayList<Node> getPathTo(String destinationLabel) {
        ArrayList<Node> path = new ArrayList<Node>();
        path.add(graph.getVertex(destinationLabel));

        while (!destinationLabel.equals(this.initialVertexLabel)) {
            Node predecessor = graph.getVertex(this.predecessors.get(destinationLabel));
            if (predecessor == null) {
                return null;
            }
            destinationLabel = predecessor.getId();
            path.add(0, predecessor);
        }
        return path;
    }

    public int getDistanceTo(String destinationLabel) {
        return this.distances.get(destinationLabel);
    }
}
