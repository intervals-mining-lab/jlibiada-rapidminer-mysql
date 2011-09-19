package libiada.Reports.Docx;

/*import libiada.Reports.IDataExporter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;*/

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 08.09.11
 * Time: 16:02
 */
public class DocxPOIReporter {} /*implements IDataExporter {
    private void save(XWPFDocument document, String repotFile) {
        FileOutputStream outputStream = null;
        try{
            outputStream = new FileOutputStream(repotFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            document.write(outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportToTable(String repotFile, ArrayList<ArrayList<String>> data, ArrayList<String> header) throws Exception, IOException {
        XWPFDocument document = new XWPFDocument();

        XWPFTable table = document.createTable();
        XWPFTableRow row = table.getRow(0);
        row.setHeight(120);
        row.getCell(0).setText(header.get(0));
        for (int i = 1; i < header.size(); i++) {
            row.createCell().setText(header.get(i));
        }

        for (int i = 0; i < data.size(); i++) {
            ArrayList<String> rowData = data.get(i);
            row = table.createRow();
            for (int j = 0; j < rowData.size(); j++) {
                row.getCell(j).setText(rowData.get(j));
            }
        }

        save(document, repotFile);
    }
}   */
