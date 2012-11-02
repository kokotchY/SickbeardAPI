package be.kokotchy.util;

import be.kokotchy.api.SickbeardAPI;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 11/2/12
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class SickbeardTest extends TestCase {
    public void testCreateParamsString() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("cmd", "test");
        params.put("id", "123");
        assertEquals("cmd=test&id=123", SickbeardAPI.createParamsString(params));

        params.put("cmd", "bla");

        assertEquals("cmd=bla&id=123", SickbeardAPI.createParamsString(params));
    }

    public void testCreateParamsStringEmptyParams() {
        assertEquals("", SickbeardAPI.createParamsString(new HashMap<String, String>()));
    }

    public void testCreateParamsStringOneParam() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("cmd", "test");
        assertEquals("cmd=test", SickbeardAPI.createParamsString(params));
    }
}
