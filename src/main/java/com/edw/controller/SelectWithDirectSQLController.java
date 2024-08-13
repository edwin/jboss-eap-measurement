package com.edw.controller;

import com.edw.service.DirectSQLService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  com.edw.controller.SelectWithDirectSQLController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 13 Aug 2024 16:28
 */
@WebServlet(name = "SelectWithDirectSQLController", urlPatterns = "/selectWithDirectSQL")
public class SelectWithDirectSQLController extends HttpServlet {

    private DirectSQLService directSQLService = new DirectSQLService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Map> result = directSQLService.select();

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1> Success </h1>");
        for (int i = 0; i < result.size(); i++) {
            out.println(result.get(i)+" <br />");
        }

    }
}
