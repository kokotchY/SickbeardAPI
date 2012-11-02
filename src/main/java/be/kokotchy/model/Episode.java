package be.kokotchy.model;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 11/2/12
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Episode {
    private String airdate;
    private String nb;
    private String name;
    private String status;
    private Season season;
    private String quality;

    public Episode(String nb) {
        this.nb = nb;
    }

    public String getAirdate() {
        return airdate;
    }

    public void setAirdate(String airdate) {
        this.airdate = airdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
