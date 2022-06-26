import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JPanel implements ActionListener {
    int width = 300;
    int height = 300;
    int dotSize = 10;
    int totaldots = 900;
    int random = 29;
    int delay = 100;

    int x[] = new int[totaldots];
    int y[] = new int[totaldots];

     int dots;
     int food_cor_x;
     int food_cor_y;
     boolean leftDir = false;
     boolean rightDir = true;
     boolean upDir = false;
     boolean downDir = false;
     boolean inGame = true;
     Timer timer;
     Image body;
     Image food;
     Image mouth;

    public Game() {
        
        this.addKeyListener(new myKeyAdapter());
        this.setBackground(Color.white);
        this.setFocusable(true);
        ImageIcon iid = new ImageIcon("2.png");
        body = iid.getImage();
        ImageIcon iia = new ImageIcon("3.png");
        food = iia.getImage();
        ImageIcon iih = new ImageIcon("1.png");
        mouth = iih.getImage();
        dots=5;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        foodRandom();
        timer = new Timer(delay, this);
        timer.start();
    }
    private void foodRandom() {
        int r = (int) (Math.random() * random);
        food_cor_x = r * dotSize;
        r = (int) (Math.random() * random);
        food_cor_y = r * dotSize;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(food, food_cor_x, food_cor_y, this);
            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(mouth, x[z], y[z], this);
                } else {
                    g.drawImage(body, x[z], y[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } 
        else 
        {
            String msg = "OVER Game Ha Ha Ha";
            Font small = new Font("Helvetica", Font.BOLD, 20);
            FontMetrics metr = getFontMetrics(small);
            g.setColor(Color.red);
            g.setFont(small);
            g.drawString(msg, (width - metr.stringWidth(msg)) / 2, height / 2);    
            String msg1 = "Developed by Anand Bind";
            Font small1 = new Font("arial", Font.BOLD, 14);
            FontMetrics metr1 = getFontMetrics(small1);
            g.setColor(Color.blue);
            g.setFont(small1);
            g.drawString(msg1, (width - metr.stringWidth(msg1)) / 2, height -20);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkfood();
            checkCollision();
            move();
        }
        repaint();
    }
    private void checkfood() {
        if ((x[0] == food_cor_x) && (y[0] == food_cor_y)) {
            dots++;
            foodRandom();
        }
    }
    private void checkCollision() {
        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }
        if (y[0] >= height) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
        if (x[0] >= width) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        if (!inGame) {
            timer.stop();
        }
    }
     private void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        if (leftDir) {
            x[0] -= dotSize;
        }
        if (rightDir) {
            x[0] += dotSize;
        }
        if (upDir) {
            y[0] -= dotSize;
        }
        if (downDir) {
            y[0] += dotSize;
        }
    }
    private class myKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (!rightDir)) {
                leftDir = true;
                upDir = false;
                downDir = false;
            }
            if ((key == KeyEvent.VK_RIGHT) && (!leftDir)) {
                rightDir = true;
                upDir = false;
                downDir = false;
            }
            if ((key == KeyEvent.VK_UP) && (!downDir)) {
                upDir = true;
                rightDir = false;
                leftDir = false;
            }
            if ((key == KeyEvent.VK_DOWN) && (!upDir)) {
                downDir = true;
                rightDir = false;
                leftDir = false;
            }
        }
    }
}