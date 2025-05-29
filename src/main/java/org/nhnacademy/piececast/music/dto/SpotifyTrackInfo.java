
package org.nhnacademy.piececast.music.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpotifyTrackInfo {
    private String trackName;
    private String album;
    private String url;
    private String thumbnail;
}