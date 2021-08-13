package tests.gui;


import baseEntities.BaseTest;
import org.testng.annotations.Test;

public class LoggerTest extends BaseTest {

    @Test
    public void loginLevelsTest (){
        logger.fatal("Level is fatal");
        logger.error("Level is error");
        logger.warn("Level is warn");
        logger.info("Level is info");
        logger.debug("Level is debug");
        logger.trace("Level is trace");
    }
}