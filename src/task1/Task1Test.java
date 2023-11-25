package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;

public class Task1Test {
    public static void main(String[] args) {
        PrintServer printServer = new PrintServer();
        Stream.of(
                new Thread(new User(printServer, 1)),
                new Thread(new User(printServer, 2)),
                new Thread(new User(printServer, 3)),
                new Thread(new User(printServer, 4)),
                new Thread(new Printer(printServer, 1)),
                new Thread(new Printer(printServer, 2))
        ).forEach(Thread::start);
    }
}
