package org.acme;

import com.example.demoOTP.Config.MD5;
import com.example.demoOTP.Model.Owner;
import com.example.demoOTP.Service.ServiceOTPService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ExampleResourceTest {
    @Autowired
    private ServiceOTPService otpService;
    @Autowired
    private MD5 md5 ;
    @Test
    public void testHelloEndpoint() {
        Owner owner = new Owner();
        String SecretKey = "Phiphatpong.key";
        String nameService ="Phiphatpong";
        String reqid = "124";

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
    }

}