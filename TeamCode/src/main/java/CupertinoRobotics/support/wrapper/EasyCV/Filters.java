package CupertinoRobotics.support.wrapper.EasyCV;

import java.util.ArrayList;
import java.util.Arrays;

public final class Filters {
    Rectangle imgBound = null;

    final ArrayList<CustomFilter> filters = new ArrayList<>();

    public void setImageBound(Rectangle r){
        imgBound = r;
    }

    public void addCustomFilters(CustomFilter... f){
        filters.addAll(Arrays.asList(f));
    }
}
