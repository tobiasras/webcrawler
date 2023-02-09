package tobiasras.webcrawler.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListUtilities<Object> {

    public List<List<Object>> splitList(List<Object> origin, int splitAmount) {
        List<List<Object>> listOfList = new ArrayList<>();
        int startIndex = 0;
        int endIndex = 0;
        double increment = (double) origin.size() / (double) splitAmount;
        for (int i = 0; i < splitAmount; i++) {

            endIndex = (int) Math.ceil(increment * (i + 1));


            List<Object> sublist = new ArrayList<>(origin.subList(startIndex, endIndex));
            listOfList.add(sublist);
            startIndex = endIndex;
        }
        return listOfList;
    }


}
