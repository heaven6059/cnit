/**
 * 文 件 名   :  SpecAndValueDTO.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-18 下午4:35:41
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.dto.spec;

import java.util.List;

import com.cnit.yoyo.model.goods.Spec;
import com.cnit.yoyo.model.goods.SpecValues;

/**
 * @description 规格及规格值数据传输
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class SpecAndValueDTO {
    private Spec spec;
    private List<SpecValues> values;
    private List<SpecValues> updates;
    private List<SpecValues> deletes;

    public Spec getSpec() {
        return spec;
    }

    public List<SpecValues> getValues() {
        return values;
    }

    public List<SpecValues> getUpdates() {
        return updates;
    }

    public List<SpecValues> getDeletes() {
        return deletes;
    }

    public void setSpec(Spec spec) {
        this.spec = spec;
    }

    public void setValues(List<SpecValues> values) {
        this.values = values;
    }

    public void setUpdates(List<SpecValues> updates) {
        this.updates = updates;
    }

    public void setDeletes(List<SpecValues> deletes) {
        this.deletes = deletes;
    }
}
