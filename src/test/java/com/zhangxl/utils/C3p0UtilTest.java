package test.com.zhangxl.utils;

import com.zhangxl.utils.C3p0Util;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import static org.junit.Assert.*;

/**
 * C3p0Util Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 22, 2019</pre>
 */
public class C3p0UtilTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getDataSource()
     */
    @Test
    public void testGetDataSource() throws Exception {
//TODO: Test goes here...

        Connection connection = C3p0Util.getConnection();
        System.out.println("connection = " + connection);

        Statement statement = connection.prepareStatement("");

        C3p0Util.close(connection,statement);
    }

    /**
     * Method: getConnection()
     */
    @Test
    public void testGetConnection() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: close(Connection conn, Statement stmt, ResultSet rs)
     */
    @Test
    public void testClose() throws Exception {
//TODO: Test goes here... 
    }


} 
