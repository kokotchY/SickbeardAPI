package be.kokotchy.model;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 11/1/12
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShowStats {

    public static class NbByCat {
        private long nb1080pBluray;
        private long nb720pBluray;
        private long nb720pWebDl;
        private long nbHdTv;
        private long nbSdDvd;
        private long nbSdTv;
        private long total;
        private long unknown;

        public long getNb1080pBluray() {
            return nb1080pBluray;
        }

        public void setNb1080pBluray(long nb1080pBluray) {
            this.nb1080pBluray = nb1080pBluray;
        }

        public long getNb720pBluray() {
            return nb720pBluray;
        }

        public void setNb720pBluray(long nb720pBluray) {
            this.nb720pBluray = nb720pBluray;
        }

        public long getNb720pWebDl() {
            return nb720pWebDl;
        }

        public void setNb720pWebDl(long nb720pWebDl) {
            this.nb720pWebDl = nb720pWebDl;
        }

        public long getNbHdTv() {
            return nbHdTv;
        }

        public void setNbHdTv(long nbHdTv) {
            this.nbHdTv = nbHdTv;
        }

        public long getNbSdDvd() {
            return nbSdDvd;
        }

        public void setNbSdDvd(long nbSdDvd) {
            this.nbSdDvd = nbSdDvd;
        }

        public long getNbSdTv() {
            return nbSdTv;
        }

        public void setNbSdTv(long nbSdTv) {
            this.nbSdTv = nbSdTv;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public long getUnknown() {
            return unknown;
        }

        public void setUnknown(long unknown) {
            this.unknown = unknown;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NbByCat nbByCat = (NbByCat) o;

            if (nb1080pBluray != nbByCat.nb1080pBluray) return false;
            if (nb720pBluray != nbByCat.nb720pBluray) return false;
            if (nb720pWebDl != nbByCat.nb720pWebDl) return false;
            if (nbHdTv != nbByCat.nbHdTv) return false;
            if (nbSdDvd != nbByCat.nbSdDvd) return false;
            if (nbSdTv != nbByCat.nbSdTv) return false;
            if (total != nbByCat.total) return false;
            if (unknown != nbByCat.unknown) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (int) (nb1080pBluray ^ (nb1080pBluray >>> 32));
            result = 31 * result + (int) (nb720pBluray ^ (nb720pBluray >>> 32));
            result = 31 * result + (int) (nb720pWebDl ^ (nb720pWebDl >>> 32));
            result = 31 * result + (int) (nbHdTv ^ (nbHdTv >>> 32));
            result = 31 * result + (int) (nbSdDvd ^ (nbSdDvd >>> 32));
            result = 31 * result + (int) (nbSdTv ^ (nbSdTv >>> 32));
            result = 31 * result + (int) (total ^ (total >>> 32));
            result = 31 * result + (int) (unknown ^ (unknown >>> 32));
            return result;
        }
    }

    private long archived;
    private NbByCat downloaded;
    private long ignored;
    private long skipped;
    private NbByCat snatched;
    private long total;
    private long unaired;
    private long wanted;

    public long getSkipped() {
        return skipped;
    }

    public void setSkipped(long skipped) {
        this.skipped = skipped;
    }

    public long getArchived() {
        return archived;
    }

    public void setArchived(long archived) {
        this.archived = archived;
    }

    public NbByCat getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(NbByCat downloaded) {
        this.downloaded = downloaded;
    }

    public long getIgnored() {
        return ignored;
    }

    public void setIgnored(long ignored) {
        this.ignored = ignored;
    }

    public NbByCat getSnatched() {
        return snatched;
    }

    public void setSnatched(NbByCat snatched) {
        this.snatched = snatched;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getUnaired() {
        return unaired;
    }

    public void setUnaired(long unaired) {
        this.unaired = unaired;
    }

    public long getWanted() {
        return wanted;
    }

    public void setWanted(long wanted) {
        this.wanted = wanted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShowStats showStats = (ShowStats) o;

        if (archived != showStats.archived) return false;
        if (ignored != showStats.ignored) return false;
        if (skipped != showStats.skipped) return false;
        if (total != showStats.total) return false;
        if (unaired != showStats.unaired) return false;
        if (wanted != showStats.wanted) return false;
        if (!downloaded.equals(showStats.downloaded)) return false;
        if (!snatched.equals(showStats.snatched)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (archived ^ (archived >>> 32));
        result = 31 * result + downloaded.hashCode();
        result = 31 * result + (int) (ignored ^ (ignored >>> 32));
        result = 31 * result + (int) (skipped ^ (skipped >>> 32));
        result = 31 * result + snatched.hashCode();
        result = 31 * result + (int) (total ^ (total >>> 32));
        result = 31 * result + (int) (unaired ^ (unaired >>> 32));
        result = 31 * result + (int) (wanted ^ (wanted >>> 32));
        return result;
    }
}
