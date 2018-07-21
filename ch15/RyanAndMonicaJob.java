//p507



class BankAccount {
    private int balance = 100; /*账户一开始有100元*/

    public int getBalance() {
        return balance;
    }
    public void withdraw(int amount) {
        balance = balance - amount;
    }
}

public class RyanAndMonicaJob implements Runnable {

    private BankAccount account = new BankAccount(); /*只有一个RyanAndMonicaJob的实例，代表只有一个共享的账户*/

    public static void main(String[] args) {
        RyanAndMonicaJob theJob = new RyanAndMonicaJob(); /*将任务初始化*/
        Thread one = new Thread(theJob); /*创建出使用相同任务的两个线程，这代表两个线程都会存取同一个账户*/
        Thread two = new Thread(theJob);
        one.setName("Ryan");
        two.setName("Monica");
        one.start();
        two.start();
    }

    public void run() {
        for (int x=0; x<10; x++) { /*检查账户余额，如果透支就列出信息，不然就去睡一会儿，然后醒来完成提款操作*/
            makeWithdrawal(10);
            if (account.getBalance() < 0) {
                System.out.println("Overdrawn!");
            }
        }
    }

    private void makeWithdrawal(int amount) {
        if (account.getBalance() >= amount) { /*检查账户余额，如果透支就列出信息，不然就去睡一会儿，然后醒来完成提款操作*/
            System.out.println(Thread.currentThread().getName() + " is about to withdraw");
            try {
                System.out.println(Thread.currentThread().getName() + " is going to sleep");
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " work up. ");
            account.withdraw(amount);
            System.out.println(Thread.currentThread().getName() + " completes the withdrawl");
        } else {
            System.out.println("Sorry, not enough for " + Thread.currentThread().getName());
        }
    }
}
