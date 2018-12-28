package container.timers.boxing;

import javafx.beans.property.*;
import javafx.scene.paint.Color;

class Profile {
    private final StringProperty          nameProperty;
    private final StringProperty          startEndSound;
    private final StringProperty          periodicSound;
    private final StringProperty          roundWarningSound;
    private final StringProperty          endOfRestSound;
    private final IntegerProperty         numberOfRoundsProperty;
    private final IntegerProperty         roundTimeMinProperty;
    private final IntegerProperty         roundTimeSecProperty;
    private final IntegerProperty         restTimeMinProperty;
    private final IntegerProperty         restTimeSecProperty;
    private final IntegerProperty         prepareTimeMinProperty;
    private final IntegerProperty         prepareTimeSecProperty;
    private final IntegerProperty         innerPeriodicAlertMinProperty;
    private final IntegerProperty         innerPeriodicAlertSecProperty;
    private final IntegerProperty         endOfRoundAlertMinProperty;
    private final IntegerProperty         endOfRoundAlertSecProperty;
    private final ObjectProperty<Color>   roundColor;
    private final ObjectProperty<Color>   warningColor;
    private final ObjectProperty<Color>   restColor;

    private final BooleanProperty         stateProperty;
    private final BooleanProperty         voiceEnabledProperty;
    private final BooleanProperty         changeProperty;
    private boolean                       alertEndOfRest;


    Profile() {
        nameProperty                    = new SimpleStringProperty();
        startEndSound                   = new SimpleStringProperty();
        periodicSound                   = new SimpleStringProperty();
        roundWarningSound               = new SimpleStringProperty();
        endOfRestSound                  = new SimpleStringProperty();
        roundColor                      = new SimpleObjectProperty<>();
        warningColor                    = new SimpleObjectProperty<>();
        restColor                       = new SimpleObjectProperty<>();
        numberOfRoundsProperty          = new SimpleIntegerProperty();
        roundTimeMinProperty            = new SimpleIntegerProperty();
        roundTimeSecProperty            = new SimpleIntegerProperty();
        restTimeMinProperty             = new SimpleIntegerProperty();
        restTimeSecProperty             = new SimpleIntegerProperty();
        prepareTimeMinProperty          = new SimpleIntegerProperty();
        prepareTimeSecProperty          = new SimpleIntegerProperty();
        innerPeriodicAlertMinProperty   = new SimpleIntegerProperty();
        innerPeriodicAlertSecProperty   = new SimpleIntegerProperty();
        endOfRoundAlertMinProperty      = new SimpleIntegerProperty();
        endOfRoundAlertSecProperty      = new SimpleIntegerProperty();

        stateProperty                   = new SimpleBooleanProperty(true);
        voiceEnabledProperty            = new SimpleBooleanProperty(false);
        changeProperty                  = new SimpleBooleanProperty(false);
        alertEndOfRest                  = Boolean.FALSE;
    }


    // ********************* setters and getters **************************** //
    StringProperty  nameProperty() { return nameProperty; }
    void setName(String name) { this.nameProperty.set(name); changeProperty.set(name != null); }
    String getName() { return this.nameProperty.get(); }

    void setStartEndSound(String str) { this.startEndSound.set(str); changeProperty.set(getName() != null); }
    String getStartEndSound() { return this.startEndSound.get(); }

    void setPeriodicSound(String str) { this.periodicSound.set(str); changeProperty.set(getName() != null); }
    String getPeriodicSound() { return this.periodicSound.get(); }

    void setEndOfRestSound(String str) { this.endOfRestSound.set(str); changeProperty.set(getName() != null); }
    String getEndOfRestSound() { return this.endOfRestSound.get(); }

    void setRoundWarningSound(String str) { this.roundWarningSound.set(str); changeProperty.set(getName() != null); }
    String getRoundWarningSound() { return this.roundWarningSound.get(); }

    void setRoundColor(Color color) { this.roundColor.set(color); changeProperty.set(getName() != null); }
    Color getRoundColor() { return this.roundColor.get(); }

    void setWarningColor(Color color) { this.warningColor.set(color); changeProperty.set(getName() != null); }
    Color getWarningColor() { return this.warningColor.get(); }

    void setRestColor(Color color) { this.restColor.set(color); changeProperty.set(getName() != null); }
    Color getRestColor() { return this.restColor.get(); }

