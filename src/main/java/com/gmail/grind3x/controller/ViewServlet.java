package com.gmail.grind3x.controller;

import com.gmail.grind3x.dao.AdvertisementDAO;
import com.gmail.grind3x.dao.AuthorDAO;
import com.gmail.grind3x.dao.PostgresDAOFactory;
import com.gmail.grind3x.model.Advertisement;
import com.gmail.grind3x.model.Author;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostgresDAOFactory daoFactory = new PostgresDAOFactory();
        AdvertisementDAO advertisementDAO = daoFactory.getAdvertisementDAO();
        AuthorDAO authorDAO = daoFactory.getAuthorDAO();

        String param = "";
        List<Advertisement> advList = null;
        Author author = null;
        if ((param = req.getParameter("author")) != null) {
            advList = advertisementDAO.findByAuthor(param);
            author = authorDAO.findAuthorByName(param);
        } else {
            advList = advertisementDAO.getAll();
        }
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("advertisements", advList);
        req.setAttribute("author", author);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view.jsp");
        requestDispatcher.forward(req, resp);
    }
}
