package com.example.squirrelscout_scouter.ui.viewmodels;

import android.os.Debug;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.caverock.androidsvg.SVG;
import com.example.squirrelscout.data.models.ComDataModel;

import org.capnproto.MessageBuilder;

import java.util.Objects;

/**
 * The information associated with a single Start Scouting session.
 */
public class ScoutingSessionViewModel extends ViewModel {
    /**
     * Some unique identifier so we know when a new session has been started
     */
    public static final String INTENT_INITIAL_LONG_SESSION_NUMBER = "Initial:SessionNumber";
    public static final String INTENT_INITIAL_STRING_SCOUT_NAME = "Initial:ScoutName";
    public static final String INTENT_INITIAL_SHORT_TEAM_NUMBER = "Initial:TeamNumber";

    /**
     * Be careful with Mutable on top of a Modifiable! Any modifications need to
     * also mutate the MutableLiveData or else notifications will not be sent.
     * <p>
     * So make sure you do a .postValue() or a .setValue() after _ANY_
     * modifications to [modifiableRawMatchData], and you also update some other
     * field.
     * <p>
     * In most cases just use updateAndSetSession().
     */
    private final MutableLiveData<ImmutableRawMatchDataSessionUiState> rawMatchDataSessionUiState =
            new MutableLiveData<>(ImmutableRawMatchDataSessionUiState.builder()
                    .sessionNumber(0L)
                    .updateNumber(0)
                    .modifiableRawMatchData(ModifiableRawMatchDataUiState.create())
                    .build());

    private final MutableLiveData<ImmutableRawMatchDataUiState> qrRequests = new MutableLiveData<>();

    private final LiveData<ImmutableRawMatchDataUiState> completedRawMatchData =
            Transformations.distinctUntilChanged(
                    Transformations.map(rawMatchDataSessionUiState, this::fromSessionToCompleted));

    /**
     * The "session" raw match data will be emitted whenever there is a change
     * to the session fields.
     */
    public LiveData<ImmutableRawMatchDataSessionUiState> getRawMatchDataSession() {
        return rawMatchDataSessionUiState;
    }

    /**
     * The "completed" raw match data will be emitted when the session is complete.
     */
    public LiveData<ImmutableRawMatchDataUiState> getCompletedRawMatchData() {
        return completedRawMatchData;
    }

    /**
     * The "QR requests" will be emitted whenever there is a new request for a QR code.
     */
    public LiveData<ImmutableRawMatchDataUiState> getQrRequests() {
        return qrRequests;
    }

    private void updateAndSetSession(ImmutableRawMatchDataSessionUiState session) {
        ImmutableRawMatchDataSessionUiState updatedSession = session.withUpdateNumber(session.updateNumber() + 1);
        rawMatchDataSessionUiState.setValue(updatedSession);
    }

