module Sem2App {
    requires javafx.controls;
    requires javafx.fxml;
    requires AuthLib;
    requires java.desktop;
    requires java.logging;
    requires commons.lang3;
    requires java.sql;
    requires javafx.graphics;
    requires Sum;

    opens app.ui.gui.ImportDataLegacySystem;
    opens app.ui.gui.AnalyzePerformance;
    opens app.ui.gui.VaccineAdministration;
    opens app.ui.gui.ExportVaccinationStatistics;
    exports app.ui.gui.AnalyzePerformance;
    exports app.ui.gui.ImportDataLegacySystem;
    exports app.ui.gui.VaccineAdministration;
    exports app.ui.gui.ExportVaccinationStatistics;

    exports app.ui.gui.Coordinator;
    opens app.ui.gui.Coordinator;


}