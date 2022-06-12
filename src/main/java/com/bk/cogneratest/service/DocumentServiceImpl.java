package com.bk.cogneratest.service;

import com.bk.cogneratest.common.exceptions.DocumentFileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * Implementation of Document Service.
 * @since 2022-06-12
 * @author burak kilinc
 */
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final GraphService graphService;

    @Value("${document.file.path}")
    private String fileName;
    @Value("${second.document.file.path}")
    private String secondFileName;
    @Value("${graph.document.file.path}")
    private String graphFileName;

    private static HashMap<String, Integer> countUniqueWordsToMap(String words) {
        HashMap<String, Integer> wordMaps = new HashMap<>();
        String textLower = words.toLowerCase();
        String[] wordsArray = textLower
                .replaceAll("\\W", " ")
                .replaceAll("\\s+", " ")
                .trim().split("\\s+");
        for (String word : wordsArray) {
            wordMaps.merge(word, 1, Integer::sum);
        }
        return wordMaps;
    }

    @Override
    public String getWordCount() throws DocumentFileNotFoundException {
        return sortMapByValueAsDesc(countUniqueWordsToMap(read(fileName)));
    }

    @Override
    public String sortMapByValueAsDesc(HashMap<String, Integer> map) {
        StringBuilder builder = new StringBuilder();
        Stream<Map.Entry<String, Integer>> sorted =
                map.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
        sorted.forEach(e -> builder.append(e.getKey()).append(" ").append(e.getValue()).append("\n"));
        return builder.toString();
    }

    @Override
    public String getJaccardSimilarity() throws DocumentFileNotFoundException {
        HashMap<String, Integer> wordMaps = countUniqueWordsToMap(read(fileName));
        HashMap<String, Integer> wordMaps2 = countUniqueWordsToMap(read(secondFileName));
        HashMap<String, Integer> wordMaps3 = (HashMap<String, Integer>) wordMaps.clone();
        HashMap<String, Integer> wordMaps4 = (HashMap<String, Integer>) wordMaps2.clone();

        Integer countTotalUniqueWords = getCountTotalUniqueWords(wordMaps, wordMaps2);
        Integer countCommonUniqueWords = getCountCommonUniqueWords(wordMaps3, wordMaps4);
        return getJaccardRate((double) countTotalUniqueWords, (double) countCommonUniqueWords);
    }

    private String getJaccardRate(double countTotalUniqueWords, double countCommonUniqueWords) {
        // Jaccard similarity = (count of unique words in common) /(total number of unique words in the two documents)
        double jaccardSimilarity = countCommonUniqueWords / countTotalUniqueWords;
        return String.format("%.6f", jaccardSimilarity);
    }

    private Integer getCountTotalUniqueWords(HashMap<String, Integer> wordMaps,
                                             HashMap<String, Integer> wordMaps2) {
        // total number of unique words in the two documents
        wordMaps.putAll(wordMaps2);
        return wordMaps.size();
    }

    private Integer getCountCommonUniqueWords(HashMap<String, Integer> wordMaps3,
                                              HashMap<String, Integer> wordMaps4) {
        // count of unique words in common
        TreeSet<String> wordsTreeSet = new TreeSet<>(wordMaps3.keySet());
        Set<String> setSecondDocumentCounts = wordMaps4.keySet();
        wordsTreeSet.retainAll(setSecondDocumentCounts);
        return wordsTreeSet.size();
    }

    @Override
    public String read(String fileName) throws DocumentFileNotFoundException {
        String everything = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } catch (IOException e) {
            throw new DocumentFileNotFoundException(fileName);
        }
        return everything;
    }

    @Override
    public void readForGraph() throws DocumentFileNotFoundException {
        String read = read(graphFileName);
        String[] split = read.split("\r\n");
        for (String s : split) {
            String[] split1 = s.split(",");
            graphService.addEdge(split1[0], split1[1]);
        }
    }
}


