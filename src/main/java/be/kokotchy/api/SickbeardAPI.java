package be.kokotchy.api;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 11/1/12
 * Time: 4:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class SickbeardAPI {

    private final String key;
    private final static Logger logger = LoggerFactory.getLogger(SickbeardAPI.class);
    private final String protocol;
    private final int port;
    private final String path;
    private HttpClient client;
    private final String host;

    private final ApiShows apiShows;

    /**
     * Create a new SickbeardAPI client with the given paremeter
     *
     * @param protocol Protocol to connect (only http is supported)
     * @param host Host of the server
     * @param port Port of the server
     * @param path Path to access the api
     * @param key Key of the api
     */
    public SickbeardAPI(String protocol, String host, int port, String path, String key) {
        this.key = key;
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.path = path;
        client = new HttpClient();
        client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        apiShows = new ApiShows(this);
    }

    public JSONObject execute(String cmd) {
        String url = String.format("%s://%s:%d/%s/api/%s/?%s", protocol, host, port, path, key, cmd);
        logger.debug("Created url: "+url);
        HttpMethod method = new GetMethod(url);
        try {
            int status = client.executeMethod(method);
            if (status == HttpStatus.SC_OK) {
                InputStream response = method.getResponseBodyAsStream();
                return (JSONObject) JSONValue.parse(new InputStreamReader(response));
            } else {
                logger.error("Method failed: "+method.getStatusLine());
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    public ApiShows getApiShows() {
        return apiShows;
    }

    public JSONObject execute(Map<String, String> params) {
        return execute(createParamsString(params));
    }

    public static String createParamsString(Map<String, String> params) {
        StringBuffer buffer = new StringBuffer();
        int nb = params.size();
        int current = 0;
        SortedSet<String> keys = new TreeSet<String>(params.keySet());
        for (String key: keys) {
            buffer.append(key+"="+params.get(key));
            if (++current < nb) {
                buffer.append("&");
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        SickbeardAPI sickbeard = new SickbeardAPI("http", "192.168.1.100", 8081, "", "be472886a3453a9d863c8ef1cc8fd3ef");
//        JSONObject jsonObject = sickbeard.execute("show.stats&tvdbid=79216");
        Map<String, String> params = new HashMap<String, String>();
        params.put("cmd", "show.stats");
        params.put("tvdbid", "79216");
        JSONObject jsonObject = sickbeard.execute(params);
        System.out.println(jsonObject);
    }
}
