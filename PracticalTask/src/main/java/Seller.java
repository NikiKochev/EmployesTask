import java.util.Objects;

public class Seller {

    private String name;
    private int totalSales;
    private int salesPeriod;
    private double experienceMultiplier;

    public Seller() {
    }

    public Seller(String name, int totalSales, int salesPeriod, double experienceMultiplier) {
        this.name = name;
        this.totalSales = totalSales;
        this.salesPeriod = salesPeriod;
        this.experienceMultiplier = experienceMultiplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public int getSalesPeriod() {
        return salesPeriod;
    }

    public void setSalesPeriod(int salesPeriod) {
        this.salesPeriod = salesPeriod;
    }

    public double getExperienceMultiplier() {
        return experienceMultiplier;
    }

    public void setExperienceMultiplier(double experienceMultiplier) {
        this.experienceMultiplier = experienceMultiplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return totalSales == seller.totalSales && salesPeriod == seller.salesPeriod && Double.compare(seller.experienceMultiplier, experienceMultiplier) == 0 && Objects.equals(name, seller.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, totalSales, salesPeriod, experienceMultiplier);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "name='" + name + '\'' +
                ", totalSales=" + totalSales +
                ", salesPeriod=" + salesPeriod +
                ", experienceMultiplier=" + experienceMultiplier +
                '}';
    }
}
