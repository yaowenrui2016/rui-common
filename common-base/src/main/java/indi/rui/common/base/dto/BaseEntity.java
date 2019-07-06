package indi.rui.common.base.dto;

import indi.rui.common.base.field.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseEntity
        implements IFieldId, IFieldName, IFieldState, IFieldCreateTime, IFieldLastModifyTime {
    private String id;
    private String name;
    private Boolean state;
    private Long createTime;
    private Long lastModifyTime;
}
