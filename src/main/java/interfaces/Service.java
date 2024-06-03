package interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Service <T> {
    void getRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void getList(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException;
    void getEdit(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException;

    void save(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException;
    void update(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException;
    void delete(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException;

}
