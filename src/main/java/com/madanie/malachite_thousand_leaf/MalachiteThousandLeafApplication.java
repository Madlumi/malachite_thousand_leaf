package com.madanie.malachite_thousand_leaf;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MalachiteThousandLeafApplication {

   public static void main(String[] args) {
      CommandLine cmd = getArgs(args);
      init(cmd.getOptionValue("i", "prospects.txt"));
      if(cmd.hasOption("w"))SpringApplication.run(MalachiteThousandLeafApplication.class, args);
   }

   public static ProspectHandler propects;
   
   public static void init(String file){
      propects = new ProspectHandler().load(file);
      propects.print(true);
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
}
