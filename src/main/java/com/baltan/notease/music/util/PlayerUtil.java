package com.baltan.notease.music.util;

import com.baltan.notease.music.constant.CustomizedException;
import com.baltan.notease.music.exception.MusicPlayException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Description: 音乐播放工具类
 * 参考：<a href="https://blog.csdn.net/qq_34814092/article/details/80889813"></a>
 *
 * @author Baltan
 * @date 2019-12-11 13:57
 */
@Component
public class PlayerUtil {
    private static Player player;

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
}
