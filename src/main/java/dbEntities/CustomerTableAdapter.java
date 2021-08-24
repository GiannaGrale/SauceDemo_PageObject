/*
package dbEntities;

import models.Customer;
import services.DataBaseService;

import java.sql.ResultSet;

public class CustomerTableAdapter {

    DataBaseService dbService;

    public CustomerTableAdapter(DataBaseService dbService) {
        this.dbService = dbService;
    }

    //Методы добавления, редактирования и удаления записей.
    public ResultSet getAllCustomers() {
        String sql ="SELECT * FROM public.customers";
        return dbService.executeQuery(sql);
    }

    public boolean addCustomer(Customer customer) {
        String insertSQL = "INSERT INTO public.customers(" +
                "firstname, lastname, email, age)" +
                "VALUES ('" + customer.firstname +"','"
                + customer.lastname +"','"
                + customer.email +"','"
                + customer.age +"');";
        return dbService.executeSQL(insertSQL);
    }

    //Методы создания и удаления таблицы
    public boolean createTable() {
        String createTableSQL = "CREATE TABLE customers (" +
                "id SERIAL PRIMARY KEY, " +
                "firstName CHARACTER VARYING(30), " +
                "lastName CHARACTER VARYING(30), " +
                "email CHARACTER VARYING(30), " +
                "age INTEGER" +
                ");";

        return dbService.executeSQL(createTableSQL);
    }

    public boolean dropTable() {
        String dropTableSQL = "DROP TABLE customers";
        return dbService.executeSQL(dropTableSQL);

    }
}
*/
