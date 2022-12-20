package ru.mikkiano.shortestpath.model;

import lombok.Data;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Data
public class Node {
    private Integer x;
    private Integer y;

    public static double DotDistance(Node node1, Node node2) {
        return sqrt(pow((node1.getX() - node2.getX()), 2) + pow((node1.getY() - node2.getY()), 2));
    }
}
