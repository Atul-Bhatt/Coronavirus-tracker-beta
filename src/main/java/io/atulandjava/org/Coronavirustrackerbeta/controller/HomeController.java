package io.atulandjava.org.Coronavirustrackerbeta.controller;

import io.atulandjava.org.Coronavirustrackerbeta.model.LocationStats;
import io.atulandjava.org.Coronavirustrackerbeta.service.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronavirusDataService coronavirusDataService;

    @GetMapping
    public String home(Model model){
        List<LocationStats> allStats = coronavirusDataService.getLocationStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getConfirmedCases()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        return "home";
    }
}
