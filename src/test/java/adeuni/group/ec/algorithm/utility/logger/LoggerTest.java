package adeuni.group.ec.algorithm.utility.logger;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by qianminming on 24/08/15.
 */
public class LoggerTest {

    @Test
    public void testWriteLog() throws Exception {
        Path p = Paths.get("resource/tsp/pcb442.tsp");
        String file = p.getFileName().toString();
        System.out.println(file);

        Logger loggerTest = new Logger("resource/logfile/test");
        for (int i = 0; i < 500; i++) {
            loggerTest.writeLog("BEST:"+String.valueOf(i)+" TIME:"+String.valueOf(i*10000)+" AVG:"+String.valueOf(i)+" STD:"+String.valueOf(i));
        }

    }

    @Test
    public void testCreateFile() throws Exception {

    }

    @Test
    public void testWriteLog1() throws Exception {

    }

    @Test
    public void testGetFileName() throws Exception {

    }

    @Test
    public void testSetFileName() throws Exception {

    }
}