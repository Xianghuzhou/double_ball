package double_ball.core.entiy;


import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description : 实体基类
 * @date 2017/9/8 上午10:02
 */
@MappedSuperclass
public class BaseEnity {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "insert_at",nullable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertAt;

    public BaseEnity() {
        insertAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInsertAt() {
        return insertAt;
    }

    public void setInsertAt(Date insertAt) {
        this.insertAt = insertAt;
    }
}
