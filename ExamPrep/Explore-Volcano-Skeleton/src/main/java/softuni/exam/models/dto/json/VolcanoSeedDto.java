package softuni.exam.models.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class VolcanoSeedDto implements Serializable {
    @Expose
    @Size(min = 2, max = 30)
    private String name;

    @Expose
    @Positive
    private int elevation;
    @Expose
    private String volcanoType;
    @Expose
    @NotNull
    private boolean isActive;
    @Expose
    private String lastEruption;
    @Expose
    private long country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public String getVolcanoType() {
        return volcanoType;
    }

    public void setVolcanoType(String volcanoType) {
        this.volcanoType = volcanoType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getLastEruption() {
        return lastEruption;
    }

    public void setLastEruption(String lastEruption) {
        this.lastEruption = lastEruption;
    }

    public long getCountry() {
        return country;
    }

    public void setCountry(long country) {
        this.country = country;
    }
}
