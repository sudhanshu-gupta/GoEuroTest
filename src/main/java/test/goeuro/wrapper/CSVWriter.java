package test.goeuro.wrapper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * Created by sudhanshu.gupta on 08/06/16.
 */

/**
 * <p>This class is responsible for writing records (data) in the csv format to a local file.
 * It implements the FileWriter interface and overrides its two methods</p>
 */
public class CSVWriter implements FileWriter {
    private static final Logger logger = LoggerFactory.getLogger(CSVWriter.class.getName());
    private static final Character newLineSeperator = '\n';
    private static final Character delimiter = ',';
    CSVPrinter csvPrinter;

    /**
     * <p>CSVWriter Constructor which initialize the CSVFormat and CSVPrinter objects and sets some basic values</p>
     * @param fileName Its accepts a filename where it will write the records
     * @throws IOException
     */
    public CSVWriter(String fileName) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(delimiter)
                .withRecordSeparator(newLineSeperator).withFirstRecordAsHeader();
        csvPrinter = new CSVPrinter(new java.io.FileWriter(fileName), csvFormat);
    }

    /**
     * <p>It write list of records to output file in the csv format with default separator as comma.
     * It calls the CSVPrinter.printRecords method for printing</p>
     * @param records Accepts list of records
     * @throws IOException
     */
    @Override
    public void printRecords(Iterable<?> records) throws IOException {
        try {
            csvPrinter.printRecords(records);
        } catch (IOException e) {
            throw new IOException("IO exception occurs while writing to file");
        }
    }

    /**
     * <p>It writes a record to output file in the csv format with default separator as comma.
     * It calls the CSVPrinter.printRecord method for printing</p>
     * @param record Accept a record which implements Iterable interface
     * @throws IOException
     */
    @Override
    public void printRecord(Iterable<?> record) throws IOException {
        try {
            csvPrinter.printRecord(record);
        } catch (IOException e) {
            throw new IOException("IO exception occurs while writing to file");
        }
    }

    /**
     * <p>This is the clean up method which closes the file object. It is annotated with @PreDestroy annotation
     * which get calls before the class instance is destroyed.</p>
     * @throws IOException
     */
    @PreDestroy
    public void cleanUp() throws IOException {
        csvPrinter.close();
    }
}