    IntegerProperty roundNumberProperty() { return this.numberOfRoundsProperty; }
    void setNumberOfRounds(int rounds) { this.numberOfRoundsProperty.set(rounds); changeProperty.set(getName() != null); }
    int getNumberOfRounds() { return this.numberOfRoundsProperty.get(); }

    IntegerProperty roundMinProperty() { return this.roundTimeMinProperty; }
    void setRoundTimeMin(int minutes) { this.roundTimeMinProperty.set(minutes); changeProperty.set(getName() != null); }
    int getRoundTimeMin() { return this.roundTimeMinProperty.get(); }

    IntegerProperty roundSecProperty() { return this.roundTimeSecProperty; }
    void setRoundTimeSec(int seconds) { this.roundTimeSecProperty.set(seconds); changeProperty.set(getName() != null); }
    int getRoundTimeSec() { return this.roundTimeSecProperty.get(); }

    IntegerProperty restMinProperty() { return this.restTimeMinProperty; }
    void setRestTimeMin(int minutes) { this.restTimeMinProperty.set(minutes); changeProperty.set(getName() != null); }
    int getRestTimeMin() { return this.restTimeMinProperty.get(); }

    IntegerProperty restSecProperty() { return this.restTimeSecProperty; }
    void setRestTimeSec(int seconds) { this.restTimeSecProperty.set(seconds); changeProperty.set(getName() != null); }
    int getRestTimeSec() { return this.restTimeSecProperty.get(); }

    void setPrepareTimeMin(int minutes) { this.prepareTimeMinProperty.set(minutes); changeProperty.set(getName() != null); }
    int getPrepareTimeMin() { return this.prepareTimeMinProperty.get(); }

    void setPrepareTimeSec(int seconds) { this.prepareTimeSecProperty.set(seconds); changeProperty.set(getName() != null); }
    int getPrepareTimeSec() { return this.prepareTimeSecProperty.get(); }

    void setInnerPeriodicAlertMin(int minutes) { this.innerPeriodicAlertMinProperty.set(minutes); changeProperty.set(getName() != null); }
    int getInnerPeriodicAlertMin() { return this.innerPeriodicAlertMinProperty.get(); }

    void setInnerPeriodicAlertSec(int seconds) { this.innerPeriodicAlertSecProperty.set(seconds); changeProperty.set(getName() != null); }
    int getInnerPeriodicAlertSec() { return this.innerPeriodicAlertSecProperty.get(); }

    void setEndOfRoundAlertMin(int minutes) { this.endOfRoundAlertMinProperty.set(minutes); changeProperty.set(getName() != null); }
    int getEndOfRoundAlertMin() { return this.endOfRoundAlertMinProperty.get(); }

    void setEndOfRoundAlertSec(int seconds) { this.endOfRoundAlertSecProperty.set(seconds); changeProperty.set(getName() != null); }
    int getEndOfRoundAlertSec() { return this.endOfRoundAlertSecProperty.get(); }

    void setState(boolean state) { this.stateProperty.set(state); changeProperty.set(getName() != null); }
    boolean getState() { return this.stateProperty.get(); }

    void enableVoice(boolean enable) { this.voiceEnabledProperty.set(enable); changeProperty.set(getName() != null); }
    boolean isVoiceEnabled() { return this.voiceEnabledProperty.get(); }

    void alertEndOfRest(boolean value) { this.alertEndOfRest = value; changeProperty.set(getName() != null); }
    boolean isEndOfRoundAlertON() { return this.alertEndOfRest; }

    void restChangeState() { changeProperty.set(false); }
    BooleanProperty changeProperty() { return changeProperty; }
    // ================================================================================== //


