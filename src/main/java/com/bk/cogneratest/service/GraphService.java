package com.bk.cogneratest.service;

import java.util.List;

/**
 * Grap data structure service.
 * @since 2022-06-12
 * @author burak kilinc
 */
public interface GraphService {
    void addEdge(String node1, String node2);

    List<String> findNodes(String node, int maxHops);
}
