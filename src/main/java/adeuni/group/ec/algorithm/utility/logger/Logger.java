package adeuni.group.ec.algorithm.utility.logger;

import java.io.*;

/**
 * Created by Joe on 24/08/15.
 *
 * This class is used to write the log
 */
public class Logger {


    //The file need to be written
    private File file;

    //The filename of the file
    private String fileName;

    public Logger() {
    }

    /**
     * Create the file with specific filename
     *
     * @param fileName
     */
    public Logger(String fileName){
        this.fileName = fileName;
        createFile();
    }

    /**
     * Used to create the file
     *
     */
    public void createFile() {
        file = new File(fileName);
        try{
            if (!file.exists()) {
                file.createNewFile();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Write the content into the file
     *
     * @param content
     */
    public void writeLog(String content){
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content+"\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the filename
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set the filename
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
