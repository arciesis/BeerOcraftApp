
/**
 * @author Arciesis https://guthub.com/arciesis/BeerOcraftApp/
 */

package xyz.beerocraft.controller;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import xyz.beerocraft.App;
import xyz.beerocraft.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static xyz.beerocraft.model.Fermentable.*;

public class MainCtrl implements Initializable {


    /**
     * the tab Pane of the main window
     */
    @FXML
    private Pane firstPane;

    /**
     * The tab Pane of the main window
     */
    @FXML
    private TabPane firstTabPane;

    /**
     * The fermentable tab of the main window
     */
    @FXML
    private Tab fermentablesTab;

    /**
     * The list view that contains oll the fermentables of the db of the main window
     */
    @FXML
    private ListView<String> fermentablesListView;

    /**
     * The Search malt text field of the main window
     */
    @FXML
    private TextField textfieldSearchMalts;

    /**
     * The Hops Tab of the main window
     */
    @FXML
    private Tab hopsTab;

    /**
     * The textfield to search into the hops
     */
    @FXML
    private TextField hopSerchingTextField;

    /**
     * The list view thatr contains the hops
     */
    @FXML
    private ListView<String> hopsListView;

    /**
     * The grid pane of the hop pane
     */
    @FXML
    private GridPane gridPaneHopsTab;

    /**
     * The Yeasts tabof the main window
     */
    @FXML
    private Tab yeastsTab;

    /**
     * The Hops anchr tab paneof the main window
     */
    @FXML
    private AnchorPane fermentablesTabAnchorPane;

    /**
     * the Hops anchor paneof the main window
     */
    @FXML
    private AnchorPane hopsTabAnchorPane;

    /**
     * the yeasts tab paneof the main window
     */
    @FXML
    private AnchorPane yeastsTabPane;

    /**
     * The label of the name of the fermetable tab
     */
    @FXML
    private Label maltNameLabel;

    /**
     * The ebc label of the fermentable tab
     */
    @FXML
    private Label maltEBCLabel;

    /**
     * The Lolovibond textfield of the fermentable tab
     */
    @FXML
    private Label maltLovibondLabel;

    /**
     * The potential label of the fermentable tab
     */
    @FXML
    private Label maltPotentialLabel;

    /**
     * The type label of the fermentable tab
     */
    @FXML
    private Label maltTypeLabel;

    /**
     * The Name text field of the main window
     */
    @FXML
    private TextField maltNameTextField;

    /**
     * The EBC text field of the main window
     */
    @FXML
    private TextField maltEBCTextField;

    /**
     * The Lovibond text field of the main window
     */
    @FXML
    private TextField maltLovibondTextField;

    /**
     * The potential text fieldof the main window
     */
    @FXML
    private TextField maltPotentialTextField;

    /**
     * The modify buttonof the main window
     */
    @FXML
    private Button maltModifyButton;

    /**
     * The add button fermentable of the main window
     */
    @FXML
    private Button maltAddButton;

    /**
     * The delete button of the main window
     */
    @FXML
    private Button maltDeleteButton;


    /**
     * The type combo box of Hops
     */
    @FXML
    private ComboBox<String> hopTypeComboBox;

    /**
     * The alpha acid text field of the Hops tab
     */
    @FXML
    private TextField hopAlphaTextField;

    /**
     * The Modify button of the Hops tab
     */
    @FXML
    private Button hopModifyButton;

    /**
     * The Add button of the Hop tab
     */
    @FXML
    private Button hopAddButton;

    /**
     * The delete button of the Hops tab
     */
    @FXML
    private Button hopDeleteButton;

    /**
     * the type combo box of the main window
     */
    @FXML
    private ComboBox<String> maltTypeComboBox;

    /**
     * The Object wich handle interaction with the DB
     */
    private FermentableDAO fermentablesDAO;

    private HopDAO hopDAO;

    @FXML
    private ResourceBundle bundle;


    /**
     * Method that Initialize the main controller
     *
     * @param url            not used
     * @param resourceBundle The resources to dynamically set the language
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialisation of the main controller");

        this.bundle = resourceBundle;

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler();
        fermentablesDAO = new FermentableDAO();
        this.hopDAO = new HopDAO();

        loadMaltsToFermentablesListView();
        this.fermentablesListView.setItems(Fermentable.malts);
        this.textfieldSearchMalts.setPromptText("Weyermann");

        loadHopsToHopsListView();
        this.hopsListView.setItems(Hop.hops);


        maltsMouseClicked();
        loadFermentablesTypeToComboBox();

        this.maltNameTextField.setDisable(true);

    }


    /**
     * Constructor of the main controller
     */
    public MainCtrl() {

        System.out.println("Initialisation of the window ");
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/data/arciesis/dev/java/BeerOCraft/src/resources/MainView.fxml"));
        loader.setController(this);*/

    }

