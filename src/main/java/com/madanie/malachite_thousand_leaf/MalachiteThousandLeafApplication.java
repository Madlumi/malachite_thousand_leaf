package com.madanie.malachite_thousand_leaf;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MalachiteThousandLeafApplication {
	private static CommandLine cmdargs;

	public static CommandLine getCmdargs() {
		return cmdargs;
	}

	private static final Logger LOG = LoggerFactory.getLogger(MalachiteThousandLeafApplication.class);

	public static void main(final String[] args) {
		LOG.info("Example log from {}", MalachiteThousandLeafApplication.class.getSimpleName());
		cmdargs = getArgs(args);
		// only start web server if -w/--web flag is set
		new SpringApplicationBuilder(MalachiteThousandLeafApplication.class)
				.web(cmdargs.hasOption("w") ? WebApplicationType.SERVLET : WebApplicationType.NONE).run(args);
	}

	private static CommandLine getArgs(final String[] args) {
		CommandLineParser parser = new DefaultParser();
		Options options = new Options();
		options.addOption("i", "input", true, "Input file(default: prospects.txt)");
		options.addOption("w", "web", false, "Enable web UI");
		options.addOption("h", "help", false, "Print help message");
		try {
			CommandLine cmd = parser.parse(options, args);
			if (cmd.hasOption("h")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("MalachiteThousandLeafApplication", options);
				System.exit(0);
			}
			return cmd;
		} catch (Exception e) {
			System.exit(1);
			return null;
		}
	}

}
