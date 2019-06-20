package GUI;

import javax.swing.*;
import java.awt.*;

public class CenterGUI extends JPanel {
    SearchBarGUI searchBarGUI = new SearchBarGUI();
    ArtsGUI artsGUI = new ArtsGUI();
    public CenterGUI() {
        setLayout(new BorderLayout());
        add(searchBarGUI,BorderLayout.PAGE_START);
        add(artsGUI,BorderLayout.CENTER);
        //  setSize(600,500);
    }

}
