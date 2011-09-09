package libiada.Reports;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 06.09.11
 * Time: 21:17
 */
public interface IDataExporter {
    void exportToTable(String repotFile, ArrayList<ArrayList<String>> data, ArrayList<String> header) throws Exception, IOException;
}
