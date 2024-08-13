package com.edw.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <pre>
 *  com.edw.controller.HelloServlet
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 13 Aug 2024 14:51
 */
@WebServlet(name = "HelloServlet", urlPatterns = "/")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1> Hello World </h1>");

    }
}