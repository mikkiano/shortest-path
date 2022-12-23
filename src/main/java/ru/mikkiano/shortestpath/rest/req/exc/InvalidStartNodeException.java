package ru.mikkiano.shortestpath.rest.req.exc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidStartNodeException extends RuntimeException {
    public InvalidStartNodeException(Integer i) {
        super(String.format("Invalid start node %d.", i));
    }
}
