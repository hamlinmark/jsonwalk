package com.helkiah.jsonwalk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import com.jayway.jsonpath.JsonPath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minidev.json.JSONArray;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "jsonwalk", footer = "Copyright(c) 2020", description = "Traverse JSON with JSON Path.")
public class JsonWalk implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(JsonWalk.class);

    public static void main(String... args) {        
        new CommandLine(new JsonWalk()).execute(args);
    }

    @Option(names={"--output", "-o"}, defaultValue = "json", required = false)
    private String output;

    @Parameters(index = "0", description = "JSON path to walk.")
    private String jsonPath;

    public void run() {
        String line = "";
        String submittedString = "";
        try {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            while ((line = stdin.readLine()) != null) {
                submittedString += line + '\n';
            }

            Object result = JsonPath.read(submittedString, jsonPath);
            if (result instanceof JSONArray && this.output.equals("json")) {
                JSONArray jsonArray = (JSONArray)result;
                System.out.println(jsonArray.toJSONString());
            } else if (result instanceof Collection) {
                for (Object item : (Collection)result) {
                    System.out.println(item.toString());                
                }
            } else  {
                System.out.println(result.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
     }
     
}
