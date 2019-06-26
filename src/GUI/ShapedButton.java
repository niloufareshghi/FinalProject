package GUI;

import Controller.Controller;
import Logic.SongInfo;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class ShapedButton extends JPanel {
    JButton playButton = new JButton("PLAY");
    JButton southButton = new JButton();

    Image img;

    public ShapedButton() {
        setPreferredSize(new Dimension(200, 200));
        setLayout(new BorderLayout());
        setPlayShape();
        setPlayAction();
        setSouthButton();
        add(playButton, BorderLayout.CENTER);
        add(southButton, BorderLayout.PAGE_END);
    }

    boolean setBGImage(Image img) {
        if (img != null) {
            this.img = img;
            return true;
        }
        return false;
    }

    protected void setButtonShape(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(new Color(0x80BED4));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseListener() {

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
                button.setForeground(Color.RED);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(new Color(0x80BED4));
            }
        });
    }

    protected void setSouthButton() {
        setButtonShape(southButton);
        southButton.setForeground(Color.GREEN);
        southButton.setIcon(new ImageIcon(getClass().getResource("addMusic.png")));
        southButton.setText("Add music");
        southButton.setFont(new Font("option", Font.ROMAN_BASELINE, 20));
        southButton.setToolTipText("Add new music to Library");
        southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showSaveFileDialog();
                } catch (InvalidDataException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedTagException e1) {
                    e1.printStackTrace();
                }
                Controller.songsStatus();
            }
        });
    }

    private void showSaveFileDialog() throws InvalidDataException, IOException, UnsupportedTagException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            Controller.addSong(new SongInfo(fileToSave.getAbsolutePath()));
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        }
    }

    private void setPlayShape() {
        playButton.setIcon(new ImageIcon(getClass().getResource("Advanceplay.png")));
        playButton.setFont(new Font("PLAY", Font.ITALIC, 15));
        setButtonShape(playButton);
    }

    protected void setPlayAction() {
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    protected void setPlayButtonTexT(String str) {
        playButton.setText(str);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight() , this);
        }
    }

    void addPlayBAction(ActionListener actionListener) {
        playButton.addActionListener(actionListener);
    }


}
