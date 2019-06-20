package GUI;

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
        scrollPane.setPreferredSize(new Dimension(20,60));//vaghean kheily moheme
        add(scrollPane, gbc);
        PLPanel.add(new JButton("BUtton  :"));

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
        libPanel.add(BSong);
        JButton BAlbums = new JButton("Albums");
        BAlbums.setIcon(new ImageIcon(getClass().getResource("Album.png")));
        BAlbums.setAlignmentX(Component.CENTER_ALIGNMENT);
        setButtonsShape(BAlbums);
        libPanel.add(BAlbums);
        JButton BMovies = new JButton("Movies");
        BMovies.setIcon(new ImageIcon(getClass().getResource("movie.png")));
        BMovies.setAlignmentX(Component.CENTER_ALIGNMENT);
        setButtonsShape(BMovies);
        libPanel.add(BMovies);
    }

    private void setPLPanel(){
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weighty = 6;
        gbc.weightx = 1;
        gbc.gridheight = 1;
        gbc.gridheight = 1;
        PLPanel.setBackground(Color.BLACK);
        PLPanel.setLayout(new BoxLayout(PLPanel,1));
        JLabel playList = new JLabel("PLAYLIST           ");

        playList.setAlignmentX(Component.CENTER_ALIGNMENT);
        playList.setIcon(new ImageIcon(getClass().getResource("PL.png")));
        playList.setFont(headFont);
        playList.setForeground(Color.RED);
        PLPanel.add(playList);
        for(int i=0;i<50;i++){
            addPListButton("Button "+i);
        }
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
    private void addPListButton (String label){
        JButton PLButton =new JButton(label);
        PLButton.setIcon(new ImageIcon(getClass().getResource("plists.png")));
        PLButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        setButtonsShape(PLButton);
        PLButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        PLPanel.add(PLButton);

    }
}

