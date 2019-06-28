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
    JProgressBar band9 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band10 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band11 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band12 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band13 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band14 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band15 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band16 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band17 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band18 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band19 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band20 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band21 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band22 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band23 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band24 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);
    JProgressBar band25 =  new JProgressBar(JProgressBar.VERTICAL,  10, 30);


    JPanel slider1 = new JPanel();
    JPanel slider2 = new JPanel();
    JPanel slider3 = new JPanel();
    JPanel slider4 = new JPanel();
    JPanel slider5 = new JPanel();
    JPanel slider6 = new JPanel();
    JPanel slider7 = new JPanel();
    JPanel slider8 = new JPanel();
    JPanel slider9 = new JPanel();
    JPanel slider10 = new JPanel();
    JPanel slider11 = new JPanel();
    JPanel slider12 = new JPanel();
    JPanel slider13 = new JPanel();
    JPanel slider14 = new JPanel();
    JPanel slider15 = new JPanel();
    JPanel slider16 = new JPanel();
    JPanel slider17 = new JPanel();
    JPanel slider18 = new JPanel();
    JPanel slider19 = new JPanel();
    JPanel slider20 = new JPanel();
    JPanel slider21 = new JPanel();
    JPanel slider22 = new JPanel();
    JPanel slider23 = new JPanel();
    JPanel slider24 = new JPanel();
    JPanel slider25 = new JPanel();


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
        band9.setValue(15);
        band9.setBorderPainted(false);
        band10.setValue(15);
        band10.setBorderPainted(false);
        band25.setBorderPainted(false);
        band25.setValue(15);
        band25.setBorderPainted(false);
        band11.setValue(15);
        band11.setBorderPainted(false);
        band12.setValue(15);
        band12.setBorderPainted(false);
        band13.setValue(15);
        band13.setBorderPainted(false);
        band14.setValue(15);
        band14.setBorderPainted(false);
        band15.setValue(15);
        band15.setBorderPainted(false);
        band16.setValue(15);
        band16.setBorderPainted(false);
        band17.setValue(15);
        band17.setBorderPainted(false);
        band18.setValue(15);
        band18.setBorderPainted(false);
        band19.setValue(15);
        band19.setBorderPainted(false);
        band20.setValue(15);
        band20.setBorderPainted(false);
        band21.setValue(15);
        band21.setBorderPainted(false);
        band22.setValue(15);
        band22.setBorderPainted(false);
        band23.setValue(15);
        band23.setBorderPainted(false);
        band24.setValue(15);
        band24.setBorderPainted(false);
        band25.setValue(15);

        band1.setBackground(Color.BLACK);
        band2.setBackground(Color.BLACK);
        band3.setBackground(Color.BLACK);
        band4.setBackground(Color.BLACK);
        band5.setBackground(Color.BLACK);
        band6.setBackground(Color.BLACK);
        band7.setBackground(Color.BLACK);
        band8.setBackground(Color.BLACK);
        band9.setBackground(Color.BLACK);
        band10.setBackground(Color.BLACK);
        band11.setBackground(Color.BLACK);
        band12.setBackground(Color.BLACK);
        band13.setBackground(Color.BLACK);
        band14.setBackground(Color.BLACK);
        band15.setBackground(Color.BLACK);
        band16.setBackground(Color.BLACK);
        band17.setBackground(Color.BLACK);
        band18.setBackground(Color.BLACK);
        band19.setBackground(Color.BLACK);
        band20.setBackground(Color.BLACK);
        band21.setBackground(Color.BLACK);
        band22.setBackground(Color.BLACK);
        band23.setBackground(Color.BLACK);
        band24.setBackground(Color.BLACK);
        band25.setBackground(Color.BLACK);

        slider1.add(band1);
        slider2.add(band2);
        slider3.add(band3);
        slider4.add(band4);
        slider5.add(band5);
        slider6.add(band6);
        slider7.add(band7);
        slider8.add(band8);
        slider9.add(band9);
        slider10.add(band10);
        slider11.add(band11);
        slider12.add(band12);
        slider13.add(band13);
        slider14.add(band14);
        slider15.add(band15);
        slider16.add(band16);
        slider17.add(band17);
        slider18.add(band18);
        slider19.add(band19);
        slider20.add(band20);
        slider21.add(band21);
        slider22.add(band22);
        slider23.add(band23);
        slider24.add(band24);
        slider25.add(band25);

        slider1.setBackground(Color.black);
        slider2.setBackground(Color.black);
        slider3.setBackground(Color.black);
        slider4.setBackground(Color.black);
        slider5.setBackground(Color.black);
        slider6.setBackground(Color.black);
        slider7.setBackground(Color.black);
        slider8.setBackground(Color.black);
        slider9.setBackground(Color.black);
        slider10.setBackground(Color.black);
        slider11.setBackground(Color.black);
        slider12.setBackground(Color.black);
        slider13.setBackground(Color.black);
        slider14.setBackground(Color.black);
        slider15.setBackground(Color.black);
        slider16.setBackground(Color.black);
        slider17.setBackground(Color.black);
        slider18.setBackground(Color.black);
        slider19.setBackground(Color.black);
        slider20.setBackground(Color.black);
        slider21.setBackground(Color.black);
        slider22.setBackground(Color.black);
        slider23.setBackground(Color.black);
        slider24.setBackground(Color.black);
        slider25.setBackground(Color.black);
        add(slider1);
        add(slider2);
        add(slider3);
        add(slider4);
        add(slider5);
        add(slider6);
        add(slider7);
        add(slider8);
        add(slider9);
        add(slider10);
        add(slider11);
        add(slider12);
        add(slider13);
        add(slider14);
        add(slider15);
        add(slider16);
        add(slider17);
        add(slider18);
        add(slider19);
        add(slider20);
        add(slider21);
        add(slider22);
        add(slider23);
        add(slider24);
        add(slider25);

        setLayout(new GridLayout(0,25));
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
                band9.setValue(rand.nextInt(30)+5);
                band10.setValue(rand.nextInt(30)+5);
                band11.setValue(rand.nextInt(30)+5);
                band12.setValue(rand.nextInt(30)+5);
                band13.setValue(rand.nextInt(30)+5);
                band14.setValue(rand.nextInt(30)+5);
                band15.setValue(rand.nextInt(30)+5);
                band16.setValue(rand.nextInt(30)+5);
                band17.setValue(rand.nextInt(30)+5);
                band18.setValue(rand.nextInt(30)+5);
                band19.setValue(rand.nextInt(30)+5);
                band20.setValue(rand.nextInt(30)+5);
                band21.setValue(rand.nextInt(30)+5);
                band22.setValue(rand.nextInt(30)+5);
                band23.setValue(rand.nextInt(30)+5);
                band24.setValue(rand.nextInt(30)+5);
                band25.setValue(rand.nextInt(30)+5);

            }
        });
//        timer.start();

    }
}
