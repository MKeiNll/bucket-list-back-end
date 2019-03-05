package com.bucketlist.controller;

import com.bucketlist.model.Entry;
import org.springframework.web.bind.annotation.*;

@RestController
public class EntryController {

    /**
     * GET /realestate : Lists all real estate objects.
     *
     * @return body with all real estate objects.
     */
    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Entry getTestEntry() {
        return new Entry("hello, ", "react");
    }
}
