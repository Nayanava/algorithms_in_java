package com.leetcode.hard.heap;

import com.leetcode.hard.utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ModifiedHeap<T extends Object> {
    Map<T, Integer> map;
    HeapModel[] heap;
    int size;
    public ModifiedHeap(int maxSize) {
        heap = new HeapModel[maxSize];
        this.size = maxSize;
    }

    public ModifiedHeap(HeapModel[] heapModels, int maxSize) {
        this.size = maxSize;
        heap = new HeapModel[maxSize];
        for(int i = 0; i < heapModels.length; i++) {
            this.heap[i] = heapModels[i];
        }
        size = heapModels.length;
        minHeap();
        initMap();

    }

    public void initMap() {
        map = new HashMap<>();
        for(int i = 0; i < size; i++) {
            map.put((T)heap[i].vertex, i);
        }
    }

    public void heapify(int i ) {
        int left = 2*i + 1;
        int right = 2*i + 2;

        int min = i;
        if( left >= 0 && left < size && heap[left].distance < heap[i].distance) {
            min = left;
        }

        if(right >= 0 && right < size && heap[right].distance < heap[min].distance) {
            min = right;
        }

        if( min != i) {
            Utils.swap(heap, i, min);
            map.put((T) heap[min].vertex, min);
            map.put((T) heap[i].vertex, i);
            heapify(min);
        }

    }

    public void addToHeap(HeapModel heapModel) {
        this.size++;
        heap[size -1] = heapModel;
        Utils.swap(heap, 0, size -1);
        heapify(0);
    }

    public HeapModel extractMin() {
        HeapModel temp = heap[0];
        map.remove((T)temp.vertex);
        heap[0] = heap[size -1];
        heap[size -1] = temp;
        this.size--;
        heapify(0);

        return temp;
    }

    int parent(int i) { return (i-1)/2; }

    public void decrease(T vertex, int value) {
        int index = map.getOrDefault(vertex, -1);
        heap[index].distance = value;
        do {
            if(heap[index].distance > heap[(index - 1)/2].distance) {
                return;
            }
            Utils.swap(heap, index, (index - 1) / 2);
            map.put((T) heap[index].vertex, index);
            index = (index - 1) / 2;
            map.put((T) heap[index].vertex, index);
        }while(index > 0);
    }

    public int findIndex(T vertex) {
        return map.getOrDefault(vertex, -1);
    }

    public void minHeap() {
        for(int i = (size - 1)/2; i >= 0; i--) {
            heapify(i);
        }
    }

    public Optional<HeapModel> search(T vertex) {
        int index = map.getOrDefault(vertex, -1);
        return index == -1 ?  Optional.empty() :  Optional.of(heap[index]);
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
