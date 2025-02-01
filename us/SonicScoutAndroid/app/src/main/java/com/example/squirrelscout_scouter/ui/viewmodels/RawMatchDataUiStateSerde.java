package com.example.squirrelscout_scouter.ui.viewmodels;

import com.diskuv.dksdk.ffi.java.Com;
import com.example.squirrelscout.data.capnp.Schema;

import org.capnproto.MessageBuilder;
import org.capnproto.MessageReader;

/**
 * Serialization/deserialization for {@link RawMatchDataUiState}.
 */
// Package-protected since only the ViewModels ... the entities responsible
// for converting the UiState into data layer model objects (capnp) ... should use this.
class RawMatchDataUiStateSerde {
    /*
        DEVELOPERS:

        Yes this is tedious writing this. But _always_ separate your data layer model objects
        from your UI state objects. That way your Android UI can change without forcing
        everything else (ex. iOS UI, manager app) to change.

        These are "strong recommendations" from
        https://developer.android.com/topic/architecture/recommendations#layered-architecture
     */

    public Schema.SPosition stringToSPosition(String sp){
        switch(sp){
            case "Amp Side":
                return Schema.SPosition.AMP_SIDE;
            case "Center":
                return Schema.SPosition.CENTER;
            case "Source Side":
                return Schema.SPosition.SOURCE_SIDE;
            default:
                return Schema.SPosition._NOT_IN_SCHEMA;
        }
    }
    public Schema.TBreakdown stringToTBreakdown(String breakdown){
        switch(breakdown){
            case "None":
                return Schema.TBreakdown.NONE;

            case "Tipped":
                return Schema.TBreakdown.TIPPED;

            case "Mechanical Failure":
                return Schema.TBreakdown.MECHANICAL_FAILURE;

            case "Incapacitated":
                return Schema.TBreakdown.INCAPACITATED;

            default:
                return Schema.TBreakdown._NOT_IN_SCHEMA;
        }
    }

    public Schema.EClimb stringToEClimb(String climb){
        switch(climb){
            case "Success":
                return Schema.EClimb.SUCCESS;

            case "Failed":
                return Schema.EClimb.FAILED;

            case "Did Not Attempt":
                return Schema.EClimb.DID_NOT_ATTEMPT;

            case "Harmony":
                return Schema.EClimb.HARMONY;

            case "Park":
                return Schema.EClimb.PARKED;

            default:
                return Schema.EClimb._NOT_IN_SCHEMA;
        }
    }

    public Schema.RobotPosition stringToAColor(String climb){
        switch(climb){
            case "Red 1":
                return Schema.RobotPosition.RED1;

            case "Red 2":
                return Schema.RobotPosition.RED2;

            case "Red 3":
                return Schema.RobotPosition.RED3;

            case "Blue 1":
                return Schema.RobotPosition.BLUE1;

            case "Blue 2":
                return Schema.RobotPosition.BLUE2;

            case "Blue 3":
                return Schema.RobotPosition.BLUE3;

            default:
                return Schema.RobotPosition._NOT_IN_SCHEMA;
        }
    }
    public MessageBuilder toMessage(RawMatchDataUiState v) {
        MessageBuilder message = Com.newMessageBuilder();
        Schema.RawMatchData.Builder rawMatchData = message.initRoot(Schema.RawMatchData.factory);

        // TODO: Keyush/Archit: For Saturday. Finish conversion to capnp.

        //scouter person
        rawMatchData.setScouterName(v.scoutName());

        //match/robot info
        rawMatchData.setTeamNumber((short) v.robotScouting());
        rawMatchData.setTeamName("NO_NAME");
        rawMatchData.setMatchNumber( (short) v.matchScouting());
        //rawMatchData.setAllianceColor(stringToAColor(v.positionScouting()));

        //2024 auto
        rawMatchData.setStartingPosition(stringToSPosition(v.startingPosition()));
        rawMatchData.setWingNote1(v.wingNote1());
        rawMatchData.setWingNote2(v.wingNote2());
        rawMatchData.setWingNote3(v.wingNote3());
        rawMatchData.setCenterNote1(v.centerNote1());
        rawMatchData.setCenterNote2(v.centerNote2());
        rawMatchData.setCenterNote3(v.centerNote3());
        rawMatchData.setCenterNote4(v.centerNote4());
        rawMatchData.setCenterNote5(v.centerNote5());
        rawMatchData.setAutoAmpScore((short) v.autoAmpScore());
        rawMatchData.setAutoAmpMiss((short) v.autoAmpMiss());
        rawMatchData.setAutoSpeakerScore((short) v.autoSpeakerScore());
        rawMatchData.setAutoSpeakerMiss((short) v.autoSpeakerMiss());
        rawMatchData.setAutoLeave(v.autoLeave());

        //2025 auto
        rawMatchData.setpreplacedCoral(v.preplacedCoral());
        rawMatchData.setAutoCoralL4Score((short) v.autoCoralL4Score());
        rawMatchData.setAutoCoralL3Score((short) v.autoCoralL3Score());
        rawMatchData.setAutoCoralL2Score((short) v.autoCoralL2Score());
        rawMatchData.setAutoCoralL1Score((short) v.autoCoralL1Score());
        rawMatchData.setAutoProcessorScore((short) v.autoProcessorScore());
        rawMatchData.setAutoProcessorMiss((short) v.autoProcessorMiss());
        rawMatchData.setAutoNetScore((short) v.autoNetScore());
        rawMatchData.setAutoNetMiss((short) v.autoNetMiss());
        rawMatchData.setAutoCoralL4Miss((short) v.autoCoralL4Miss());
        rawMatchData.setAutoCoralL3Miss((short) v.autoCoralL3Miss());
        rawMatchData.setAutoCoralL2Miss((short) v.autoCoralL2Miss());
        rawMatchData.setAutoCoralL1Miss((short) v.autoCoralL1Miss());

        //2025 Tele-OP
        rawMatchData.setTeleOpL4Score((short) v.teleOpCoralL4Score());

        


        //2024 Tele-op
        rawMatchData.setTeleSpeakerScore((short) v.teleSpeakerScore());
        rawMatchData.setTeleSpeakerMiss((short) v.teleSpeakerMiss());
        rawMatchData.setTeleAmpScore((short) v.teleAmpScore());
        rawMatchData.setTeleAmpMiss((short) v.teleAmpMiss());
        rawMatchData.setDistance(v.teleRange());
        rawMatchData.setTeleBreakdown(stringToTBreakdown(v.teleBreakdown()));
        rawMatchData.setEndgameClimb(stringToEClimb(v.endgameClimb()));
        rawMatchData.setEndgameTrap(v.endgameTrap());
//        rawMatchData.setTelePickup(v.pickUpAbility());

        return message;
    }

    public ImmutableRawMatchDataUiState fromMessageReader(MessageReader reader) {
        Schema.RawMatchData.Reader root = reader.getRoot(Schema.RawMatchData.factory);
        ImmutableRawMatchDataUiState.Builder builder = ImmutableRawMatchDataUiState.builder();

        // etc.
        //builder.coneHighA(root.getAutoConeHigh());

        return builder.build();
    }
}
