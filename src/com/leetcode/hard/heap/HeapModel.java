package com.leetcode.hard.heap;

public class HeapModel<T> {
    public T vertex;
    public Integer distance;

    public HeapModel(T vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public T getVertex() {
        return vertex;
    }

    public void setVertex(T vertex) {
        this.vertex = vertex;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "HeapModel{" +
                "vertex=" + vertex +
                ", distance=" + distance +
                '}';
    }
}
