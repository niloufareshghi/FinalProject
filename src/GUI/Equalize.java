package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Equalize extends JPanel {
    Timer timer;
    Random rand = new Random();
    JProgressBar band1 = new JProgressBar(JProgressBar.VERTICAL, 10, 30);
    JProgressBar band2 = new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band3 = new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band4 = new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band5 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band6 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band7 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band8 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);

    JPanel slider1 = new JPanel();
    JPanel slider2 = new JPanel();
    JPanel slider3 = new JPanel();
    JPanel slider4 = new JPanel();
    JPanel slider5 = new JPanel();
    JPanel slider6 = new JPanel();
    JPanel slider7 = new JPanel();
    JPanel slider8 = new JPanel();


    public Equalize (){
        setPreferredSize(new Dimension(200,20));
//        band1.setMajorTickSpacing(2);
//        band1.setMinorTickSpacing(2);
//        band1.setPaintTicks(true);
//
//        band2.setMajorTickSpacing(2);
//        band2.setMinorTickSpacing(2);
//        band2.setPaintTicks(true);
//
//        band3.setMajorTickSpacing(2);
//        band3.setMinorTickSpacing(2);
//        band3.setPaintTicks(true);
//        band3.setPaintTrack(true);
//        band3.setPaintLabels(false
//        );
//        band3.setValueIsAdjusting(false);
//
//        band4.setMajorTickSpacing(2);
//        band4.setMinorTickSpacing(2);
//        band4.setPaintTicks(true);
//
//        band5.setMajorTickSpacing(2);
//        band5.setMinorTickSpacing(2);
//        band5.setPaintTicks(true);
//
//        band6.setMajorTickSpacing(2);
//        band6.setMinorTickSpacing(2);
//        band6.setPaintTicks(true);
//
//        band7.setMajorTickSpacing(2);
//        band7.setMinorTickSpacing(2);
//        band7.setPaintTicks(true);
//
//        band8.setMajorTickSpacing(2);
//        band8.setMinorTickSpacing(2);
//
//        band8.setPaintTicks(true);
        setBackground(Color.BLACK);
        band1.setValue(15);
        band1.setBorderPainted(false);
        band2.setValue(15);
        band2.setBorderPainted(false);
        band3.setValue(15);
        band3.setBorderPainted(false);
        band4.setValue(15);
        band4.setBorderPainted(false);
        band5.setValue(15);
        band5.setBorderPainted(false);
        band6.setValue(15);
        band6.setBorderPainted(false);
        band7.setValue(15);
        band7.setBorderPainted(false);
        band8.setValue(15);
        band8.setBorderPainted(false);
        band1.setBackground(Color.BLACK);
        band2.setBackground(Color.BLACK);
        band3.setBackground(Color.BLACK);
        band4.setBackground(Color.BLACK);
        band5.setBackground(Color.BLACK);
        band6.setBackground(Color.BLACK);
        band7.setBackground(Color.BLACK);
        band8.setBackground(Color.BLACK);

        slider1.add(band1);
        slider2.add(band2);
        slider3.add(band3);
        slider4.add(band4);
        slider5.add(band5);
        slider6.add(band6);
        slider7.add(band7);
        slider8.add(band8);
        slider1.setBackground(Color.black);
        slider2.setBackground(Color.black);
        slider3.setBackground(Color.black);
        slider4.setBackground(Color.black);
        slider5.setBackground(Color.black);
        slider6.setBackground(Color.black);
        slider7.setBackground(Color.black);
        slider8.setBackground(Color.black);

        add(slider1);
        add(slider2);
        add(slider3);
        add(slider4);
        add(slider5);
        add(slider6);
        add(slider7);
        add(slider8);

        setLayout(new GridLayout(0,8));
        if (timer != null && timer.isRunning())
            timer.stop();
        timer=new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                band1.setValue(rand.nextInt(30)+5);
                band2.setValue(rand.nextInt(30)+5);
                band3.setValue(rand.nextInt(30)+5);
                band4.setValue(rand.nextInt(30)+5);
                band5.setValue(rand.nextInt(30)+5);
                band6.setValue(rand.nextInt(30)+5);
                band7.setValue(rand.nextInt(30)+5);
                band8.setValue(rand.nextInt(30)+5);
            }
        });
//        timer.start();

    }
}
