package com.baltan.notease.music.util;

import com.baltan.notease.music.constant.CustomizedException;
import com.baltan.notease.music.exception.DataFormatException;
import com.baltan.notease.music.exception.MusicPlayException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.springframework.stereotype.Component;

import java.io.*;

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
    private static Media media;
    private static MediaPlayer mediaPlayer;

    /**
     * 开始播放
     *
     * @param filePath
     * @throws IOException
     * @throws JavaLayerException
     * @throws MusicPlayException
     */
    public static void play(String filePath)
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
            stop();
            IOUtil.close(bis);
        }
    }

    /**
     * 停止播放
     *
     * @throws MusicPlayException
     */
    public static void stop() throws MusicPlayException {
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
    public static void startOnlinePlay(String url) throws MusicPlayException {
        try {
            media = new Media(url);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.play();
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
            mediaPlayer.stop();
            mediaPlayer.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MusicPlayException(CustomizedException.MUSIC_PLAY_EXCEPTION.getCODE(),
                    CustomizedException.MUSIC_PLAY_EXCEPTION.getMESSAGE());
        }
    }

    /**
     * 暂停在线播放
     *
     * @throws MusicPlayException
     */
    public static void pauseOnlinePlay() throws MusicPlayException {
        try {
            mediaPlayer.pause();
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
