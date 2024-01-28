package com.madanie.malachite_thousand_leaf;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

@SpringBootApplication
public class MalachiteThousandLeafApplication {
  
   public static void main(String[] args) {
      CommandLine cmd = getArgs(args);
      ProspectManager.setProspectFile(cmd.getOptionValue("i", "prospects.txt"));
      //only start web server if -w/--web flag is set
      new SpringApplicationBuilder(MalachiteThousandLeafApplication.class)
         .web( cmd.hasOption("w") ? WebApplicationType.SERVLET : WebApplicationType.NONE)
         .run(args);
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
