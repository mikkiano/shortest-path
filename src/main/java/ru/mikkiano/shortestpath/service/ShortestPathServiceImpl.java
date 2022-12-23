package ru.mikkiano.shortestpath.service;

import org.springframework.stereotype.Service;
import ru.mikkiano.shortestpath.model.Node;
import ru.mikkiano.shortestpath.rest.req.exc.InvalidFinishNodeException;
import ru.mikkiano.shortestpath.rest.req.exc.InvalidStartNodeException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.mikkiano.shortestpath.model.Node.DotDistance;


@Service
public class ShortestPathServiceImpl implements ShortestPathService {
    private static List<Integer> dijkstraAlgorithm(Integer startNode, List<Node> nodes, Map<Integer, List<Integer>> neighbors, Integer nodeCount) {
        List<Double> distances = new ArrayList<>(Collections.nCopies(nodeCount, Double.POSITIVE_INFINITY));
        List<Integer> prevNode = new ArrayList<>(Collections.nCopies(nodeCount, null));
        Queue<Integer> queue = new PriorityQueue<>(nodeCount, Comparator.comparingDouble(distances::get));
        queue.addAll(IntStream.range(0, nodeCount).boxed().collect(Collectors.toList()));
        distances.set(startNode, (double) 0);

        while (!queue.isEmpty()) {
            Integer u = queue.poll();
            if (neighbors.containsKey(u)) {
                for (int v : neighbors.get(u)) {
                    double pathLength = distances.get(u) + DotDistance(nodes.get(u), nodes.get(v));
                    if (pathLength < distances.get(v)) {
                        distances.set(v, pathLength);
                        prevNode.set(v, u);
                        queue.remove(v);
                        queue.add(v);
                    }
                }
            }
        }
        return prevNode;
    }

    private static List<Node> restorePath(Integer startNode, Integer finishNode, List<Node> nodes, List<Integer> prevNode) {
        List<Node> result = new ArrayList<>();
        if (prevNode.get(finishNode) != null || finishNode.equals(startNode)) {
            while (finishNode != null) {
                result.add(nodes.get(finishNode));
                finishNode = prevNode.get(finishNode);
            }
        }
        Collections.reverse(result);
        return result;
    }

    @Override
    public List<Node> calculatePath(Integer startNode, Integer finishNode, List<Node> nodes, Map<Integer, List<Integer>> neighbors) {
        int nodeCount = nodes.size();
        if (startNode < 0 || startNode > nodeCount - 1) {
            throw new InvalidStartNodeException(startNode);
        }
        if (finishNode < 0 || finishNode > nodeCount - 1) {
            throw new InvalidFinishNodeException(finishNode);
        }

        List<Integer> prevNode = dijkstraAlgorithm(startNode, nodes, neighbors, nodeCount);
        return restorePath(startNode, finishNode, nodes, prevNode);
    }
}
