package libiada.EventTheory;

import libiada.Root.IBaseObject;
import libiada.Root.IBin;
import libiada.Root.ValueInt;

/**
 * Created by IntelliJ IDEA.
 * User: Алексей
 * Date: 30.11.2010
 * Time: 9:08:58
 * To change this template use File | Settings | File Templates.
 */
public class Event extends Space implements IBaseObject {
    public Event() throws Exception {
        super();
    }
    public void addDimension(Dimension dimension)
    {
        super.addDimension(dimension);
    }

    public IBaseObject Clone() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean Equals(Object obj) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public IBin GetBin() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addToReadRule(Place key, Place value) {
    }

    public void removeFromReadRuleAt(int index) {
    }

    public void removeFromReadRule(Place key) {
    }

    public void addItem(IBaseObject value, Place key) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void addToReadRule(Place value, ReadRule rule) {
        //To change body of created methods use File | Settings | File Templates.
    }

    ///<summary>
    /// �������� ������� ��� ����� Key.
    /// Key ������ ������������ ������� �������, ����� ���������� ����������.
    ///</summary>
    ///<param name="Key"></param>
    ///<returns></returns>
    public ReadRule getFromReadRule(Place key) throws Exception {
        if (key == null)
        {
            throw new NullPointerException("�������� ���� ������� ��� ������ ������");
        }
        return null;
    }

    public long getReadRuleCount() {
        return 0;
    }
}
