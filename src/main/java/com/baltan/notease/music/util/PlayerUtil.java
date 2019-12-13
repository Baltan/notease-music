package com.baltan.notease.music.util;

import com.baltan.notease.music.constant.CustomizedException;
import com.baltan.notease.music.exception.DataFormatException;
import com.baltan.notease.music.exception.MusicPlayException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.springframework.stereotype.Component;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;

/**
 * Description: 音乐播放工具类
 * 参考：<a href="https://blog.csdn.net/qq_34814092/article/details/80889813"></a>
 * <a href="https://blog.csdn.net/yourlittlelemon/article/details/79250674"></a>
 *
 * @author Baltan
 * @date 2019-12-11 13:57
 */
@Component
public class PlayerUtil {
    private static Player player;
    private static SourceDataLine sourceDataLine;

    /**
     * 开始本地播放
     *
     * @param filePath
     * @throws IOException
     * @throws JavaLayerException
     * @throws MusicPlayException
     */
    public static void startLocalPlay(String filePath)
            throws IOException, JavaLayerException, MusicPlayException {
        BufferedInputStream bis = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(filePath));
            player = new Player(bis);
            player.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (JavaLayerException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MusicPlayException(CustomizedException.MUSIC_PLAY_EXCEPTION.getCODE(),
                    CustomizedException.MUSIC_PLAY_EXCEPTION.getMESSAGE());
        } finally {
            stopLocalPlay();
            IOUtil.close(bis);
        }
    }

    /**
     * 停止本地播放
     *
     * @throws MusicPlayException
     */
    public static void stopLocalPlay() throws MusicPlayException {
        try {
            player.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MusicPlayException(CustomizedException.MUSIC_PLAY_EXCEPTION.getCODE(),
                    CustomizedException.MUSIC_PLAY_EXCEPTION.getMESSAGE());
        }
    }

    /**
     * 开始在线播放
     *
     * @param url
     * @throws MusicPlayException
     */
    public static void startOnlinePlay(URL url)
            throws LineUnavailableException, IOException, UnsupportedAudioFileException, MusicPlayException {
        try {
            /**
             * 获取格式为pcm的音频流
             */
            AudioInputStream audioInputStream = getPcmAudioInputStream(url);

            if (audioInputStream == null) {
                throw new MusicPlayException(CustomizedException.MUSIC_PLAY_EXCEPTION.getCODE(),
                        CustomizedException.MUSIC_PLAY_EXCEPTION.getMESSAGE());
            }
            /**
             * 获得此音频输入流中声音数据的音频格式
             */
            AudioFormat audioFormat = audioInputStream.getFormat();
            /**
             * 设置数据输入
             */
            DataLine.Info dataLineInfo =
                    new DataLine.Info(SourceDataLine.class, audioFormat, AudioSystem.NOT_SPECIFIED);
            /**
             * 输出设备混频器
             */
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();
            int len;
            byte[] buf = new byte[1024];
            /**
             * 从音频输入流中读取数据
             */
            while ((len = audioInputStream.read(buf)) > 0) {
                /**
                 * 将数据写入混音器
                 */
                sourceDataLine.write(buf, 0, len);
            }
            /**
             * 阻塞等待，清空缓冲数据
             */
            sourceDataLine.drain();
            /**
             * 关闭音频流
             */
            audioInputStream.close();
            /**
             * 停止播放
             */
            sourceDataLine.stop();
            sourceDataLine.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MusicPlayException(CustomizedException.MUSIC_PLAY_EXCEPTION.getCODE(),
                    CustomizedException.MUSIC_PLAY_EXCEPTION.getMESSAGE());
        }
    }

    /**
     * 停止在线播放
     *
     * @throws MusicPlayException
     */
    public static void stopOnlinePlay() throws MusicPlayException {
        try {
            sourceDataLine.stop();
            sourceDataLine.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MusicPlayException(CustomizedException.MUSIC_PLAY_EXCEPTION.getCODE(),
                    CustomizedException.MUSIC_PLAY_EXCEPTION.getMESSAGE());
        }
    }

    /**
     * 获取音源文件的音频流
     *
     * @param url
     * @return
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws MusicPlayException
     */
    private static AudioInputStream getPcmAudioInputStream(URL url)
            throws UnsupportedAudioFileException, IOException, MusicPlayException {
        try {
            /**
             * 读取音频文件的类
             */
            MpegAudioFileReader reader = new MpegAudioFileReader();
            /**
             * 获取音频输入流，从提供的音源文件获得音频输入流，也支持从本地文件读取：mp.getAudioInputStream(file)
             */
            AudioInputStream sourceInputStream = reader.getAudioInputStream(url);
            /**
             * 获得此音频输入流中声音数据的音频格式
             */
            AudioFormat audioFormat = sourceInputStream.getFormat();
            /**
             * 设定输出格式为pcm格式的音频文件
             */
            AudioFormat format =
                    new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, audioFormat.getSampleRate(), 16,
                            audioFormat.getChannels(), audioFormat.getChannels() * 2,
                            audioFormat.getSampleRate(),
                            false);
            /**
             * 输出到音频
             */
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(format, sourceInputStream);
            return audioInputStream;
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MusicPlayException(CustomizedException.MUSIC_PLAY_EXCEPTION.getCODE(),
                    CustomizedException.MUSIC_PLAY_EXCEPTION.getMESSAGE());
        }
    }

    /**
     * 获得MP3播放时长
     * 参考：<a href="https://www.cnblogs.com/yansum/p/6629636.html"></a>
     *
     * @param file
     * @return
     * @throws IOException
     * @throws CannotReadException
     * @throws ReadOnlyFileException
     * @throws TagException
     * @throws InvalidAudioFrameException
     * @throws MusicPlayException
     */
    @Deprecated
    public static String getDuration(File file)
            throws IOException, CannotReadException, ReadOnlyFileException, TagException,
            InvalidAudioFrameException, MusicPlayException, DataFormatException {
        try {
            MP3File f = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
            int second = audioHeader.getTrackLength();
            String duration = DataUtil.second2MinuteAndSecond(second);
            return duration;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (CannotReadException e) {
            e.printStackTrace();
            throw e;
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
            throw e;
        } catch (TagException e) {
            e.printStackTrace();
            throw e;
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
            throw e;
        } catch (DataFormatException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MusicPlayException(CustomizedException.MUSIC_PLAY_EXCEPTION.getCODE(),
                    CustomizedException.MUSIC_PLAY_EXCEPTION.getMESSAGE());
        }
    }
}
