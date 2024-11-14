package Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Entity.StatisticsView;
import General.IFileService;

public class FileService implements IFileService<StatisticsView> { 

    public FileService() {}

    @Override
    public List<StatisticsView> read(File target) {
        List<StatisticsView> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(target));
            reader.lines().forEach(x -> {
                if (x.isEmpty()) return;
                String[] props = x.split(";");
                StatisticsView sv = new StatisticsView(
                    Integer.parseInt(props[0].trim()),
                    Integer.parseInt(props[1].trim()),
                    Integer.parseInt(props[2].trim()),
                    Integer.parseInt(props[3].trim()),
                    LocalDate.parse(props[4].trim())
                );
                list.add(sv);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<StatisticsView> write(File target) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
