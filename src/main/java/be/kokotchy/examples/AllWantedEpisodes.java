package be.kokotchy.examples;

import be.kokotchy.api.SickbeardAPI;
import be.kokotchy.api.enumerations.ApiShowsSort;
import be.kokotchy.api.enumerations.ApiShowsStatus;
import be.kokotchy.model.Episode;
import be.kokotchy.model.Season;
import be.kokotchy.model.Show;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 11/2/12
 * Time: 1:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class AllWantedEpisodes {

    private static Logger logger = LoggerFactory.getLogger(AllWantedEpisodes.class);
    private static final String KEY = "be472886a3453a9d863c8ef1cc8fd3ef";

    public static void main(String[] args) {
        new AllWantedEpisodes();
    }

    public AllWantedEpisodes() {
        SickbeardAPI sickbeardAPI = new SickbeardAPI("http", "192.168.1.100", 8081, "", KEY);
        List<Show> shows = sickbeardAPI.getApiShows().getShows(ApiShowsStatus.ALL, ApiShowsSort.IDTVDB);
        List<String> excludedShows = new ArrayList<String>();
        excludedShows.add("Fairy Tail");
        excludedShows.add("Merlin (2008)");
        excludedShows.add("Bref.");
        excludedShows.add("Doctor Who (2005)");
        long total = 0;
        for (Show show : shows) {
            long idTvdb = show.getIdTvdb();
            long wanted = sickbeardAPI.getApiShow().getStats(idTvdb).getWanted();
            if (wanted > 0  && !excludedShows.contains(show.getName())) {
                total += wanted;
//                logger.info("The show "+show.getName()+" has "+wanted+" wanted episodes.");
                Map<String,Season> seasons = sickbeardAPI.getApiShow().getSeasons(idTvdb);
                for (Map.Entry<String, Season> entrySeason : seasons.entrySet()) {
                    Season season = entrySeason.getValue();
                    for (Map.Entry<String, Episode> entryEpisode : season.getEpisodes().entrySet()) {
                        Episode episode = entryEpisode.getValue();
                        if ("Wanted".equals(episode.getStatus())) {
                            String quality = episode.getQuality();
                            if ("N/A".equals(quality)) {
                                quality = show.getQuality();
                            }
                            logger.info(show.getName()+" S"+season.getNb()+"E"+ episode.getNb()+" in "+ quality);
                        }
                    }
                }
            }
        }

        logger.info("There is "+total+" wanted episodes");
    }
}
