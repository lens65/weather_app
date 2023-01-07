package application_v2;

import javax.swing.*;

public abstract class ContentPanel extends JPanel {
    public ContentPanel(MainFrame frame){}
    abstract void actionOnResizing(int width, int height);
}
