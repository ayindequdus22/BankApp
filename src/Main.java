
import javax.swing.*;

import gui.LaunchPage;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LaunchPage();
            }
        });
    }
}