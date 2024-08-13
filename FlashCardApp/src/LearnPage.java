import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LearnPage extends JFrame{



    LearnPage(CardLayout cardLayout){


        JProgressBar progressBar = new JProgressBar(0, 30);
        progressBar.setValue(5);
        progressBar.setStringPainted(true);
        progressBar.setString(String.format("%1d of %2d", progressBar.getValue() ,progressBar.getMaximum()));

        add(progressBar, BorderLayout.PAGE_START);
        
        LearnCardPanel card = new LearnCardPanel();
        add(card, BorderLayout.CENTER);


        JButton nextButton = new JButton("Next");
        add(nextButton, BorderLayout.PAGE_END);

        


        // set layout
        setSize(400, 300);
        setVisible(true);

    }
}
