package indi.rui.common.base.dto;

import indi.rui.common.base.field.*;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class BaseVO
        implements IFieldId, IFieldName, IFieldCreateTime, IFieldLastModifyTime {
    private String id;
    private String name;
    private Date createTime;
    private Date lastModifyTime;
    @JsonIgnore
    private Map<String, Object> nullAbles = new HashMap<String, Object>();
}
