package task1;

import java.util.PriorityQueue;
import java.util.Queue;

public class PrintServer {
    private Queue<Integer> queue;

    public PrintServer() {
        this.queue = new PriorityQueue<>();
    }
    public PrintServer(Queue<Integer> queue){
        this.queue = queue;
    }

    public Queue<Integer> getQueue() {
        return queue;
    }
}
