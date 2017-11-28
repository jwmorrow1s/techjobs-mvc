package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {
    /*accumulator for search objects from jobData.findAll() ArrayList*/

    @RequestMapping(value = "")
    public String search(Model model) {
        ArrayList<HashMap<String,String>> allJobs = JobData.findAll();
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", allJobs);
        model.addAttribute("currSearch", "");
        model.addAttribute("currSearchType", "all");

        return "search";
    }


    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String processSearch(Model model,
                         @RequestParam String searchType,
                         @RequestParam String searchTerm){
        ArrayList<HashMap<String,String>> result = new ArrayList<>();
        String currSearch = searchTerm;
        String currSearchType = searchType;
        if(searchType.equals("all")) {
            if(searchTerm == "") result = JobData.findAll();
            else result = JobData.findByValue(searchTerm);
        }
        /* column selected */
        else{
            result = JobData.findByColumnAndValue(searchType,searchTerm);
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", result);
        model.addAttribute("currSearch", searchTerm);
        model.addAttribute("currSearchType", searchType);

        return "search";
    }
}
