import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LearnPage extends JFrame{



    LearnPage(CardLayout cardLayout){
        setLayout(new GridBagLayout());

        // quit button
        JButton quitButton = new JButton("Quit");

        GridBagConstraints quitGBC = UIController.getGBC(0, 1);
        quitGBC.anchor = GridBagConstraints.LINE_START;
        quitGBC.fill = GridBagConstraints.NONE;
        add(quitButton, quitGBC);
        

        // progress bar
        JProgressBar progressBar = new JProgressBar(0, 30);
        progressBar.setValue(5);
        progressBar.setStringPainted(true);
        progressBar.setString(String.format("%1d of %2d", progressBar.getValue() ,progressBar.getMaximum()));

        GridBagConstraints barGBC = UIController.getGBC(0, 1);
        add(progressBar, barGBC);
        
        // learn card panel
        LearnCardPanel card = new LearnCardPanel();

        GridBagConstraints cardGBC = UIController.getGBC(0, 2, 1, 1);
        cardGBC.fill = GridBagConstraints.BOTH;
        add(card, cardGBC);



        // next button
        JButton nextButton = new JButton("Next");
        GridBagConstraints nextGBC = UIController.getGBC(0, 3);
        add(nextButton, nextGBC);

        


        // set layout
        setSize(400, 300);
        setVisible(true);

    }


    // load current Card
        // load card: Controller.getCurrentCard()
        // update UI
        // update ProgressBar

        // if current card = final card
            // change Next button to Finish 

    // next button clicked
        // r = selected rating value 
        // controller.updateCurrentCardRating(r)

        // if not controller.deckCompleted:
            // controller.nextCard
            // this.loadCurrentCard()
        //else
            // controller.goHome()

}
