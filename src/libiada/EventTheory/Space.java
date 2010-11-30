package libiada.EventTheory;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 30.11.2010
 * Time: 9:13:07
 * To change this template use File | Settings | File Templates.
 */
public class Space {
    private ArrayList<Dimension> pDimentions;

    public Place getPlacePattern() {
        return new Place(pDimentions);
     }
}