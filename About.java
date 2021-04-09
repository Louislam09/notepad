import java.awt.Image;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

public class About extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    JButton btn;

    
    public About(){
        setBounds(400, 100, 400, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("img/me.jpg"));
        Image image2 = image.getImage().getScaledInstance(260, 260, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel imageLabel = new JLabel(image3);
        imageLabel.setBounds(62, 10, 260, 260);
        add(imageLabel);

        String aboutMe = "<html>"
        +"Nombre: Luis Alejandro Martinez Magallanes <br>"
        +"Matricula: 2019-7725 <br>"
        +"Profesor: Luis Soto <br>"
        +"Asignatura: Programacion 1 (java) <br>"
        +"Proyecto: Segundo Parcial <br>"
        +"</html>";

        JLabel descriptionLabel = new JLabel(aboutMe);
        descriptionLabel.setBounds(10, 200, 500, 300);
        descriptionLabel.setFont(new Font("SAN SERIF",Font.PLAIN, 18));
        add(descriptionLabel);

        btn = new JButton("OK");
        btn.setBounds(290, 430, 80, 25);
        btn.addActionListener(this);
        add(btn);
    }

    public static void main(String[] args) {
        new About().setVisible(true);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);        
    }
}
