package be.kokotchy.util;

import be.kokotchy.model.Show;
import be.kokotchy.model.ShowStats;
import junit.framework.TestCase;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 11/1/12
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShowsParserTest extends TestCase {
    public void testParseShow() throws Exception {
        String json = "{ \"251562\": {\n" +
                "            \"air_by_date\": 0, \n" +
                "            \"cache\": {\n" +
                "                \"banner\": 1, \n" +
                "                \"poster\": 1\n" +
                "            }, \n" +
                "            \"language\": \"en\", \n" +
                "            \"network\": \"Canal+\", \n" +
                "            \"next_ep_airdate\": \"\", \n" +
                "            \"paused\": 0, \n" +
                "            \"quality\": \"SD\", \n" +
                "            \"show_name\": \"Bref.\", \n" +
                "            \"status\": \"Ended\", \n" +
                "            \"tvrage_id\": 29721, \n" +
                "            \"tvrage_name\": \"Bref\"\n" +
                "        }," +
                "     \"257655\": {\n" +
                "        \"air_by_date\": 0,\n" +
                "                \"cache\": {\n" +
                "            \"banner\": 1,\n" +
                "                    \"poster\": 1\n" +
                "        },\n" +
                "        \"language\": \"en\",\n" +
                "                \"network\": \"The CW\",\n" +
                "                \"next_ep_airdate\": \"2012-11-07\",\n" +
                "                \"paused\": false,\n" +
                "                \"quality\": \"SD\",\n" +
                "                \"show_name\": \"Arrow\",\n" +
                "                \"status\": \"Continuing\",\n" +
                "                \"tvrage_id\": \"30715\",\n" +
                "                \"tvrage_name\": \"Arrow\"\n" +
                "    }}";
        JSONObject jsonObject = (JSONObject) JSONValue.parse(json);
        assertEquals(2, jsonObject.size());
        Map<Long, Show> shows = new HashMap<Long, Show>();
        shows.put(251562L, createShow(251562L, false, true, true, "en", "Canal+", "", false, "SD", "Bref.", "Ended", 29721L, "Bref"));
        shows.put(257655L, createShow(257655L, false, true, true, "en", "The CW", "2012-11-07", false, "SD", "Arrow", "Continuing", 30715L, "Arrow"));

        Iterator iterator = jsonObject.entrySet().iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (next instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) next;
                Long idTvdb = Long.parseLong(entry.getKey().toString());
                System.out.println("Testing a new show #"+idTvdb);
                Show expectedShow = shows.get(idTvdb);
                assertNotNull(expectedShow);
                JSONObject showsObject = (JSONObject) entry.getValue();
                assertEquals(expectedShow, ShowsParser.parseShow(idTvdb, showsObject));
            } else {
                fail("Must be an entry map");
            }
        }
    }

    public void testParseShowStats1() {
        String json = "{\n" +
                "    \"data\": {\n" +
                "        \"archived\": 0, \n" +
                "        \"downloaded\": {\n" +
                "            \"1080p_bluray\": 0, \n" +
                "            \"720p_bluray\": 0, \n" +
                "            \"720p_web-dl\": 0, \n" +
                "            \"hd_tv\": 0, \n" +
                "            \"sd_dvd\": 40, \n" +
                "            \"sd_tv\": 17, \n" +
                "            \"total\": 59, \n" +
                "            \"unknown\": 2\n" +
                "        }, \n" +
                "        \"ignored\": 0, \n" +
                "        \"skipped\": 0, \n" +
                "        \"snatched\": {\n" +
                "            \"1080p_bluray\": 0, \n" +
                "            \"720p_bluray\": 0, \n" +
                "            \"720p_web-dl\": 0, \n" +
                "            \"hd_tv\": 0, \n" +
                "            \"sd_dvd\": 0, \n" +
                "            \"sd_tv\": 0, \n" +
                "            \"total\": 0, \n" +
                "            \"unknown\": 0\n" +
                "        }, \n" +
                "        \"total\": 82, \n" +
                "        \"unaired\": 0, \n" +
                "        \"wanted\": 23\n" +
                "    }, \n" +
                "    \"message\": \"\", \n" +
                "    \"result\": \"success\"\n" +
                "}";
        ShowStats expectedShowStats = createShowStats(0, 0, 0, 0, 0, 40, 17, 59, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 82, 0, 23);
        JSONObject jsonObject = (JSONObject) JSONValue.parse(json);
        ShowStats showStats = ShowsParser.parseShowStats(jsonObject);
        if (!expectedShowStats.equals(showStats)) {
            assertEquals(expectedShowStats.getArchived(), showStats.getArchived());
            assertEquals(expectedShowStats.getIgnored(), showStats.getIgnored());
            assertEquals(expectedShowStats.getSkipped(), showStats.getSkipped());
            assertEquals(expectedShowStats.getTotal(), showStats.getTotal());
            assertEquals(expectedShowStats.getUnaired(), showStats.getUnaired());
            assertEquals(expectedShowStats.getWanted(), showStats.getWanted());
            ShowStats.NbByCat expectedDownloaded = expectedShowStats.getDownloaded();
            ShowStats.NbByCat downloaded = showStats.getDownloaded();
            if (!expectedDownloaded.equals(downloaded)) {
                assertEquals(expectedDownloaded.getNb1080pBluray(), downloaded.getNb1080pBluray());
                assertEquals(expectedDownloaded.getNb720pBluray(), downloaded.getNb720pBluray());
                assertEquals(expectedDownloaded.getNb720pWebDl(), downloaded.getNb720pWebDl());
                assertEquals(expectedDownloaded.getNbHdTv(), downloaded.getNbHdTv());
                assertEquals(expectedDownloaded.getNbSdDvd(), downloaded.getNbSdDvd());
                assertEquals(expectedDownloaded.getTotal(), downloaded.getTotal());
                assertEquals(expectedDownloaded.getUnknown(), downloaded.getUnknown());
                assertEquals(expectedDownloaded, downloaded);
            }


            ShowStats.NbByCat expectedSnatched = expectedShowStats.getSnatched();
            ShowStats.NbByCat snatched = showStats.getSnatched();
            if (!expectedSnatched.equals(snatched)) {
                assertEquals(expectedSnatched.getNb1080pBluray(), snatched.getNb1080pBluray());
                assertEquals(expectedSnatched.getNb720pBluray(), snatched.getNb720pBluray());
                assertEquals(expectedSnatched.getNb720pWebDl(), snatched.getNb720pWebDl());
                assertEquals(expectedSnatched.getNbHdTv(), snatched.getNbHdTv());
                assertEquals(expectedSnatched.getNbSdDvd(), snatched.getNbSdDvd());
                assertEquals(expectedSnatched.getTotal(), snatched.getTotal());
                assertEquals(expectedSnatched.getUnknown(), snatched.getUnknown());
                assertEquals(expectedSnatched, snatched);
            }

            assertEquals(expectedShowStats, showStats);
        }
    }


    public void testParseShowStats2() {
        String json = "{\n" +
                "    \"data\": {\n" +
                "        \"archived\": 0, \n" +
                "        \"downloaded\": {\n" +
                "            \"1080p_bluray\": 0, \n" +
                "            \"720p_bluray\": 0, \n" +
                "            \"720p_web-dl\": 0, \n" +
                "            \"hd_tv\": 0, \n" +
                "            \"sd_dvd\": 0, \n" +
                "            \"sd_tv\": 12, \n" +
                "            \"total\": 12, \n" +
                "            \"unknown\": 0\n" +
                "        }, \n" +
                "        \"ignored\": 0, \n" +
                "        \"skipped\": 9, \n" +
                "        \"snatched\": {\n" +
                "            \"1080p_bluray\": 0, \n" +
                "            \"720p_bluray\": 0, \n" +
                "            \"720p_web-dl\": 0, \n" +
                "            \"hd_tv\": 0, \n" +
                "            \"sd_dvd\": 0, \n" +
                "            \"sd_tv\": 0, \n" +
                "            \"total\": 6, \n" +
                "            \"unknown\": 0\n" +
                "        }, \n" +
                "        \"total\": 27, \n" +
                "        \"unaired\": 0, \n" +
                "        \"wanted\": 0\n" +
                "    }, \n" +
                "    \"message\": \"\", \n" +
                "    \"result\": \"success\"\n" +
                "}";
        ShowStats expectedShowStats = createShowStats(0, 0, 0, 0, 0, 0, 12, 12, 0, 0, 9, 0, 0, 0, 0, 0, 0, 6, 0, 27, 0, 0);
        JSONObject jsonObject = (JSONObject) JSONValue.parse(json);
        ShowStats showStats = ShowsParser.parseShowStats(jsonObject);
        if (!expectedShowStats.equals(showStats)) {
            assertEquals(expectedShowStats.getArchived(), showStats.getArchived());
            assertEquals(expectedShowStats.getIgnored(), showStats.getIgnored());
            assertEquals(expectedShowStats.getSkipped(), showStats.getSkipped());
            assertEquals(expectedShowStats.getTotal(), showStats.getTotal());
            assertEquals(expectedShowStats.getUnaired(), showStats.getUnaired());
            assertEquals(expectedShowStats.getWanted(), showStats.getWanted());
            ShowStats.NbByCat expectedDownloaded = expectedShowStats.getDownloaded();
            ShowStats.NbByCat downloaded = showStats.getDownloaded();
            if (!expectedDownloaded.equals(downloaded)) {
                assertEquals(expectedDownloaded.getNb1080pBluray(), downloaded.getNb1080pBluray());
                assertEquals(expectedDownloaded.getNb720pBluray(), downloaded.getNb720pBluray());
                assertEquals(expectedDownloaded.getNb720pWebDl(), downloaded.getNb720pWebDl());
                assertEquals(expectedDownloaded.getNbHdTv(), downloaded.getNbHdTv());
                assertEquals(expectedDownloaded.getNbSdDvd(), downloaded.getNbSdDvd());
                assertEquals(expectedDownloaded.getTotal(), downloaded.getTotal());
                assertEquals(expectedDownloaded.getUnknown(), downloaded.getUnknown());
                assertEquals(expectedDownloaded, downloaded);
            }


            ShowStats.NbByCat expectedSnatched = expectedShowStats.getSnatched();
            ShowStats.NbByCat snatched = showStats.getSnatched();
            if (!expectedSnatched.equals(snatched)) {
                assertEquals(expectedSnatched.getNb1080pBluray(), snatched.getNb1080pBluray());
                assertEquals(expectedSnatched.getNb720pBluray(), snatched.getNb720pBluray());
                assertEquals(expectedSnatched.getNb720pWebDl(), snatched.getNb720pWebDl());
                assertEquals(expectedSnatched.getNbHdTv(), snatched.getNbHdTv());
                assertEquals(expectedSnatched.getNbSdDvd(), snatched.getNbSdDvd());
                assertEquals(expectedSnatched.getTotal(), snatched.getTotal());
                assertEquals(expectedSnatched.getUnknown(), snatched.getUnknown());
                assertEquals(expectedSnatched, snatched);
            }

            assertEquals(expectedShowStats, showStats);
        }
    }

    private ShowStats createShowStats(long i, long i1, long i2, long i3, long i4, long i5, long i6, long i7, long i8, long i9, long i10, long i11, long i12, long i13, long i14, long i15, long i16, long i17, long i18, long i19, long i20, int i21) {
        ShowStats showStats = new ShowStats();
        showStats.setArchived(i);
        showStats.setDownloaded(createShowStatsNbByCat(i1, i2, i3, i4, i5, i6, i7, i8));
        showStats.setIgnored(i9);
        showStats.setSkipped(i10);
        showStats.setSnatched(createShowStatsNbByCat(i11, i12, i13, i14, i15, i16, i17, i18));
        showStats.setTotal(i19);
        showStats.setUnaired(i20);
        showStats.setWanted(i21);
        return showStats;
    }

    private ShowStats.NbByCat createShowStatsNbByCat(long i1, long i2, long i3, long i4, long i5, long i6, long i7, long i8) {
        ShowStats.NbByCat nbByCat = new ShowStats.NbByCat();
        nbByCat.setNb1080pBluray(i1);
        nbByCat.setNb720pBluray(i2);
        nbByCat.setNb720pWebDl(i3);
        nbByCat.setNbHdTv(i4);
        nbByCat.setNbSdDvd(i5);
        nbByCat.setNbSdTv(i6);
        nbByCat.setTotal(i7);
        nbByCat.setUnknown(i8);
        return nbByCat;
    }

    private Show createShow(long idTvdb, boolean airByDate, boolean cacheBanner, boolean cachePoster, String language, String network, String nextEpAirDate, boolean paused, String quality, String name, String status, long idTvrage, String nameTvrage) {
        Show show = new Show(idTvdb);
        show.setAirByDate(airByDate);
        show.setCacheBanner(cacheBanner);
        show.setCachePoster(cachePoster);
        show.setLanguage(language);
        show.setNetwork(network);
        show.setNextEpAirdate(nextEpAirDate);
        show.setPaused(paused);
        show.setQuality(quality);
        show.setName(name);
        show.setStatus(status);
        show.setIdTvrage(idTvrage);
        show.setNameTvrage(nameTvrage);
        return show;
    }
}