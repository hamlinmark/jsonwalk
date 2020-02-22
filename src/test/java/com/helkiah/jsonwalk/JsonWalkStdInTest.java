package com.helkiah.jsonwalk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JsonWalkStdInTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private InputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void setupStdIn(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private void setupStdIn(File file) {
        try {
            InputStream inputStream = new FileInputStream(file);
            System.setIn(inputStream);
        } catch (FileNotFoundException e) {
        }
        
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    public void testStdInHelloWorld() {
        setupStdIn("{\"hello\":\"world\"}");
        JsonWalk.main("$.hello");
        String blah = getOutput();
        assertEquals("world\n", blah);
    }

    @Test
    public void testStdInBooks() {
        File file = new File(
            getClass().getClassLoader().getResource("sample.json").getFile()
        );
        setupStdIn(file);
        JsonWalk.main("$..book..category");
        String blah = getOutput();
        assertEquals("reference\nfiction\nfiction\nfiction\n", blah);
    }


    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

}
