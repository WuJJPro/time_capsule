package com.twt.time_capsule.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.twt.time_capsule.entity.CapsulePool;
import lombok.Data;

@Data
public class CapsulePoolVo extends CapsulePool {
    private Integer participated;
}
