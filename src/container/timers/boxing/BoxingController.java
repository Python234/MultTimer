package container.timers.boxing;

import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import container.database.dbConnection;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoxingController {

    /////////////////////////////////////////////////////////////////////
    // *********************** FXML CONTROLS ************************* //
    /////////////////////////////////////////////////////////////////////
    private final Label               lbScreen;
    private final Label               lbRoundNumber;
    private final Label               lbRoundTime;
    private final Label               lbRestTime;
    private final JFXButton           btnSettings;
    private final JFXButton           btnBack;
    private final JFXButton           btnAction;
    private final JFXButton           btnCancel;
    private final JFXButton           btnSave;
    private final JFXButton           btnNew;
    private final ScrollPane          scrollPane;
    private final ColorPicker         cpRoundColor;
    private final ColorPicker         cpRoundWarningColor;
    private final ColorPicker         cpRestColor;
    private final JFXCheckBox         ckbEnableVoice;
    private final JFXCheckBox         ckbEnableEndOfRestAlert;
    private final JFXComboBox<Object> cbProfileNames;
    private final JFXComboBox<Object> cbStartEndSound;
    private final JFXComboBox<Object> cbRoundWarningSound;
    private final JFXComboBox<Object> cbInnerPeriodicSound;
    private final JFXComboBox<Object> cbEndOfRestSound;
    private final JFXComboBox<Object> cbNumberOfRounds;
    private final JFXComboBox<Object> cbRoundMin;
    private final JFXComboBox<Object> cbRoundSec;
    private final JFXComboBox<Object> cbRestMin;
    private final JFXComboBox<Object> cbRestSec;
    private final JFXComboBox<Object> cbPrepareMin;
    private final JFXComboBox<Object> cbPrepareSec;
    private final JFXComboBox<Object> cbInnerPeriodicAlertMin;
    private final JFXComboBox<Object> cbInnerPeriodicAlertSec;
    private final JFXComboBox<Object> cbEndOfRoundWarningMin;
    private final JFXComboBox<Object> cbEndOfRoundWarningSec;
    //=================================================================//


    ///////////////////////////////////////////////////////////////////////
    // *************************  Global variables ********************* //
    ///////////////////////////////////////////////////////////////////////
    private Profile                  profile_backup;
    private final ArrayList<Object> profileNames = new ArrayList<>();
    private final Profile            profile = new Profile();
    private final Connection         CONN = getConnection();
    // ================================================================= //

    /**
     *
     * Constructor
     *
     * @param labels  all dynamic labels in the Boxing tab.
     * @param buttons   all buttons on the Boxing tab.
     * @param checkBoxes    all check-boxes in boxing tab.
     * @param colorPickers all color-pickers in boxing tab.
     * @param comboBoxes all combo-boxes in boxing tab.
     */
    public BoxingController(ArrayList<Label>            labels,
                            ArrayList<JFXButton>        buttons,
                            ArrayList<JFXCheckBox>      checkBoxes,
                            ArrayList<ColorPicker>      colorPickers,
                            ArrayList<JFXComboBox<Object>>   comboBoxes,
                            ScrollPane                  scrollPane)
    {
        this.lbScreen                   = labels.get(0);
        this.lbRoundNumber              = labels.get(1);
        this.lbRoundTime                = labels.get(2);
        this.lbRestTime                 = labels.get(3);
        this.btnSettings                = buttons.get(0);
        this.btnBack                    = buttons.get(1);
        this.btnAction                  = buttons.get(2);
        this.btnCancel                  = buttons.get(3);
        this.btnSave                    = buttons.get(4);
        this.btnNew                     = buttons.get(5);
        this.ckbEnableVoice             = checkBoxes.get(0);
        this.ckbEnableEndOfRestAlert    = checkBoxes.get(1);
        this.cpRoundColor               = colorPickers.get(0);
        this.cpRoundWarningColor        = colorPickers.get(1);
        this.cpRestColor                = colorPickers.get(2);
        this.cbNumberOfRounds           = comboBoxes.get(0);
        this.cbRoundMin                 = comboBoxes.get(1);
        this.cbRoundSec                 = comboBoxes.get(2);
        this.cbRestMin                  = comboBoxes.get(3);
        this.cbRestSec                  = comboBoxes.get(4);
        this.cbPrepareMin               = comboBoxes.get(5);
        this.cbPrepareSec               = comboBoxes.get(6);
        this.cbInnerPeriodicAlertMin    = comboBoxes.get(7);
        this.cbInnerPeriodicAlertSec    = comboBoxes.get(8);
        this.cbEndOfRoundWarningMin     = comboBoxes.get(9);
        this.cbEndOfRoundWarningSec     = comboBoxes.get(10);
        this.cbStartEndSound            = comboBoxes.get(11);
        this.cbInnerPeriodicSound       = comboBoxes.get(12);
        this.cbRoundWarningSound        = comboBoxes.get(13);
        this.cbEndOfRestSound           = comboBoxes.get(14);
        this.cbProfileNames             = comboBoxes.get(15);
        this.scrollPane                 = scrollPane;
    }


    /**
     * initializes parameters and setting up listeners.
     */
    public void __init() {
        btnSettings.setOnAction(this::showSettingsPane);
        btnBack.setOnAction(this::hideSettingsPane);
        btnCancel.setOnAction(this::cancelAction);
        btnSave.setOnAction(this::saveAction);
        btnNew.setOnAction(this::createNewProfile);

        // setting up listeners on the settings controls to update the profile
        // object on user update
        cbProfileNames.valueProperty().addListener((o, ov, nv) ->            profile.setName((String) nv));
        cbNumberOfRounds.valueProperty().addListener((o, ov, nv) ->          profile.setNumberOfRounds((Integer) nv));
        cbRoundMin.valueProperty().addListener((o, ov, nv) ->                profile.setRoundTimeMin((Integer) nv));
        cbRoundSec.valueProperty().addListener((o, ov, nv) ->                profile.setRoundTimeSec((Integer) nv));
        cbRestMin.valueProperty().addListener((o, ov, nv) ->                 profile.setRestTimeMin((Integer) nv));
        cbRestSec.valueProperty().addListener((o, ov, nv) ->                 profile.setRestTimeSec((Integer) nv));
        cbInnerPeriodicAlertMin.valueProperty().addListener((o, ov, nv) ->   profile.setInnerPeriodicAlertMin((Integer) nv));
        cbInnerPeriodicAlertSec.valueProperty().addListener((o, ov, nv) ->   profile.setInnerPeriodicAlertSec((Integer) nv));
        cbEndOfRoundWarningMin.valueProperty().addListener((o, ov, nv) ->    profile.setEndOfRoundAlertMin((Integer) nv));
        cbEndOfRoundWarningSec.valueProperty().addListener((o, ov, nv) ->    profile.setEndOfRoundAlertSec((Integer) nv));
        cbPrepareMin.valueProperty().addListener((o, ov, nv) ->              profile.setPrepareTimeMin((Integer) nv));
        cbPrepareSec.valueProperty().addListener((o, ov, nv) ->              profile.setPrepareTimeSec((Integer) nv));
        ckbEnableEndOfRestAlert.visibleProperty().addListener((o, ov, nv) -> profile.alertEndOfRest(nv));
        cbStartEndSound.valueProperty().addListener((o, ov, nv) ->           profile.setStartEndSound((String) nv));
        cbInnerPeriodicSound.valueProperty().addListener((o , ov, nv) ->     profile.setPeriodicSound((String) nv));
        cbRoundWarningSound.valueProperty().addListener((o, ov, nv) ->       profile.setRoundWarningSound((String) nv));
        cbEndOfRestSound.valueProperty().addListener((o, ov, nv) ->          profile.setEndOfRestSound((String) nv));
        cpRoundColor.valueProperty().addListener((o, ov, nv) ->              profile.setRoundColor(nv));
        cpRoundWarningColor.valueProperty().addListener((o, ov, nv) ->       profile.setWarningColor(nv));
        cpRestColor.valueProperty().addListener((o, ov, nv) ->               profile.setRestColor(nv));
        ckbEnableVoice.visibleProperty().addListener((o, ov, nv) ->          profile.enableVoice(nv));

        profile.roundNumberProperty().addListener((o, ov, nv) ->             lbRoundNumber.setText("0/" + nv));
        profile.roundMinProperty().addListener((o, ov, nv) ->                lbRoundTime.setText(nv + ":" + profile.getRoundTimeSec()));
        profile.roundSecProperty().addListener((o, ov, nv) ->                lbRoundTime.setText(profile.getRoundTimeMin() + ":" + nv));
        profile.restMinProperty().addListener((o, ov, nv) ->                 lbRestTime.setText(nv + ":" + profile.getRestTimeSec()));
        profile.restSecProperty().addListener((o, ov, nv) ->                 lbRestTime.setText(profile.getRestTimeMin() + ":" + nv));

        cpRoundColor.getCustomColors().addListener((ListChangeListener<Color>) c -> updateCustomColors("roundColors", c.getList()));
        cpRoundWarningColor.getCustomColors().addListener((ListChangeListener<Color>) c -> updateCustomColors("warningColors", c.getList()));
        cpRestColor.getCustomColors().addListener((ListChangeListener<Color>) c -> updateCustomColors("restColors", c.getList()));

        profile.changeProperty().addListener((o, ov, nv) ->                 { if (ov != nv) profileChangeHandler(nv); });


        // adding items to the combo-boxes
        ArrayList<Integer> items = new ArrayList<>();
        ArrayList<Integer> rounds = new ArrayList<>();

        for (int i = 0; i < 60; i++) {
            items.add(i);
            rounds.add(i + 1);
        }

        cbRestMin.getItems().setAll(items);
        cbRestSec.getItems().setAll(items);
        cbRoundMin.getItems().setAll(items);
        cbRoundSec.getItems().setAll(items);
        cbInnerPeriodicAlertMin.getItems().setAll(items);
        cbInnerPeriodicAlertSec.getItems().setAll(items);
        cbEndOfRoundWarningMin.getItems().setAll(items);
        cbEndOfRoundWarningSec.getItems().setAll(items);
        cbPrepareMin.getItems().setAll(items);
        cbPrepareSec.getItems().setAll(items);
        cbNumberOfRounds.getItems().setAll(rounds);

        // load profile names.
        getProfileNames();

        // load sound file names.
        getSoundFileNames();

        // load custom colors.
        getCustomColors();

        // load active profile.
        getActiveProfile();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    // ************************************* Event Handlers ************************************** //
    /////////////////////////////////////////////////////////////////////////////////////////////////
    private void showSettingsPane(ActionEvent event) {
        scrollPane.setVisible(true);
        new FadeIn(scrollPane).play();
        scrollPane.setDisable(false);

        // hide settings button
        new FadeOutLeft(btnSettings).play();
        btnSettings.setDisable(true);

        // hide action button
        hideButton(btnAction);

        // show back button
        showButton(btnBack, false);

        // show new button
        showButton(btnNew, false);

        // show save button
        showButton(btnSave, true);

        // show cancel button
        showButton(btnCancel, true);

        event.consume();
    }

    private void hideSettingsPane(ActionEvent event) {
        // hide settings pane
        new FadeOut(scrollPane).play();
        scrollPane.setDisable(true);

        // show settings button
        new FadeInLeft(btnSettings).play();
        btnSettings.setDisable(false);

        // hide back button
        hideButton(btnBack);

        // hide new button
        hideButton(btnNew);

        // hide save button
        hideButton(btnSave);

        // hide cancel button
        hideButton(btnCancel);

        // show action button
        showButton(btnAction, false);

        btnCancel.fire();

        event.consume();
    }

    private void cancelAction(ActionEvent event) {
        // TODO: code for cancel button hire
        event.consume();
    }

    private void saveAction(ActionEvent event) {
        System.out.println(profile.equals(profile_backup));
        profile_backup = profile.copy();
        System.out.println(profile.equals(profile_backup));
        // TODO: code for save button hire
        event.consume();
    }

    private void createNewProfile(ActionEvent event) {
        // TODO: code for creating new profile hire
        if (profile.hasItems()) profile_backup = profile.copy();

        profileNames.clear();
        profileNames.addAll(cbProfileNames.getItems());
        clearSettingFields();
        cbProfileNames.getItems().clear();
        cbProfileNames.setEditable(true);

        btnCancel.setDisable(false);
        event.consume();
    }

    private void profileChangeHandler(boolean changed) {
        btnSave.setDisable(!changed);
        btnCancel.setDisable(!changed);
        btnNew.setDisable(cbProfileNames.isEditable());
    }
    // ======================================================================================== //


    ///////////////////////////////////////////////////////////////////////////////////////////////
    // ************************************ Database Interactions ****************************** //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private Connection getConnection() {
        try { return dbConnection.getConnection(); }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private void getProfileNames() {
        try {
            String query = "SELECT name FROM tbl_profiles";

            assert CONN != null;
            PreparedStatement pstmt = CONN.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (rs.isClosed()) return;
            while(rs.next()) cbProfileNames.getItems().add(rs.getString("name"));

            rs.close();
            pstmt.close();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    private void getSoundFileNames() {
        try {
            String query = "SELECT fileName FROM tbl_sounds ORDER BY fileName";

            assert CONN != null;
            PreparedStatement pstmt = CONN.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (rs.isClosed()) return;
            while (rs.next()) {
                cbStartEndSound.getItems().add(rs.getString("fileName"));
                cbRoundWarningSound.getItems().add(rs.getString("fileName"));
                cbInnerPeriodicSound.getItems().add(rs.getString("fileName"));
                cbEndOfRestSound.getItems().add(rs.getString("fileName"));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    private void getCustomColors() {
        try {
            String query = "SELECT * FROM tbl_customColors";

            assert CONN != null;
            PreparedStatement pstmt = CONN.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (rs.isClosed()) return;
            while (rs.next()) {
                switch (rs.getString("for")) {
                    case "roundColors":
                        addCustomColors(cpRoundColor, rs.getString("colors"));
                        break;
                    case "warningColors":
                        addCustomColors(cpRoundWarningColor, rs.getString("colors"));
                        break;
                    case "restColors":
                        addCustomColors(cpRestColor, rs.getString("colors"));
                        break;
                }
            }

            rs.close();
            pstmt.close();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    private void getActiveProfile() {
        try {
            String query = "SELECT * FROM tbl_profiles WHERE state=1";

            assert CONN != null;
            PreparedStatement pstmt = CONN.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.isClosed()) {
                cbProfileNames.setValue(rs.getString("name"));
                cbNumberOfRounds.setValue(rs.getInt("rounds"));
                cbRoundMin.setValue(getMinutes(rs.getString("workTime")));
                cbRoundSec.setValue(getSeconds(rs.getString("workTime")));
                cbRestMin.setValue(getMinutes(rs.getString("restTime")));
                cbRestSec.setValue(getSeconds(rs.getString("restTime")));
                cbEndOfRoundWarningMin.setValue(getMinutes(rs.getString("warningTime")));
                cbEndOfRoundWarningSec.setValue(getSeconds(rs.getString("warningTime")));
                cbInnerPeriodicAlertMin.setValue(getMinutes(rs.getString("periodicTime")));
                cbInnerPeriodicAlertSec.setValue(getSeconds(rs.getString("periodicTime")));
                ckbEnableEndOfRestAlert.setSelected(rs.getBoolean("alertEndOfRest"));
                cbStartEndSound.setValue(rs.getString("startEndSound"));
                cbRoundWarningSound.setValue(rs.getString("warningSound"));
                cbInnerPeriodicSound.setValue(rs.getString("periodicSound"));
                cbEndOfRestSound.setValue(rs.getString("endOfRestSound"));
                cpRoundColor.setValue(stringToColor(rs.getString("roundColor")));
                cpRoundWarningColor.setValue(stringToColor(rs.getString("warningColor")));
                cpRestColor.setValue(stringToColor(rs.getString("restColor")));
                ckbEnableVoice.setSelected(rs.getBoolean("enableVoice"));
            }

            rs.close();
            pstmt.close();

        } catch (SQLException ex) { ex.printStackTrace(); }
    }



    private void updateCustomColors(String columnLabel, ObservableList colorsList){
        try {
            String query = "UPDATE tbl_customColors SET colors=? WHERE for=?";

            assert CONN != null;
            PreparedStatement pstmt = CONN.prepareStatement(query);
            pstmt.setString(1, colorsList.toString());
            pstmt.setString(2, columnLabel);
            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
    // ============================================================================================= //


    //////////////////////////////////////////////////////////////////////////////////////////////////
    // *************************************** Helper Methods ************************************* //
    //////////////////////////////////////////////////////////////////////////////////////////////////
    private void hideButton(JFXButton button) {
        new FadeOutDown(button).play();
        button.setDisable(true);
    }

    private void showButton(JFXButton button, boolean disabled) {
        new FadeInUp(button).play();
        button.setDisable(disabled);
        button.setVisible(true);
    }

    private void addCustomColors(ColorPicker colorPicker, String colorsString) {
        // do nothing if their are no custom colors.
        if (colorsString.equals("[]")) return;

        String[] colorStringArray = colorsString.replace("[","").replace("]","").split(",");
        for (String colorString : colorStringArray)
            colorPicker.getCustomColors().add(stringToColor(colorString.trim()));
    }

    private void clearSettingFields() {
        cbProfileNames.setValue(null);
        cbNumberOfRounds.setValue(0);
        cbRoundMin.setValue(0);
        cbRoundSec.setValue(0);
        cbRestMin.setValue(0);
        cbRestSec.setValue(0);
        cbEndOfRoundWarningMin.setValue(0);
        cbEndOfRoundWarningSec.setValue(0);
        cbInnerPeriodicAlertMin.setValue(0);
        cbInnerPeriodicAlertSec.setValue(0);
        cbPrepareMin.setValue(0);
        cbPrepareSec.setValue(0);
        ckbEnableEndOfRestAlert.setSelected(false);
        cbStartEndSound.setValue("default");
        cbRoundWarningSound.setValue("default");
        cbInnerPeriodicSound.setValue("default");
        cbEndOfRestSound.setValue("default");
        cpRoundColor.setValue(Color.GREEN);
        cpRoundWarningColor.setValue(Color.ORANGE);
        cpRestColor.setValue(Color.RED);
        ckbEnableVoice.setSelected(false);
    }

    private int getMinutes(String time) { return Integer.valueOf(time.split(":")[0]); }

    private int getSeconds(String time) { return Integer.valueOf(time.split(":")[1]); }

    private String joinTime(int minutes, int seconds) { return minutes + ":" + seconds; }

    private Color stringToColor(String colorString) { return Color.valueOf(colorString); }

    private String colorToString(Color color) { return color.toString(); }
    // ========================================================================================= //
}
