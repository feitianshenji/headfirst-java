//p503



public class RunThreads implements Runnable {
    public static void main(String[] args) {
        RunThreads runner = new RunThreads(); /*创建runnable实例*/
        Thread alpha = new Thread(runner); /*创建两个线程，使用相同的Runnable*/
        Thread beta = new Thread(runner);
        alpha.setName("Alpha thread"); /*帮线程取名字*/
        beta.setName("Beta thread");
        alpha.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        beta.start();
    }

    public void run() {
        for (int i=0; i<25; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " is running");
        }
    }
}
