package com.mycompany.logwrite;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Logs {

    private static java.util.logging.Logger LOGGER = null;
    private static String LOGFILE = "RCS-SENDER";
    private String DIRECTORY = "/tmp/logs/RCS-SENDER/";
    private String route;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        LOGGER = java.util.logging.Logger.getLogger(Main.class.getName());

    }

    public String getCurrentDate() {
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = dtf.format(LocalDateTime.now());
        return currentDate;
    }

//    public boolean findAllFilesInFolder(File folder) {
//        String currentDate = getCurrentDate();
//        int count = 0;
//        for (File file : folder.listFiles()) {
//            if (!file.isDirectory() && file.getName().contains(currentDate)) {
//                //System.out.println(file.getName()
//                count += 1;
//            }
//        }
//
//        if (count != 0) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }
    public void generateDailyRouteDirectory() {

        String year = getCurrentDate().substring(0, 4);
        String month = getCurrentDate().substring(5, 7);
        //String day = getCurrentDate().substring(8);
        String route = year + "/" + month;
        this.route = route;

    }

    public void createFolder() {
        generateDailyRouteDirectory();
        File directorios = new File(DIRECTORY + route);
        if (!directorios.exists()) {
            if (directorios.mkdirs()) {
                System.out.println("Folder created");
            } else {
                System.out.println("Error");
            }
        }
    }

    public void writeLog(String message, String type) {
        createFolder();
        FileHandler fh;
        boolean append = true; // DEFINE SI SE ESCRIBE NUEVO ARCHIVO O EN EL MISMO
        //final String format = "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$s %2$s %5$s%6$s%n";
        LOGGER.setUseParentHandlers(false);

        try {

            String fecha = getCurrentDate();
            // Al cambio de dia el log se genera solo ya que no encontrara el archivo
            fh = new FileHandler(DIRECTORY + route +"/" + LOGFILE + "." + fecha + ".log", append);
            LOGGER.addHandler(fh);

            //SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            if (type == null) {
                type = "INFO";
            }
            String typeValue = type.toUpperCase();
            switch (typeValue) {
                case "INFO":
                    LOGGER.info(message);
                    break;
                case "WARNING":
                    LOGGER.warning(message);
                    break;
                case "SEVERE":
                    LOGGER.severe(message);
                    break;

                default:
                    LOGGER.info(message);
            }

            fh.close();

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
