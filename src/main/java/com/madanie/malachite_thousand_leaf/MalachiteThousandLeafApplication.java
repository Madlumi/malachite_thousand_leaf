package com.madanie.malachite_thousand_leaf;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


import org.springframework.context.annotation.Bean;

import com.madanie.malachite_thousand_leaf.Prospect.ProspectRepo;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

@SpringBootApplication
public class MalachiteThousandLeafApplication{
   private static CommandLine cmdargs;
   public static CommandLine getCmdargs(){return cmdargs;}

   public static void main(String[] args){
      cmdargs = getArgs(args);
      //only start web server if -w/--web flag is set
      new SpringApplicationBuilder(MalachiteThousandLeafApplication.class).web(
            cmdargs.hasOption("w") ? WebApplicationType.SERVLET : WebApplicationType.NONE 
            ).run(args);
   }

   private static CommandLine getArgs(String[] args){
      CommandLineParser parser = new DefaultParser();
      Options options = new Options();
      options.addOption("i", "input", true, "Input file(default: prospects.txt)");
      options.addOption("w", "web", false, "Enable web UI");
      options.addOption("h", "help", false, "Print help message");
      try{
         CommandLine cmd = parser.parse(options, args);
         if(cmd.hasOption("h")){
            HelpFormatter formatter= new HelpFormatter();
            formatter.printHelp("MalachiteThousandLeafApplication", options);
            System.exit(0);
         }
         return cmd;
      }catch(Exception e){
         System.exit(1);
         return null;
      }
   }

   /**Initialized the prospect repo with the file provided in args or a default file
    *then prints all the prospect data */
   @Bean 
   public int initProspectRepo(ProspectRepo pr){
      if(MalachiteThousandLeafApplication.getCmdargs()==null){ return 1; }
      String file = getCmdargs().getOptionValue("i", "prospects.txt");
      pr.fromCsv(file);
      pr.printAll();
      return 1;
   }
}
