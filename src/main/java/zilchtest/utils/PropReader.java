package zilchtest.utils;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;

import java.util.logging.Logger;
public class PropReader {
    private static Properties configprop;
    static Logger logger = Logger.getLogger(PropReader.class.getName());

        static FileInputStream file = null;
        static  {
            configprop = new Properties();
            try {
                file = new FileInputStream("src/test/resources/TestEnv.properties");
                configprop.load(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        public  static String getProperty(String key) {
            return configprop.getProperty(key);
        }


}
