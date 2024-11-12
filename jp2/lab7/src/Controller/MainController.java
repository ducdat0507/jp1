package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Entity.ProductMonthlyPerformance;
import Service.ProductDailyPerformanceService;

public class MainController {
    private ProductDailyPerformanceController dailyPerf;

    public MainController() {
        dailyPerf = new ProductDailyPerformanceController();
    }

    public void aggregate() {
        List<ProductMonthlyPerformance> monthlyPref = dailyPerf.stream().collect(Collectors.groupingBy(
            x -> new ProductMonthlyPerformance(
                x.getProductId() * 31 + YearMonth.of(x.getPeriod().getYear(), x.getPeriod().getMonth()).hashCode(),
                x.getProductId(), 
                YearMonth.of(x.getPeriod().getYear(), x.getPeriod().getMonth())
            )
        )).entrySet().stream().map(x -> {
            ProductMonthlyPerformance key = x.getKey();
            x.getValue().stream().forEach(y -> {
                key.setClickCount(key.getClickCount() + y.getClickCount());
                key.setAddToCartCount(key.getAddToCartCount() + y.getAddToCartCount());
                key.setCheckoutCount(key.getCheckoutCount() + y.getCheckoutCount());
            });
            return key;
        }).collect(Collectors.toList());

        try {
            File file = new File(System.getProperty("user.dir"), "jp2/lab7/data/product_monthly_perf.out.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            monthlyPref.stream().forEach(x -> {
                try {
                    writer.write(x.toSaveString());
                    writer.newLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
