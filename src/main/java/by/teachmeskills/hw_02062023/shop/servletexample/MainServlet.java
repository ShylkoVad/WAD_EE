package by.teachmeskills.hw_02062023.shop.servletexample;

import by.teachmeskills.hw_02062023.shop.listener.DBConnectionManager;
import by.teachmeskills.hw_02062023.shop.model.User;
import by.teachmeskills.hw_02062023.shop.utils.CRUDUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/shop")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (((User) req.getSession().getAttribute("user")).getLogin().equals("empty")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/shop.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        ServletContext servletContext = getServletContext();
        DBConnectionManager dbConnectionManager = (DBConnectionManager) servletContext.getAttribute("DBManager");
        User user = CRUDUtils.getUserDB(login, password, dbConnectionManager.getConnection());
        if (user != null) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", user);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/shop.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
