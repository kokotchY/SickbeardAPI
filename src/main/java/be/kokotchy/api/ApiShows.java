package be.kokotchy.api;

import be.kokotchy.api.enumerations.ApiShowsSort;
import be.kokotchy.api.enumerations.ApiShowsStatus;
import be.kokotchy.model.Show;
import be.kokotchy.util.ShowsParser;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 11/2/12
 * Time: 12:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApiShows {

    private SickbeardAPI sickbeard;

    public ApiShows(SickbeardAPI sickbeard) {
        this.sickbeard = sickbeard;
    }

    public List<Show> getShows(ApiShowsStatus status, ApiShowsSort sort) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("cmd", "shows");
        switch (status) {
            case NOT_PAUSED:
                params.put("paused", "0");
                break;
            case PAUSED:
                params.put("paused", "1");
                break;
        }
        switch (sort) {
            case IDTVDB:
            case NO_SORT:
                params.put("sort", "id");
                break;
            case NAME:
                params.put("sort", "name");
                break;
        }

        JSONObject jsonObject = sickbeard.execute(params);
        return ShowsParser.parseShows(jsonObject, sort);
    }
}
