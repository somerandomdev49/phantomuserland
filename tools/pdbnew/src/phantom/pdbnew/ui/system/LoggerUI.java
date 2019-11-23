package phantom.pdbnew.ui.system;

import phantom.pdbnew.internal.debug.PLogger;

import java.awt.*;

public abstract class LoggerUI<P extends Component> extends UI<P> {
    private PLogger logger;

    protected LoggerUI(P self) {
        super(self);
    }

    protected void initializeLog(Class clazz) {
        logger = new PLogger();
        logger.initializeLog(clazz);
    }

    protected void info(String f, Object...args) {
        logger.info(f, args);
    }

    protected void debug(String f, Object...args) {
        logger.debug(f, args);
    }

    protected void error(String f, Object...args) {
        logger.error(f, args);
    }
}
