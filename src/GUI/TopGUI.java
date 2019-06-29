package GUI;

import Controller.Controller;
import Logic.SongInfo;
import client.FileClient;
import client.PlayListClient;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class TopGUI extends JPanel {
    JTextField searchText=new JTextField("search...");
    JMenuBar mb;
    JMenu x;
    JMenuItem addBtn;
    public TopGUI() {
        setBackground(new Color(0x01000A));
        FlowLayout flowLayout =new FlowLayout(FlowLayout.LEFT);
        x = new JMenu("IP LIST");
        addBtn = new JMenuItem("add IP");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField ip = new JTextField();

                final JComponent[] inputs = new JComponent[] {
                        new JLabel("IP"),
                        ip,
                };
                int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    JMenuItem IP = new JMenuItem(ip.getText());
                    IP.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            PlayListClient client = new PlayListClient(ip.getText(),5555);
                            client.start();
                            JFrame frame = new JFrame();
                            frame.setSize(new Dimension(1000,500));
                            frame.setLayout(new FlowLayout());
                            frame.setVisible(true);
                            frame.setEnabled(true);
                            for(String x : client.getSongs()){
                                JButton song = new JButton(x);
                                frame.add(song);
                                song.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        SongInfo songInfo = null;
                                        try {
                                            songInfo = new SongInfo(song.getText());
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        } catch (UnsupportedTagException e1) {
                                            e1.printStackTrace();
                                        } catch (InvalidDataException e1) {
                                            e1.printStackTrace();
                                        }
                                        FileClient client1 = new FileClient(ip.getText(),5058, songInfo);
                                        try {
                                            client1.download();
                                        } catch (InvalidDataException e1) {
                                            e1.printStackTrace();
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        } catch (UnsupportedTagException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }
                    });
                    x.add(IP);
                } else {
                    System.out.println("User canceled / closed the dialog, result = " + result);
                }
            }
        });
        x.add(addBtn);
        x.setBackground(Color.WHITE);
        x.setAlignmentX(Component.RIGHT_ALIGNMENT);
        mb = new JMenuBar();
        mb.add(x);
        searchText.setEnabled(true);
//        searchText.setSize(30,10);
        searchText.setBackground(Color.WHITE);
        searchText.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchText.setPreferredSize(new Dimension(200,30));
        add(searchText);
        JButton searchButton =new JButton();
        searchButton.setIcon(new ImageIcon(getClass().getResource("loupe.png")));
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        add(searchButton);
        add(mb);
        setAction(searchButton);
        setLayout(flowLayout);
        JButton Username=new JButton(Controller.getUsername());
        add(Username);

    }


    private void setAction(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getWindowsGUI().getArtsGUI().getMainGUI().setSongsList(search());
            }
        });
    }
    private ArrayList search(){
        ArrayList<SongInfo> songsOfsearch=new ArrayList<>();
        for(SongInfo s: Controller.getAllSongs()){
            if(s.getTitle().indexOf(searchText.getText())>=0
                    || s.getAlbum().indexOf(searchText.getText())>=0
                    || s.getArtist().indexOf(searchText.getText())>=0)
            {
                songsOfsearch.add(s);
            }
        }
        return songsOfsearch;
    }



}