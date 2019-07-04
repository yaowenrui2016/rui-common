package indi.rui.common.base.dto;

import indi.rui.common.base.dto.field.IFieldId;
import indi.rui.common.base.dto.field.IFieldName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEntity implements IFieldId, IFieldName {
    private String id;
    private String name;
}
