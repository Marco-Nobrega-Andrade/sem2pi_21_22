package app.domain.model.SortingAlgorithms;

import app.domain.model.VaccineAdministration.VaccineAdministration;

import java.util.ArrayList;

public interface SortingAlgorithms {
    ArrayList<VaccineAdministration> sortList(String criteria, String order, VaccineAdministration[] list);
}
