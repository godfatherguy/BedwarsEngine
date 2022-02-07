package org.godfather.bedwars.utils;

import java.io.*;

public final class FileUtil {

    public static void copy(File source, File destination) throws IOException {
        if(source.isDirectory()){
            if(!destination.exists()){
                destination.mkdir();
            }

            String[] files = source.list();
            if(files == null) return;
            for(String file : files){
                File newsource = new File(source, file);
                File newdestination = new File(destination, file);
                copy(newsource, newdestination);
            }
        }
        else{
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(destination);

            byte[] buffer = new byte[1024];

            int lenght;
            while((lenght = in.read(buffer)) > 0){
                out.write(buffer, 0, lenght);
            }

            in.close();
            out.close();
        }
    }

    public static void delete(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files == null) return;
            for(File child : files){
                delete(child);
            }
        }
        file.delete();
    }
}
