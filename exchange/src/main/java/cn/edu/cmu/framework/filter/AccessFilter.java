package cn.edu.cmu.framework.filter;

import cn.edu.cmu.dao.JzgMapper;
import cn.edu.cmu.domain.Jzg;
import cn.edu.cmu.domain.JzgParams;
import cn.edu.cmu.framework.CmuConstants;
import cn.edu.cmu.framework.util.CmuStringUtil;
import cn.edu.cmu.framework.util.WebAppContextUtils;
import cn.edu.cmu.framework.util.YmlUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jasig.cas.client.authentication.AttributePrincipal;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: jshand
 * @Date: 2018/9/23 14:15
 * @site https://github.com/jshand
 * @Project cmu
 * @Version 1.0
 */
public class AccessFilter implements Filter {
    private Logger logger = Logger.getLogger(AccessFilter.class);
    private static String ignoreExt;
    private boolean debug = false;
    private boolean ignoreLogin = false;
    private String ignoreLoginUserId;
    private String ignoreLoginUserType;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        ignoreExt = (String) YmlUtils.getProperty("sys.access.ignore.ext");
        debug = Boolean.valueOf((Boolean) YmlUtils.getProperty("sys.debug.switch"));
        ignoreLogin = Boolean.valueOf((Boolean) YmlUtils.getProperty("sys.ignoreLogin.switch"));
        ignoreLoginUserId = (String) YmlUtils.getProperty("sys.ignoreLogin.userId");
        ignoreLoginUserType = (String) YmlUtils.getProperty("sys.ignoreLogin.userType");
    }

    private void noCache(HttpServletResponse response) {
        //禁止浏览器缓存所有动态页面
        response.setDateHeader("Expires", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");

    }


    /**
     * 次浏览器验证，在session中是否有用户信息,如果访问到此filter，说明已经登录或者是 debug模式，需要从request获取用户信息
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = CmuStringUtil.REQUEST_URI(request);
        String ext = CmuStringUtil.REQUEST_EXTENSION_NAME(uri);

        logger.debug("Access URI is : " + uri);
        logger.debug("Access ext is : " + ext);

        //禁用浏览器缓存
        noCache((HttpServletResponse) servletResponse);
        try {
            validateSessionUserInfo(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 验证是否存在session中的用户新
     *
     * @param request
     * @param response
     */
    private void validateSessionUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        String sessionUserId = (String) session.getAttribute(CmuConstants.SESSION.USER_ID);

        //第一次访问系统，session中没有userid的内容
        if (StringUtils.isEmpty(sessionUserId)) {

            String loginUserType = null;
            //根据类型查询教职工、本科生、研究生信息
            if (ignoreLogin) {//本地测试，忽略登录过程
                sessionUserId = ignoreLoginUserId;
                loginUserType = ignoreLoginUserType;
            } else {
                AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
                if (principal == null) {
                    throw new Exception("请管理员配置统一登录信息");
                }
                Map<String, Object> attributes = principal.getAttributes();

                sessionUserId = principal.getName();
                loginUserType = String.valueOf(attributes.get("ID_TYPE"));
            }

            //根据用户id获取 用户主体 信息

            session.setAttribute(CmuConstants.SESSION.USER_TYPE, loginUserType);
            session.setAttribute(CmuConstants.SESSION.USER_ID, sessionUserId);

            queryUserBody(loginUserType, sessionUserId, session);
        }

    }

    //根据用户类型 及id，查询用户信息
    private void queryUserBody(String loginUserType, String sessionUserId, HttpSession session) throws Exception {
        //教职工
        if (loginUserType.equals(CmuConstants.SESSION.USER_TYPE_JZG)) {
            queryJzgBody(loginUserType, sessionUserId,session);
        }
        //本科生

        //研究生
    }


    private void queryJzgBody(String loginUserType, String sessionUserId, HttpSession session) throws Exception {
        JzgMapper jzgDao = WebAppContextUtils.getBean(JzgMapper.class);

        JzgParams params = new JzgParams();
        params.createCriteria().andGhEqualTo(sessionUserId);

        List list = jzgDao.selectByExample(params);
        if(CollectionUtils.isEmpty(list)){
            throw new Exception("根据工号，未查询到教职工信息..");
        }
        Jzg jzg = (Jzg) list.get(0);
        session.setAttribute(CmuConstants.SESSION.USER_INFO_JZG, jzg);
        session.setAttribute(CmuConstants.SESSION.USER_NAME, jzg.getXm());
    }

    @Override
    public void destroy() {

    }


    public void debugUser(HttpServletRequest request) {
        AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
        if (principal == null) {

        }
        String userID = principal.getName();

    }


}
