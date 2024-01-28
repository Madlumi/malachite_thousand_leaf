package com.madanie.malachite_thousand_leaf;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class MalachiteThousandLeafApplication {

   public static void main(String[] args) {
      //CommandLine cmd = getArgs(args);
      //init(cmd.getOptionValue("i", "prospects.txt"));
      //if(cmd.hasOption("w"))
         SpringApplication.run(MalachiteThousandLeafApplication.class, args);
   }
   public static void init(String file){
     // propects = new ProspectHandler().load(file);
     // propects.print(true);
   }

   private static CommandLine getArgs(String[] args){
      CommandLineParser parser = new DefaultParser();
      Options options = new Options();
      options.addOption("i", "input", true, "Input file (default: prospects.txt)");
      options.addOption("w", "web", false, "Enable web UI");
      options.addOption("h", "help", false, "Print help message");
      try {
         CommandLine cmd = parser.parse(options, args);
         if (cmd.hasOption("h")) {
            HelpFormatter formatter= new HelpFormatter();
            formatter.printHelp("MalachiteThousandLeafApplication", options);
            System.exit(0);
         }
         return cmd;
      } catch (Exception e) {
         System.err.println("Error parsing command line arguments: " + e.getMessage());
         System.exit(1);
         return null;
      }
   }

   public static List<Map<String, String>> read_csv(String file){
      List<Map<String, String>> data = new ArrayList<>();
      try (CSVReader reader = new CSVReader(new FileReader(file))) {
         //assume first line declares data fields
         String[] fields = reader.readNext();
         if (fields == null) { return data; }

         String[] line;
         while ((line = reader.readNext()) != null) {
            if (line.length != fields.length) { continue; }
            Map<String, String> row = new HashMap<>();
            for (int i = 0; i < fields.length; i++) { row.put(fields[i], line[i]); }
            data.add(row);
         }

      } catch (IOException | CsvValidationException e) {
         System.err.println("CSV Parse error:");
         e.printStackTrace();
      }
      return data;
   }
   @Bean 
   public int addProspects(ProspectRepo pr){
      System.out.println("uwu");
      for (Map<String, String> m : read_csv("prospects.txt")) {
         Prospect p = Prospect.prospectFromMap(m);
         if (p!=null)pr.save(p);
      }
      print(true,pr);
      return 1;

   }


   public void print(boolean separators, ProspectRepo pr) {
      pr.findAll().forEach(p -> {
         if(separators){System.out.println("****************************************************************************************************");}
         System.out.println(p.toString());
         if(separators){System.out.println("****************************************************************************************************");}
      });
   }
}
