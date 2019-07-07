package indi.rui.common.base.dto;

import indi.rui.common.base.field.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class BaseVO
        implements IFieldId, IFieldName, IFieldState, IFieldCreateTime, IFieldLastModifyTime {
    private String id;
    private String name;
    private Boolean state;
    private Date createTime;
    private Date lastModifyTime;
    private Map<String, Object> nullAbles = new HashMap<String, Object>();
}
