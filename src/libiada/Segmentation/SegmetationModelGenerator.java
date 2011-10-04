package libiada.Segmentation;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 05.10.11
 * Time: 1:10
 */
public class SegmetationModelGenerator {
    private int length = 0;

    public SegmetationModelGenerator(int length) {
        this.length = length;
    }


    public ArrayList<SegmentationModel> generate() throws CloneNotSupportedException {
        ArrayList<SegmentationModel> result = new ArrayList<SegmentationModel>();
        result.add(new SegmentationModel("1"));
        return generate(result, length-1);
    }

    private ArrayList<SegmentationModel> generate(ArrayList<SegmentationModel> models, int iteration) throws CloneNotSupportedException {
        ArrayList<SegmentationModel> result = new ArrayList<SegmentationModel>();
        if (iteration > 0) {
            for (int i = 0; i < models.size(); i++) {
                SegmentationModel model1 = models.get(i).clone();
                model1.add(true);
                result.add(model1);

                SegmentationModel model2 = models.get(i).clone();
                model2.add(false);
                result.add(model2);
            }
        }
        else return models;
        return generate(result, iteration-1);
    }
}
