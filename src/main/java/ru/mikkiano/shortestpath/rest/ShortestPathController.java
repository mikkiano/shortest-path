package ru.mikkiano.shortestpath.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mikkiano.shortestpath.model.Node;
import ru.mikkiano.shortestpath.rest.req.GraphRequest;
import ru.mikkiano.shortestpath.rest.resp.ShortestPathResponse;
import ru.mikkiano.shortestpath.service.ShortestPathService;

import java.util.List;

@RestController
public class ShortestPathController {
    private final ShortestPathService shortestPathService;

    public ShortestPathController(ShortestPathService shortestPathService) {
        this.shortestPathService = shortestPathService;
    }

    @GetMapping("/calc")
    public ShortestPathResponse sumNumbers(@RequestBody GraphRequest request) {
        List<Node> result = shortestPathService.calculatePath(request.getStart(), request.getFinish(), request.getNodes(), request.getNeighbors());
        return new ShortestPathResponse(result);
    }
}
