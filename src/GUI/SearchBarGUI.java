package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SearchBarGUI extends JPanel {
    public SearchBarGUI() {
        setBackground(new Color(0x01000A));
        FlowLayout flowLayout =new FlowLayout(FlowLayout.LEFT);
        JTextField searchText=new JTextField("search...");
        searchText.setEnabled(true);
//        searchText.setSize(30,10);
        searchText.setBackground(Color.WHITE);
        searchText.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchText.setPreferredSize(new Dimension(200,30));
        add(searchText);
        JButton searchButton =new JButton();
        //searchButton.setIcon(new ImageIcon(getClass().getResource("loupe.png")));
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        add(searchButton);

        setLayout(flowLayout);
    }

}
