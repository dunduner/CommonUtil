package io.github.sunning.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeepCopyUtilTest {

    @Test
    public void testDepthClone() {
        assertEquals("result", DeepCopyUtil.depthClone("srcObj"));
    }

    @Test
    public void testListDepthClone() {
    }
}
