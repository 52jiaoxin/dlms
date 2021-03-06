package com.psx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.psx.server.config.security.JwtTokenUtil;
import com.psx.server.config.utils.StringUtils;
import com.psx.server.mapper.TUserMapper;
import com.psx.server.mapper.TUserRoleMapper;
import com.psx.server.pojo.RespBean;
import com.psx.server.pojo.RespPageBean;
import com.psx.server.pojo.TBook;
import com.psx.server.pojo.TUser;
import com.psx.server.service.ITUserService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author psx
 * @date 2021/3/23 17:17
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;//?????????????????????

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.qcloud.secretId}")
    private String accessKey;
    @Value("${spring.qcloud.secretKey}")
    private String secretKey;
    @Value("${spring.qcloud.region}")
    private String bucket;
    @Value("${spring.qcloud.bucketName}")
    private String bucketName;
    @Value("${spring.qcloud.url}")
    private String path;
    @Value("${spring.qcloud.prefix}")
    private String qianzui;

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private TUserRoleMapper userRoleMapper;

    /*/**
    * Description:??????????????????token
    * @author: psx
    * @date: 2021/3/23 21:24
    * @paramType:[java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest]
    * @param:[username, password, request]
    * @return:com.psx.server.pojo.RespBean
    */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request){
        //???????????????
        String kaptcha = (String) request.getSession().getAttribute("kaptcha");
        if(code==null||!kaptcha.equalsIgnoreCase(code)){
            return RespBean.error("???????????????????????????????????????");
        }
        //??????
       UserDetails userDetails = userDetailsService.loadUserByUsername(username);//??????UserDetails
        if (userDetails!=null){
            String newPassword=passwordEncoder.encode(userDetails.getPassword().trim());
            if (!passwordEncoder.matches(password,newPassword)){
                return RespBean.error("????????????????????????");
            }
        }else{
            return RespBean.error("??????????????????");
        }


        System.out.println(userDetails);
       if (!userDetails.isEnabled())
           return RespBean.error("????????????????????????????????????");
       //??????security??????????????????
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new
                UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);//????????????
        //??????token
       String token=jwtTokenUtil.generateToken(userDetails);
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        map.put("tokenHead",tokenHead);
        return RespBean.success("????????????",map);
    }

    /*/**
    * Description:???????????????????????????
    * @author: psx
    * @date: 2021/3/23 21:34
    * @paramType:[java.lang.String]
    * @param:[username]
    * @return:Admin
    */
    @Override
    public TUser getAdminByUsername(String username){
            //?????????????????????username???????????????????????????????????????
            return userMapper.selectOne(new QueryWrapper<TUser>().eq("username",username));

    }

    @Override
    public RespBean register(TUser user, String code, HttpServletRequest request) {
        //???????????????
        String email = (String) request.getSession().getAttribute("email");
        String ecode = (String) request.getSession().getAttribute("code");

        if (email==null||email.isEmpty()){
            return RespBean.error("??????????????????????????????");
        }
        if (!ecode.equals(code)){
            return RespBean.error("???????????????");
        }
        if(userMapper.selectOne(new QueryWrapper<TUser>().eq("username",user.getUsername()))!=null){
            return RespBean.error("????????????????????????????????????????????????");
        }
        TUser user1 =new TUser();
        user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());
        user1.setMail(user.getMail());
        System.out.println(user1.getMail());
        if(userMapper.insert(user1)==1){
//            TUserRole userRole=new TUserRole();
//            userRole.setUserid(userMapper.selectOne(new QueryWrapper<TUser>().eq("username",username)).getId());
//            userRoleMapper.insert(userRole);
            return RespBean.error("???????????????");
        }
        return RespBean.error("?????????????????????????????????");
    }

    @Override
    public List<TUser> getEmpList(String nickname) {
        return userMapper.getEmpList(nickname);
    }

    @Override
    public List<TUser> getReaderList(String username) {
        return userMapper.getReaderList(username);
    }

    @Override
    public RespPageBean getUserByPage(Integer currentPage, Integer size, TUser user) {
        //        ????????????
        Page<TBook> page=new Page<>(currentPage,size);
        IPage<TBook> bookIPage=userMapper.getUserByPage(page,user);
        RespPageBean respPageBean=new RespPageBean(bookIPage.getTotal(),bookIPage.getRecords());

        return respPageBean;
    }

    @Override
    public RespPageBean getReaderByPage(Integer currentPage, Integer size, TUser user) {
        //        ????????????
        Page<TBook> page=new Page<>(currentPage,size);
        IPage<TBook> bookIPage=userMapper.getReaderByPage(page,user);
        RespPageBean respPageBean=new RespPageBean(bookIPage.getTotal(),bookIPage.getRecords());
        return respPageBean;
    }
    @Override
    public RespBean updatePassword(String oldPass, String pass, Integer userid) {
        TUser user=userMapper.selectById(userid);

        if(user.getPassword().equals(oldPass)){
            System.out.println(pass);
            user.setPassword(pass);
            System.out.println(user.getPassword());
            if(userMapper.updateById(user)!=0){
                return RespBean.success("????????????");
            }else{
                return RespBean.error("????????????");
            }
        }
        return RespBean.error("???????????????");
    }

    @Override
    public List<TUser> getUserList(String keywords, Integer id,String role) {
        return userMapper.getUserList(keywords,id,role);
    }

    @Override
    public RespBean upload(MultipartFile file, Integer id, Authentication authentication) {
        if (file == null) {
            return RespBean.error( "????????????", null);
        }
        String oldFileName = file.getOriginalFilename();
        String newFileName=StringUtils.add(oldFileName);
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        // 2 ??????bucket?????????,
        ClientConfig clientConfig = new ClientConfig(new Region(bucket));
        // 3 ??????cos?????????
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket??????????????????{name}-{appid} ???
        String bucketName = this.bucketName;

        // ??????????????????, ???????????? 5 GB, ????????????????????????, ?????? 20 M ??????????????????????????????
        File localFile = null;
        try {
            localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            // ?????????????????? COS ????????????
            String key = "/" + this.qianzui + "/user/"+ newFileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            if(id==null){
                return RespBean.error("??????");
            }
            TUser user=userMapper.selectById(id);
            user.setIcon(newFileName);
            int result=userMapper.updateById(user);
            if(result==1){
                TUser principal=(TUser) authentication.getPrincipal();
                principal.setIcon(newFileName);
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(principal,null,
                                authentication.getAuthorities()));
                return RespBean.success("????????????",newFileName);
            }else{
                return  RespBean.error("????????????");
            }
        } catch (IOException e) {
            return RespBean.error(e.getMessage(), null);
        } finally {
            // ???????????????(??????????????????)
            cosclient.shutdown();
        }

