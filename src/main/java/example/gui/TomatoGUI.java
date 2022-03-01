package example.gui;

import example.ExampleModTomato;

import javax.swing.*;
import java.awt.*;

/**
 * Example GUI for Tomato mod.
 */
public class TomatoGUI {
    private static JTextArea textArea;
    private static JLabel statusLabel;
    private static JFrame frame;
    private JScrollPane scroll;
    private JMenuBar jMenuBar;
    private JPanel mainPanel;
    private TomatoMenuBar menuBar;
    private Point center;
    private Image icon;

    /**
     * Create main panel and initializes the GUI for the example Tomato.
     */
    public void create() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        mainPanel = new JPanel();

        textArea = new JTextArea();
        textArea.setEnabled(true);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setAutoscrolls(true);
        new SmartScroller(scroll);

        center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        menuBar = new TomatoMenuBar();
        jMenuBar = menuBar.make();

        statusLabel = new JLabel(" Network Tap: OFF");
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        icon = Toolkit.getDefaultToolkit().getImage(ExampleModTomato.imagePath);
        makeFrame();
        frame.setVisible(true);
    }

    /**
     * Creates the frame with icon.
     */
    public void makeFrame() {
        frame = new JFrame("Tomato");
        frame.setIconImage(icon);
        frame.setLocation(center.x / 2, 25);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(jMenuBar);
        frame.setJMenuBar(jMenuBar);
        menuBar.setFrame(frame);
        frame.setContentPane(mainPanel);
    }

    /**
     * Add text to the text area.
     *
     * @param s The text to be added at the end of text area.
     */
    public static void appendTextAreaText(String s) {
        System.out.print(s);
        if (textArea != null) textArea.append(s);
    }

    /**
     * Updates the state of the sniffer at the bottom label to show if running or off.
     *
     * @param running Set the label to running or off.
     */
    public static void setStateOfSniffer(boolean running) {
        statusLabel.setText(" Network Tap: " + (running ? "RUNNING" : "OFF"));
    }
}