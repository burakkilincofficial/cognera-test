package com.bk.cogneratest;

import com.bk.cogneratest.common.exceptions.DocumentFileNotFoundException;
import com.bk.cogneratest.service.DocumentService;
import com.bk.cogneratest.service.GraphService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for test endpoints.
 * @since 2022-06-12
 * @author burak kilinc
 */
@Log4j2
@RequestMapping("/test/v1")
@RestController
@RequiredArgsConstructor
public class TestController {
    private final DocumentService documentService;
    private final GraphService graphService;

    @GetMapping()
    public String test() {
        return "Successful test";
    }

    @PostMapping("/count-words")
    public String getCountUniqueWords() throws DocumentFileNotFoundException {
        return documentService.getWordCount();
    }

    @PostMapping("/document-similarity")
    public String getJaccardSimilarity() throws DocumentFileNotFoundException {
        return documentService.getJaccardSimilarity();
    }

    @PostMapping("/find-nodes")
    public String findNodes(@RequestParam String node, @RequestParam int maxHops) throws DocumentFileNotFoundException {
        documentService.readForGraph();
        return graphService.findNodes(node, maxHops).toString();
    }


}
