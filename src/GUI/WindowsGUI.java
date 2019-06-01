package GUI;

import javax.swing.*;
import java.awt.*;

public class WindowsGUI extends JFrame {

    JPanel listGUI=new ListGUI();
    FriendsActivityGUI friendsActivityGUI=new FriendsActivityGUI();
    CenterGUI artsGUI=new CenterGUI();
    PlayerGUI playerGUI=new PlayerGUI();
    GridBagConstraints gbc=new GridBagConstraints();
    public WindowsGUI(){
        designLayout();

    }
    private void addJpanel(Component component,int gridy,int gridx,int width,int height){
        gbc.gridwidth = width;
        gbc.gridheight= height;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        getContentPane().add(component,gbc);
    }
    private void designLayout(){
        setLayout(new GridBagLayout());
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weighty=1;
        gbc.weightx=0.1;
        gbc.ipady=600;
        gbc.ipadx=150;
        addJpanel(listGUI,0,0,1,1);
        gbc.ipadx=850;
        gbc.weightx=1;
        addJpanel(artsGUI,0,1,1,1);
        gbc.ipadx=200;
        gbc.weightx=0;
        addJpanel(friendsActivityGUI,0,2,1,1);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.ipady=0;
        gbc.weightx=1;
        gbc.weighty=0;
        addJpanel(playerGUI,1,0,3,1);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
