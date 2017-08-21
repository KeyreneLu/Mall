package com.keyrene.controller;

import com.keyrene.po.User;
import com.keyrene.service.UserService;
import com.keyrene.utils.CommonUtils;
import com.keyrene.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by DELL on 2017/7/20.
 */
@Controller
public class UserController extends BaseController{
    @Autowired
    UserService userService;

    @RequestMapping("/user/register")
    public String register(User user){

        user.setUid(CommonUtils.getUUid());
        user.setTelephone(null);
        user.setState(0);
        String activeCode = CommonUtils.getUUid();
        user.setCode(activeCode);

        boolean isSuccess = userService.insertByRegister(user);
        System.out.print("userid"+isSuccess);
        if (isSuccess){
            String emailMsg = " 恭喜您注册功能，请点击下面的链接激活账号"+"<a href='http://localhost:8081/user/active?activeCode="+activeCode+"'>"
            +"http://localhost:8081/user/active?activeCode="+activeCode+"</a>";
            try {
                MailUtils.sendMail(user.getEmail(),emailMsg);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping("/user/active")
    public String activeUser(@RequestParam("activeCode") String activeCode){

        boolean isActive = userService.updateUserByCode(activeCode);

        if (isActive){
            return "redirect:/account/login.html";
        }else {
            return "fail";
        }
    }

    @RequestMapping("/user/getImage")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int width = 63;
        int height = 37;
        Random random = new Random();
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        //Graphics类的样式
        g.setColor(this.getRandColor(200, 250));
        g.setFont(new Font("Times New Roman",0,28));
        g.fillRect(0, 0, width, height);
        //绘制干扰线
        for(int i=0;i<40;i++){
            g.setColor(this.getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        //绘制字符
        String strCode = "";
        for(int i=0;i<4;i++){
            String rand = String.valueOf(random.nextInt(10));
            strCode = strCode + rand;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(rand, 13*i+6, 28);
        }
//        System.out.println("request="+request+"action里的request对象");
        //将字符保存到session中用于前端的验证

        request.getSession().setAttribute("strCode", strCode);
        g.dispose();

        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
    }

    //创建颜色
    Color getRandColor(int fc,int bc){
        Random random = new Random();
        if(fc>255)
            fc = 255;
        if(bc>255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }

    @RequestMapping("/user/getStrCode")
    @ResponseBody
    public Map getStrCode(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            map.put("strCode",(String)request.getSession().getAttribute("strCode"));
            map.put("flag",true);//1表示请求成功
        }catch(Exception e){
            map.put("flag",false);
        }
        return map;
    }

    @RequestMapping("/user/checkname")
    @ResponseBody
    public Map checkName(String username){
//        System.out.print("code"+username);
        Map<String,Object> map = new HashMap<String,Object>();

        if (userService.selectUserByName(username)){
            map.put("flag",true);
        }else {
            map.put("flag",false);
        }
        return map;
    }

    @RequestMapping("/user/login")
    @ResponseBody
    public Map userLogin(String username, String password,HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        User user = userService.selectUserLoginByName(username);

        if (user !=null){
            if (user.getPassword().equals(password)){
                map.put("code",200);
                map.put("msg","登录成功!");
                session.setAttribute("user",user);
            }else {
                map.put("code",101);
                map.put("msg","密码错误!");
            }
        }else {
            map.put("code",100);
            map.put("msg","用户名不存在!");
        }

        return map;

    }
}
