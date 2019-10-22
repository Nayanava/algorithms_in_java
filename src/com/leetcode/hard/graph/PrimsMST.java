package com.leetcode.hard.graph;

import com.leetcode.hard.heap.HeapModel;
import com.leetcode.hard.heap.ModifiedHeap;
import com.leetcode.hard.utils.Graph;
import com.leetcode.hard.utils.Node;
import com.leetcode.hard.utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PrimsMST {

    public static void main(String[] args) {
        Graph e = new Graph(9, false);
        e.addEdge( 0, 1, 4, false);
        e.addEdge( 0, 7, 8, false);
        e.addEdge( 1, 2, 8, false);
        e.addEdge( 1, 7, 11, false);
        e.addEdge( 2, 3, 7, false);
        e.addEdge( 2, 8, 2, false);
        e.addEdge( 2, 5, 4, false);
        e.addEdge( 3, 4, 9, false);
        e.addEdge( 3, 5, 14, false);
        e.addEdge( 4, 5, 10, false);
        e.addEdge( 5, 6, 2, false);
        e.addEdge( 6, 7, 1, false);
        e.addEdge( 6, 8, 6, false);
        e.addEdge( 7, 8, 7, false);



        HeapModel<Integer>[] heapModels = new HeapModel[]{new HeapModel<>(0, 0),
                new HeapModel<>(1, Integer.MAX_VALUE),
                new HeapModel<>(2, Integer.MAX_VALUE),
                new HeapModel<>(3, Integer.MAX_VALUE),
                new HeapModel<>(4, Integer.MAX_VALUE),
                new HeapModel<>(5, Integer.MAX_VALUE),
                new HeapModel<>(6, Integer.MAX_VALUE),
                new HeapModel<>(7, Integer.MAX_VALUE),
                new HeapModel<>(8, Integer.MAX_VALUE)
        };
        ModifiedHeap heap = new ModifiedHeap(heapModels, e.nvertices);

        Map<Integer, Pair> minimumSpanningTree = new HashMap<>();

        while(!heap.isEmpty()) {
            HeapModel minNode = heap.extractMin();
            Node node = e.nedges[(int) minNode.vertex];
            while( node != null) {
                int y = node.y;
                Optional<HeapModel> adjacentVertexOptional = heap.search(y);
                if(adjacentVertexOptional.isPresent()) {
                    HeapModel adjacentVertex = adjacentVertexOptional.get();
                    if(node.weight < adjacentVertex.distance) {
                        heap.decrease(y, node.weight);
                        minimumSpanningTree.put(node.y, new Pair(node.x, node.y));
                    }
                }
                node = node.next;
            }
        }

        minimumSpanningTree.values().stream()
                .forEach(pair -> System.out.println(pair.x + " " + pair.y));
    }

}
