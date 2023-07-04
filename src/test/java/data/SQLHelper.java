package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();
    private static final String url = System.getProperty( "db.url" );


    private SQLHelper() {
    }
    @SneakyThrows
    private static Connection getConn()  {
        return DriverManager.getConnection(url, "app", "pass");

    }
    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM payment_entity");

    }

    @SneakyThrows
    public static String getStatusPay() {
        var status = "SELECT status FROM payment_entity";
        return runner.query(getConn(), status, new ScalarHandler<>());
    }


}

