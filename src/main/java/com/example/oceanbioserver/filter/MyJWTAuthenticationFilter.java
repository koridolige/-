package com.example.oceanbioserver.filter;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.example.oceanbioserver.common.Result;
import com.example.oceanbioserver.common.ResultStatus;
import com.example.oceanbioserver.util.JWTUtils;
import com.example.oceanbioserver.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
/*
*
jwt工具类 完成了  怎么使用？
使用jwt过滤器，即 BasicAuthenticationFilter继承方法的使用 即 创建MyJWTAuthenticationFilter过滤器
所以要搭建过滤器BasicAuthenticationFilter
继承  BasicAuthenticationFilter  父类  ，必须需要创建他的   两个构造方法，并且重写（覆盖）doFilterInternal(  )方法**
创建 filter包  下创建MyJWTAuthenticationFilter
代码中包含一个自定义的 JWT 认证过滤器 `MyJWTAuthenticationFilter`，该过滤器继承自 Spring Security 的 `BasicAuthenticationFilter`。
这个过滤器的主要作用是处理 JWT 认证逻辑。
*
*
* 这段代码是一个自定义的JWT认证过滤器，继承了Spring Security的BasicAuthenticationFilter类。该过滤器的主要作用是处理JWT认证逻辑。
在doFilterInternal方法中，首先输出日志信息表示进入了JWT过滤器，然后判断请求头中是否包含有效的token。如果token为空，则抛出异常或返回错误信息。
接下来，判断token是否正确，如果不正确则抛出异常或返回错误信息。如果token正确且未过期，则进行续时处理。
最后，根据token获取用户名信息，构造UsernamePasswordAuthenticationToken对象，并将其存储在SecurityContextHolder中，以便后续的请求处理中进行身份认证和授权。
如果token为空或不正确，会返回给前端相应的错误信息。
需要注意的是，在该代码中，JWTUtils是一个工具类，用于处理JWT相关的操作，如验证token、生成新token、获取token中的用户信息等。
另外，该代码中还包含了一个RequestError方法，用于返回前端错误信息的处理。
*
*
实现一个自定义的过滤器，在过滤请求之前会检查请求中是否包含有效的 token（通常是用于身份验证的凭证）。
如果请求中的 token 为空，则会抛出异常或返回错误信息。
这段代码的逻辑大致如下：
1. 首先，输出日志信息表明进入了 JWT 过滤器。
2. 接着，调用 `super.doFilterInternal(request, response, chain)` 来继续执行过滤器链中的下一个过滤器，
这里放行了请求，特别是对 "/login" 请求的放行。
3. 然后，从请求头中获取 Authorization 头部（通常用于携带 token）。
4. 如果 token 为 null，那么抛出 RuntimeException 异常，提示 "没有token"。
需要注意的是，在这段代码中，如果 token 为空，会直接抛出异常。
*
* 这段代码是一个自定义过滤器的实现方法 `doFilterInternal`，用于对请求进行过滤和处理。以下是对代码的简要解释：
1. `protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)` 方法
   用于实现过滤器的逻辑，其中参数`request`为HTTP请求对象，`response`为HTTP响应对象，`chain`为过滤器链对象。
2. 首先，在日志中打印信息，表示进入了JWT过滤器。
3. 注释掉了`super.doFilterInternal(request, response, chain);`，表示暂时放行所有请求，即不对请求进行拦截和处理。
   注释掉这行代码后，该过滤器不会对请求进行任何操作，直接将请求传递给下一个过滤器或处理器进行处理。
4. 从请求头部获取Authorization字段的值，即token。
5. 判断token是否为空。如果为空，抛出异常或进行其他处理，表示没有携带token。
6. 根据token使用`JWTUtils.getTokenUserInfo(token)`方法获取用户名信息。
7. 构造`UsernamePasswordAuthenticationToken`对象，将token中的用户名信息作为**principal（当前用户的主体）**传入，并将认证凭证设置为空字符串。
8. 将用户认证信息传递给上下文，即使用`SecurityContextHolder.getContext().setAuthentication(authenticationToken)`将认证信息存储在`SecurityContextHolder`中。
9. 继续处理请求，调用`chain.doFilter(request, response)`将请求传递给下一个过滤器或处理器进行处理。
需要注意的是，这段代码主要实现了使用JWT验证和解析token，并将认证信息存储在`SecurityContextHolder`中，
以便在后续的请求处理中进行身份认证和授权。同时，通过暂时注释掉`super.doFilterInternal(request, response, chain);`，实现了放行所有请求的效果。
*
在Spring Security中，`Principal`是一个接口，用于表示当前用户的主体（Principal）。主体通常是指与身份相关联的对象，代表了当前经过身份验证的用户。
* 在认证过程中，主体通常包含了用户的身份信息，如用户名、ID等。在代码中，`UsernamePasswordAuthenticationToken`对象中的`principal`参数即代表了用户的身份信息，
* 通常是用户的用户名或者其他标识。在这里，`tokenUserInfo`被作为`principal`传入`UsernamePasswordAuthenticationToken`进行构造，
* 用于表示经过JWT解析后得到的用户信息。在Spring Security的认证过程中，`Principal`会被存储在`Authentication`对象中，
* 通过`SecurityContextHolder.getContext().setAuthentication(authenticationToken)`将`Authentication`对象存储在`SecurityContextHolder`中，
* 以便在后续的请求处理过程中进行访问控制和权限判断。
总之，`Principal`在Spring Security中代表着当前经过身份验证的用户信息，是认证和授权过程中的重要组成部分之一。
* */
@Slf4j
//@Component

