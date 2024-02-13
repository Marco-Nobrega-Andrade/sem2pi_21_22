package app.domain.model.SortingAlgorithms;

import app.domain.model.VaccineAdministration.ArrivalTimeComparator;
import app.domain.model.VaccineAdministration.LeavingTimeComparator;
import app.domain.model.VaccineAdministration.VaccineAdministration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeSort implements SortingAlgorithms{
    /**
     * Sorts an Array by criteria and order
     * @param criteria what the array should be sorted by (in this case, by arrival or leaving time)
     * @param order in what order, ascending or descending, should the array be sorted by
     * @param list array to be sorted
     * @return sorted list
     */
    @Override
    public ArrayList<VaccineAdministration> sortList(String criteria, String order, VaccineAdministration[] list) {
        if(criteria.equalsIgnoreCase("Arrival time")){
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
        VaccineAdministration[] ascArray = mergeSort(list, comparator);
        ArrayList<VaccineAdministration> ascList= new ArrayList<>(List.of(ascArray));

        if (order.equalsIgnoreCase("Descending")) {
            Collections.reverse(ascList);
        }
        return ascList;

    }

    /**
     * Keeps dividing the array in 2 parts until both are arrays of length 1 or 0 (arrays always sorted), merging them together according to the comparator
     * @param list array to be divided
     * @param comparator defines the object´s comparation criteria
     * @return if the input list has length less than 2, returns that list, otherwise returns the 2 parts merged in order
     */
    private static VaccineAdministration[] mergeSort(VaccineAdministration[] list, Comparator<VaccineAdministration> comparator){
        int inputLength = list.length;

        if (inputLength < 2){
            return list;
        }

        int midIndex = inputLength / 2 ;
        VaccineAdministration[] leftHalf = new VaccineAdministration[midIndex];
        VaccineAdministration[] rightHalf = new VaccineAdministration[inputLength-midIndex];

        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = list[i];
        }

        for (int i = midIndex; i < inputLength; i++) {
            rightHalf[i-midIndex] = list[i];
        }
        mergeSort(leftHalf, comparator);
        mergeSort(rightHalf, comparator);

        return merge(list,leftHalf,rightHalf,comparator);
    }

    /**
     * Merges the 2 list, ordering them by the criteria
     * @param list input list
     * @param leftHalf array to be merged
     * @param rightHalf array to be merged
     * @param comparator criteria to order by
     * @return merged list
     */
    private static VaccineAdministration[] merge(VaccineAdministration[] list, VaccineAdministration[] leftHalf, VaccineAdministration[] rightHalf, Comparator<VaccineAdministration> comparator) {

        int leftLength = leftHalf.length;
        int rightLength = rightHalf.length;

        int i = 0, j = 0, k = 0;

        while (i < leftLength && j < rightLength){
            if (comparator.compare(leftHalf[i],rightHalf[j]) > 0 ){
                list[k] = rightHalf[j];
                j++;
            }else{
                list[k] = leftHalf[i];
                i++;
            }
            k++;
        }

        while (i < leftLength){
            list[k] = leftHalf[i];
            i++;
            k++;
        }

        while (j < rightLength){
            list[k] = rightHalf[j];
            j++;
            k++;
        }
        return list;
    }


}
