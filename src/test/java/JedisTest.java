import com.itheima.service.Impl.PcdServiceImpl;
import com.itheima.utils.JedisUtil;
import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = JedisUtil.getJedis();
//        PcdServiceImpl pcdService = new PcdServiceImpl();
//        String json = pcdService.findPcdByPid(0);
//        jedis.set("a", json);
        String a = jedis.get("pcd"+0);
        System.out.println(a);
    }
}
