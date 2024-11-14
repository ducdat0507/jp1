package General;

import java.io.File;
import java.util.List;

public interface IFileService<T> {
    public List<T> read(File target);
    public List<T> write(File target);
}
