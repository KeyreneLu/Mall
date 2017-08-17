package com.keyrene.controller;

import com.keyrene.domain.Cart;
import com.keyrene.domain.CartItem;
import com.keyrene.domain.EasyUiDataForGrid;
import com.keyrene.po.Product;
import com.keyrene.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by DELL on 2017/7/26.
 */
@Controller
public class ProductController extends BaseController {

    @Autowired
    ProductService productService;

    @RequestMapping("/index")
    public String findHotList(HttpServletRequest request, HttpServletResponse response){
        List<Product> newProductList = productService.findNewProductList();
        List<Product> hotProductList = productService.findHotProductList();

        request.setAttribute("HotProductList",hotProductList);
        request.setAttribute("NewProductList",newProductList);

//        System.out.print("New+Hot"+hotProductList.size()+"="+newProductList.size());
        return "index";
    }

    @RequestMapping("/list")
    public String selectProductByCid(@RequestParam("categoryid")String id,HttpServletRequest request,@RequestParam(value="currentPage", defaultValue="1") int currentPage){
        EasyUiDataForGrid grid = productService.selectAllProductByCid(id,currentPage);
        request.setAttribute("productList",grid);
        request.setAttribute("cid",id);

        List<Product> products = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if ("pids".equals(cookie.getName())){
                    String pids = cookie.getValue();
                    String[] pidList = pids.split("-");

                    for (String pid :pidList){
                        products.add(productService.selectProductByPid(pid));
                    }
                }
            }
        }

        request.setAttribute("history",products);

        return "product_list";
    }

    @RequestMapping("/productinfo")
    public String selectOneProductByPid(@RequestParam("pid")String pid,HttpServletResponse response,HttpServletRequest request,@RequestParam("cid")String cid,@RequestParam("currentPage")int currentPage){
        Product product = productService.selectProductByPid(pid);
        request.setAttribute("product",product);
        request.setAttribute("cid",cid);
        request.setAttribute("currentPage",currentPage);

        String pids = pid;
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie:cookies) {
                if ("pids".equals(cookie.getName())){
                    pids = cookie.getValue();

                    String[] pidList =  pids.split("-");
                    List<String> datas = Arrays.asList(pidList);
                    LinkedList<String> list = new LinkedList<>(datas);
                    if (list.contains(pid)){
                        list.remove(pid);
                    }
                    list.addFirst(pid);

                    StringBuffer sb = new StringBuffer();
                    for (int j = 0;j<list.size()&&j<7;j++){
                        sb.append(list.get(j));
                        if (j!=list.size()-1){
                            sb.append("-");
                        }
                    }
                    pids = sb.toString();
                }
            }
        }
        Cookie cookie = new Cookie("pids",pids);
        response.addCookie(cookie);
        return "product_info";
    }

    @RequestMapping("/addcart")
    public String addProductToCart(@RequestParam("pid")String pid,@RequestParam("buyNum")int buyNum,HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();

        Product product = productService.selectProductByPid(pid);

        int number = buyNum;

        double cost = product.getShopPrice()*buyNum;

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null){
            cart = new Cart();
        }else {
           Set<String> pids =  cart.getItem().keySet();
           for (String id : pids){
               if (id.equals(pid)){
                   CartItem cartItem = cart.getItem().get(pid);
                    cost +=cartItem.getCost();
                    number += cartItem.getBuyNum();
               }
           }
        }
        CartItem item = new CartItem(product,number,cost);

        cart.setTotal(cart.getTotal()+product.getShopPrice()*buyNum);
        cart.getItem().put(pid,item);

        session.setAttribute("cart",cart);

        return "redirect:/cart";
    }

}
