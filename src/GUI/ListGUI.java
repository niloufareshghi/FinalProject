package GUI;

import javax.swing.*;
import java.awt.*;

public class ListGUI extends JPanel {
    public ListGUI() {
        //setSize(w,h);
        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc =new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.gridwidth = 1;
        gbc.gridheight= 1;
        gbc.gridy = 0;
        gbc.gridx = 0;
        JPanel j=new JPanel();
        j.setBackground(new Color(0xC9000B));
        add(j,gbc);
        gbc.gridy =1;
        gbc.gridx = 0;
        gbc.weighty=0.5;
        gbc.gridheight=1;
        gbc.gridheight= 1;
        add(new JPanel(),gbc);
    }
}
