//p390

import javax.sound.midi.*;

public class MiniMusicPlayer2 implements ControllerEventListener {

    public static void main(String[] args) {
        MiniMusicPlayer2 mini = new MiniMusicPlayer2();
        mini.go();
    }

    public void go() {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            int[] eventIWant = {127}; /*向Sequencer注册事件。注册的方法取用监听者与代表想要监听的事件的int数组，我们只需要127事件*/
            sequencer.addControllerEventListener(this, eventIWant);

            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            for (int i = 0; i < 61; i+=4) {
                track.add(makeEvent(144,1,i,100,i));
                track.add(makeEvent(176,1,127,0,i));
                //插入事件编号为127的自定义ControllerEvent(176),它不会做任何事情，只是让我们知道有音符被播放，因为它的tick跟NOTE ON是同时进行的
                track.add(makeEvent(144,1,i,100,i+2));
            }

            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(220);
            sequencer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void controlChange(ShortMessage event) {
        System.out.println("la");
    }

    private static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage message = new ShortMessage();
            message.setMessage(comd, chan, one, two);
            event = new MidiEvent(message, tick);

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return event;
    }
}
