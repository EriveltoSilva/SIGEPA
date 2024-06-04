package dao;

import interfaces.DAO;
import models.CategoryModel;
import models.ProductModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class ProductDAO implements DAO<ProductModel, Long> {
    private final Connection connection;

    public ProductDAO(Connection connection)
    {
        if(connection == null)
            throw new NullPointerException("A conexão com o banco não pode ser null!");
        this.connection = connection;
    }

    public Map<Date, Integer> getProductCountsByDate(){
        String query = "SELECT created_at::date AS date, COUNT(*) AS count FROM product GROUP BY created_at::date ORDER BY created_at::date;";
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

    public int countProduct(){
        int result=0;
        String query = "SELECT COUNT(*) FROM product;";

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
    public int save(ProductModel obj) {
        String cmd = "INSERT INTO product(name, description, price, quantity, category_id, created_at) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP);";
        int rowAffecteds=0;
        try(PreparedStatement st = connection.prepareStatement(cmd)) {
            st.setString(1, obj.getName());
            st.setString(2, obj.getDescription());
            st.setDouble(3, obj.getPrice());
            st.setLong(4, obj.getQuantity());
            st.setLong(5, obj.getCategory().getId());
            rowAffecteds = st.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowAffecteds;
    }

    @Override
    public int update(ProductModel obj) {
        String cmd = "UPDATE product SET name=?, description=? , price=?, quantity=?, category_id=? WHERE id=?;";
        int rowAffecteds=0;
        try(PreparedStatement st = connection.prepareStatement(cmd)) {
            st.setString(1, obj.getName());
            st.setString(2, obj.getDescription());
            st.setDouble(3, obj.getPrice());
            st.setLong(4, obj.getQuantity());
            st.setLong(5, obj.getCategory().getId());
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
        String cmd = "DELETE FROM product WHERE id=?;";
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
    public ProductModel convertToModel(ResultSet rs) throws SQLException {
        CategoryModel cat = new CategoryModel(rs.getLong("c_id"), rs.getString("c_name"), rs.getString("c_description"));
        return  new ProductModel(rs.getLong("p_id"), rs.getString("p_name"),
                                rs.getString("p_description"), rs.getLong("p_quantity"),
                                rs.getDouble("p_price"), rs.getTimestamp("p_created_at"), cat);
    }

    @Override
    public ProductModel findById(Long id) {
        ProductModel product = null;
        String query = "SELECT p.id as \"p_id\", p.name as \"p_name\", " +
                "p.description as \"p_description\", p.price as \"p_price\", p.quantity as \"p_quantity\", " +
                "p.created_at as \"p_created_at\", c.id as \"c_id\", c.name as \"c_name\", " +
                "c.description as \"c_description\", c.created_at as \"c_created_at\" " +
                "FROM product as p, category as c where p.category_id = c.id and p.id=? ;";

        try(PreparedStatement st = createFindByIdStatement(connection, query, id); ResultSet rs = st.executeQuery()) {
            if (rs.next())
                product = convertToModel(rs);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public List<ProductModel> findAll() {
        List<ProductModel> list = new ArrayList<>();
        String query = "SELECT p.id as \"p_id\", p.name as \"p_name\", " +
                                                "p.description as \"p_description\", p.price as \"p_price\", p.quantity as \"p_quantity\", " +
                                                "p.created_at as \"p_created_at\", c.id as \"c_id\", c.name as \"c_name\", " +
                                                "c.description as \"c_description\", c.created_at as \"c_created_at\" " +
                                                "FROM product as p, category as c where p.category_id = c.id ;";

        try(PreparedStatement st = connection.prepareStatement(query); ResultSet rs = st.executeQuery()) {
            while (rs.next())
                list.add(convertToModel(rs));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private PreparedStatement  createFindByIdStatement(Connection connection, String query, Long id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, id);
        return ps;
    }
}
