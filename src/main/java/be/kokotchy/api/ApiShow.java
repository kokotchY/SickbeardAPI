package be.kokotchy.api;

import be.kokotchy.model.Season;
import be.kokotchy.model.ShowStats;
import be.kokotchy.util.ShowParser;
import be.kokotchy.util.ShowsParser;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 11/2/12
 * Time: 1:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApiShow {
    private SickbeardAPI sickbeard;
    private final Logger logger = LoggerFactory.getLogger(ApiShow.class);

    public ApiShow(SickbeardAPI sickbeard) {
        this.sickbeard = sickbeard;
    }

    public ShowStats getStats(Long idtvdb) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("cmd", "show.stats");
        params.put("tvdbid", ""+idtvdb);
        JSONObject jsonObject = sickbeard.execute(params);
        return ShowsParser.parseShowStats(jsonObject);
    }

    public Map<String,Season> getSeasons(Long idtvdb) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("cmd", "show.seasons");
        params.put("tvdbid", ""+idtvdb);
        JSONObject jsonObject = sickbeard.execute(params);
        return ShowParser.parseShowSeasons(jsonObject);
    }
}
