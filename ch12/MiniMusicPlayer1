//p389

import javax.sound.midi.*;

public class MiniMusicPlayer1 {
    public static void main(String[] args) {

        try {
            Sequencer sequencer = MidiSystem.getSequencer(); /*创建并打开队列*/
            sequencer.open();

            Sequence seq = new Sequence(Sequence.PPQ, 4); /*创建队列并track*/
            Track track = seq.createTrack();

            for (int i=5; i<61; i+=4) { /*创建一堆连续的音符事件*/

                track.add(makeEvent(144, 1, i, 100, i)); /*调用makeEvent（）来产生信息和事件，然后把他们加到track上*/
                track.add(makeEvent(128, 1, i, 100, i+2));
            }

            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();
        } catch (Exception ex) {ex.printStackTrace();}
}

public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) { /*前4个参数是给信息用的，tick是何时播放信息*/
    MidiEvent event = null;
    try {
        ShortMessage a = new ShortMessage();
        a.setMessage(comd, chan, one, two);
        event = new MidiEvent(a, tick);
    } catch (Exception e) {}
    return event;
    }
}