public class MyJWTAuthenticationFilter extends BasicAuthenticationFilter {

    public MyJWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public MyJWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    /*doFilterInternal   过滤器框框    要符合过滤器的要求  */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
     log.info("进入JWT过滤器");

     //放行 例如放行 /login 请求  这里目前不能删除
     //super.doFilterInternal(request, response, chain);

        /**
         * token为空时
         */
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("token="+token);
        System.out.println("token="+token);
        //判空
        if(token==null){
             //throw  new RuntimeException("没有token");//token为空
            RequestError(request,response,"没有token");
        }
        /**
         * token不正确，过期的token也是不正确的
         * 判断你没有token  或者 为空  或者token不正确（token过期的也属于不正确）就会 抛出异常
         *携带了token 但是否正确  还需要验证  token不正确，过期的token也是不正确的
         */
        //if(!JWTUtils.Verify(token)){
        //    //throw new RuntimeException("token不正确,抛出异常");
        //    RequestError(request,response,"token不正确");
        //}
        try {
            JWTUtils.Verify(token);
        }catch (SignatureVerificationException e){
             //throw new RuntimeException("token不正确,抛出异常");
            RequestError(request,response,"token不正确");
        }

        /*
        * 续时：生成新的token进行续时处理
        * */
        //获取当前的时间
        Long cur_time =System.currentTimeMillis();
        //获取过时的时间  调用工具类
        Long exp_time = JWTUtils.getExpireTime(token);
        //当前的时间一定要小于过期的时间，如果大于，说明已经过期
        //1000*60代表一分钟  time变量存储的是  还剩下多少分钟就过期了
        Long time =(exp_time-cur_time)/(1000*60);
        //校验  token 小于十五分钟 就过期 此时对toekn进行续时处理
        if(time>0  && time <15){
            //调用工具类 工具目前还没过期的token  获取到用户信息 （即  用户名）
            String userinfo = JWTUtils.getTokenUserInfo(token);
            //根据 用户名  生成新的 token
            String new_token = JWTUtils.createToken(userinfo);
            //将新的token 给到 当前的 用户
            RedisUtil.set(userinfo,new_token);
        }

        //根据token 获取用户名 信息
        String tokenUserInfo = JWTUtils.getTokenUserInfo(token);
        // 构造用户名密码认证信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(tokenUserInfo,"",null);
        //将用户信息传送给上下文    将认证信息存储在 SecurityContextHolder 中
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request,response);

    }


    /**
     * 在过滤器中返回前端正确的信息反馈 向前端传递具体的信息
     * 非法请求
     * @param msg
     */
    public static void RequestError(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String msg){
        //设置编码格式
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");

        Writer out = null;
        try {
            out= new BufferedWriter(new OutputStreamWriter(httpServletResponse.getOutputStream()));

            Result result = new Result(ResultStatus.SERVER_ERROR,null);
            result.setCode(ResultStatus.SERVER_ERROR.getCode());
            //String msg     向前端传递具体的信息
            result.setMessage(msg);

            out.append(JSON.toJSONString(result));
            out.flush();
            out.close();;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
