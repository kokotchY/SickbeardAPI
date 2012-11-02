package be.kokotchy.model;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 11/1/12
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class Show {
    private boolean airByDate;
    private boolean cacheBanner;
    private boolean cachePoster;
    private String language;
    private String network;
    private String nextEpAirdate;
    private boolean paused;
    private String quality;
    private String name;
    private String status;
    private long idTvrage;
    private String nameTvrage;
    private long idTvdb;

    public Show(long idTvdb) {
        setIdTvdb(idTvdb);
    }

    public Show(String name) {
        setName(name);
    }

    public long getIdTvdb() {
        return idTvdb;
    }

    public void setIdTvdb(long idTvdb) {
        this.idTvdb = idTvdb;
    }

    public boolean getAirByDate() {
        return airByDate;
    }

    public void setAirByDate(boolean airByDate) {
        this.airByDate = airByDate;
    }

    public boolean isCacheBanner() {
        return cacheBanner;
    }

    public void setCacheBanner(boolean cacheBanner) {
        this.cacheBanner = cacheBanner;
    }

    public boolean isCachePoster() {
        return cachePoster;
    }

    public void setCachePoster(boolean cachePoster) {
        this.cachePoster = cachePoster;
    }

    public long getIdTvrage() {
        return idTvrage;
    }

    public void setIdTvrage(long idTvrage) {
        this.idTvrage = idTvrage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameTvrage() {
        return nameTvrage;
    }

    public void setNameTvrage(String nameTvrage) {
        this.nameTvrage = nameTvrage;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getNextEpAirdate() {
        return nextEpAirdate;
    }

    public void setNextEpAirdate(String nextEpAirdate) {
        this.nextEpAirdate = nextEpAirdate;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Show{" +
                "airByDate=" + airByDate +
                ", cacheBanner=" + cacheBanner +
                ", cachePoster=" + cachePoster +
                ", language='" + language + '\'' +
                ", network='" + network + '\'' +
                ", nextEpAirdate='" + nextEpAirdate + '\'' +
                ", paused=" + paused +
                ", quality='" + quality + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", idTvrage=" + idTvrage +
                ", nameTvrage='" + nameTvrage + '\'' +
                ", idTvdb=" + idTvdb +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Show show = (Show) o;

        if (airByDate != show.airByDate) return false;
        if (cacheBanner != show.cacheBanner) return false;
        if (cachePoster != show.cachePoster) return false;
        if (idTvdb != show.idTvdb) return false;
        if (idTvrage != show.idTvrage) return false;
        if (paused != show.paused) return false;
        if (language != null ? !language.equals(show.language) : show.language != null) return false;
        if (!name.equals(show.name)) return false;
        if (nameTvrage != null ? !nameTvrage.equals(show.nameTvrage) : show.nameTvrage != null) return false;
        if (network != null ? !network.equals(show.network) : show.network != null) return false;
        if (nextEpAirdate != null ? !nextEpAirdate.equals(show.nextEpAirdate) : show.nextEpAirdate != null)
            return false;
        if (quality != null ? !quality.equals(show.quality) : show.quality != null) return false;
        if (status != null ? !status.equals(show.status) : show.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (airByDate ? 1 : 0);
        result = 31 * result + (cacheBanner ? 1 : 0);
        result = 31 * result + (cachePoster ? 1 : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (network != null ? network.hashCode() : 0);
        result = 31 * result + (nextEpAirdate != null ? nextEpAirdate.hashCode() : 0);
        result = 31 * result + (paused ? 1 : 0);
        result = 31 * result + (quality != null ? quality.hashCode() : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (int) (idTvrage ^ (idTvrage >>> 32));
        result = 31 * result + (nameTvrage != null ? nameTvrage.hashCode() : 0);
        result = 31 * result + (int) (idTvdb ^ (idTvdb >>> 32));
        return result;
    }
}
