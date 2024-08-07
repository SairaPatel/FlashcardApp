import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame{



    HomePage(){

        setLayout(new GridBagLayout());


        // Sets label
        JLabel setsLabel = new JLabel("Sets:");

        GridBagConstraints setsLabelGBC = UIController.getGBC(0, 0);
        add(setsLabel, setsLabelGBC);
        
        // sets input
        String[] sets = {"Maths", "WebDev", "Func prog", "Architecture", "intro to programming", "Maths 2", "info structures"};
        JList<String> setsList = new JList<String>(sets);      
        JScrollPane setsListPanel = new JScrollPane(setsList);  

        GridBagConstraints setsGBC = UIController.getGBC(0, 1, 1, 0.5);
        setsGBC.fill = GridBagConstraints.BOTH;
        add(setsListPanel, setsGBC);


        // Tags label
        JLabel tagsLabel = new JLabel("Tags:");

        GridBagConstraints tagsLabelGBC = UIController.getGBC(1, 0);
        add(tagsLabel, tagsLabelGBC);
        
        // tags input
        String[] tags = {"Memory", "Hardware", "Software", "Security", "Logic", "Graphs", "Trees", "Functions"};
        JList<String> tagsList = new JList<String>(tags);      
        JScrollPane tagsListPanel = new JScrollPane(tagsList);  

        GridBagConstraints tagsGBC = UIController.getGBC(1, 1, 1, 0.5);
        tagsGBC.fill = GridBagConstraints.BOTH;
        add(tagsListPanel, tagsGBC);

        

        // keyword label
        JLabel keywordLabel = new JLabel("Keyword:");

        GridBagConstraints keywordLabelGBC = UIController.getGBC(0, 2);
        add(keywordLabel, keywordLabelGBC);
        
        // keyword input
        JTextField keywordInput = new JTextField();        

        GridBagConstraints keywordInputGBC = UIController.getGBC(0, 3, 1,0);
        keywordInputGBC.gridwidth = 2;
        add(keywordInput, keywordInputGBC);

        // clear button
        JButton clearButton = new JButton("Clear Filters");        

        GridBagConstraints clearButtonGBC = UIController.getGBC(0, 4, 1,0);
        clearButtonGBC.gridwidth = 2;
        add(clearButton, clearButtonGBC);


        // table
        String[][] data = {
            {"Web Dev", "HTML basics"},
            {"Web Dev", "Usability"},
            {"Web Dev", "JQuery"},
            {"Programming", "Recursion"},
            {"Programming", "Generics"},
            {"Programming", "Inheritance"},
            {"Programming", "Interfaces"},
            {"Architecture", "Logic Gates"},
            {"Architecture", "Sequential logic"},
            {"Architecture", "IO"},
            {"Architecture", "FE Cycle"}
        };
        String[] columnNames = {"Set", "Card Title"};
        JTable table = new JTable(data, columnNames);
        
        GridBagConstraints tableGBC = UIController.getGBC(0, 5, 1, 1);
        tableGBC.fill = GridBagConstraints.BOTH;
        tableGBC.gridwidth = 2;
        add(table, tableGBC);

        // study button
        JButton studyButton = new JButton("Study Selected Cards Only");        

        GridBagConstraints studyButtonGBC = UIController.getGBC(0, 6, 1,0);
        studyButtonGBC.gridwidth = 2;
        add(studyButton, studyButtonGBC);

        // studyAll button
        JButton studyAllButton = new JButton("Study All Displayed Cards");        

        GridBagConstraints studyAllButtonGBC = UIController.getGBC(0, 7, 1,0);
        studyAllButtonGBC.gridwidth = 2;
        add(studyAllButton, studyAllButtonGBC);

        // set layout
        setSize(400, 300);
        setVisible(true);

    }
}
