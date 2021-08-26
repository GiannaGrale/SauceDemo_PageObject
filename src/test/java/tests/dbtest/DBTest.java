package tests.dbtest;

import baseDBEntity.CustomersTableAdapter;
import baseEntities.BaseApiTest;
import models.Customer;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTest extends BaseApiTest {

    @Test
    public void connectionTest() {

    }

    @Test
    public void firstTest() {
        String sql = "SELECT * FROM public.customers";

        ResultSet rs = dataBaseServices.executeQuery(sql);
        try {
            while (rs.next()) {
                String userID = rs.getString("id");
                String firstname = rs.getString("firstname");
                int age = rs.getInt("age");
                System.out.println("id " + userID);
                System.out.println("firstname " + firstname);
                System.out.println("age " + age);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.toString());
        }
        System.out.println("Test is completed...");
    }

    @Test
    public void fullDBTest() throws SQLException {
        Customer customer = Customer.builder()
                .firstname("Anna")
                .lastname("Grace")
                .age(26)
                .email("customer@gmail.com")
                .build();
        CustomersTableAdapter customersTableAdapter = new CustomersTableAdapter(dataBaseServices);
        customersTableAdapter.addCustomer(customer);

        ResultSet rs = customersTableAdapter.getAllCustomers();

        while (rs.next()) {
            String firstname = rs.getString("firstname");
            int age = rs.getInt("age");

            System.out.println("firstname " + firstname);
            System.out.println("age " + age);

        }
    }

    @Test
    public void homeTaskSQLTest() {
        String sql1_1 = "SELECT * FROM Customers WHERE City = 'London';";
        String sql1_2 = "SELECT CustomerName, ContactName FROM Customers WHERE PostalCode LIKE '%23';";
        String sql1_3 = "SELECT distinct City FROM Customers;";
        String sql1_4 = "SELECT * FROM Customers WHERE PostalCode LIKE '0%';"; //вывести пользователей? только имена?
        String sql1_5 = "SELECT CustomerName FROM Customers WHERE Country NOT LIKE 'USA';";
        String sql1_6 = "SELECT * FROM Customers WHERE Country LIKE 'France' ORDER BY ContactName DESC;";
        String sql1_7 = "SELECT CustomerName FROM Customers WHERE Country ='USA' OR Country ='Germany' LIMIT 10;";
        String sql1_7_1 = "SELECT CustomerName FROM Customers WHERE Country IN ('USA','Germany') LIMIT 10;"; //7. 2 options

        String sql2_1 = "SELECT * FROM [Products] WHERE ProductName LIKE 'M%';";
        String sql2_2 = "SELECT Unit FROM [Products] WHERE ProductName ='Steeleye Stout';";
        String sql2_3 = "SELECT ProductName FROM [Products] WHERE Price > 22;";
        String sql2_4 = "SELECT * FROM [Products] WHERE Unit LIKE '500 g';";
        String sql2_5 = "SELECT * FROM [Products] WHERE Unit like '%bottles%';";
        String sql2_6 = "SELECT * FROM [Products] WHERE SupplierID LIKE '7' ORDER BY Price  DESC;";

        String sql4_1 = "SELECT LastName, FirstName, Notes FROM [Employees] WHERE Lastname = 'Leverling';";
        String sql4_2 = "SELECT * FROM [Employees] WHERE BirthDate >='1961%';";
        String sql4_3 = "SELECT BirthDate FROM [Employees] WHERE FirstName LIKE 'A%';";
        String sql4_4 = "SELECT LastName, FirstName, BirthDate FROM [Employees] ORDER BY BirthDate ASC;";

        String sql3 = " SELECT * FROM characters WHERE level > 45 AND race = 'dwarf';";
    }
}
