/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.logwrite;

import java.io.File;
import java.io.IOException;
import java.lang.System.Logger.Level;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author sebas
 */
public class Main {

    public static void main(String[] args) throws IOException {

        Logs log = new Logs();
        String nombre = "Caols";
        log.writeLog("Hola como estas " + nombre, "info");
        log.writeLog("Soy un warning y q pasaaaa ¿?", "warning");
        log.writeLog("Error critico asjhaksd", "severe");
        log.writeLog("TEST con valor nulo", null);
        log.writeLog("TEST con valor nulo", "cualquier cosa");
        log.writeLog("fine test", "fine");
        
        
    }
}
