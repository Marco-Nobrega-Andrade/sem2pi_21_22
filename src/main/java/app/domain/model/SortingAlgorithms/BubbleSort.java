package app.domain.model.SortingAlgorithms;

import app.domain.model.VaccineAdministration.ArrivalTimeComparator;
import app.domain.model.VaccineAdministration.LeavingTimeComparator;
import app.domain.model.VaccineAdministration.VaccineAdministration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BubbleSort implements SortingAlgorithms{

    /**
     * Sorts an Array by criteria and order
     * @param criteria what the array should be sorted by (in this case, by arrival or leaving time)
     * @param order in what order, ascending or descending, should the array be sorted by
     * @param list array to be sorted
     * @return sorted list
     */
    @Override
    public ArrayList<VaccineAdministration> sortList(String criteria, String order, VaccineAdministration[] list) {
        if(criteria.equalsIgnoreCase("arrival")){
            ArrivalTimeComparator comparator = new ArrivalTimeComparator();
            return sortListByCriteria(order,list, comparator);
        }else {
            LeavingTimeComparator comparator = new LeavingTimeComparator();
            return sortListByCriteria(order,list,comparator);
        }
    }

    /**
     * Sorts the array utilizing the comparator
     * @param order in what order, ascending or descending, should the array be sorted by
     * @param list array to be sorted
     * @param comparator defines the object´s comparation criteria
     * @return sorted list
     */
    private ArrayList<VaccineAdministration> sortListByCriteria(String order, VaccineAdministration[] list, Comparator<VaccineAdministration> comparator){
        VaccineAdministration[] ascArray = bubbleSort(list, comparator);
        ArrayList<VaccineAdministration> ascList= new ArrayList<>(List.of(ascArray));

        if (order.equalsIgnoreCase("Descending")) {
            Collections.reverse(ascList);
        }
        return ascList;

    }

    /**
     * Examining each set of adjacent elements in the array, from left to right, switching their positions if they are out of order
     * @param list array to be sorted
     * @param comparator defines the object´s comparation criteria
     * @return sorted list
     */
    private VaccineAdministration[] bubbleSort(VaccineAdministration[] list, Comparator<VaccineAdministration> comparator) {
        int n = list.length;
        for (int i = 0; i < n - 1; i++){
            for (int j = 0; j < n - i - 1; j++){
                if (comparator.compare(list[j],list[j+1]) > 0) {
                    VaccineAdministration temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
        return list;
    }
}
