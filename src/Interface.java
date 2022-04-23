import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.event.*;

public class Interface {
    public JFrame frame = new JFrame("Java Cryptography");

    public void start() {
        frame.setVisible(true);
    }

    public Interface() {

        frame.setSize(260, 125);
        frame.setResizable(false);

        JPanel encode_panel = new JPanel();
        JLabel key_label = new JLabel("Key: ");
        JTextField key = new JTextField("ABCDEFGHI");
        JLabel message_label = new JLabel("Input: ");
        JTextField message = new JTextField("HELLO WORLD");
        JButton encode_button = new JButton("Encode");
        JButton decode_button = new JButton("Decode");
        JLabel output = new JLabel("");

        encode_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                output.setText(Encrypter.encrypt(message.getText(), new Key(key.getText())));
            }
        });

        decode_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                output.setText(Encrypter.decrypt(message.getText(), new Key(key.getText())));
            }
        });

        encode_panel.add(key_label);
        encode_panel.add(key);
        encode_panel.add(message_label);
        encode_panel.add(message);
        encode_panel.add(encode_button);
        encode_panel.add(decode_button);
        encode_panel.add(output);

        frame.getContentPane().add(encode_panel);
    }
}