    /**
     * Method that load the details of the selectionned fermentable
     */
    @FXML
    private void maltsMouseClicked() {
        //String maltName = listOfFermentablesTab.getSelectionModel().getSelectedItems().get(0);
        //PreparedStatement pstmt = DBHandler.myConn.prepareStatement("SELECT")

        this.fermentablesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                System.out.println("Selectioned malt : " + newValue);

                Fermentable malt = fermentablesDAO.getAllValuesOfAFermentable(newValue);

                maltNameTextField.setText(malt.getName());
                maltEBCTextField.setText(String.valueOf(malt.getEbc()));
                maltLovibondTextField.setText(String.valueOf(malt.getLovibond()));
                maltPotentialTextField.setText(String.valueOf(malt.getPotential()));
                maltTypeComboBox.setValue(malt.getType());
            }
        });
    }


    /**
     * Method that search and clear the list view according thetext typed on the search text field
     *
     * @param event the event that is listened
     */
    @FXML
    private void maltsKeyHasBeenReleased(KeyEvent event) {

        String letters = textfieldSearchMalts.getText();
        fermentablesDAO.searchingForFermentable(letters);

        if (this.textfieldSearchMalts.getText().equalsIgnoreCase("")) {
            this.fermentablesListView.setItems(Fermentable.malts);
        } else {
            this.fermentablesListView.setItems(Fermentable.searchingMalts);
        }
    }


    /**
     * Method that load all the malts contained on the db into the list view of the fermentable tab of the main window
     */
    private void loadMaltsToFermentablesListView() {

        fermentablesDAO.loadFermentableToListViewFromName();

    }

    /**
     * Method that laod the Hops from the db to the HopsListView
     */
    private void loadHopsToHopsListView(){
        hopDAO.loadHopsToListViewFromName();
    }

    /**
     * Method that load the type of malt to the combo box of the tab fermentable of the main window
     */
    private void loadFermentablesTypeToComboBox() {
        if (maltTypeComboBox.getItems().isEmpty()) {

            String[] bundleType = new String[Fermentable.TYPE_POSSIBLE.length];

            for (int i = 0; i < bundleType.length; i++) {
                bundleType[i] = this.bundle.getString(Fermentable.TYPE_POSSIBLE[i]);
            }

            maltTypeChoices.addAll(bundleType);
            maltTypeComboBox.getItems().addAll(maltTypeChoices);

        }
    }

    /**
     * method that handle the add a malt button of the main window
     * in fact this button create a new window to add a malt properly
     *
     * @param event the event that is listened
     */
    @FXML
    private void handleAddMaltButton(ActionEvent event) {

        Parent root;
        try {
            root = App.loadFXML("/home/arciesis/dev/java/BeerOcraft/src/main/resources/fxml/AddAFermentable.fxml");
            Stage addAFermentablePopUp = new Stage();
            addAFermentablePopUp.setTitle(this.bundle.getString("AddFermentable"));
            addAFermentablePopUp.setScene(new Scene(root, 800, 450));
            addAFermentablePopUp.centerOnScreen();
            addAFermentablePopUp.showAndWait();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
        malts.clear();
        loadMaltsToFermentablesListView();
        this.fermentablesListView.setItems(malts);
    }


    /**
     * Method that delete a fermentables of the db
     *
     * @param event the event that is listened
     */
    @FXML
    private void handleDeleteFermentablesButton(ActionEvent event) {
        Alert confirmYouWantToDeleteFermentableAlert = new Alert(Alert.AlertType.INFORMATION, this.bundle.getString("ask.delete"), ButtonType.YES, ButtonType.NO);
        confirmYouWantToDeleteFermentableAlert.setTitle(this.bundle.getString("DeleteFermentable"));
        confirmYouWantToDeleteFermentableAlert.setContentText(this.bundle.getString("alert.core.delete"));
        confirmYouWantToDeleteFermentableAlert.showAndWait();

        if (confirmYouWantToDeleteFermentableAlert.getResult() == ButtonType.YES) {

            System.out.println("remove fermentable confirmed");
            String name = fermentablesListView.getSelectionModel().getSelectedItem();

            fermentablesDAO.deleteFermentableOfDB(name);
            malts.clear();
            loadMaltsToFermentablesListView();
            fermentablesListView.setItems(malts);

        }
    }


    /**
     * The method that handle the modify malt button of the main window
     *
     * @param event that is listened
     */
    @FXML
    private void handleModifyMaltButton(ActionEvent event) {
        System.out.println("Modify fermentable Button clicked");

        String nameOfTheFermentable = fermentablesListView.getSelectionModel().getSelectedItem();
        Fermentable modifiedMalt = fermentablesDAO.selectFermentableFromName(nameOfTheFermentable);

        if (modifiedMalt != null) {
            String stringEbc = maltEBCTextField.getText();
            String stringLovibond = maltLovibondTextField.getText();
            String stringPotential = maltPotentialTextField.getText();
            String stringType = maltTypeComboBox.getSelectionModel().getSelectedItem();

            if (isFloatInput((stringEbc)) && isFloatInput(stringLovibond) && isFloatInput(stringPotential)) {

                float floatEbc = stringToFloatParser(stringEbc);
                float floatLovibond = stringToFloatParser(stringLovibond);
                float floatPotential = stringToFloatParser(stringPotential);

                if (floatPotential >= 0 && floatPotential <= 100) {

                    modifiedMalt.setPotential(floatPotential);
                    modifiedMalt.setType(stringType);

                    if (floatEbc != fermentablesDAO.getEbcFromDB(modifiedMalt)) {
                        ;
                        floatLovibond = (floatEbc + 1.2f) / 2.65f;
                    } else if (floatLovibond != fermentablesDAO.getLovibondFromDB(modifiedMalt)) {
                        floatEbc = (floatLovibond * 2.65f) - 1.2f;
                    }


                    modifiedMalt.setEbc(floatEbc);
                    modifiedMalt.setLovibond(floatLovibond);

                    fermentablesDAO.updateMaltEntry(modifiedMalt);

                    malts.clear();
                    loadMaltsToFermentablesListView();
                    fermentablesListView.setItems(malts);
                    fermentablesListView.getSelectionModel().select(nameOfTheFermentable);

                } else {

                    System.out.println("Potential entry not valid");

                    Alert potentialEntryIsNotValidAlert = new Alert(Alert.AlertType.WARNING);
                    potentialEntryIsNotValidAlert.setTitle(this.bundle.getString("alert.invalid.potential.title"));
                    ;
                    potentialEntryIsNotValidAlert.setContentText(this.bundle.getString("alert.invalid.potential.core"));
                    potentialEntryIsNotValidAlert.showAndWait();

                }
            } else {

                System.out.println("An Input dosen't match requirement to modify this fermentable");

                Alert inputDosentMatchRequirementAlert = new Alert(Alert.AlertType.WARNING);
                inputDosentMatchRequirementAlert.setTitle(this.bundle.getString("alert.input.title"));
                inputDosentMatchRequirementAlert.setContentText(this.bundle.getString("alert.input.core"));
                inputDosentMatchRequirementAlert.showAndWait();
            }

        } else {
            System.out.println("Hmm The name has been modify what can I do ? c'est pour ca qu'il faut que jenbtraite aavec l'id");
        }
    }

    /**
     * Method that handle the text search field on the Hop tab
     *
     * @param event the event listened
     */
    @FXML
    void hopsKeyHasBeenReleased(ActionEvent event) {

        String letters = hopSerchingTextField.getText();

        hopDAO.searchingForHops(letters);

        if (this.hopSerchingTextField.getText().equalsIgnoreCase("")) {
            this.hopsListView.setItems(Hop.hops);
        } else {
            this.hopsListView.setItems(Hop.searchingHops);

        }
    }

    /**
     * Method that handle the click on the delete button on the hop tab
     *
     * @param event the event listened
     */
    @FXML
    void handleHopDeleteButton(ActionEvent event) {

    }

    /**
     * Method that handle the click on the modify button on the Hop tab
     *
     * @param event the event listened
     */
    @FXML
    void handleHopModifyButton(ActionEvent event) {

    }

    /**
     * Metghod that handle the click on the add b utton on the Hop tab
     *
     * @param event the event listened
     */
    @FXML
    void handleHopAddButton(ActionEvent event) {

    }


    /**
     * Test if a String only contains digit
     *
     * @param str The String to test
     * @return True if the String only contains digit
     */
    public static boolean isIntInput(String str) {
        if (str == null || str.trim().equalsIgnoreCase(""))
            return false;

        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] < '0' || c[i] > '9')
                return false;
        }
        return true;
    }

    /**
     * the method that parse a string nto an Int
     *
     * @param str the string to parse
     * @return the parsed int
     */
    public static int stringToIntParser(String str) {
        if (isIntInput(str)) {
            return Integer.parseInt(str);
        } else return -1;
    }

    /**
     * the method that parse a string into a float
     *
     * @param str the strings that need to be parsed
     * @return the parsed float
     */
    public static float stringToFloatParser(String str) {
        if (isFloatInput(str)) {
            return Float.parseFloat(str);
        } else return -1;
    }

    /**
     * the method that test if a string can be cparse into a float
     *
     * @param str the strings to test
     * @return true if the float is parsable and false if it isn't
     */
    public static boolean isFloatInput(String str) {
        if (str == null || str.trim().equalsIgnoreCase(""))
            return false;

        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] < '0' || c[i] > '9') {
                return c[i] == '.';
            }
        }
        return true;
    }
}
