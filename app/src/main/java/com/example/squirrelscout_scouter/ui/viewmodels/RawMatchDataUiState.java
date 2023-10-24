package com.example.squirrelscout_scouter.ui.viewmodels;

import org.immutables.value.Value;

/**
 * The UI state for raw match data obtained during a scouting session.
 *
 * It can be made immutable (a requirement for Android UI state) and
 * has the good Java citizen behaviors like proper equals/hashCode (with
 * the immutable) and can be converted to/from Capn' Proto (which is
 * part of the "data" layer model object).
 */
@Value.Immutable
public interface RawMatchDataUiState extends WithRawMatchDataUiState {

    String scoutName();

    String positionScouting();

    String autoClimb();

    String teleClimb();

    String notes();

    int scoutTeam();

    int matchScouting();

    int robotScouting();

    int coneHighA();

    int coneMidA();

    int coneLowA();

    int cubeHighA();

    int cubeMidA();

    int cubeLowA();

    int coneHighT();

    int coneMidT();

    int coneLowT();

    int cubeHighT();

    int cubeMidT();

    int cubeLowT();

    boolean mobility();

    boolean defense();

    boolean incapacitated();
}
