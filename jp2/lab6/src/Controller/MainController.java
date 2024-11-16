package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import Entity.ProductMonthlyCR;
import Entity.ProductMonthlyPerformance;

public class MainController {
    private ProductDailyPerformanceController dailyPerf;

    public MainController() {
        dailyPerf = new ProductDailyPerformanceController();
    }

    public void aggregate() {
        List<ProductMonthlyCR> monthlyCR = dailyPerf.stream().collect(Collectors.groupingBy(
            x -> {
                YearMonth period = YearMonth.of(x.getPeriod().getYear(), x.getPeriod().getMonth());
                return new ProductMonthlyPerformance(
                    Objects.hash(x.getProductId(), period),
                    x.getProductId(), 
                    period
                );
            }
        )).entrySet().stream().map(x -> {
            ProductMonthlyPerformance key = x.getKey();
            x.getValue().stream().forEach(y -> {
                key.setClickCount(key.getClickCount() + y.getClickCount());
                key.setAddToCartCount(key.getAddToCartCount() + y.getAddToCartCount());
                key.setCheckoutCount(key.getCheckoutCount() + y.getCheckoutCount());
            });
            return new ProductMonthlyCR(key);
        }).collect(Collectors.toList());

        try {
            File file = new File(System.getProperty("user.dir"), "data/product_monthly_perf.out.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            monthlyCR.stream().forEach(x -> {
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
