package be.kokotchy.util;

import be.kokotchy.model.Episode;
import be.kokotchy.model.Season;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static be.kokotchy.util.JsonUtil.getString;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 11/2/12
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShowParser {
    public static Map<String, Season> parseShowSeasons(JSONObject jsonObject) {
        Map<String, Season> result = new HashMap<String, Season>();
        JSONObject data = (JSONObject) jsonObject.get("data");
        Iterator itSeasons = data.entrySet().iterator();
        while (itSeasons.hasNext()) {
            Map.Entry entrySeason = (Map.Entry)itSeasons.next();
            Season season = new Season();
            season.setNb(entrySeason.getKey().toString());
            JSONObject episodes = (JSONObject)entrySeason.getValue();
            Iterator itEpisodes = episodes.entrySet().iterator();
            while (itEpisodes.hasNext()) {
                Map.Entry entryEpisode = (Map.Entry)itEpisodes.next();
                season.addEpisode(parseShowEpisode(entryEpisode.getKey().toString(), (JSONObject) entryEpisode.getValue()));
            }
            result.put(season.getNb(), season);
        }

        return result;
    }

    private static Episode parseShowEpisode(String nb, JSONObject jsonObject) {
        if (jsonObject != null) {
            Episode episode = new Episode(nb);
            episode.setAirdate(getString(jsonObject, "airdate"));
            episode.setName(getString(jsonObject, "name"));
            episode.setQuality(getString(jsonObject, "quality"));
            episode.setStatus(getString(jsonObject, "status"));
            return episode;
        }
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
