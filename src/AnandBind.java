import javax.swing.JFrame;

public class AnandBind extends JFrame {


    public AnandBind() {
        
        this.setSize(330,350);
        this.add(new Game());     
        this.setResizable(false);        
        this.setTitle("Snake develop.AnandBind");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    public static void main(String[] args) {
        AnandBind snake=new AnandBind();
            snake.setVisible(true);
      
    }
}