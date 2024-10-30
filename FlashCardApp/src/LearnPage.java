import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LearnPage extends JPanel{

    private Controller controller;

    private JProgressBar progressBar;
    private LearnCardPanel card;

    private JButton nextButton;

    LearnPage(Controller c){
        controller = c;
        
        setLayout(new GridBagLayout());

        // quit button
        JButton quitButton = new JButton("Quit");

        GridBagConstraints quitGBC = Controller.getGBC(0, 1);
        quitGBC.anchor = GridBagConstraints.LINE_START;
        quitGBC.fill = GridBagConstraints.NONE;
        add(quitButton, quitGBC);
        
        // quit button click action listener
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                controller.switchToHome();
            }
        });
        

        // progress bar
        progressBar = new JProgressBar(0, 30);
        progressBar.setStringPainted(true);

        GridBagConstraints barGBC = Controller.getGBC(0, 1);
        add(progressBar, barGBC);
        
        // learn card panel
        card = new LearnCardPanel();

        GridBagConstraints cardGBC = Controller.getGBC(0, 2, 1, 1);
        cardGBC.fill = GridBagConstraints.BOTH;
        add(card, cardGBC);

        

        // next button
        nextButton = new JButton("Next");
        GridBagConstraints nextGBC = Controller.getGBC(0, 3);
        add(nextButton, nextGBC);

        nextButton.addActionListener(nextClickedListener);


        // set layout
        setSize(400, 300);
        setVisible(true);


    }

    public void loadCurrentCard(){

        controller.loadCurrentCardProperties();

        if (controller.getCurrentIndex() == controller.getCurrentCardsLength() -1){
            nextButton.setText("Finish");
        }
        else{
            nextButton.setText("Next");
        }


        Card c = controller.getCurrentCard();
        card.setValues(c.getTitle(), c.getFront(), c.getBack(), c.getRating());
        
        progressBar.setValue(controller.getCurrentIndex()+1);
        progressBar.setMaximum(controller.getCurrentCardsLength());
        progressBar.setString(String.format("%1d of %2d", progressBar.getValue() ,progressBar.getMaximum()));

    }

    private ActionListener nextClickedListener = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            // update card rating
            controller.updateCurrentCardRating(card.getSelectedRating());

            if (nextButton.getText() == "Finish"){
                controller.switchToHome();
            }
            else{
                
                controller.nextCardIndex();
                loadCurrentCard();
                
            }
            
        }
    };

}
