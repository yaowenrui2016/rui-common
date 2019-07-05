package indi.rui.common.base.dto;

import indi.rui.common.base.field.IFieldId;
import indi.rui.common.base.field.IFieldName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseVO implements IFieldId, IFieldName {
    private String id;
    private String name;
}
