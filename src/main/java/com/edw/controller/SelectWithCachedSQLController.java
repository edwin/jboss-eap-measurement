package com.edw.controller;

import com.edw.service.CacheSQLService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  com.edw.controller.SelectWithCachedSQLController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 14 Aug 2024 13:37
 */
@WebServlet(name = "SelectWithCachedSQLController", urlPatterns = "/selectWithCachedSQL")
public class SelectWithCachedSQLController extends HttpServlet {

    private CacheSQLService cacheSQLService = new CacheSQLService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Map> result = cacheSQLService.select();

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1> Success </h1>");
        for (int i = 0; i < result.size(); i++) {
            out.println(result.get(i)+" <br />");
        }

    }

}
