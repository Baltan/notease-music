package com.baltan.notease.music;

import com.baltan.notease.music.config.NeteaseConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteaseMusicApplicationTests {
    @Autowired
    private NeteaseConfig neteaseConfig;

    @Test
    public void testNeteaseConfig() {
        System.out.println(neteaseConfig);
    }
}
