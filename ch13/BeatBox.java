//p420

import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;


public class BeatBox {

    JPanel mainPanel;
    ArrayList<JCheckBox> checkboxList; /*把checkbox储存在ArrayList中*/
    Sequencer sequencer;
    Sequence sequence;
    Track track;
    JFrame theFrame;

    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap",
            "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo",
            "Open Hi Conga"}; /*乐器的名称，以string的array维护*/
    int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63}; /*实际的乐器关键字，例如说35是bass，42是closed hi-hat*/

    public static void main(String[] args) {
        new BeatBox().buildGUI();
    }

    public void buildGUI() {

        theFrame = new JFrame("Cyber BeatBox");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); /*设定面板上摆设组件的空白边缘*/

        checkboxList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);

        JButton upTempo = new JButton("Tempo Up");
        upTempo.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);

        JButton downTempo = new JButton("Tempo Down");
        downTempo.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTempo);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i < 16; i++) {
            nameBox.add(new Label(instrumentNames[i]));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        theFrame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);

        for (int i = 0; i < 256; i++) {
            JCheckBox c = new JCheckBox(); /*创建checkbox组，设定成未勾选的为false并加到ArrayList和面板上*/
            c.setSelected(false);
            checkboxList.add(c);
            mainPanel.add(c);
        }

        setUpMidi();

        theFrame.setBounds(50, 50, 300, 300);
        theFrame.pack();
        theFrame.setVisible(true);
    }

        public void setUpMidi() {
            try {
                sequencer = MidiSystem.getSequencer();
                sequencer.open();
                sequence = new Sequence(Sequence.PPQ, 4);
                track = sequence.createTrack();
                sequencer.setTempoInBPM(120);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void buildTrackAndStart() { /*创建出16个元素的数组来存储一项乐器的值。如果该节应该要演奏，其值会是关键字值，否则值为零*/
            int[] trackList = null;

            sequence.deleteTrack(track); /*清除掉旧的track做一个新的*/
            track = sequence.createTrack();

            for (int i=0; i<16; i++) { /*对每个乐器都执行一次*/
                trackList = new int[16];
                int key = instruments[i];

                for (int j=0; j<16; j++) { /*对每一拍执行一次*/
                    JCheckBox jc = (JCheckBox) checkboxList.get(j + (16*i));
                    if (jc.isSelected()) { /*如果有勾选，将关键字放到数组的该位置上，不然的话就补零*/
                        trackList[j] = key;
                    } else {
                        trackList[j] = 0;
                    }
                }

                makeTracks(trackList); /*创建此乐器的事件并加到track上*/
                track.add(makeEvent(176, 1, 127, 0, 16));
            }
            track.add(makeEvent(192, 9, 1, 0, 15)); /*确保第16拍有事件，否则button不会重复播放*/
            try {
                sequencer.setSequence(sequence);
                sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY); /*指定无穷的重复次数*/
                sequencer.start();
                sequencer.setTempoInBPM(120);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public class MyStartListener implements ActionListener { /*第一个内部类，按钮的监听者*/
            public void actionPerformed(ActionEvent a) {
                buildTrackAndStart();
            }
        }

        public class MyStopListener implements ActionListener {
            public void actionPerformed(ActionEvent a) {
                sequencer.stop();
            }
        }

        public class MyUpTempoListener implements ActionListener {
            public void actionPerformed(ActionEvent a) {
                float tempoFactor = sequencer.getTempoFactor();
                sequencer.setTempoFactor((float)(tempoFactor * 1.03));
            }
        }

        public class MyDownTempoListener implements ActionListener {
            public void actionPerformed(ActionEvent a) {
                float tempoFactor = sequencer.getTempoFactor();
                sequencer.setTempoFactor((float)(tempoFactor * .97));
            }
        }

        public void makeTracks(int[] list) { /*创建某项乐器的所有事件*/
            for (int i=0; i<16; i++) {
                int key = list[i];

                if (key != 0) {
                    track.add(makeEvent(144, 9, key, 100, i));
                    track.add(makeEvent(128, 9, key, 100, i+1));
                }
            }
        }

        public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
            MidiEvent event = null;
            try {
                ShortMessage a = new ShortMessage();
                a.setMessage(comd, chan, one, two);
                event = new MidiEvent(a, tick);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return event;
        }
    }
