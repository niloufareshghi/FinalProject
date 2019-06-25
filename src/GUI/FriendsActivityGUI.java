package GUI;

import javax.swing.*;
import java.awt.*;

public class FriendsActivityGUI extends JPanel {
    BoxLayout friends;
    Panel p;

    public FriendsActivityGUI() {

        setBackground(Color.DARK_GRAY);
        this.setBorder(BorderFactory.createTitledBorder("<HTML><BODY><FONT face=\"Ariel\" size=\"6\"><B><font color = 'white'>Friends Activity</B></FONT></BODY></HTML>"));
        addFirends("Friend 1", "Rahro");
    }

    public void addFirends(String name , String lastSong){
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        Font f = new Font("serif", Font.PLAIN, 20);


        JLabel c1 = new JLabel(name);
        c1.setForeground(Color.LIGHT_GRAY);
        c1.setFont(f);
        JButton c2 = new JButton(lastSong);
        c2.setFont(f);
        c2.setForeground(Color.PINK);
        c2.setContentAreaFilled(false);
        c2.setOpaque(false);
        c2.setMargin(new Insets(1,1,1,1));

        layout.setHorizontalGroup( layout.createParallelGroup()
                .addComponent( c1 )
                .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(c1, c2, LayoutStyle.ComponentPlacement.INDENT)
                        .addComponent( c2 )
                )
        );
        layout.setVerticalGroup( layout.createSequentialGroup()
                .addComponent( c1 )
                .addComponent( c2 )
        );



    }

}
