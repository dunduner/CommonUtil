package io.github.sunning.util;

import org.junit.Test;

/**
 * @author ：lzz
 * @BelongsProject: io.github.ljwlgl.util
 * @date ：Created in 2020/6/9 16:04
 * @description ：
 * @modified By：
 */

public class FileUtilTest {

    @Test
    public void getSuffix() {
        String rs = FileUtil.getSuffix("aaa.txt");
        String rs1 = FileUtil.getSuffix("");
    }
}
