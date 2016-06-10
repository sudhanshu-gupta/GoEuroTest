package test.goeuro.wrapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sudhanshu.gupta on 08/06/16.
 */
public interface FileWriter {

        public void printRecord(Iterable<?> records) throws IOException;

        public void printRecords(Iterable<?> record) throws IOException;
}
