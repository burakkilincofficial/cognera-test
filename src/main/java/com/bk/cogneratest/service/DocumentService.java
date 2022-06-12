package com.bk.cogneratest.service;

import com.bk.cogneratest.common.exceptions.DocumentFileNotFoundException;

import java.util.HashMap;

/**
 * Service for word calculations for words.
 * @since 2022-06-12
 * @author burak kilinc
 */
public interface DocumentService {
    String getWordCount() throws DocumentFileNotFoundException;

    String sortMapByValueAsDesc(HashMap<String, Integer> map);

    String getJaccardSimilarity() throws DocumentFileNotFoundException;

    String read(String fileName) throws DocumentFileNotFoundException;

    void readForGraph() throws DocumentFileNotFoundException;
}
