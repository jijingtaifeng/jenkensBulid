package com.example.test.picker;

import java.io.Serializable;

/**
 * @Author: 叶俊
 * @CreateDate: 2019/10/30 9:51
 * @UpdateDate: 2019/10/30 9:51
 * @Description:
 * @Version: 1.0
 */
public class MaintainPayMethodEntity implements  Serializable {
    public int id;
    public String method;

    public MaintainPayMethodEntity(int id, String method) {
        this.id = id;
        this.method = method;
    }

}

