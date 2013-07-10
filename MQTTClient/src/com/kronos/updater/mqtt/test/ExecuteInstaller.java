package com.kronos.updater.mqtt.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteInstaller {
	
	public static void runConfigurationManager(String extractedFolder) throws InterruptedException, IOException{
        
        String batFileLocation = extractedFolder+File.separator+"install.exe";
        String optFile =  extractedFolder+File.separator+"file.opt";
        File batchFile = new File(batFileLocation);
        ProcessBuilder processBuilder = new ProcessBuilder(batchFile.getAbsolutePath(),"-options",optFile, "-silent");
        
    // Map<String, String> environment = launcher.environment();
        //ProcessBuilder processBuilder = new ProcessBuilder("cmd");
        //processBuilder.redirectInput(batchFile);
     processBuilder.redirectErrorStream(true);
   //  processBuilder.directory(new File("F:\\script"));

     //environment.put("", "var");
     //launcher.command("callConfigurationManager.bat");
     Process p = processBuilder.start(); // And launch a new process
     BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
     String line;
     while ((line = output.readLine()) != null)
       System.out.println(line);

     // The process should be done now, but wait to be sure.
     p.waitFor();

        
 }


}
