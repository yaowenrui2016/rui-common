package indi.rui.common.base.dto;

import indi.rui.common.base.field.IFieldId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdVO implements IFieldId {
    private String id;

    public static IdVO ofId(String id) {
        IdVO idVO = new IdVO();
        idVO.setId(id);
        return idVO;
    }
}
