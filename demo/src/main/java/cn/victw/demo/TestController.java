package cn.victw.demo;

import cn.victw.sensitive.annotation.SensitiveMethod;
import cn.victw.sensitive.service.UseSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口控制器
 *
 * @author Vincent Tao
 * @date 2022/7/25 19:57
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/getUser")
    @SensitiveMethod
    public User getUserInfo(String phone) {
        return new User(1L, "张三", phone, "320400192509071234");
    }

}
