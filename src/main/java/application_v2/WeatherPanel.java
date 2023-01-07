package application_v2;

public class WeatherPanel extends ContentPanel implements Runnable{
    private MainFrame frame;

    public WeatherPanel(MainFrame frame) {
        super(frame);
        this.frame = frame;
    }

    @Override
    void actionOnResizing(int width, int height) {

    }

    @Override
    public void run() {

    }
}
