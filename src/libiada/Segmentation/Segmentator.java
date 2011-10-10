package libiada.Segmentation;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.Segmentation.Criteria.Criteria;
import libiada.Segmentation.Dividers.ChainDivider;
import libiada.Statistics.Generators.IGenerator;
import org.apache.commons.collections15.iterators.ArrayListIterator;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 09.10.11
 * Time: 17:08
 */
public class Segmentator {
    private int maxWordLength = 0;
    private IGenerator generator = null;
    private Criteria criteria = null;

    public Segmentator(int maxWordLength, Criteria criteria, IGenerator generator) {
        this.maxWordLength = maxWordLength;
        this.generator = generator;
        this.criteria = criteria;
    }

    public FastChain segmetate(FastChain chain) throws Exception {
        ChainDivider divider = new ChainDivider(maxWordLength, generator);
        ArrayList<FastChain> chains = divider.divide(chain);
        SimpleSegmentator simpleSegmentator = new SimpleSegmentator();
        ArrayList<SegmentationModel> bestModels = new ArrayList<SegmentationModel>();
        for (int i = 0; i < chains.size(); i++) {
            FastChain current = chains.get(i);
            SegmetationModelGenerator modelGenerator = new SegmetationModelGenerator(current.length());
            ArrayList<SegmentationModel> models = modelGenerator.generate();
            bestModels.add(criteria.getBest(current, models));
        }
        ModelsReorganiser reorganiser = new ModelsReorganiser();
        SegmentationModel resultmodel = reorganiser.reorganise(bestModels);
        return simpleSegmentator.segment(chain, resultmodel);
    }
}