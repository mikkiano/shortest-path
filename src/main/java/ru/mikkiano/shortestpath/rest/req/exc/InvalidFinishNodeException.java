package ru.mikkiano.shortestpath.rest.req.exc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidFinishNodeException extends RuntimeException {
    public InvalidFinishNodeException(Integer i) {
        super(String.format("Invalid finish node %d.", i));
    }
}
