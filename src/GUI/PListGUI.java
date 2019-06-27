package GUI;

import Controller.Controller;
import Logic.PlayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;

public class PListGUI extends JPanel implements ActionListener {
    private Font headFont = new Font("head", Font.BOLD, 20);
    private Font BF = new Font("ButtonsFont1", Font.TYPE1_FONT, 15);
    GridBagConstraints gbc = new GridBagConstraints();
    JPanel header = new JPanel();
    JPanel libPanel = new JPanel();
    JPanel PLPanel = new JPanel();
    public PListGUI() {
        setLayout(new GridBagLayout());
        setHeadP();
        add(header, gbc);
        setLibP();
        add(libPanel, gbc);
        setPLPanel();
        JScrollPane scrollPane =new JScrollPane(PLPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(40,60));//vaghean kheily moheme
        add(scrollPane, gbc);

    }

    private void setHeadP() {
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridy = 0;
        gbc.gridx = 0;
        header.setBackground(Color.black);
        FlowLayout fLayout = new FlowLayout(FlowLayout.LEFT);
        header.setLayout(fLayout);
        JLabel jpotify = new JLabel("jpotify        ");
        jpotify.setFont(headFont);
        jpotify.setIcon(new ImageIcon(getClass().getResource("jpotify.png")));
        jpotify.setForeground(Color.WHITE);
        header.add(jpotify);
    }

    private void setLibP() {

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        gbc.gridx = 0;
         gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.gridheight = 1;
        gbc.gridheight = 1;
        libPanel.setBackground(new Color(0xFC070005, true));
        libPanel.setLayout(new BoxLayout(libPanel, BoxLayout.Y_AXIS));
        JLabel library = new JLabel("LIBRARY           ");
        library.setAlignmentX(Component.CENTER_ALIGNMENT);
        library.setIcon(new ImageIcon(getClass().getResource("lib.png")));
        library.setFont(headFont);
        library.setForeground(Color.RED);
        libPanel.add(library);
        JButton BSong = new JButton("Songs");
        BSong.setIcon(new ImageIcon(getClass().getResource("speaker.png")));
        BSong.setAlignmentX(Component.CENTER_ALIGNMENT);
        setButtonsShape(BSong);
        BSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.songsStatus();
            }
        });
        libPanel.add(BSong);
        JButton BAlbums = new JButton("Albums");
        BAlbums.setIcon(new ImageIcon(getClass().getResource("Album.png")));
        BAlbums.setAlignmentX(Component.CENTER_ALIGNMENT);
        setButtonsShape(BAlbums);
        BAlbums.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.albumsStatus();
            }
        });
        libPanel.add(BAlbums);
        JButton BMovies = new JButton("Movies");
        BMovies.setIcon(new ImageIcon(getClass().getResource("movie.png")));
        BMovies.setAlignmentX(Component.CENTER_ALIGNMENT);
        setButtonsShape(BMovies);
        libPanel.add(BMovies);
    }

    private void setPLPanel() {
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weighty = 6;
        gbc.weightx = 1;
        gbc.gridheight = 1;
        gbc.gridheight = 1;
        PLPanel.setBackground(Color.BLACK);
        PLPanel.setLayout(new BoxLayout(PLPanel, 1));
        setUpdatePlPanel();
    }
    public void setUpdatePlPanel(){
        PLPanel.removeAll();
        JLabel playListLabel = new JLabel("PLAYLIST           ");
        playListLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playListLabel.setIcon(new ImageIcon(getClass().getResource("PL.png")));
        playListLabel.setFont(headFont);
        playListLabel.setForeground(Color.RED);
        PLPanel.add(playListLabel);
        JButton addNewPL=new JButton("+Add New Playlist");
        setButtonsShape(addNewPL);
        addNewPL.setAlignmentX(Component.RIGHT_ALIGNMENT);
        addNewPL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayList playList =new PlayList("New PlayList");

                for(int i =0; Controller.getRepository().getLists().contains(playList);i++) {
                    System.out.println(playList.getName() + " != " );
                    playList.setName("New PlayList(" + i + ")");
                }
//                SetPlayListName setPlayListName = new SetPlayListName(playList);
                do {
                    playList.setName(JOptionPane.showInputDialog("Enter Name of PlayList: ", playList.getName()));
                }while ( Controller.getRepository().getLists().contains(playList));
                Controller.getRepository().addNewPL(playList);
                    addPListButton(playList);
                updateUI();

            }
        });
        PLPanel.add(addNewPL);
        for(int i=0;i<Controller.getRepository().getLists().size();i++){
            addPListButton(Controller.getRepository().getLists().get(i));
        }
//        addPListButton("favorites");
//        addPListButton("sharedList");
    }

    private void setButtonsShape(JButton jButton) {
        jButton.setFocusPainted(false);
        jButton.setContentAreaFilled(false);
        jButton.setBorderPainted(false);
        jButton.setFont(BF);
        jButton.setForeground(new Color(0xD2FCC0));
        jButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jButton.setForeground(Color.GREEN);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                jButton.setForeground(new Color(0xD2FCC0));

            }
        });
        jButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    private void addPListButton (PlayList playList){
        JButton PLButton =new JButton(playList.getName());
        PLButton.setIcon(new ImageIcon(getClass().getResource("plists.png")));
        PLButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        setButtonsShape(PLButton);
        PLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getWindowsGUI().getArtsGUI().getMainGUI().setSongsPlayList(playList);
            }
        });
        PLPanel.add(PLButton);

    }

}

