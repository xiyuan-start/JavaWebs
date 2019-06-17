package com.luo.springcloudeurekaclientuser.Tools.SpringSecurity;

import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@PropertySource(value = {"classpath:jst.properties"} ,encoding="UTF-8")
public class ThirdSessionAuthFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Resource(name = "ObjectredisTemplate")
    ValueOperations<String,Object> valueOperations;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置post请求的编码问题.
        //获取请求头部分的Authorization

        String authHeader = request.getHeader("auth");

        //如果请求路径为微信通知后台支付结果则不需要token（之后会在具体的controller中，对双方签名进行验证防钓鱼）
        String url = request.getRequestURI().substring(request.getContextPath().length());

        System.out.println(url +url.contains("/GetUser"));



        if (url.equals("/auth") || url.equals("/ConsumerInfologout") || url.contains("/GetUser") || url.contains("/Reduce")) {
            chain.doFilter(request, response);
            return;
        }

        if (null == authHeader || !authHeader.startsWith("Bearer")) {
            throw new RuntimeException("非法访问用户");
        }

        // The part after "Bearer "
        //从redis 查看session是否已过期
        final String thirdSessionId = authHeader.substring(tokenHead.length());
        String wxSessionObj = stringRedisTemplate.opsForValue().get(thirdSessionId);
        if (StringUtils.isEmpty(wxSessionObj)) {
            throw new RuntimeException("用户身份已过期");
        }

        // 设置当前登录用户
        try (AppContext appContext = new AppContext(wxSessionObj.substring(wxSessionObj.indexOf("#") + 1))) {
            chain.doFilter(request, response);
        }
    }

}