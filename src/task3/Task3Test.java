package task3;

import java.util.concurrent.TimeUnit;

public class Task3Test {
    public static void main(String[] args) throws InterruptedException {
        Thread threadForBlocking = new Thread(() ->  {
            try {
                TimeUnit.SECONDS.sleep(1);
                doJob();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread mainThread = Thread.currentThread();
        Thread slave = new Thread(() ->  {
            try {
                doJob();
                TimeUnit.SECONDS.sleep(1);
                mainThread.join();
                showThreadStatus(Thread.currentThread());
                TimeUnit.SECONDS.sleep(1);
                showThreadStatus(mainThread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        showThreadStatus(slave);
        slave.start();
        threadForBlocking.start();
        TimeUnit.SECONDS.sleep(3);
        showThreadStatus(threadForBlocking);
        TimeUnit.SECONDS.sleep(2);
        showThreadStatus(slave);
        TimeUnit.SECONDS.sleep(2);
        showThreadStatus(slave);
    }
    private static void showThreadStatus(Thread thread){
        System.out.printf("%s : %s\n",thread.getName(),thread.getState());
    }
    private static synchronized void doJob() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
    }
}
