package com.keyrene.controller;

import com.keyrene.domain.Cart;
import com.keyrene.po.Orderitem;
import com.keyrene.po.Orders;
import com.keyrene.po.User;
import com.keyrene.service.OrderItemService;
import com.keyrene.service.OrdersService;
import com.keyrene.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Dell on 2017/8/20.
 */

@Controller
public class OrderController extends BaseController {

    private boolean Status;

    @Autowired
    OrdersService mOrdersService;

    @Autowired
    OrderItemService mOrderItemService;

    @RequestMapping("/addorder")
    public String addOrderByCart(HttpServletRequest request, HttpServletResponse response){

        List<Orderitem> datas = new ArrayList<>();

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        if (user == null){
            return "redirect:/account/login.html";
        }
        Orders orders = new Orders();

        String oid = CommonUtils.getUUid();
        orders.setOid(oid);

        orders.setOrdertime(new Date());

        orders.setUid(user.getUid());
        orders.setState(0);

        orders.setName(null);
        orders.setAddress(null);
        orders.setTelephone(null);
        Cart cart = (Cart) session.getAttribute("cart");

        orders.setTotal(cart.getTotal());
        boolean isSuccess = mOrdersService.insertOrderByCart(orders);
        if (!isSuccess){
            return "";
        }

        if (cart != null){

            orders.setTotal(cart.getTotal());

            Set<String> pids = cart.getItem().keySet();

            for (String id : pids){
                Orderitem orderitem = new Orderitem();

                orderitem.setCount(cart.getItem().get(id).getBuyNum());
                orderitem.setOrders(orders);
                orderitem.setOid(oid);
                orderitem.setSubtotal(cart.getItem().get(id).getCost());
                orderitem.setPid(cart.getItem().get(id).getProduct().getPid());
                orderitem.setItemid(CommonUtils.getUUid());
                orderitem.setProduct(cart.getItem().get(id).getProduct());
                Status = mOrderItemService.insertOrderItemByCart(orderitem);

                if (!Status){
//                    pid = id;
                    break;
                }
                datas.add(orderitem);
            }
        }
        orders.setOrderitems(datas);

        request.setAttribute("orders", orders);

        return "order_info";
    }

    @RequestMapping("/order/update")
    public void updateOrdersToPay(@ModelAttribute("form") Orders orders, HttpServletRequest request, HttpServletResponse response){

        boolean status = mOrdersService.updateAddressByOid(orders);

        request.getSession().removeAttribute("cart");

        String pd_FrpId = request.getParameter("pd_FrpId");

        System.out.print(pd_FrpId);
    }
}
