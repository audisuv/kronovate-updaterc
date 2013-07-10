package com.kronos.updater.mqtt.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;



public class UnzipUtil {
	
	 public static final void unzip(String zipFile, String unzipFolder) throws IOException {
		 	ZipFile file = new ZipFile(zipFile);
	        for (Enumeration<? extends ZipEntry> i = file.entries(); i.hasMoreElements(); ) {
	            ZipEntry entry = (ZipEntry) i.nextElement();
	            if (entry.isDirectory()){
	            	Files.createDirectory(Paths.get(unzipFolder, entry.getName()));
	            } else {
	            	unzipZipEntry(file, entry, unzipFolder);

	            }
	        }
	 }
	 private static void unzipZipEntry(ZipFile file, ZipEntry entry, String unzipFolder) throws IOException, FileNotFoundException {
			try(InputStream in = file.getInputStream(entry); 
				OutputStream fos = Files.newOutputStream(Paths.get(unzipFolder, entry.getName()), StandardOpenOption.WRITE)){
			    
			    byte[] buffer = new byte[1024];
			    int len;
			    while ((len = in.read(buffer)) >= 0){
			        fos.write(buffer, 0, len);
			    }
			}
		}
	        
	        

}
