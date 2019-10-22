package com.leetcode.hard.divide.and.conquer;

import com.leetcode.hard.utils.Point;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClosestPairOfPoints {

    //Util functions

    //1. distance between two points
    double distance(Point a, Point b) {
        double x_distance = Math.abs(a.x - b.x);
        double y_distance = Math.abs(a.y - b.y);
        return Math.sqrt(x_distance * x_distance - y_distance * y_distance);
    }
    //2. brute force for points size less than 3.

    double bruteForce(Point[] arr) {

        double min = Double.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                double distance = distance(arr[i], arr[j]);
                if (distance < min) {
                    min = distance;
                }
            }
        }

        return min;
    }

    //3. closestInStrip
    double closestInStrip(Point strip[], int size, double d) {

        double min = d;
        for (int i = 0; i < strip.length - 1; i++) {
            for (int j = i + 1; j < strip.length && strip[j].y - strip[i].y < d; j++) {
                double distance = distance(strip[i], strip[j]);
                if (distance < min) {
                    min = distance;
                }
            }
        }
        return min;
    }

    double closestPairUtil(Point[] pointsX, Point[] pointsY, int n) {

        if (n <= 3) {
            return bruteForce(pointsX);
        }

        int mid = pointsX.length / 2;

        Point middlePoint = pointsX[mid];

        Point PyL[] = new Point[mid + 1];
        Point PxL[] = new Point[mid + 1];

        Point PyR[] = new Point[n - mid - 1];
        Point PxR[] = new Point[n - mid - 1];

        for (int i = 0; i < n; i++) {
            if (i <= mid) {
                PxL[i] = pointsX[i];
            } else {
                PxR[i-mid-1] = pointsX[i];
            }
        }
        int li = 0, ri = 0;
        for (int i = 0; i < n; i++) {
            if (pointsY[i].x < middlePoint.x) {
                PyL[li++] = pointsY[i];
            } else
                PyR[ri++] = pointsY[i];
        }

        double dl = closestPairUtil(PxL, PyL, mid);
        double dr = closestPairUtil(PxR, PyR, n - mid);

        double d = Math.min(dl, dr);

        Point strip[] = new Point[n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (pointsY[i].x - middlePoint.x < d) {
                strip[index++] = pointsY[i];
            }
        }

        return Math.min(d, closestInStrip(strip, index, d));

    }

    double closestPair(Point[] points) {

        List<Point> pointsXAsList = Arrays.stream(points).sorted(Comparator.comparing(point -> point.x)).collect(Collectors.toList());
        Point[] pointsX = pointsXAsList.toArray(new Point[0]);
        List<Point> pointsYAsList = Arrays.stream(points).sorted(Comparator.comparing(point -> point.y)).collect(Collectors.toList());
        Point[] pointsY = pointsYAsList.toArray(new Point[0]);
        return closestPairUtil(pointsX, pointsY, points.length);
    }

    public static void main(String args[]) {
        Point P[] = new Point[]{new Point(2, 3), new Point(12, 30),
                new Point(40, 50), new Point(5, 1), new Point(12, 10), new Point(3, 4)};
        ClosestPairOfPoints cpp = new ClosestPairOfPoints();
        System.out.println(cpp.closestPair(P));
    }
}