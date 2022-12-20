package ru.mikkiano.shortestpath.rest.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikkiano.shortestpath.model.Node;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortestPathResponse {
    private List<Node> path;
}
