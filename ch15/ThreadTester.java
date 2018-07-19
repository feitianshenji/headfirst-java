//p494



class MyRunnable implements Runnable {

    public void run() { /*只有一个方法需要实现*/
        go();
    }

    public void go() {
        doMore();
    }

    public void doMore() {
        System.out.println("top o` the stack");
    }
}

class ThreadTester {
    public static void main(String[] args) {
        Runnable threadJob = new MyRunnable(); /*将Runnable的实例传给Thread的构造函数*/
        Thread myThread = new Thread(threadJob);
        myThread.start(); /*要调用start（）才会让线程开始执行。在此之前，它只是个Thread（）的实例，并不是真正的线程*/
        System.out.println("back in main");
    }
}
