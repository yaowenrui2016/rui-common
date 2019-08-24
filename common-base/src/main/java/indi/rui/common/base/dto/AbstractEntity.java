package indi.rui.common.base.dto;

import indi.rui.common.base.field.*;
import indi.rui.common.base.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@Setter
public abstract class AbstractEntity
        implements IModule, IFieldId, IFieldName, IFieldState, IFieldCreateTime, IFieldLastModifyTime {
    private String id;
    private String name;
    private Boolean state;
    private Date createTime;
    private Date lastModifyTime;

    private Map<String, Object> nullAbles = new HashMap<String, Object>();

    @Override
    public String toString() {
        return JsonUtil.encode(this);
    }
}