    /**
     * Return a non-null RawMatchData if and only if the session fields are complete.
     *
     * @param session the partially completed session
     * @return the completed RawMatchData, or null if incomplete
     */
    private ImmutableRawMatchDataUiState fromSessionToCompleted(ImmutableRawMatchDataSessionUiState session) {
        Log.d("Qr Code", "Generating");
        // TODO: The UI is not complete:
        // - We pretend as if the UI completed all the fields.
        // - But we do _not_ touch the session ... we instead operate on a clone of the
        //   session and fill in the clone's fields. That way the real UI can keep track
        //   of which fields have been set and which haven't.
        ModifiableRawMatchDataUiState clone = ModifiableRawMatchDataUiState.create().from(
                session.modifiableRawMatchData()
        );

        // This check should not be needed when the UI is complete!
        try {
            clone.toImmutable();
        } catch (IllegalStateException e) {
            Log.e("ScoutingSession", "The UI has not been completed! For now we'll try to patch the UI fields. The following error message should tell you which fields still need to be set: " + e.getMessage());
            // TODO: Keyush/Archit: For Saturday. All of these should be marked DONE once they are set from the *Activity pages
            // DONE: if (!clone.scoutTeamIsSet()) clone.setScoutTeam(-1);
            // DONE: if (!clone.scoutNameIsSet()) clone.setScoutName("UI needs setScoutName");
            if (!clone.positionScoutingIsSet())
                clone.setPositionScouting("UI needs setPositionScouting");
            if (!clone.matchScoutingIsSet()) clone.setMatchScouting(-1);
            if (!clone.startingPositionIsSet()) clone.setStartingPosition("UI needs Starting Position");
            if (!clone.wingNote1IsSet()) clone.setWingNote1(false);
            if (!clone.wingNote2IsSet()) clone.setWingNote2(false);
            if (!clone.wingNote3IsSet()) clone.setWingNote3(false);
            if (!clone.centerNote1IsSet()) clone.setCenterNote1(false);
            if (!clone.centerNote2IsSet()) clone.setCenterNote2(false);
            if (!clone.centerNote3IsSet()) clone.setCenterNote3(false);
            if (!clone.centerNote4IsSet()) clone.setCenterNote4(false);
            if (!clone.centerNote5IsSet()) clone.setCenterNote5(false);
            if (!clone.autoAmpScoreIsSet()) clone.setAutoAmpScore(-1);
            if (!clone.autoAmpMissIsSet()) clone.setAutoAmpMiss(-1);
            if (!clone.autoSpeakerScoreIsSet()) clone.setAutoSpeakerScore(-1);
            if (!clone.autoSpeakerMissIsSet()) clone.setAutoSpeakerMiss(-1);
            if (!clone.autoLeaveIsSet()) clone.setAutoLeave(false);
            if (!clone.preplacedCoralIsSet()) clone.setPreplacedCoral(false);
            if (!clone.teleSpeakerScoreIsSet()) clone.setTeleSpeakerScore(-1);
            if (!clone.teleSpeakerMissIsSet()) clone.setTeleSpeakerMiss(-1);
            if (!clone.teleAmpScoreIsSet()) clone.setTeleAmpScore(-1);
            if (!clone.teleAmpMissIsSet()) clone.setTeleAmpMiss(-1);
            if (!clone.teleRangeIsSet()) clone.setTeleRange("UI needs Tele Range");
            if (!clone.breakdownDropdownIsSet()) clone.setBreakdownDropdown("UI needs breakdown");
            if (!clone.climbDropdownIsSet()) clone.setClimbDropdown("UI needs endgame climb");
            if (!clone.endgameTrapIsSet()) clone.setEndgameTrap(false);
            if (!clone.autoCoralL4ScoreIsSet()) clone.setAutoCoralL4Score(-1);
            if (!clone.autoCoralL3ScoreIsSet()) clone.setAutoCoralL3Score(-1);
            if (!clone.autoCoralL2ScoreIsSet()) clone.setAutoCoralL2Score(-1);
            if (!clone.autoCoralL1ScoreIsSet()) clone.setAutoCoralL1Score(-1);
            if (!clone.autoProcessorScoreIsSet()) clone.setAutoProcessorScore(-1);
            if (!clone.autoProcessorMissIsSet()) clone.setAutoProcessorMiss(-1);
            if (!clone.autoNetScoreIsSet()) clone.setAutoNetScore(-1);
            if (!clone.autoNetMissIsSet()) clone.setAutoNetMiss(-1);
            if (!clone.autoCoralL4MissIsSet()) clone.setAutoCoralL4Miss(-1);
            if (!clone.autoCoralL3MissIsSet()) clone.setAutoCoralL3Miss(-1);
            if (!clone.autoCoralL2MissIsSet()) clone.setAutoCoralL2Miss(-1);
            if (!clone.autoCoralL1MissIsSet()) clone.setAutoCoralL1Miss(-1);

            //tele2025
            if (!clone.teleOpCoralL4ScoreIsSet()) clone.setTeleOpCoralL4Score(-1);
            if (!clone.teleOpCoralL3ScoreIsSet()) clone.setTeleOpCoralL3Score(-1);
            if (!clone.teleOpCoralL2ScoreIsSet()) clone.setTeleOpCoralL2Score(-1);
            if (!clone.teleOpCoralL1ScoreIsSet()) clone.setTeleOpCoralL1Score(-1);
            if (!clone.teleOpProcessorScoreIsSet()) clone.setTeleOpProcessorScore(-1);
            if (!clone.teleOpProcessorMissIsSet()) clone.setTeleOpProcessorMiss(-1);
            if (!clone.teleOpNetScoreIsSet()) clone.setTeleOpNetScore(-1);
            if (!clone.teleOpNetMissIsSet()) clone.setTeleOpNetMiss(-1);
            if (!clone.teleOpCoralL4MissIsSet()) clone.setTeleOpCoralL4Miss(-1);
            if (!clone.teleOpCoralL3MissIsSet()) clone.setTeleOpCoralL3Miss(-1);
            if (!clone.teleOpCoralL2MissIsSet()) clone.setTeleOpCoralL2Miss(-1);
            if (!clone.teleOpCoralL1MissIsSet()) clone.setTeleOpCoralL1Miss(-1);


        }
        return clone.toImmutable();
    }

