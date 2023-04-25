package com.codeforces.div.second;

import java.util.*;
import java.util.stream.Collectors;

public final class ShawarmaTent {
    private int[] maximumShawarmaTent(int n, int[][] coordinates, int[] dest) {
        Map<Complex, Integer> map = new HashMap<>();
        Complex destAsComplex = new Complex(dest[0], dest[1]);
        Set<Complex> seen = new HashSet<>();
        for(int [] coordinate : coordinates) {
            seen.clear();
            //while traversing across the x-axis we can do the following
            int minY = Math.min(dest[1], coordinate[1]);
            int maxY = Math.max(dest[1], coordinate[1]);

            int minX = Math.min(dest[0], coordinate[0]);
            int maxX = Math.max(dest[0], coordinate[0]);
            for(int i = minX; i <= maxX; i++) {
                Complex pointLow = new Complex(i, minY);
                Complex pointHigh = new Complex(i, maxY);
                if(!seen.contains(pointLow)) {
                    map.put(pointLow, map.getOrDefault(pointLow, 0) + 1);
                    seen.add(pointLow);
                }
                if(!seen.contains(pointHigh)) {
                    map.put(pointHigh, map.getOrDefault(pointHigh, 0) + 1);
                    seen.add(pointHigh);
                }
            }

            for(int j = minY+1; j <= maxY-1; j++) {
                Complex pointLow = new Complex(minX, j);
                Complex pointHigh = new Complex(maxX, j);
                if(!seen.contains(pointLow)) {
                    map.put(pointLow, map.getOrDefault(pointLow, 0) + 1);
                    seen.add(pointLow);
                }
                if(!seen.contains(pointHigh)) {
                    map.put(pointHigh, map.getOrDefault(pointHigh, 0) + 1);
                    seen.add(pointHigh);
                }
            }
        }
        int max = 0;
        map.remove(destAsComplex);
        Complex shawarmaPoint = new Complex(-1, -1);
        for(Map.Entry<Complex, Integer> entry : map.entrySet()) {
            if(max < entry.getValue()) {
                max = entry.getValue();
                shawarmaPoint = entry.getKey();
            }
        }
        return new int[] {max, shawarmaPoint.re, shawarmaPoint.im};
    }

    class Complex {
        int re;
        int im;
        Complex(int re, int im) {
            this.re = re;
            this.im = im;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Complex complex = (Complex) o;
            return re == complex.re &&
                    im == complex.im;
        }

        @Override
        public int hashCode() {
            return Objects.hash(re, im);
        }
    }
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        String line = myScanner.nextLine();
        String[] rowAsString = line.split(" ");
        List<Integer> row = Arrays.stream(rowAsString).map(Integer::parseInt).collect(Collectors.toList());
        int n = row.get(0);
        int[] dest = new int[]{row.get(1), row.get(2)};
        int [][] coordinates = new int[n][2];
        int t = n;
        int index = 0;
        while(t-- > 0) {
            line = myScanner.nextLine();
            rowAsString = line.split(" ");
            row = Arrays.stream(rowAsString).map(Integer::parseInt).collect(Collectors.toList());
            coordinates[index++][0] = row.get(0);
            coordinates[index++][1] = row.get(1);
        }

        ShawarmaTent swt = new ShawarmaTent();
         int arr[] = swt.maximumShawarmaTent(n, coordinates, dest);

    }
}
