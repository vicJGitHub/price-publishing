package com.hywa.pricepublish.controller.inform;

import com.hywa.pricepublish.common.enums.CommonEnum;
import com.hywa.pricepublish.common.utils.CodeUtils;
import com.hywa.pricepublish.config.interceptor.TokenManager;
import com.hywa.pricepublish.representation.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

@RequestMapping("code")
@RestController
public class CodeController {

    @Autowired
    TokenManager tokenManager;

    @GetMapping("showCodeImage")
    public void showCodeImage(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Map<String, Object> map = CodeUtils.generateCodeAndPic();
        // 禁止图像缓存
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);
        resp.setContentType("image/jpeg");
        // 将图像输出
        ServletOutputStream sos;
        try {
            sos = resp.getOutputStream();
            ImageIO.write((RenderedImage) map.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String codeKey="code:"+tokenManager.getToken(req.getHeader("accessToken")).getToken();
        session.setAttribute(codeKey,map.get("code").toString());
    }

    @GetMapping("checkoutCode")
    public ResponseEntity checkoutCode(@RequestParam("code") String code, HttpServletRequest req){
        ResponseBase responseBase = new ResponseBase();
        String codeKey ="code:"+tokenManager.getToken(req.getHeader("accessToken")).getToken();
        String realCode=(String)req.getSession().getAttribute(codeKey);
        if (!StringUtils.isEmpty(realCode)&&realCode.toLowerCase().equals(code.toLowerCase())){
            req.getSession().removeAttribute(codeKey);
            responseBase.setRetHead(CommonEnum.SUCCESS.getIndex(),CommonEnum.SUCCESS.getValue());
        }else{
            responseBase.setRetHead(CommonEnum.FAILURE.getIndex(),CommonEnum.FAILURE.getValue());
        }
        return new ResponseEntity(responseBase,HttpStatus.OK);
    }
}
