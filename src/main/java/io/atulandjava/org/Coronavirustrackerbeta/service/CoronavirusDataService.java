package io.atulandjava.org.Coronavirustrackerbeta.service;

import io.atulandjava.org.Coronavirustrackerbeta.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusDataService {
    List<LocationStats> locationStats = new ArrayList<>();
    /* URL for fetching raw data from a GitHub repo*/
    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/01-01-2021.csv";

    public List<LocationStats> getLocationStats() {
        return locationStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchData() throws IOException, InterruptedException {
        /* Creating a temporary list to make it concurrency safe */
        List<LocationStats> tempStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        /* Parsing response body using commons-csv */
        StringReader csvParser = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvParser);
        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();
            if(record.get("Province_State").equals(""))     locationStat.setState(record.get("Country_Region"));
            else    locationStat.setState(record.get("Province_State"));
            locationStat.setCountry(record.get("Country_Region"));
            locationStat.setConfirmedCases(Integer.parseInt(record.get("Confirmed")));
            tempStats.add(locationStat);
        }
        locationStats.addAll(tempStats);
    }
}
