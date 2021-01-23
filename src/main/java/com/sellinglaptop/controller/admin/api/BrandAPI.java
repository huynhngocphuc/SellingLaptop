package com.sellinglaptop.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sellinglaptop.model.BrandModel;
import com.sellinglaptop.service.BrandService;
import com.sellinglaptop.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-brand"})
public class BrandAPI extends HttpServlet {
    @Inject
    BrandService brandService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<BrandModel> brands= brandService.findAll();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json"); //
        // Convert from type json to Model

        BrandModel brandModel = HttpUtil.of(req.getReader()).toModel(BrandModel.class);

        brandModel = brandService.save(brandModel);
        mapper.writeValue(resp.getOutputStream(),brandModel);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json"); //
        // Convert from type json to Model

        int id = Integer.parseInt(req.getReader().readLine());
        brandService.delete(id);
        mapper.writeValue(resp.getOutputStream(),"{}");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json"); //
        // Convert from type json to Model
        BrandModel brandModel = HttpUtil.of(req.getReader()).toModel(BrandModel.class);

        brandService.update(brandModel);
        mapper.writeValue(resp.getOutputStream(),"{}");
    }
}
