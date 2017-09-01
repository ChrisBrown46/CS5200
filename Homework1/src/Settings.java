import org.apache.log4j.Logger;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Settings {

    private final String SETTINGSPATH = "Homework1/resources/settings.txt";
    private final static Logger logger = Logger.getLogger(Settings.class);
    private final static Scanner reader = new Scanner(System.in);

    private String address;
    private String port;

    public String getAddress() { return address; }
    public void setAddress(final String address) { this.address = address; }

    public String getPort() { return port; }
    public void setPort(final String port) { this.port = port; }

    /**
     * Load address/port settings from settings.txt if the user wants to, while the file
     * exists, or have the user enter new settings and save them to settings.txt.
     */
    Settings() {
        final File settings = new File(SETTINGSPATH);
        if (settings.exists() && loadPreviousSettingsPrompt())
            loadPreviousSettings();
        else
            loadNewSettings();

        logger.debug(String.format("Saved address %s and port %s.", address, port));
    }

    private boolean loadPreviousSettingsPrompt() {
        System.out.println("Would you like to load previous addressing settings? (Y/n)");
        final String response = reader.next().toLowerCase();
        return Objects.equals(response, "y");
    }

    private void loadPreviousSettings() {
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(SETTINGSPATH))) {
            address = bufferedReader.readLine();
            port = bufferedReader.readLine();
        } catch (final Exception error) {
            logger.error("Could not find address and port in settings.", error);
            address = "127.0.0.1";
            port = "8080";
        }
    }

    private void loadNewSettings() {
        System.out.print("Enter your server's address: ");
        address = reader.next();

        System.out.print("Enter your port: ");
        port = reader.next();

        try {
            final FileWriter writer = new FileWriter(SETTINGSPATH);
            final PrintWriter printWriter = new PrintWriter(writer);

            printWriter.print(String.format(address, "\n"));
            printWriter.print(String.format(port, "\n"));

            printWriter.close();
        } catch (final Exception error) {
            logger.error("Could not write to settings file", error);
        }
    }
}
