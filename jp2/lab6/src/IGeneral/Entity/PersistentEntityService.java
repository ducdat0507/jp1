package IGeneral.Entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class PersistentEntityService<T extends Entity> extends EntityService<T> {
    private File file;

    public PersistentEntityService(File file) {
        this.file = file;
        load();
    }

    public void load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.lines().forEach(x -> {
                T ins = instanceT(x);
                add(ins);
                nextId = Math.max(nextId, ins.getId());
            });
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            stream().forEach(x -> {
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

    protected T instanceT(String line) {
        throw new UnsupportedOperationException("Instance of T method not implemented");
    }
}
