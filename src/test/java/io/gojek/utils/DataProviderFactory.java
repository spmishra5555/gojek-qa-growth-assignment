package io.gojek.utils;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class DataProviderFactory {

    @DataProvider(name = "urls", parallel = true)
    public static Iterator<Object[]> getUrls() {
        File file1 = new File("src/test/resources/file1.txt");
        File file2 = new File("src/test/resources/file2.txt");

        List<String> file1Lines = FileUtils.readLines(file1);
        List<String> file2Lines = FileUtils.readLines(file2);

        //Removing empty lines from file and also removing trailing spaces
        file1Lines = file1Lines.stream().filter(e -> !e.trim().isEmpty()).map(e -> e.trim()).collect(Collectors.toList());

        file2Lines = file2Lines.stream().filter(e -> !e.trim().isEmpty()).map(e -> e.trim()).collect(Collectors.toList());

        if (file1Lines.size() != file2Lines.size()) {
            throw new IllegalArgumentException(String.format("file %s has %s lines & file %s has %s lines",
                    file1, file1Lines.size(), file2, file2Lines.size()));
        }

        List<Object[]> data = new ArrayList<>();
        for (int i = 0; i < file1Lines.size(); i++) {
            data.add(new Object[]{file1Lines.get(i), file2Lines.get(i)});
        }
        return data.iterator();
    }

}