//        String localDir = "H:/photo/user/";
//        //????????????????????????
//
//        if(file.isEmpty()!=true){
//            //???????????????
//            String name=file.getOriginalFilename();
//            //????????????????????????
//            String filename= StringUtils.add(name);
//            File dest=new File(localDir+filename);
//            try {
//                //????????????
//                file.transferTo(dest);
//                if(id==null){
//                    return RespBean.error("",filename);
//                }
//                TUser user=userMapper.selectById(id);
//                user.setIcon(filename);
//                int result=userMapper.updateById(user);
//                if(result==1){
//                    TUser principal=(TUser) authentication.getPrincipal();
//                    principal.setIcon(filename);
//                    SecurityContextHolder.getContext().setAuthentication(
//                            new UsernamePasswordAuthenticationToken(principal,null,
//                                    authentication.getAuthorities()));
//                    return RespBean.success("????????????",filename);
//                }else{
//                    return  RespBean.error("????????????");
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//               return  RespBean.error("????????????");
//            }
//        }
//        else {
//            return RespBean.error("?????????????????????");
//        }
    }

    /*/**
    * Description:????????????
    * @author: psx
    * @date: 2021/5/1 10:20
    * @paramType:[java.lang.String, javax.servlet.http.HttpServletRequest]
    * @param:[mail, request]
    * @return:com.psx.server.pojo.RespBean
    */
    @Override
    public RespBean sendMail(String mail, HttpServletRequest request) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setSubject("???????????????");//??????
            //???????????????
            String code = randomCode();
            System.out.println(code);
            //?????????????????????session???
            request.getSession().setAttribute("email",mail);
            request.getSession().setAttribute("code",code);


            mailMessage.setText("???????????????????????????"+code);//??????

            mailMessage.setTo(mail);//?????????

            mailMessage.setFrom(from);//??????????????????

            mailSender.send(mailMessage);//??????
            return  RespBean.success("????????????");
        }catch (Exception e){
            e.printStackTrace();
            return RespBean.error("????????????");
        }
    }
//     ????????????????????????
    private String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }


}
