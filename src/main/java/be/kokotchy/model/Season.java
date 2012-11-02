package be.kokotchy.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 11/2/12
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Season {
    private String nb;
    private Map<String, Episode> episodes = new HashMap<String, Episode>();

    public Map<String, Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Map<String, Episode> episodes) {
        this.episodes = episodes;
    }

    public String getNb() {
        if (Integer.parseInt(nb) < 10) {
            return "0"+nb;
        } else {
            return nb;
        }
    }

    public void setNb(String nb) {
        this.nb = nb;
    }

    public void addEpisode(Episode episode) {
        episode.setSeason(this);
        episodes.put(episode.getNb(), episode);
    }
}
