
import java.awt.*;

import javax.swing.*;

public class EditCardPage extends JFrame{
    JFrame f;

    EditCardPage(CardLayout cardLayout){

        setLayout(new GridBagLayout());

        // side panel
        EditCardPropertiesPanel sidePanel = new EditCardPropertiesPanel();

        // cards panel
        EditCardContentPanel cardsPanel = new EditCardContentPanel();
        
        // // cards panel gbc
        // GridBagConstraints cardsPanelGBC = UIController.getGBC(1,0,1,1);
        // cardsPanelGBC.fill = GridBagConstraints.BOTH;
        // cardsPanelGBC.gridheight = 9;


        JTabbedPane tabbedPane = new JTabbedPane();
        GridBagConstraints gbc = UIController.getGBC(0,0, 1, 1);
        gbc.fill = GridBagConstraints.BOTH;

        tabbedPane.add("Properties", sidePanel);
        tabbedPane.add("Content", cardsPanel);
        
        add(tabbedPane, gbc);


        // set layout
        setSize(400, 300);
        setVisible(true);

    }

}
