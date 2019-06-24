package GUI;

import javax.swing.*;
import java.awt.*;

public class CenterGUI extends JPanel {
    SearchBarGUI searchBarGUI = new SearchBarGUI();
    MainGUI mainGUI = new MainGUI();
    public CenterGUI() {
        setLayout(new BorderLayout());
        add(searchBarGUI,BorderLayout.PAGE_START);
        add(mainGUI,BorderLayout.CENTER);
        //  setSize(600,500);
    }

    public MainGUI getMainGUI() {
        return mainGUI;
    }

    public SearchBarGUI getSearchBarGUI() {
        return searchBarGUI;
    }
}

