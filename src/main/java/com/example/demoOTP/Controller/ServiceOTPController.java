package com.example.demoOTP.Controller;

import com.example.demoOTP.Config.MD5;
import com.example.demoOTP.Config.Reponse.reponseError;
import com.example.demoOTP.Config.Reponse.responseSuccessfully;
import com.example.demoOTP.Config.Request.hsahmd5;
import com.example.demoOTP.Config.Request.otprequest;
import com.example.demoOTP.Model.ServiceOTP;
import com.example.demoOTP.Model.Owner;
import com.example.demoOTP.Service.ServiceOTPService;
import org.jboss.resteasy.reactive.RestHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceOTPController {
    @Autowired
    private ServiceOTPService otpService;
    @Autowired
    private MD5 md5 ;

    @GetMapping("/randomOTP")
    public Object RequestServiceOTP(@RestHeader("Secret") String Secret, @RequestBody otprequest request) throws Exception {
        Object numOTP = otpService.randomCode(Secret , request );

        return numOTP;

    }

    @PostMapping("/CheckOTP")
    public Object CheckOTP(@RequestBody ServiceOTP serviceOTP ){
        String code = serviceOTP.getOtpCode();
        String reference = serviceOTP.getReferenceCode();
        String uuid = serviceOTP.getUuId();

      return otpService.CheckTimeOTP(code , reference , uuid);
    }

    @PostMapping("/hashMD5")
    public String HashMD5(@RequestBody hsahmd5 hsahmd5){
        String SecretKey = hsahmd5.getSecretKey();
        String nameService = hsahmd5.getNameService();
        String reqid = hsahmd5.getReqid();

        Owner owner = new Owner();
        String hashSecretKey = md5.getMd5(SecretKey+reqid);

        owner = otpService.getUserByNameService(nameService);

        String key = md5.getMd5(owner.getSecretKey()+reqid);

        String message = "" ;

        if(hashSecretKey.equals(key)){
             message = "Ok => key : "+ hashSecretKey  ;
        }else{
             message = "No => key : "+ hashSecretKey  ;
        }
        System.out.println(message );

        return message ;
    }

    @GetMapping("/getalloener")
    public List<Owner> getalloener(){
        return otpService.getAllUSer();
    }
}
