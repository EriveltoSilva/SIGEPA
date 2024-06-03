package interfaces;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Service <T> {
    void save(HttpServletRequest req, HttpServletResponse resp);
    void update(HttpServletRequest req, HttpServletResponse resp);
    void delete(HttpServletRequest req, HttpServletResponse resp);

    void findById(HttpServletRequest req, HttpServletResponse resp);
    void findAll(HttpServletRequest req, HttpServletResponse resp);

}
