import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Entity.CRStatistics;
import Entity.StatisticsView;
import Service.FileService;

public class Main {
    public static void main(String[] args) {
        File statViewFile = new File(
            System.getProperty("user.dir"), 
            "jp2/CRRating/data/statistics.view.txt"
        );
        FileService fs = new FileService();
        List<StatisticsView> statViews = fs.read(statViewFile);
        statViews.stream().forEach(System.out::println);

        Map<CRStatistics, CRStatistics> dataCRS = statViews.stream()
            .collect(
                Collectors.groupingBy(
                    x -> new CRStatistics(x.getId(), x.getMonthOfDate(), x.getYearOfDate()),
                    Collectors.collectingAndThen(Collectors.toList(), list -> {
                        var first = list.get(0);
                        return new CRStatistics(
                            first.getId(),
                            first.getMonthOfDate(),
                            first.getYearOfDate(),
                            list.stream().mapToInt(StatisticsView::getView).sum(),
                            list.stream().mapToInt(StatisticsView::getAddToCart).sum(),
                            list.stream().mapToInt(StatisticsView::getCheckOut).sum()
                        );
                    })
                )
            );

        dataCRS.values().stream().forEach(System.out::println);
    }
}
