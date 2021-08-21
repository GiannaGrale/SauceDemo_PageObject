package baseDBEntity;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.log4testng.Logger;
import services.DataBaseService;

public class BaseDBTest {
    public static Logger logger = Logger.getLogger(BaseDBTest.class);

    public DataBaseService dataBaseServices;

    @BeforeTest
    public  void setUp (){
        dataBaseServices = new DataBaseService();
    }

    @AfterTest
    public  void  tearDown(){
        dataBaseServices.closeConnection();
    }
}
