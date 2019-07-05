package indi.rui.common.base.dto;

import indi.rui.common.base.field.IFieldIds;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IdsVO implements IFieldIds {
    private List<String> ids;
}
