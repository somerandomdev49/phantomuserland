package phantom.pdbnew.internal.debug;

import org.slf4j.*;

public class PLogger {
    private Class clazz;
    private Logger logger;
    public void initializeLog(Class clazz) {
        logger = LoggerFactory.getLogger(clazz);
        this.clazz = clazz;
    }

    public void info(String f, Object...args) {
        logger.info(f, args);
    }

    public void debug(String f, Object...args) {
        logger.debug(f, args);
    }

    public void error(String f, Object...args) {
        logger.error(f, args);
    }
}
