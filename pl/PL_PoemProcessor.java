package pl;
import bll.PoemProcessorBO;
import dal.DataLayerAssignRoots;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PL_PoemProcessor {

    private PoemProcessorBO poemProcessorBO;

    private JFrame frame;
    private JTextArea verseTextArea;
    private JTextArea rootTextArea;

    public PL_PoemProcessor() {
        this.poemProcessorBO = new PoemProcessorBO(new DataLayerAssignRoots());
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Poem Processor App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        JLabel verseLabel = new JLabel("Enter Verse:");
        verseTextArea = new JTextArea();
        verseTextArea.setLineWrap(true);
        JScrollPane verseScrollPane = new JScrollPane(verseTextArea);

        JLabel rootLabel = new JLabel("Enter Roots (comma-separated):");
        rootTextArea = new JTextArea();
        rootTextArea.setLineWrap(true);
        JScrollPane rootScrollPane = new JScrollPane(rootTextArea);

        JButton addButton = new JButton("Add Verse and Assign Roots");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVerseAndAssignRoots();
            }
        });

        mainPanel.add(verseLabel);
        mainPanel.add(verseScrollPane);
        mainPanel.add(rootLabel);
        mainPanel.add(rootScrollPane);
        mainPanel.add(addButton);

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
    }

    private void addVerseAndAssignRoots() {
        String verse = verseTextArea.getText();
        String rootInput = rootTextArea.getText();

        // Split roots by commas and trim spaces
        String[] rootsArray = rootInput.split(",");
        List<String> roots = new ArrayList<>();
        for (String root : rootsArray) {
            roots.add(root.trim());
        }

        List<String> verses = new ArrayList<>();
        verses.add(verse);

        poemProcessorBO.addVerse(verses, verse);
        poemProcessorBO.assignRootsToVerses(verses, roots);
        verseTextArea.setText("");
        rootTextArea.setText("");
    }

    public void display() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
    	PL_PoemProcessor poemProcessorApp = new PL_PoemProcessor();
        poemProcessorApp.display();
    }
}
