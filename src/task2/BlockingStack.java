package task2;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingStack<T> {
    private Stack<T> stack;
    private Lock locker;
    private Condition isFull;
    private Condition isEmpty;
    public static final int CAPACITY = 4;

    public BlockingStack(Stack<T> stack) {
        locker = new ReentrantLock();
        this.stack = stack;
        isFull = locker.newCondition();
        isEmpty = locker.newCondition();
    }

    public void push(T t) {
        try {
            locker.lock();
            while (stack.size() == CAPACITY) {
                isFull.await();
            }
            stack.push(t);
            System.out.printf(Thread.currentThread().getName() + " pie was produce, total count  %d\n", stack.size());
            isEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public T pop() {
        try {
            locker.lock();
            while (stack.size() < 1) {
                isEmpty.await();
            }
            System.out.printf(Thread.currentThread().getName() + " pie was consumed, total count %d\n", stack.size()-1);
            isFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
        return stack.pop();
    }
}
