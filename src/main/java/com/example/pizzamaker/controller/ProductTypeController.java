package com.example.pizzamaker.controller;

import com.example.pizzamaker.model.ProductType;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ProductTypeController extends HttpServlet {
    private List<ProductType> list = new LinkedList<>();
    private static Random random = new Random();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("this is GET method");

        if(list.size() == 0) {
            for (int i = 0; i < 10; i++) {
                list.add(new ProductType(i, "productType"+i));

            }
            System.out.println(list);
        }

        resp.getWriter().println(gson.toJson(list));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("this is POST method");

        int id = list.get(list.size() - 1).getId() + 1;
        String name = req.getParameter("name");

        ProductType data = new ProductType(id,name);

        list.add(data);

        resp.getWriter().println(gson.toJson(list));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("desk");
        resp.getWriter().println("this is PUT method");

        ProductType pdtype = null;

        int id = Integer.parseInt(req.getParameter("id"));

        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getId() == id){
                pdtype = list.get(i);
                break;
            }
        }

        if(pdtype == null){
            resp.sendError(400, "there is no table with mentioned id");
            return;
        }

        String name = req.getParameter("name");
        pdtype.setName(name);

        resp.getWriter().println(gson.toJson(list));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("this is a delete method");

        int delId = Integer.parseInt(req.getParameter("idToDelete"));

        List<ProductType> collect = list.stream().filter(item -> item.getId() == delId).collect(Collectors.toList());

        list.removeAll(collect);

        resp.getWriter().println(gson.toJson(list));
    }

}