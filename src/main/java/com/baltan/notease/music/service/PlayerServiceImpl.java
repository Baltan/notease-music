package com.baltan.notease.music.service;

import com.baltan.notease.music.config.DownloadConfig;
import com.baltan.notease.music.constant.CustomizedException;
import com.baltan.notease.music.constant.Response;
import com.baltan.notease.music.exception.MusicPlayException;
import com.baltan.notease.music.util.PlayerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 音乐播放实现类
 *
 * @author Baltan
 * @date 2019-12-11 15:01
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private DownloadConfig downloadConfig;

    /**
     * 开始播放歌曲
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> startPlay(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        int responseCode = Response.SUCCESSFUL.getCODE();
        String responseMessage = Response.SUCCESSFUL.getMESSAGE();

        try {
            String songName = (String) params.get("songName");

            String filePath =
                    downloadConfig.getDirectoryPath() + songName + downloadConfig.getSongSuffix();
            File file = new File(filePath);

            if (file.exists()) {
                PlayerUtil.play(filePath);
            } else {

            }
        } catch (MusicPlayException e) {
            responseCode = e.getCode();
            responseMessage = e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            responseCode = CustomizedException.UNKNOWN_EXCEPTION.getCODE();
            responseMessage = CustomizedException.UNKNOWN_EXCEPTION.getMESSAGE();
            e.printStackTrace();
        } finally {
            response.put("responseCode", responseCode);
            response.put("responseMessage", responseMessage);
        }
        return response;
    }

    /**
     * 结束播放歌曲
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> stopPlay(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        int responseCode = Response.SUCCESSFUL.getCODE();
        String responseMessage = Response.SUCCESSFUL.getMESSAGE();

        try {
            PlayerUtil.stop();
        } catch (MusicPlayException e) {
            responseCode = e.getCode();
            responseMessage = e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            responseCode = CustomizedException.UNKNOWN_EXCEPTION.getCODE();
            responseMessage = CustomizedException.UNKNOWN_EXCEPTION.getMESSAGE();
            e.printStackTrace();
        } finally {
            response.put("responseCode", responseCode);
            response.put("responseMessage", responseMessage);
        }
        return response;
    }

    /**
     * 开始播放在线歌曲
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> startOnlinePlay(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        int responseCode = Response.SUCCESSFUL.getCODE();
        String responseMessage = Response.SUCCESSFUL.getMESSAGE();

        try {
            String id = (String) params.get("id");
            String url = downloadConfig.getSongUrl() + id + downloadConfig.getSongSuffix();
            PlayerUtil.startOnlinePlay(url);
        } catch (MusicPlayException e) {
            responseCode = e.getCode();
            responseMessage = e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            responseCode = CustomizedException.UNKNOWN_EXCEPTION.getCODE();
            responseMessage = CustomizedException.UNKNOWN_EXCEPTION.getMESSAGE();
            e.printStackTrace();
        } finally {
            response.put("responseCode", responseCode);
            response.put("responseMessage", responseMessage);
        }
        return response;
    }

    /**
     * 结束播放在线歌曲
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> stopOnlinePlay(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        int responseCode = Response.SUCCESSFUL.getCODE();
        String responseMessage = Response.SUCCESSFUL.getMESSAGE();

        try {
            PlayerUtil.stopOnlinePlay();
        } catch (MusicPlayException e) {
            responseCode = e.getCode();
            responseMessage = e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            responseCode = CustomizedException.UNKNOWN_EXCEPTION.getCODE();
            responseMessage = CustomizedException.UNKNOWN_EXCEPTION.getMESSAGE();
            e.printStackTrace();
        } finally {
            response.put("responseCode", responseCode);
            response.put("responseMessage", responseMessage);
        }
        return response;
    }

    /**
     * 暂停播放在线歌曲
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> pauseOnlinePlay(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        int responseCode = Response.SUCCESSFUL.getCODE();
        String responseMessage = Response.SUCCESSFUL.getMESSAGE();

        try {
            PlayerUtil.pauseOnlinePlay();
        } catch (MusicPlayException e) {
            responseCode = e.getCode();
            responseMessage = e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            responseCode = CustomizedException.UNKNOWN_EXCEPTION.getCODE();
            responseMessage = CustomizedException.UNKNOWN_EXCEPTION.getMESSAGE();
            e.printStackTrace();
        } finally {
            response.put("responseCode", responseCode);
            response.put("responseMessage", responseMessage);
        }
        return response;
    }
}
