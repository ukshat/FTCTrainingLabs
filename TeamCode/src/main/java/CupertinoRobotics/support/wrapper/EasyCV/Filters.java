package CupertinoRobotics.support.wrapper.EasyCV;

import java.util.ArrayList;
import java.util.Arrays;

public class Filters {
    Rectangle imgBound = null;

    final ArrayList<CustomFilter> filters = new ArrayList<>();

    public void setImageBound(Rectangle r){
        imgBound = r;
    }

    public void addFilters(CustomFilter... f){
        filters.addAll(Arrays.asList(f));
    }
}
