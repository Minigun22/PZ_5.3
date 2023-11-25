package task1;

import java.util.Random;
import java.util.stream.IntStream;

public class User implements Runnable {
    private String name;
    private PrintServer printServer;

    public static final int RANGE_OF_STEAM_TEST = 10;

    public User(PrintServer printServer, int id) {
        this.name = "User" + id;
        this.printServer = printServer;
    }

    @Override
    public void run() {
        IntStream.rangeClosed(1, RANGE_OF_STEAM_TEST).forEach(x -> {
            try {
                this.sendTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void sendTask() throws InterruptedException {
        synchronized (printServer) {
            int sumAllTask = printServer.getQueue().stream().mapToInt(Integer::intValue).sum();
            while (sumAllTask == 10) {
                System.out.println(this.name + " is waiting");
                printServer.wait();
            }
            int num = new Random().nextInt(2, 7);
            if(sumAllTask + num <= 10){
                printServer.getQueue().add(num);
                System.out.println(this.name + " send " + num + " task");
            }
            printServer.notifyAll();
        }
    }
}
