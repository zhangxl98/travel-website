package com.zhangxl.utils;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * UuidUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 22, 2019</pre>
 */
public class UuidUtilTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getUuid()
     */
    @Test
    public void testGetUuid() throws Exception {
//TODO: Test goes here...

        for (int i = 0; i < 10; i++) {

            String uuid = UUID.randomUUID().toString().replace("-", "");

            System.out.println("uuid = " + uuid);
        }

    }


} 
