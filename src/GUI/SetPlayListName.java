package GUI;

import Logic.PlayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class SetPlayListName extends JFrame {
    public SetPlayListName(PlayList playList){
        setVisible(true);
        setSize(200,100);
        setLayout(new FlowLayout());
        JTextField name=new JTextField(playList.getName());
        name.setSize(100,30);
        JButton submit =new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playList.setName(name.getText());
                System.out.println(playList.getName());
                setVisible(false);
            }
        });
        add(name);
        add(submit);
    }
}
