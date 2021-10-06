import com.spike.crowd.entity.Admin;
import com.spike.crowd.service.api.AdminService;
import com.spike.crowd.util.Encryption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 指定 Spring 给 Junit 提供的运行器类
@RunWith(SpringJUnit4ClassRunner.class)
// 加载 Spring 配置文件的注解
@ContextConfiguration(value = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class MybatisTest {

    @Autowired
    private AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testInsert() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YY-MM-DD HH:mm:ss");
        String currentTime = simpleDateFormat.format(new Date());
        Admin admin = new Admin(null, "rei", Encryption.encrypt("123"), "Rei Ayanami", "rei@gmail.com", currentTime);

        logger.info(String.valueOf(adminService.insertAdmin(admin)));
    }

}
