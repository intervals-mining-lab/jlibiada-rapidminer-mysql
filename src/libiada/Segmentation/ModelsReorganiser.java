package libiada.Segmentation;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 09.10.11
 * Time: 18:05
 */
public class ModelsReorganiser {
    public SegmentationModel reorganise(ArrayList<SegmentationModel> models) {
        SegmentationModel result = new SegmentationModel();
        for (int i = 0; i < models.size(); i++) {
            result.concat(models.get(i));
        }
        return result;
    }
}
