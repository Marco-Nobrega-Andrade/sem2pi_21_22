package app.domain.model.VaccineAdministration;

import java.util.Comparator;

public class ArrivalTimeComparator implements Comparator<VaccineAdministration> {
    /**
     * Compares vaccine administration objects by Arrival date and time
     * @param o1 vaccine administration object to compare
     * @param o2 vaccine administration object to compare
     * @return -1 if o1 is before o2, 0 if they are at the same time and 1 if o1 is after o2
     */
    @Override
    public int compare(VaccineAdministration o1, VaccineAdministration o2) {
        if (o1.getArrivalDate().compareTo(o2.getArrivalDate()) == 0) {
            return o1.getArrivalTime().compareTo(o2.getArrivalTime());
        }else {
            return o1.getArrivalDate().compareTo(o2.getArrivalDate());
        }
    }
}
