package ru.mikkiano.shortestpath.rest.req;

import lombok.Data;
import ru.mikkiano.shortestpath.model.Node;

import java.util.List;
import java.util.Map;


@Data
public class GraphRequest {
    private Integer start;
    private Integer finish;
    private List<Node> nodes;
    private Map<Integer, List<Integer>> neighbors;
}
