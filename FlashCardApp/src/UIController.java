import java.awt.*;

public class UIController {

    final static Color darkColour = new Color(220, 40, 80);
    final static Color midColour = new Color(245, 245, 245);
    final static Color lightColour = new Color(255, 255, 255);

   
    /**
     * Returns a GridBagConstraints object with: ipadx = 2 ipady = 2, weightx and weighty = 0.
     * And gridx, gridy, values specified by params
     *  
     * @param gridx the column of the component
     * @param gridy the row of the component
     * @return the GridBagConstraints object with certain values set
     */
    static public GridBagConstraints getGBC(int gridx, int gridy){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.ipadx = 3;
        gbc.ipady = 3;
        gbc.insets = new Insets(3,3,3,3);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    /**
     * Returns a GridBagConstraints object with: ipadx = 2 ipady = 2. 
     * And gridx, gridy, weightx and weighty values specified by params
     *  
     * @param gridx the column of the component
     * @param gridy the row of the component
     * @param weightx the weight of the component's column
     * @param weighty the weight of the component's row
     * @return the GridBagConstraints object with certain values set
     */
    static public GridBagConstraints getGBC(int gridx, int gridy, double weightx, double weighty){
        GridBagConstraints gbc = getGBC(gridx, gridy);
        gbc.weightx = weightx;
        gbc.weighty = weighty;

        return gbc;
    }

    static public Font getFont(int fontsize){
        return new Font("sans serif", Font.BOLD, fontsize);
    }
}
