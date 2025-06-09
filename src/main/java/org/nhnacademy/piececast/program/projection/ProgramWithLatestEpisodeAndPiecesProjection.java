package org.nhnacademy.piececast.program.projection;

import java.time.LocalDate;

public interface ProgramWithLatestEpisodeAndPiecesProjection {
    Long getProgramId();
    String getProgramName();
    String getStation();
    Long getEpisodeId();
    LocalDate getLatestEpisodeDate();
    Long getPieceId();
    String getPieceTitle();
}
