package com.inconcept.task.config;

import com.inconcept.task.service.dto.BookDto;
import org.apache.commons.csv.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;

import java.util.ArrayList;

import java.util.List;

@Component
public class BookCSVConfig {
    public static String TYPE = "text/csv";
    public static boolean hasCSVFormat(MultipartFile file) {
        if (TYPE.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        }

        return false;
    }

    public List<BookDto> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT
                             .withDelimiter(';').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<BookDto> developerTutorialList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                BookDto bookDto = new BookDto(
                        csvRecord.get("Book-Title"),
                        csvRecord.get("Year-Of-Publication"),
                        csvRecord.get("Publisher"),
                        csvRecord.get("Image-URL-S")
                );
                bookDto.getBookAuthors().add(csvRecord.get("Book-Author"));
                developerTutorialList.add(bookDto);
            }
            return developerTutorialList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
