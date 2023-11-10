package pl;

import bll.TokenizerBO;
import dal.DataLayerTokenizer;
import dal.TokenizerDAL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PL_Tokenizer {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            DataLayerTokenizer dataLayerTokenizer = new DataLayerTokenizer();
            TokenizerDAL DataLayerTokenizer;
			TokenizerBO tokenizerBO = new TokenizerBO((TokenizerDAL) dataLayerTokenizer);

            JFrame frame = new JFrame("Tokenizer Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            JLabel label = new JLabel("Enter a verse to tokenize:");
            JTextField textField = new JTextField(20);
            JButton tokenizeButton = new JButton("Tokenize");
            JTextArea resultArea = new JTextArea(10, 20);
            resultArea.setEditable(false);

            tokenizeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String verse = textField.getText();
                    try {
                        tokenizerBO.tokenizeVerse(verse);
                        resultArea.setText("Tokens saved to the database.");
                    } catch (Exception exception) {
                        resultArea.setText("Error: " + exception.getMessage());
                    }
                }
            });

            panel.add(label);
            panel.add(textField);
            panel.add(tokenizeButton);
            panel.add(resultArea);

            frame.getContentPane().add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
