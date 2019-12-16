package com.baltan.notease.music.util;

import com.baltan.notease.music.config.DownloadConfig;
import com.baltan.notease.music.constant.CustomizedException;
import com.baltan.notease.music.exception.DownloadFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

/**
 * Description: 文件操作工具类
 *
 * @author Baltan
 * @date 2019-12-10 15:41
 */
@Component
public class FileUtil {
    private static DownloadConfig downloadConfig;

    /**
     * 无法实例化工具类
     */
    private FileUtil() {
    }

    /**
     * 下载歌曲
     *
     * @param id
     * @param artistNames
     * @param songName
     * @throws IOException
     * @throws DownloadFailureException
     */
    public static void downloadSong(String id, List<String> artistNames, String songName)
            throws IOException, DownloadFailureException {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;

        try {
            /**
             * 统一资源定位符
             */
            URL url = new URL(downloadConfig.getSongUrl() + id + downloadConfig.getSongSuffix());
            /**
             * HTTP连接类
             */
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            /**
             * 配置请求的方法，默认是GET请求
             */
            httpURLConnection.setRequestMethod(RequestMethod.POST.name());
            /**
             * 配置字符编码
             */
            httpURLConnection.setRequestProperty("charset", downloadConfig.getCharset());
            /**
             * 打开到此URL资源的通信连接
             */
            httpURLConnection.connect();

            File directory = new File(downloadConfig.getDirectoryPath());

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filename = downloadConfig.getDirectoryPath() + renameSongFileName(artistNames, songName);
            File file = new File(filename);
            bis = new BufferedInputStream(httpURLConnection.getInputStream());
            fos = new FileOutputStream(file);
            int size;
            byte[] buf = new byte[1024];

            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (ProtocolException e) {
            e.printStackTrace();
            throw e;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DownloadFailureException(CustomizedException.DOWNLOAD_FAILURE_EXCEPTION.getCODE(),
                    CustomizedException.DOWNLOAD_FAILURE_EXCEPTION.getMESSAGE());
        } finally {
            IOUtil.close(bis);
            IOUtil.close(fos);
        }
    }

    /**
     * 下载歌词
     *
     * @param lyric
     * @param artistNames
     * @param songName
     * @throws IOException
     * @throws DownloadFailureException
     */
    public static void downloadLyric(String lyric, List<String> artistNames, String songName)
            throws IOException, DownloadFailureException {
        PrintStream ps = null;

        try {
            File directory = new File(downloadConfig.getDirectoryPath());

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filename = downloadConfig.getDirectoryPath() + renameLyricFileName(artistNames, songName);
            File file = new File(filename);
            ps = new PrintStream(file);
            ps.print(lyric);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (DownloadFailureException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DownloadFailureException(CustomizedException.DOWNLOAD_FAILURE_EXCEPTION.getCODE(),
                    CustomizedException.DOWNLOAD_FAILURE_EXCEPTION.getMESSAGE());
        } finally {
            IOUtil.close(ps);
        }
    }

    /**
     * 重命名下载的音乐文件
     *
     * @param artistNames
     * @param songName
     * @return
     * @throws DownloadFailureException
     */
    private static String renameSongFileName(List<String> artistNames, String songName)
            throws DownloadFailureException {
        try {
            StringBuilder builder = new StringBuilder();

            for (String artistName : artistNames) {
                builder.append(artistName).append("&");
            }
            return builder.deleteCharAt(builder.length() - 1).append("-").append(songName)
                    .append(downloadConfig.getSongSuffix()).toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DownloadFailureException(CustomizedException.DOWNLOAD_FAILURE_EXCEPTION.getCODE(),
                    CustomizedException.DOWNLOAD_FAILURE_EXCEPTION.getMESSAGE());
        }
    }

    /**
     * 重命名下载的歌词文件
     *
     * @param artistNames
     * @param songName
     * @return
     * @throws DownloadFailureException
     */
    private static String renameLyricFileName(List<String> artistNames, String songName)
            throws DownloadFailureException {
        try {
            StringBuilder builder = new StringBuilder();

            for (String artistName : artistNames) {
                builder.append(artistName).append("&");
            }
            return builder.deleteCharAt(builder.length() - 1).append("-").append(songName)
                    .append(downloadConfig.getLyricSuffix()).toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DownloadFailureException(CustomizedException.DOWNLOAD_FAILURE_EXCEPTION.getCODE(),
                    CustomizedException.DOWNLOAD_FAILURE_EXCEPTION.getMESSAGE());
        }
    }

    @Autowired
    public void setDownloadConfig(DownloadConfig downloadConfig) {
        FileUtil.downloadConfig = downloadConfig;
    }
}
