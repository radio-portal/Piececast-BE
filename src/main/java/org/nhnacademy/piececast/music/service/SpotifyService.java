
package org.nhnacademy.piececast.music.service;

import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.music.dto.SpotifyTrackInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SpotifyService {

    @Value("${spotify.client-id}")
    private String clientId;

    @Value("${spotify.client-secret}")
    private String clientSecret;

    private String accessToken;

    public SpotifyTrackInfo searchTrack(String title, String artist) {
        ensureAccessToken();

        String query = title + " " + artist;
        String uri = UriComponentsBuilder.fromHttpUrl("https://api.spotify.com/v1/search")
                .queryParam("q", query)
                .queryParam("type", "track")
                .queryParam("limit", 1)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Map.class);

        Map track = (Map) ((Map)((List)((Map) response.getBody().get("tracks")).get("items")).get(0));
        Map album = (Map) track.get("album");
        List images = (List) album.get("images");

        return new SpotifyTrackInfo(
                (String) track.get("name"),
                (String) album.get("name"),
                (String) ((Map) track.get("external_urls")).get("spotify"),
                (String) ((Map) images.get(0)).get("url")
        );
    }

    private void ensureAccessToken() {
        if (accessToken != null) return;

        String auth = clientId + ":" + clientSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + encodedAuth);

        HttpEntity<String> entity = new HttpEntity<>("grant_type=client_credentials", headers);
        RestTemplate restTemplate = new RestTemplate();
        Map body = restTemplate.postForObject("https://accounts.spotify.com/api/token", entity, Map.class);

        accessToken = (String) body.get("access_token");
    }
}
