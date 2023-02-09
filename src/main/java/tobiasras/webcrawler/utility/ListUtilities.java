package tobiasras.webcrawler.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListUtilities<Object> {

    public static void main(String[] args) {
        ListUtilities<String> utilities = new ListUtilities<>();
        LinkedList<String> list = new LinkedList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("2");
        list.add("2");
        list.add("2");
        list.add("3");


        var listOfList = utilities.splitList(list, 2);

        for (List<String> strings : listOfList) {
            //System.out.println(strings.size());

            for (String string : strings) {
                System.out.println(string);
            }


        }

    }

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
