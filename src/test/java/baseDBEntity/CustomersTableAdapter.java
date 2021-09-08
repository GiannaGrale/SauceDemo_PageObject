package baseDBEntity;

import models.Customer;
import services.DataBaseService;
import java.sql.ResultSet;


public class CustomersTableAdapter {

    DataBaseService dataBaseService;

    public CustomersTableAdapter(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    //методы добавления,редактирования, удаления
    public ResultSet getAllCustomers (){
        String sql = "SELECT * FROM public.customers";
        return dataBaseService.executeQuery(sql);
    }

    public  boolean addCustomer (Customer customer){
        String insertSQL = "INSERT INTO public.customers(" +
                "firstname, lastname, email, age)" +
                "VALUES ('"+ customer.firstname + "', '" + customer.lastname
                + "', '" + customer.email +"', " + customer.age +");";
        return  dataBaseService.executeSQL(insertSQL);
    }

    //методы создания и удаления таблицы
    public boolean createTable (){
        String createTableSQL = "CREATE TABLE customers (" +
                "id SERIAL PRIMARY KEY, " +
                "firstName CHARACTER VARYING(30), " +
                "lastName CHARACTER VARYING(30), " +
                "email CHARACTER VARYING(30), " +
                "age INTEGER" +
                ");";

        return  dataBaseService.executeSQL(createTableSQL);
    }

    public boolean dropTable (){
        String dropTableSQl = "DROP TABLE customers;";

        return dataBaseService.executeSQL(dropTableSQl);

    }
}