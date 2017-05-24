import com.towli.BasicNaiveBayes;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.Discretize;

/**
 * Created by Towli on 24/05/2017.
 */
public class ExtendedNaiveBayes extends BasicNaiveBayes {

    public ExtendedNaiveBayes() {
        super();
    }

    /**
     * Makes use of Discretize filter to migrate data to a categorical (discrete) set.
     * @return
     * @throws Exception
     */
    public Instances discretizeInstances() throws Exception {
        Discretize discretize = new Discretize();
        discretize.setInputFormat(this.instances);
        this.instances = Filter.useFilter(this.instances, discretize);
        return this.instances;
    }

}
