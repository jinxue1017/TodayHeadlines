package bean;

import com.google.gson.annotations.SerializedName;

public class Version {
    @SerializedName("has_new_version")
    private boolean hasNewVersion;
    @SerializedName("must_update")
    private boolean mustUpdate;
    private String url;

    public boolean isHasNewVersion() {
        return hasNewVersion;
    }

    public void setHasNewVersion(boolean hasNewVersion) {
        this.hasNewVersion = hasNewVersion;
    }

    public boolean isMustUpdate() {
        return mustUpdate;
    }

    public void setMustUpdate(boolean mustUpdate) {
        this.mustUpdate = mustUpdate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
