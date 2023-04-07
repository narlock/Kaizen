package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class FadingLabel extends JLabel {
    private static final long serialVersionUID = 1L;
    private Timer timer;
    private int duration;
    private int alpha = 255;

    public FadingLabel(String text, int duration) {
        super(text);
        this.duration = duration;
        setForeground(Color.GREEN);
    }

    public void fade() {
        setForeground(Color.GREEN); // Reset color to green
        alpha = 255; // Reset alpha to 255
        if (timer != null) {
            timer.stop(); // Stop the timer if it's already running
        }
        timer = new Timer(duration / 100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha -= 5;
                if (alpha < 0) {
                    alpha = 0;
                    timer.stop();
                }
                setForeground(new Color(0, alpha, 0));
            }
        });
        timer.start();
    }
}

