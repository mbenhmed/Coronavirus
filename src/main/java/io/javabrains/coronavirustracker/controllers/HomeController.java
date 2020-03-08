package io.javabrains.coronavirustracker.controllers;

import io.javabrains.coronavirustracker.models.LocationStats;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totlalCases = allStats.stream().mapToInt(locationStats -> locationStats.getLatestTotalCases()).sum();
        int totlalNewCases = allStats.stream().mapToInt(locationStats -> locationStats.getDiffFronPrevDay()).sum();
        model.addAttribute("totalReportedCases", totlalCases);
        model.addAttribute("totlalNewCases", totlalNewCases);
        model.addAttribute("locationStats", allStats);
        return "home";
    }
}
