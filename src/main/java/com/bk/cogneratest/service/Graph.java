package com.bk.cogneratest.service;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class Graph {
    private static final Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();


    public void addVertex(String name) {
        adjVertices.putIfAbsent(new Vertex(name), new ArrayList<>());
    }

    public void removeVertex(String name) {
        Vertex v = new Vertex(name);
        adjVertices.values().forEach(e -> e.remove(v));
        adjVertices.remove(new Vertex(name));
    }

    public void addEdge(String node1, String node2) {
        Vertex v1 = new Vertex(node1);
        Vertex v2 = new Vertex(node2);
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }

    private void findNodes(Vertex v, int maxHops, List<String> nodes) {
        if (maxHops == 0) {
            return;
        }
        for (Vertex vertex : adjVertices.get(v)) {
            if (!nodes.contains(vertex.getName())) {
                nodes.add(vertex.getName());
                findNodes(vertex, maxHops - 1, nodes);
            }
        }
    }

    public List<String> findNodes(Vertex v, int maxHops) {
        List<String> nodes = new ArrayList<>();
        findNodes(v, maxHops, nodes);
        return nodes;
    }

    List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

    void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        List<Vertex> eV1 = adjVertices.get(v1);
        List<Vertex> eV2 = adjVertices.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }
}