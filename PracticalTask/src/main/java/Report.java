
import java.util.*;
import java.util.stream.Collectors;

public class Report {

    private TreeSet<ReportColumn> rows;

    public Report(List <Seller> sellers, ReportDefinition definition) {

        rows = new TreeSet<>((o1,o2)->{
            if(o1.getScore() == o2.getScore()){
                return o1.getName().compareTo(o2.getName());
            }
            return Double.compare(o2.getScore(), o1.getScore());
        });
        addRows(sellers, definition);

    }

    private void addRows(List<Seller> sellers, ReportDefinition definition) {

        TreeMap<Double, TreeSet<ReportColumn>> orderedSellersByScore =
                new TreeMap<>((o1, o2) ->  Double.compare(o2, o1));

        sellers.stream()
                .filter(seller -> seller.getSalesPeriod()<= definition.getPeriodLimit())
                .forEach(seller -> {
                    ReportColumn reportColumn = new ReportColumn(seller, definition);
                    if(!orderedSellersByScore.containsKey(reportColumn.getScore())){
                        orderedSellersByScore.put(reportColumn.getScore(),
                                new TreeSet<>(Comparator.comparing(ReportColumn::getName)));
                    }
                    orderedSellersByScore.get(reportColumn.getScore()).add(reportColumn);
                });

        int limit = Math.max(orderedSellersByScore.size() / definition.getPeriodLimit(), 1);
        orderedSellersByScore
                .entrySet()
                .stream()
                .limit(limit)
                .forEach(s->rows.addAll(s.getValue()));

    }

    public Report() {
    }

    public TreeSet<ReportColumn> getRows() {
        return rows;
    }

    public void setRows(TreeSet<ReportColumn> rows) {
        this.rows = rows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(rows, report.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }

    @Override
    public String toString() {
        return "Report{" +
                "rows=[" + rows.stream().map(ReportColumn::toString) +
                "]"+
                '}';
    }

    public List<String[]> createCsvTemplate(){
        return rows.stream()
                .map(ReportColumn::createCsvTemplate)
                .collect(Collectors.toList());
    }

    public class ReportColumn {
        private String name;
        private double score;

        public ReportColumn(Seller seller, ReportDefinition  definition) {
            this.name = seller.getName();
            this.score = addScore(seller,definition.isUseExprienceMultiplier());
        }

        private double addScore(Seller seller, boolean expMultiplier) {
            if(expMultiplier){
                return (double) seller.getTotalSales()/
                        seller.getSalesPeriod()*
                        seller.getExperienceMultiplier();
            }
            return (double) seller.getTotalSales()/
                    seller.getSalesPeriod();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ReportColumn that = (ReportColumn) o;
            return Double.compare(that.score, score) == 0 && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, score);
        }

        @Override
        public String toString() {
            return "ReportColumn{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }

        public String[] createCsvTemplate() {
            String[] row = new String[2];
            row[0]=this.name;
            row[1] = Double.toString(this.score);
            return row;
        }
    }
}
