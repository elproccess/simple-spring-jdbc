package doa;

import model.Person;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDOAImpl implements personDOA{

    DataSource dataSource;
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public void insertPerson(Person person) {
        String query = "INSERT INTO CUSTOMERS(name,email) VALUES(?,?)";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, person.getName());
            ps.setString(2,person.getEmail());
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            if(conn != null){
                try{
                    conn.close();
                } catch (SQLException e){}
            }
        }
    }

    @Override
    public Person getUserById(int Pid) {
        String query = "SELECT * FROM CUSTOMERS WHERE idCustomers=?";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            Person person = null;
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Pid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                person = new Person(
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
            rs.close();
            ps.close();
            return person;
        }catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            if(conn!= null){
                try{
                    conn.close();
                }catch (SQLException e){}
            }
        }
    }
}
