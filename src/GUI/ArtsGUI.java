package GUI;

import javax.swing.*;
import java.awt.*;

public class ArtsGUI extends JPanel{
    public ArtsGUI() {
        // super(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //setBackground(Color.GRAY);
        JPanel jp = new JPanel( ) ;
        jp.setLayout( new GridLayout( 1, 4 ) ) ;

        for(int i = 0 ; i < 4 ; i++)
            for( int j = 0 ; j < 1 ; j ++);
        //jp.add(new JButton("Button " + j));
        jp.setVisible(true);
    }
}       // setViewportView(jp);

