package com.gmail.grind3x.controller;

import com.gmail.grind3x.dao.AdvertisementDAO;
import com.gmail.grind3x.dao.CategoryDAO;
import com.gmail.grind3x.dao.ImageDAO;
import com.gmail.grind3x.dao.PostgresDAOFactory;
import com.gmail.grind3x.model.Advertisement;
import com.gmail.grind3x.model.Category;
import com.gmail.grind3x.model.Image;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/index.jsp")
public class MainServlet extends HttpServlet {
    static final int PAGE_LIMIT = 5;

    @Override
    public void init() throws ServletException {
        PostgresDAOFactory daoFactory = new PostgresDAOFactory();
        CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
        ImageDAO imageDAO = daoFactory.getImageDAO();

        Image image = new Image("imac.png", "/img/");
        imageDAO.insertImage(image);
        Category autoCat = new Category("Computers, Tablets & Network Hardware", image);
        categoryDAO.insert(autoCat);

        Image image1 = new Image("clothes.png", "/img/");
        imageDAO.insertImage(image1);
        Category clothes = new Category("Clothing, Shoes & Accessories", image1);
        categoryDAO.insert(clothes);

        Image image2 = new Image("oculus-rift.png", "/img/");
        imageDAO.insertImage(image2);
        Category games = new Category("Video Games & Consoles", image2);
        categoryDAO.insert(games);

        Image image3 = new Image("jewels.png", "/img/");
        imageDAO.insertImage(image3);
        Category jewelry = new Category("Jewelry & Watches", image3);
        categoryDAO.insert(jewelry);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostgresDAOFactory daoFactory = new PostgresDAOFactory();
        CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
        AdvertisementDAO advertisementDAO = daoFactory.getAdvertisementDAO();
        List<Category> categories = categoryDAO.getAll();
        HttpSession session = req.getSession(true);
        session.setAttribute("categories", categories);
        int page = 1;
        try {
            page = Integer.valueOf(req.getParameter("page"));
        } catch (NumberFormatException e) {
        }
        List<Advertisement> advertisements = advertisementDAO.getAllLimit((page - 1) * PAGE_LIMIT, PAGE_LIMIT);
        int total = (int) advertisementDAO.getTotalCount();

        int totalPages = 0;
        if (total % PAGE_LIMIT == 0) {
            totalPages = total / PAGE_LIMIT;
        } else {
            totalPages = (total / PAGE_LIMIT) + 1;
        }
        req.setAttribute("total_pages", totalPages);
        req.setAttribute("advertisements", advertisements);
        req.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/main.jsp");
        requestDispatcher.forward(req, resp);
    }
}
