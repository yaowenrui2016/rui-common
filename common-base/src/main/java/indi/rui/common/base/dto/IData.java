package indi.rui.common.base.dto;

import indi.rui.common.base.field.*;

import java.util.List;

public interface IData extends
        IFieldId, IFieldName, IFieldState, IFieldCreateTime, IFieldLastModifyTime {
    List<String> getKeywords();
}
