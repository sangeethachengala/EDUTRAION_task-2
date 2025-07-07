import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;

public class PasswordGeneratorApp extends JFrame {
    private JTextField lengthField;
    private JTextArea outputArea;
    private JButton generateButton;

    // Character set for password
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+<>?/|{}[]";
    private static final String ALL_CHARS = UPPER + LOWER + DIGITS + SYMBOLS;

    private SecureRandom random = new SecureRandom();

    public PasswordGeneratorApp() {
        setTitle("Password Generator");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Enter password length:"), gbc);

        // Length input
        gbc.gridx = 1;
        lengthField = new JTextField(10);
        add(lengthField, gbc);

        // Generate Button
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        generateButton = new JButton("Generate Password");
        add(generateButton, gbc);

        // Output area
        gbc.gridy = 2;
        outputArea = new JTextArea(3, 25);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, gbc);

        // Action on button click
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });

        setVisible(true);
    }

    // Generate random password
    private void generatePassword() {
        try {
            int length = Integer.parseInt(lengthField.getText());
            if (length <= 0) {
                outputArea.setText("Please enter a positive number.");
                return;
            }

            StringBuilder password = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(ALL_CHARS.length());
                password.append(ALL_CHARS.charAt(index));
            }

            outputArea.setText("Generated Password:\n" + password.toString());
        } catch (NumberFormatException ex) {
            outputArea.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        new PasswordGeneratorApp();
    }
}