    // ********************* Override equals and hashcode methods *********************** //
    @Override public boolean equals(Object obj) {
        if (!(obj instanceof Profile)) return false;

        Profile profile = (Profile) obj;

        return  this.getNumberOfRounds() == profile.getNumberOfRounds() &&
                this.getRoundTimeMin() == profile.getRoundTimeMin() &&
                this.getRoundTimeSec() == profile.getRoundTimeSec() &&
                this.getRestTimeMin() == profile.getRestTimeMin() &&
                this.getRestTimeSec() == profile.getRestTimeSec() &&
                this.getEndOfRoundAlertMin() == profile.getEndOfRoundAlertMin() &&
                this.getEndOfRoundAlertSec() == profile.getEndOfRoundAlertSec() &&
                this.getInnerPeriodicAlertMin() == profile.getInnerPeriodicAlertMin() &&
                this.getInnerPeriodicAlertSec() == profile.getInnerPeriodicAlertSec() &&
                this.getPrepareTimeMin() == profile.getPrepareTimeMin() &&
                this.getPrepareTimeSec() == profile.getPrepareTimeSec() &&
                this.isEndOfRoundAlertON() == profile.isEndOfRoundAlertON() &&
                this.isVoiceEnabled() == profile.isVoiceEnabled() &&
                this.getState() == profile.getState() &&
                this.getName().equals(profile.getName()) &&
                this.getStartEndSound().equals(profile.getStartEndSound()) &&
                this.getRoundWarningSound().equals(profile.getRoundWarningSound()) &&
                this.getPeriodicSound().equals(profile.getPeriodicSound()) &&
                this.getEndOfRestSound().equals(profile.getEndOfRestSound()) &&
                this.getRoundColor().equals(profile.getRoundColor()) &&
                this.getWarningColor().equals(profile.getWarningColor()) &&
                this.getRestColor().equals(profile.getRestColor());
    }

    @Override public int hashCode() {
        int result = 11;

        result = 13 * result + nameProperty.get().hashCode();
        result = 13 * result + numberOfRoundsProperty.get();
        result = 13 * result + roundTimeMinProperty.get();
        result = 13 * result + roundTimeSecProperty.get();
        result = 13 * result + restTimeMinProperty.get();
        result = 13 * result + restTimeSecProperty.get();
        result = 13 * result + endOfRoundAlertMinProperty.get();
        result = 13 * result + endOfRoundAlertSecProperty.get();
        result = 13 * result + innerPeriodicAlertMinProperty.get();
        result = 13 * result + innerPeriodicAlertSecProperty.get();
        result = 13 * result + prepareTimeMinProperty.get();
        result = 13 * result + prepareTimeSecProperty.get();
        result = 13 * result + roundColor.get().hashCode();
        result = 13 * result + warningColor.get().hashCode();
        result = 13 * result + restColor.get().hashCode();
        result = 13 * result + startEndSound.get().hashCode();
        result = 13 * result + roundWarningSound.get().hashCode();
        result = 13 * result + periodicSound.get().hashCode();
        result = 13 * result + endOfRestSound.get().hashCode();

        return result;
    }
    // ========================================================================================== //

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // ************************************** Other methods ************************************ //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    Profile copy() {
        Profile profile = new Profile();
        profile.setName(this.getName());
        profile.setNumberOfRounds(this.getNumberOfRounds());
        profile.setRoundTimeMin(this.getRoundTimeMin());
        profile.setRoundTimeSec(this.getRoundTimeSec());
        profile.setRestTimeMin(this.getRoundTimeMin());
        profile.setRestTimeSec(this.getRoundTimeSec());
        profile.setEndOfRoundAlertMin(this.getEndOfRoundAlertMin());
        profile.setEndOfRoundAlertSec(this.getEndOfRoundAlertSec());
        profile.setInnerPeriodicAlertMin(this.getInnerPeriodicAlertMin());
        profile.setInnerPeriodicAlertSec(this.getInnerPeriodicAlertSec());
        profile.setPrepareTimeMin(this.getPrepareTimeMin());
        profile.setPrepareTimeSec(this.getPrepareTimeSec());
        profile.setRoundWarningSound(this.getRoundWarningSound());
        profile.setStartEndSound(this.getStartEndSound());
        profile.setEndOfRestSound(this.getEndOfRestSound());
        profile.setPeriodicSound(this.getPeriodicSound());
        profile.setRoundColor(this.getRoundColor());
        profile.setWarningColor(this.getWarningColor());
        profile.setRestColor(this.getRestColor());
        profile.alertEndOfRest = this.alertEndOfRest;
        profile.setState(this.getState());
        profile.enableVoice(this.isVoiceEnabled());

        return profile;
    }

    boolean hasItems() {
        return getName() != null && getName().isEmpty();
    }
    // ================================================================================ //
}