    public String printSession() {
        ImmutableRawMatchDataSessionUiState session = rawMatchDataSessionUiState.getValue();
        return session == null ? "no session" : session.toString();
    }

    public void startNewSessionIfNecessary(long sessionNumber, String scoutName, short teamNumber) {
        ImmutableRawMatchDataSessionUiState oldState = rawMatchDataSessionUiState.getValue();
        if (oldState == null || oldState.sessionNumber() != sessionNumber) {
            // new session! everything should be cleared except the scout name and team number
            ModifiableRawMatchDataUiState state = ModifiableRawMatchDataUiState.create();
            state.setScoutName(scoutName);

            rawMatchDataSessionUiState.setValue(
                    ImmutableRawMatchDataSessionUiState.builder()
                            .sessionNumber(sessionNumber)
                            .updateNumber(0)
                            .modifiableRawMatchData(state)
                            .build()
            );
        }
    }

    //need to implement
    //gets the robot based on the match number and position

    //TODO: Add this functionality in later. Remove the manual entering of robot number
//    public void findRobot() {
//        ImmutableRawMatchDataSessionUiState session = rawMatchDataSessionUiState.getValue();
//        assert session != null;
//        ModifiableRawMatchDataUiState rawMatchData = session.modifiableRawMatchData();
//
//        //placement for now
//        rawMatchData.setRobotScouting(1234);
//
//        updateAndSetSession(session);
//    }

    public void captureScoutingRobotNumber(int num){
        ImmutableRawMatchDataSessionUiState session = rawMatchDataSessionUiState.getValue();
        assert session != null;
        ModifiableRawMatchDataUiState rawMatchData = session.modifiableRawMatchData();

        rawMatchData.setRobotScouting(num);
    }

    public void captureMatchRobot(int scoutMatch, String robotPosition) {
        ImmutableRawMatchDataSessionUiState session = rawMatchDataSessionUiState.getValue();
        assert session != null;
        ModifiableRawMatchDataUiState rawMatchData = session.modifiableRawMatchData();

        rawMatchData.setMatchScouting(scoutMatch);
        //rawMatchData.setPositionScouting(robotPosition);

        updateAndSetSession(session);
    }

    public void captureAutoData(String startingPos, boolean wn1, boolean wn2, boolean wn3, boolean cn1, boolean cn2, boolean cn3, boolean cn4, boolean cn5, int ampScore, int ampMiss, int speakerScore, int speakerMiss, boolean autoLeave, boolean autoPreplacedCoral, int autoCoralL4Score, int autoCoralL4Miss, int autoCoralL3Score, int autoCoralL3Miss, int autoCoralL2Score, int autoCoralL2Miss, int autoCoralL1Score, int autoCoralL1Miss, int autoProcessorScore, int autoProcessorMiss, int autoNetScore, int autoNetMiss){
        ImmutableRawMatchDataSessionUiState session = rawMatchDataSessionUiState.getValue();
        assert  session != null;

        ModifiableRawMatchDataUiState rawMatchData = session.modifiableRawMatchData();

        //2024
        rawMatchData.setStartingPosition(startingPos);
        rawMatchData.setWingNote1(wn1);
        rawMatchData.setWingNote2(wn2);
        rawMatchData.setWingNote3(wn3);
        rawMatchData.setCenterNote1(cn1);
        rawMatchData.setCenterNote2(cn2);
        rawMatchData.setCenterNote3(cn3);
        rawMatchData.setCenterNote4(cn4);
        rawMatchData.setCenterNote5(cn5);
        rawMatchData.setAutoAmpScore(ampScore);
        rawMatchData.setAutoAmpMiss(ampMiss);
        rawMatchData.setAutoSpeakerScore(speakerScore);
        rawMatchData.setAutoSpeakerMiss(speakerMiss);
        rawMatchData.setAutoLeave(autoLeave);
        rawMatchData.setPreplacedCoral(autoPreplacedCoral);
        rawMatchData.setAutoCoralL4Score(autoCoralL4Score);
        rawMatchData.setAutoCoralL3Score(autoCoralL3Score);
        rawMatchData.setAutoCoralL2Score(autoCoralL2Score);
        rawMatchData.setAutoCoralL1Score(autoCoralL1Score);
        rawMatchData.setAutoProcessorScore(autoProcessorScore);
        rawMatchData.setAutoProcessorMiss(autoProcessorMiss);
        rawMatchData.setAutoNetScore(autoNetScore);
        rawMatchData.setAutoNetMiss(autoNetMiss);
        rawMatchData.setAutoCoralL4Miss(autoCoralL4Miss);
        rawMatchData.setAutoCoralL3Miss(autoCoralL3Miss);
        rawMatchData.setAutoCoralL2Miss(autoCoralL2Miss);
        rawMatchData.setAutoCoralL1Miss(autoCoralL1Miss);

        //does this need to be called? would this overwrite/lose the previous captureMatchRobot method's session data?
        updateAndSetSession(session);
    }

