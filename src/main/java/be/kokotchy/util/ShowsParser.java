package be.kokotchy.util;

import be.kokotchy.api.enumerations.ApiShowsSort;
import be.kokotchy.model.Show;
import be.kokotchy.model.ShowStats;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 11/1/12
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShowsParser {

    public static List<Show> parseShows(JSONObject jsonObject, ApiShowsSort sort) {
        List<Show> result = new ArrayList<Show>();
        JSONObject data = (JSONObject) jsonObject.get("data");
        Iterator iterator = data.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            JSONObject nextJsonObject = (JSONObject) data.get(key);
            switch (sort) {
                case NAME:
                    String name = (String) key;
                    result.add(parseShow(name, nextJsonObject));
                    break;
                case IDTVDB:
                case NO_SORT:
                    long id = Long.parseLong(key.toString());
                    result.add(parseShow(id, nextJsonObject));
                    break;
            }
        }
        return result;
    }

    public static Show parseShow(long idTvdb, JSONObject jsonObject) {
        if (jsonObject != null) {
            Show show = new Show(idTvdb);
            show.setAirByDate(getBoolean(jsonObject, "air_by_date"));
            JSONObject cache = (JSONObject) jsonObject.get("cache");
            show.setCacheBanner(getBoolean(cache, "banner"));
            show.setCachePoster(getBoolean(cache, "poster"));
            show.setLanguage(getString(jsonObject, "language"));
            show.setNetwork(getString(jsonObject, "network"));
            show.setNextEpAirdate(getString(jsonObject, "next_ep_airdate"));
            show.setPaused(getBoolean(jsonObject, "paused"));
            show.setQuality(getString(jsonObject, "quality"));
            show.setName(getString(jsonObject, "show_name"));
            show.setStatus(getString(jsonObject, "status"));
            show.setIdTvrage(getLong(jsonObject, "tvrage_id"));
            show.setNameTvrage(getString(jsonObject, "tvrage_name"));
            return show;
        }
        return null;
    }

    public static Show parseShow(String name, JSONObject jsonObject) {
        if (jsonObject != null) {
            Show show = new Show(name);
            show.setAirByDate(getBoolean(jsonObject, "air_by_date"));
            JSONObject cache = (JSONObject) jsonObject.get("cache");
            show.setCacheBanner(getBoolean(cache, "banner"));
            show.setCachePoster(getBoolean(cache, "poster"));
            show.setLanguage(getString(jsonObject, "language"));
            show.setNetwork(getString(jsonObject, "network"));
            show.setNextEpAirdate(getString(jsonObject, "next_ep_airdate"));
            show.setPaused(getBoolean(jsonObject, "paused"));
            show.setQuality(getString(jsonObject, "quality"));
            show.setStatus(getString(jsonObject, "status"));
            show.setIdTvdb(getLong(jsonObject, "tvdbid"));
            show.setIdTvrage(getLong(jsonObject, "tvrage_id"));
            show.setNameTvrage(getString(jsonObject, "tvrage_name"));
            return show;
        }
        return null;
    }

    public static ShowStats parseShowStats(JSONObject jsonObject) {
        if (jsonObject != null) {
            JSONObject data = (JSONObject) jsonObject.get("data");
            if (data != null) {
                ShowStats showStats = new ShowStats();
                showStats.setArchived(getLong(data, "archived"));
                showStats.setDownloaded(loadNbByCat(data, "downloaded"));
                showStats.setIgnored(getLong(data, "ignored"));
                showStats.setSkipped(getLong(data, "skipped"));
                showStats.setSnatched(loadNbByCat(data, "snatched"));
                showStats.setTotal(getLong(data, "total"));
                showStats.setUnaired(getLong(data, "unaired"));
                showStats.setWanted(getLong(data, "wanted"));
                return showStats;
            }
        }
        return null;
    }

    private static ShowStats.NbByCat loadNbByCat(JSONObject jsonObject, String field) {
        ShowStats.NbByCat nbByCat = new ShowStats.NbByCat();
        JSONObject nbByCatObject = (JSONObject) jsonObject.get(field);
        if (nbByCatObject != null) {
            nbByCat.setNb1080pBluray(getLong(nbByCatObject, "1080p_bluray"));
            nbByCat.setNb720pBluray(getLong(nbByCatObject, "720p_bluray"));
            nbByCat.setNb720pWebDl(getLong(nbByCatObject, "720p_web-dl"));
            nbByCat.setNbHdTv(getLong(nbByCatObject, "hd_tv"));
            nbByCat.setNbSdDvd(getLong(nbByCatObject, "sd_dvd"));
            nbByCat.setNbSdTv(getLong(nbByCatObject, "sd_tv"));
            nbByCat.setTotal(getLong(nbByCatObject, "total"));
            nbByCat.setUnknown(getLong(nbByCatObject, "unknown"));
        }
        return nbByCat;
    }

    private static long getLong(JSONObject jsonObject, String key) {
        if (jsonObject.containsKey(key)) {
            Object value = jsonObject.get(key);
            if (value instanceof Long) {
                return (Long) value;
            } else {
                return Long.parseLong(value.toString());
            }
        } else {
            return -1;
        }
    }

    private static String getString(JSONObject jsonObject, String key) {
        if (jsonObject.containsKey(key)) {
            return jsonObject.get(key).toString();
        } else {
            return null;
        }
    }

    private static boolean getBoolean(JSONObject jsonObject, String key) {
        if (jsonObject.containsKey(key)) {

            Object value = jsonObject.get(key);
            if (value instanceof Boolean) {
                return Boolean.parseBoolean(value.toString());
            } else {
                long longValue = (Long) value;
                return longValue == 1;
            }
        } else {
            return false;
        }
    }

    public static ShowStats parseShowStats(InputStream response) {
        return parseShowStats((JSONObject) JSONValue.parse(new InputStreamReader(response)));
    }
}
