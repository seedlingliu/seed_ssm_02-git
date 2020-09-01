package com.seed.ssm.controller;

import com.seed.ssm.domain.Product;
import com.seed.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping("/findAll.do")
    @RolesAllowed("DBA")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
//        for (Product product : products) {
//            System.out.println(product.toString());
//        }
        mv.addObject("productList",products);
        mv.setViewName("product-list1");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        System.out.println(product);
        productService.save(product);
        System.out.println("/save.do...");
        return "redirect:findAll.do";
    }
}