    public void captureTeleData(int speakerScore, int speakerMiss, int ampScore, int ampMiss, String distance, String breakdownDropdown, String climbDropdown, boolean trap, String pickup, int teleOpCoralL4Score, int teleOpCoralL4Miss, int teleOpCoralL3Score, int teleOpCoralL3Miss, int teleOpCoralL2Score, int teleOpCoralL2Miss, int teleOpCoralL1Score, int teleOpCoralL1Miss, int teleOpProcessorScore, int teleOpProcessorMiss, int teleOpNetScore, int teleOpNetMiss) {
        ImmutableRawMatchDataSessionUiState session = rawMatchDataSessionUiState.getValue();
        assert  session != null;

        ModifiableRawMatchDataUiState rawMatchData = session.modifiableRawMatchData();

        //2024 tele
        rawMatchData.setTeleSpeakerScore(speakerScore);
        rawMatchData.setTeleSpeakerMiss(speakerMiss);
        rawMatchData.setTeleAmpScore(ampScore);
        rawMatchData.setTeleAmpMiss(ampMiss);
        rawMatchData.setTeleRange(distance);
        rawMatchData.setBreakdownDropdown(breakdownDropdown);
        rawMatchData.setClimbDropdown(climbDropdown);
        rawMatchData.setEndgameTrap(trap);
        rawMatchData.setPickUpAbility(pickup);
        //2025 tele
        rawMatchData.setTeleOpCoralL4Score(teleOpCoralL4Score);
        rawMatchData.setTeleOpCoralL4Miss(teleOpCoralL4Miss);
        rawMatchData.setTeleOpCoralL3Score(teleOpCoralL3Score);
        rawMatchData.setTeleOpCoralL3Miss(teleOpCoralL3Miss);
        rawMatchData.setTeleOpCoralL2Score(teleOpCoralL2Score);
        rawMatchData.setTeleOpCoralL2Miss(teleOpCoralL2Miss);
        rawMatchData.setTeleOpCoralL1Score(teleOpCoralL1Score);
        rawMatchData.setTeleOpCoralL1Miss(teleOpCoralL1Miss);
        rawMatchData.setTeleOpProcessorScore(teleOpProcessorScore);
        rawMatchData.setTeleOpProcessorMiss(teleOpProcessorMiss);
        rawMatchData.setTeleOpNetScore(teleOpNetScore);
        rawMatchData.setTeleOpNetMiss(teleOpNetMiss);



        updateAndSetSession(session);
    }

    public void requestQrCode() {
        ImmutableRawMatchDataUiState completeRawMatchData = completedRawMatchData.getValue();
        /* Do nothing if the session is not complete. You should set the UI button (etc.) to
           not be visible so you never request a QR code. */
        if (completeRawMatchData == null) {
            Log.d("Notes", "Null rawMatchData");
            return;
        }
        qrRequests.setValue(completeRawMatchData);
    }

    public SVG generateQrCode(ComDataModel data, ImmutableRawMatchDataUiState completeRawMatchData) {
        MessageBuilder completeRawMatchDataMessage = new RawMatchDataUiStateSerde()
                .toMessage(Objects.requireNonNull(completeRawMatchData));
        return data.getScoutQR().qrCodeOfRawMatchData(completeRawMatchDataMessage);
    }
}
