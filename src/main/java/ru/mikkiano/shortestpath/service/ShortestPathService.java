package ru.mikkiano.shortestpath.service;


import ru.mikkiano.shortestpath.model.Node;

import java.util.List;
import java.util.Map;

public interface ShortestPathService {
    List<Node> calculatePath(Integer start, Integer finish, List<Node> nodes, Map<Integer, List<Integer>> neighbors);

}
