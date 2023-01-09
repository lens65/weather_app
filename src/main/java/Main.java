import application_v2.MainFrame;
import data.dao.JpaService;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                JpaService.getJpaService();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
