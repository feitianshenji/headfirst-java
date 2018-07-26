import java.util.*;
import java.io.*;



public class Jukebox3 {

    ArrayList<Song> songList = new ArrayList<Song>(); //歌曲名称会存在Song对象的ArrayList上

    public static void main(String[] args) {
        new Jukebox3().go();
    }

    public void go() { //载入文件并列出内容
        getSongs();
        System.out.println(songList);
        Collections.sort(songList); //调研Collections静态的sort（）然后再列出清单。第二次的输出会依赖字母排序
        System.out.println(songList);
    }

    void getSongs() { //读取文件的程序
        try {
            File file = new File("D://SongList.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file)); //FileReader是字符的连接到文本文件的串流
            String line = null; //用String变量来承接所读取的结果
            while ((line = reader.readLine()) != null) { //读取一行
                addSong(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void addSong(String lineToParse) {

        String[] tokens = lineToParse.split("/"); //split（）方法会用反斜线来拆开歌曲内容
        Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]); //构造函数
        songList.add(nextSong); //Song对象加入SongList
    }

    class Song implements Comparable<Song> { //要使用sort（）方法，必须实现Comparable借口

        String title;
        String artist;
        String rating;
        String bpm;

        public int compareTo(Song s) { //还要重写compareTo（）方法
            return title.compareTo(s.getTitle());
        }

        Song(String t, String a, String r, String b) {
            title = t;
            artist = a;
            rating = r;
            bpm = b;
        }

        public String getTitle() {
            return title;
        }
        public String getArtist() {
            return artist;
        }

        public String getRating() {
            return rating;
        }

        public String getBpm() {
            return bpm;
        }

        public String toString() { //将toString（）覆盖过，让它返回歌名
            return title;
        }
    }
}
