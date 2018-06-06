package com.gmail.grind3x.controller;

import com.gmail.grind3x.dao.AdvertisementDAO;
import com.gmail.grind3x.dao.PostgresDAOFactory;
import com.gmail.grind3x.model.Advertisement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adv")
public class AdvServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdvertisementDAO advertisementDAO = new PostgresDAOFactory().getAdvertisementDAO();
        Advertisement advertisement = null;
        Long id = Long.parseLong(req.getParameter("id"));
        advertisement = advertisementDAO.findById(id);

        req.setAttribute("advertisement", advertisement);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/adv.jsp");
        requestDispatcher.forward(req, resp);
    }
}
