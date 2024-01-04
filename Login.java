import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Login extends JFrame {
    private JTextField usernameField;

    
    public Login() throws FontFormatException {
        try {
            // Thêm hình ảnh làm nền cho giao diện
            Image backgroundImage = ImageIO.read(new File("images\\12345.jpg"));
            setContentPane(new JLabel(new ImageIcon(backgroundImage)));
            setSize(backgroundImage.getWidth(null), backgroundImage.getHeight(null));
        } catch (IOException e) {
            e.printStackTrace();
        }
//Set Title
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(200, 200, 150, 30);
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setForeground(Color.WHITE);

        usernameField = new JTextField();
        usernameField.setBounds(350, 200, 150, 30);
        usernameField.setHorizontalAlignment(JTextField.CENTER);

        JButton loginButton = new JButton("Enter");
        loginButton.setBounds(300, 250, 100, 50);
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(Color.BLACK);

        try(InputStream is = new BufferedInputStream(Files.newInputStream(Paths.get("Silkscreen\\Silkscreen-Regular.ttf")))) {
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            usernameLabel.setFont(font.deriveFont(Font.BOLD, 18));
            usernameField.setFont(font.deriveFont(Font.BOLD, 14));
            loginButton.setFont(font.deriveFont(Font.BOLD, 18));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        // Add KeyListener to the usernameField to capture Enter key presses
        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });

        add(usernameLabel); 
        add(usernameField);
        add(loginButton);
        setVisible(true);
    }

    public void updateName(String name)
{
    PrintWriter out;
    try
    {
        out = new PrintWriter("highScores.txt");
        out.write(name+"\n");
        out.write("0");
        out.close();
    }
    catch(Exception e)
    {
    }
}

private void performLogin() {
    String username = usernameField.getText().trim(); // Trim to remove leading/trailing spaces
    username = Character.toUpperCase(username.charAt(0)) + username.substring(1);

    if (username.isEmpty()) {
        // Authenticate name, if username is not typed, remind user
        JOptionPane.showMessageDialog(Login.this, "Please enter your username!");
        usernameField.requestFocusInWindow();
    } else {

        // Authenticate name, if username is not typed, remind user
        updateName(username);
        JOptionPane.showMessageDialog(Login.this, "Logged in successfully! ");
        openGame();
        dispose();
    }
}
    private void openGame() {
        /* Main function simply creates a new pacman instance*/
        Pacman c = new Pacman();
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Login();
                } catch (FontFormatException e) {
                    e.printStackTrace();
                    // Handle the FontFormatException or log it appropriately
                }
            }
        });
    }
}

