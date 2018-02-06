package test.log;


import java.io.InputStream;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;
import org.apache.logging.log4j.core.util.Loader;

public class Log {
//	static final String DEFAULT_XML_CPNFIGURATION_FILE = "log4j2.xml";
	static Logger LOGGER = LogManager.getLogger("Main2");
	
//	private static Logger logger = Logger.getLogger(Log.class);  

    public static void main(String[] args) {
//    	URL resource = Loader.getResource(DEFAULT_XML_CPNFIGURATION_FILE, Log.class.getClassLoader());
    	
//    	new XmlConfiguration(new LoggerContext(), new ConfigurationSource(new InputStream(), resource));
    	LOGGER.debug("123");
    	LOGGER.error("1234");
    	LOGGER.info("12345");
    }
//     //  记录 debug 级别的信息   
//
//      logger.debug("This is debug message.");  
//
//      //  记录 info 级别的信息   
//
//      logger.info("This is info message.");  
//
//      //  记录 error 级别的信息   
//
//      logger.error("This is error message.");
}
