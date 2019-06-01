package GUI;

import javax.swing.*;
import java.awt.*;

public class CenterGUI extends JPanel {
    UpSettingGUI upSettingGUI= new UpSettingGUI();
    ArtsGUI artsGUI = new ArtsGUI();
    public CenterGUI() {
        setLayout(new BorderLayout());
        add(upSettingGUI,BorderLayout.PAGE_START);
        add(artsGUI,BorderLayout.CENTER);
        //  setSize(600,500);
    }
}
