package task1;

import java.util.Queue;
import java.util.stream.IntStream;

public class Printer implements Runnable{
    private String name;
    private PrintServer printServer;
    public Printer(PrintServer printServer, int id){
        this.name = "Printer"+id;
        this.printServer = printServer;
    }
    @Override
    public void run() {
        IntStream.rangeClosed(1,10).forEach(x ->{
            try {
                this.doTask();
            }catch (InterruptedException e ){
                e.printStackTrace();
            }
        });
    }
    private void doTask() throws InterruptedException{
        synchronized (printServer){
            while (printServer.getQueue().size()==0){
                System.out.println(this.name + " is waiting");
                printServer.wait();
            }
            int num = printServer.getQueue().poll();
            System.out.println(this.name + " do " + num + " task");
            printServer.notifyAll();
        }
    }
}
