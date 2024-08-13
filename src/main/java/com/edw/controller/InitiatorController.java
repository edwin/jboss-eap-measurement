package com.edw.controller;

import com.edw.service.DirectSQLService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <pre>
 *  com.edw.controller.InitiatorController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 13 Aug 2024 16:10
 */
@WebServlet(name = "InitiatorController", urlPatterns = "/initiate")
public class InitiatorController extends HttpServlet {

    private DirectSQLService directSQLService = new DirectSQLService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        directSQLService.initiateData();

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1> Success </h1>");

    }
}