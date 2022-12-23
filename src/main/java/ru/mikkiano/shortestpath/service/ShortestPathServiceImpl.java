package ru.mikkiano.shortestpath.service;

import org.springframework.stereotype.Service;
import ru.mikkiano.shortestpath.model.Node;

import java.util.*;

import static ru.mikkiano.shortestpath.model.Node.DotDistance;


@Service
public class ShortestPathServiceImpl implements ShortestPathService {
    @Override
    public List<Node> calculatePath(Integer startNode, Integer finishNode, List<Node> nodes, Map<Integer, List<Integer>> neighbors) {
        int nodeCount = nodes.size();

        List<Double> distances = new ArrayList<>(Collections.nCopies(nodeCount, Double.MAX_VALUE));
        List<Integer> prevNode = new ArrayList<>(Collections.nCopies(nodeCount, null));
        Set<Integer> nodeSet = new HashSet<>(neighbors.keySet());

        distances.set(startNode, (double) 0);

        while (!nodeSet.isEmpty()) {
            Integer u = findNodeWithMinDistance(nodeSet, distances);
            assert (u != null);
            nodeSet.remove(u);
            if (neighbors.containsKey(u)) {
                for (int v : neighbors.get(u)) {
                    if (nodeSet.contains(v)) {
                        double pathLength = distances.get(u) + DotDistance(nodes.get(u), nodes.get(v));
                        if (pathLength < distances.get(v)) {
                            distances.set(v, pathLength);
                            prevNode.set(v, u);
                        }
                    }
                }
            }
        }

        List<Node> result = new ArrayList<>();
        if (prevNode.get(finishNode) != null || finishNode.equals(startNode))
            while (finishNode != null) {
                result.add(nodes.get(finishNode));
                finishNode = prevNode.get(finishNode);
            }
        Collections.reverse(result);
        return result;
    }

    private Integer findNodeWithMinDistance(Set<Integer> nodes, List<Double> distances) {
        if (nodes.size() == 1) {
            return nodes.iterator().next();
        }
        Integer index = null;
        double currentMin = Double.MAX_VALUE;
        for (Integer node : nodes) {
            double nodeDistance = distances.get(node);
            if (nodeDistance < currentMin) {
                index = node;
                currentMin = nodeDistance;
            }
        }
        if (index == null){
            return nodes.iterator().next();
        }
        return index;
    }
}
