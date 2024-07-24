import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class EditCardPage extends JFrame{
    JFrame f;

    EditCardPage(){
        
        
        // Add-tag textfield and button
        JTextField tagTextField = new JTextField(10); 
        JButton addTagBtn = new JButton("Add Tag");
        JPanel addTagPanel = new JPanel();
        addTagPanel.setLayout(new BoxLayout(addTagPanel, BoxLayout.X_AXIS));
        addTagPanel.add(tagTextField);
        addTagPanel.add(addTagBtn);
        
        // scrollable tags list
        String[] tags = {"Storage", "Secondary Storage", "HDD", "Memory", "HDD", "Memory", "HDD", "Memory"};
        JList<String> tagList = new JList<String>(tags);
        JScrollPane tagListPane = new JScrollPane(tagList);
        
        // Tags Panel
        JPanel tagsPanel = new JPanel();
        
        tagsPanel.setLayout(new BoxLayout(tagsPanel, BoxLayout.Y_AXIS));
        tagsPanel.add(addTagPanel);
        tagsPanel.add(tagListPane);
        tagsPanel.setSize(new Dimension(100,100));
        //tagsPanel.setBounds(10,10, 300,200);
        add(tagsPanel, BorderLayout.CENTER);

        // set layout
        setSize(400, 400);
        setLayout(null);
        setVisible(true);

    }

}
