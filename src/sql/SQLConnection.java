package sql;

import java.sql.*;

//make this static? todo: think about making this class static.
/**
 * Manages connections to the database.
 *
 * @author Eric
 */
public class SQLConnection implements AutoCloseable{
    private Statement sqlStatement;
    private Connection sqlConnection;

    public Statement getSqlStatement() {
        return sqlStatement;
    }

    /**
     * Instantiates a new Sql connection.
     *
     * @throws SQLException If the database connection has an error.
     */
    public SQLConnection() throws SQLException {
        sqlConnection = DriverManager.getConnection("jdbc:derby:MealDatabase;create=true");
        sqlStatement = sqlConnection.createStatement();

    }

    @Override
    public void close() throws Exception {
        sqlStatement.close();
        sqlConnection.close();
    }
}
