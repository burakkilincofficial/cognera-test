package com.bk.cogneratest.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of Graph Service.
 * @since 2022-06-12
 * @author burak kilinc
 */
@Service
public class GraphServiceImpl implements GraphService {
    private static final Graph graph = new Graph();

    @Override
    public void addEdge(String node1, String node2) {
        createGraph(node1, node2);
    }

    @Override
    public List<String> findNodes(String node, int maxHops) {
        List<String> nodes = graph.findNodes(new Vertex(node), maxHops);
        if (nodes.contains(node)) {
            nodes.remove(node);
        }
        return nodes;
    }

    private void createGraph(String node1, String node2) {
        graph.addVertex(node1);
        graph.addVertex(node2);
        graph.addEdge(node1, node2);

    }


}
