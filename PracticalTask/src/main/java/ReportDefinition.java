import java.util.Objects;

public class ReportDefinition {

    private int topPerformersThreshold;
    private boolean useExprienceMultiplier;
    private int periodLimit;

    public ReportDefinition() {
    }

    public ReportDefinition(int topPerformersThreshold, boolean useExprienceMultiplier, int periodLimit) {
        this.topPerformersThreshold = topPerformersThreshold;
        this.useExprienceMultiplier = useExprienceMultiplier;
        this.periodLimit = periodLimit;
    }

    public int getTopPerformersThreshold() {
        return topPerformersThreshold;
    }

    public void setTopPerformersThreshold(int topPerformersThreshold) {
        this.topPerformersThreshold = topPerformersThreshold;
    }

    public boolean isUseExprienceMultiplier() {
        return useExprienceMultiplier;
    }

    public void setUseExprienceMultiplier(boolean useExprienceMultiplier) {
        this.useExprienceMultiplier = useExprienceMultiplier;
    }

    public int getPeriodLimit() {
        return periodLimit;
    }

    public void setPeriodLimit(int periodLimit) {
        this.periodLimit = periodLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportDefinition that = (ReportDefinition) o;
        return topPerformersThreshold == that.topPerformersThreshold && useExprienceMultiplier == that.useExprienceMultiplier && periodLimit == that.periodLimit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(topPerformersThreshold, useExprienceMultiplier, periodLimit);
    }

    @Override
    public String toString() {
        return "ReportDefinition{" +
                "topPerformersThreshold=" + topPerformersThreshold +
                ", useExprienceMultiplier=" + useExprienceMultiplier +
                ", periodLimit=" + periodLimit +
                '}';
    }
}
