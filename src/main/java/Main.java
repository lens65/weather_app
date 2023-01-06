import application.FirstWindow;
import data.dao.JpaService;


public class Main {
    public static void main(String[] args) {
        JpaService.getJpaService();
        FirstWindow fw = new FirstWindow();
    }
}
