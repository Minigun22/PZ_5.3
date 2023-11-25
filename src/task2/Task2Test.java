package task2;

import java.util.Random;
import java.util.Stack;
import java.util.stream.Stream;

public class Task2Test {
    public static void main(String[] args) {
        BlockingStack<Integer> blockingStack = new BlockingStack<>(new Stack<>());
        Stream.of(
                new Thread(() -> {
                    for (int i = 0; i < 10; i++) {
                        blockingStack.push(new Random().nextInt(0, 10));
                    }
                }),
                new Thread(() -> {
                    for (int i = 0; i < 5; i++) {
                        blockingStack.pop();
                    }
                }),
                new Thread(() -> {
                    for (int i = 0; i < 5; i++) {
                        blockingStack.pop();
                    }
                })
        ).forEach(Thread::start);
    }
}
