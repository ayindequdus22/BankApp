package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage extends JFrame {
        private  ImageIcon image;
        //Note: This is the initial size of my image
        private int imageSize = 50;
        private Timer timer;
    public LaunchPage() {
        image = new ImageIcon("src/images/logo2.jpg");
        setResizable(false);
        setSize(400, 700);
        setVisible(true);
        setLocation(500,20);
        setTitle("Bank App");
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Note: I started a timer to gradually increase the size of the image
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Note: I increased the image size by 10 each second
                imageSize += 10;
                if (imageSize >= 300){
                    //Note: If the image size gets to 300, stop the timer
                    timer.stop();
                    navigateToNextPage();
                }
                // Note: Since the size of the image is being changed over time, calling repaint() ensures that the frame is updated to reflect
                // these changes in the image size.
                repaint();
            }
        });
        timer.start();
    }
    //Note: Invoked a method that redraws the frames content including the image
    @Override
    public void paint(Graphics g){
        super.paint(g);
        //Note: Here i calculated the position to center the image
        int x = (getWidth() - imageSize) / 2;
        int y = (getHeight() - imageSize) / 2;
        //Note: Here, i drew the image at the calculated position with the current size
        g.drawImage(image.getImage(), x,y,imageSize,imageSize,null);
    }
    //Note: I am navigating to the next page here
    private void navigateToNextPage(){
        //Note:
        dispose();
        new LandingPage();
    }
}
