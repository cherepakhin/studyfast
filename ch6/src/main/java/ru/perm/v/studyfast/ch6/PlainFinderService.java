package ru.perm.v.studyfast.ch6;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class PlainFinderService {
    private final AircraftRepository repo;
    private URL acURL;
    private final ObjectMapper om = new ObjectMapper();

    @SneakyThrows
    public PlainFinderService(AircraftRepository repo) {
        this.repo = repo;
        acURL = new URL("http://192.168.1.10/ajax/aircraft");
    }


}
