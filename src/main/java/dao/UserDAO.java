package dao;

import interfaces.DAO;
import models.UserModel;
import models.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDAO implements DAO<UserModel, Long> {
    private final Connection connection;

    public UserDAO(Connection connection)
    {
        if(connection == null)
            throw new NullPointerException("A conexão com o banco não pode ser null!");
        this.connection = connection;
    }

    public Map<Date, Integer> getUsersCountsByDate(){
        String query = "SELECT created_at::date AS date, COUNT(*) AS count FROM users GROUP BY created_at::date ORDER BY created_at::date;";
        Map<Date, Integer> productCounts = new LinkedHashMap<>();

        try(PreparedStatement st = connection.prepareStatement(query); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Date date = rs.getDate("date");
                int count = rs.getInt("count");
                productCounts.put(date, count);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productCounts;
    }


    public int countUsers(){
        int result=0;
        String query = "SELECT COUNT(*) FROM users;";

        try(PreparedStatement st = connection.prepareStatement(query); ResultSet rs = st.executeQuery()) {
            rs.next();
            result = rs.getInt("count");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int save(UserModel obj) {
        String cmd = "INSERT INTO users( full_name, email, username, password, created_at, type)\n" +
                "    VALUES (?, ?, ?,md5(?), CURRENT_TIMESTAMP, ?);";
        int rowAffecteds=0;
        try(PreparedStatement st = connection.prepareStatement(cmd)) {
            st.setString(1, obj.getFullName());
            st.setString(2, obj.getEmail());
            st.setString(3, obj.getUsername());
            st.setString(4, obj.getPassword());
            st.setString(5, obj.getUserType());
            rowAffecteds = st.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowAffecteds;
    }

    @Override
    public int update(UserModel obj) {
        String cmd = "UPDATE users SET full_name =?, email=?, username=? , password=md5(?), type=? WHERE id=?;";
        int rowAffecteds=0;
        try(PreparedStatement st = connection.prepareStatement(cmd)) {
            st.setString(1, obj.getFullName());
            st.setString(2, obj.getEmail());
            st.setString(3, obj.getUsername());
            st.setString(4, obj.getPassword());
            st.setString(5, obj.getUserType());
            st.setLong(6, obj.getId());
            rowAffecteds = st.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowAffecteds;
    }

    @Override
    public int delete(Long id) {
        String cmd = "DELETE FROM users WHERE id=?;";
        int rowAffecteds=0;
        try(PreparedStatement st = connection.prepareStatement(cmd)) {
            st.setLong(1, id);
            rowAffecteds = st.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowAffecteds;
    }

    @Override
    public UserModel convertToModel(ResultSet rs) throws SQLException {
        return new UserModel(rs.getLong("id"), rs.getString("full_name"),
                rs.getString("email"),rs.getString("username"),
                rs.getString("password"), rs.getTimestamp("created_at"), rs.getString("type"));
    }

    @Override
    public UserModel findById(Long id) {
        UserModel user = null;
        String query = "SELECT * FROM users where id=?;";
        try(PreparedStatement st = createFindByIdStatement(connection, query, id); ResultSet rs = st.executeQuery()) {
            if (rs.next())
                user = convertToModel(rs);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public UserModel find(UserModel obj) {
        UserModel user = null;
        String query = "SELECT * FROM users where email=? and password=md5(?);";
        try(PreparedStatement st = createFindStatement(connection, query, obj); ResultSet rs = st.executeQuery()) {
            if (rs.next())
                user = convertToModel(rs);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List<UserModel> findAll() {
        List<UserModel> list = new ArrayList<>();
        String query = "SELECT * FROM users;";
        try(PreparedStatement st = connection.prepareStatement(query); ResultSet rs = st.executeQuery()) {
            while (rs.next())
                list.add(convertToModel(rs));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private PreparedStatement  createFindStatement(Connection connection, String query, UserModel obj) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, obj.getEmail());
        ps.setString(2, obj.getPassword());
        return ps;
    }

    private PreparedStatement  createFindByIdStatement(Connection connection, String query, Long id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, id);
        return ps;
    }
}
