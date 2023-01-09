package application_v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame{
    private JPanel framePanel;
    private ContentPanel contentPanel;
    private final Color panelBackground = new Color(32, 33, 36);

    public MainFrame(){
        setFrame();
        addFramePanel();
        setContentPanel(new MenuPanel(this));
        resizeComponents(this.getContentPane().getWidth(), this.getContentPane().getHeight());
    }

    private void setFrame(){
        this.setName("Погода");
        this.setLayout(null);
        this.setMinimumSize(new Dimension(800,600));
        this.setPreferredSize(new Dimension(800,600));
        this.setMaximumSize(new Dimension(1920,1080));
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JFrame c = (JFrame) e.getSource();
                resizeComponents(c.getContentPane().getWidth(), c.getContentPane().getHeight());
            }
        });
    }

    private void addFramePanel(){
        framePanel = new JPanel();
        framePanel.setLayout(null);
        framePanel.setBackground(panelBackground);
        framePanel.setLocation(0,0);
        this.add(framePanel);
    }

    public void setContentPanel(ContentPanel contentPanel){
        if(this.contentPanel != null) framePanel.remove(this.contentPanel);
        this.repaint();
        this.contentPanel = contentPanel;
        framePanel.add(contentPanel);
        resizeComponents(this.getContentPane().getWidth(), this.getContentPane().getHeight());
    }

    private void resizeComponents(int width, int height){
        if(framePanel != null)framePanel.setSize(width, height);
        if(contentPanel != null) contentPanel.actionOnResizing(width, height);
    }
}
