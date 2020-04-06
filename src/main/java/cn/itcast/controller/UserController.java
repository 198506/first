package cn.itcast.controller;

import cn.itcast.domain.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
/*
*
* testString
*
* */
    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("testString执行了");
        //模拟从数据库中查询对象
        User user = new User();
        user.setUsername("美美");
        user.setPassword("123");
        user.setAge(30);
        //model对象
        model.addAttribute("user",user);
        return  "success";
    }
/*
*
* 是void
* 请求转发一次，不用编写项目名称
*
* */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("testVoid执行了");
        //编写请求转发的程序
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);
        //重定向你
        //response.sendRedirect(request.getContextPath()+"/index.jsp");

        //设置中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //直接会进行响应
        response.getWriter().print("你好");
        return;
    }
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        //创建ModelAndView对象
        ModelAndView mv=new ModelAndView();
        System.out.println("testModelAndView执行了");
        //模拟从数据库中查询对象
        User user = new User();
        user.setUsername("小梅");
        user.setPassword("456");
        user.setAge(30);
        //把user对象存储到mv对象中，也会把user对象存储到request对象中
        mv.addObject("user",user);
         //跳转到那个页面
        mv.setViewName("success");

        return mv;
    }

    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect(){
        System.out.println("testForwardOrRedirect执行了");


        //请求的转发
       return "forward:/WEB-INF/pages/index.jsp";
        //重定向
        //return "redirect:/index.jsp";

    }
    /*
    * 模拟异步请求响应
    *
    * */
    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        System.out.println("testAjax执行了");
        //客户端发送ajax的请求，传的是json字符串，后端把json字符串封装到user对象中
        System.out.println(user);
        //做响应，模拟查询数据库
        user.setUsername("haha");
        user.setAge(40);
        //做响应
        return user;

    }
}
