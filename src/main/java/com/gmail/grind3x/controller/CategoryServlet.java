package com.gmail.grind3x.controller;

import com.gmail.grind3x.dao.AdvertisementDAO;
import com.gmail.grind3x.dao.CategoryDAO;
import com.gmail.grind3x.dao.PostgresDAOFactory;
import com.gmail.grind3x.model.Advertisement;
import com.gmail.grind3x.model.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    static final int PAGE_LIMIT = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostgresDAOFactory daoFactory = new PostgresDAOFactory();
        CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
        AdvertisementDAO advertisementDAO = daoFactory.getAdvertisementDAO();
        Category category = categoryDAO.findById(Long.valueOf(req.getParameter("id")));
        int page = 1;
        try {
            page = Integer.valueOf(req.getParameter("page"));
        } catch (NumberFormatException e) {
        }
        List<Advertisement> advertisements = advertisementDAO.findByCategoryLimit(category, (page - 1) * PAGE_LIMIT, PAGE_LIMIT);
        int total = (int) advertisementDAO.getTotalCountByCategory(category);

        int totalPages = 0;
        if (total % PAGE_LIMIT == 0) {
            totalPages = total / PAGE_LIMIT;
        } else {
            totalPages = (total / PAGE_LIMIT) + 1;
        }
        req.setAttribute("total_pages", totalPages);
        req.setAttribute("advertisements", advertisements);

        req.setAttribute("category", category);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/category.jsp");
        requestDispatcher.forward(req, resp);
    }
}
