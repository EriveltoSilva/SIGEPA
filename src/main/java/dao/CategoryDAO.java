package dao;

import interfaces.DAO;
import models.CategoryModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements DAO<CategoryModel, Integer> {
    private final Connection connection;

    public CategoryDAO(Connection connection)
    {
        if(connection == null)
            throw new NullPointerException("A conexão com o banco não pode ser null!");
        this.connection = connection;
    }

    @Override
    public int save(CategoryModel obj) {
        String cmd = "INSERT INTO category(name, description, created_at) VALUES (?, ?, CURRENT_TIMESTAMP);";
        int rowAffecteds=0;
        try(PreparedStatement st = connection.prepareStatement(cmd)) {
            st.setString(1, obj.getName());
            st.setString(2, obj.getDescription());
            rowAffecteds = st.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowAffecteds;
    }

    @Override
    public int update(CategoryModel obj) {
        String cmd = "UPDATE category SET name=?, description=? WHERE id=?;";
        int rowAffecteds=0;
        try(PreparedStatement st = connection.prepareStatement(cmd)) {
            st.setString(1, obj.getName());
            st.setString(2, obj.getDescription());
            st.setInt(3, obj.getId());
            rowAffecteds = st.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowAffecteds;
    }

    @Override
    public int delete(Integer id) {
        String cmd = "DELETE FROM category WHERE id=?;";
        int rowAffecteds=0;
        try(PreparedStatement st = connection.prepareStatement(cmd)) {
            st.setInt(1, id);
            rowAffecteds = st.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowAffecteds;
    }

    @Override
    public CategoryModel convertToModel(ResultSet rs) throws SQLException {
        return new CategoryModel(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getTimestamp("created_at"));
    }

    @Override
    public CategoryModel findById(Integer id) {
        CategoryModel category = null;
        String query = "SELECT * FROM category where id=?;";
        try(PreparedStatement st = createFindByIdStatement(connection, query, id); ResultSet rs = st.executeQuery()) {
            if (rs.next())
                category = convertToModel(rs);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public List<CategoryModel> findAll() {
        List<CategoryModel> list = new ArrayList<>();
        String query = "SELECT * FROM category;";
        try(PreparedStatement st = connection.prepareStatement(query); ResultSet rs = st.executeQuery()) {
            while (rs.next())
                list.add(convertToModel(rs));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private PreparedStatement  createFindByIdStatement(Connection connection, String query, Integer id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        return ps;
    }

}
