import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author peove
 * @date 2022-01-09-19:55
 *
 * 根据雪花算法 生成Id, 需要用到 hutool .
 *
 * 注意: 生成的id（19位） 依赖于 服务器当前时间, 如果时间回拨, 可能产生相同的id.
 */
@Slf4j
@Component
public class IdGenerateSnowflake {

    private long workerId = 0;
    private long dataCenterId = 1;

    // 0号机房, 1号机器
    private Snowflake snowflake = IdUtil.createSnowflake(workerId, dataCenterId);

    @PostConstruct
    public void init() {

        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            workerId = NetUtil.getLocalhost().hashCode();
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        System.err.println("workId = " + workerId);
    }

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long dataCenterId) {
        return IdUtil.createSnowflake(workerId, dataCenterId).nextId();
    }
}
