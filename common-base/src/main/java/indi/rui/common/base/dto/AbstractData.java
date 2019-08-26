package indi.rui.common.base.dto;

import indi.rui.common.base.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.*;

@Getter
@Setter
public abstract class AbstractData implements IData {
    private String id;
    private String name;
    private Boolean state;
    private Date createTime;
    private Date lastModifyTime;
    @JsonIgnore
    private Map<String, Object> nullAbles = new HashMap<String, Object>();
    private List<String> keywords = new ArrayList<>();

    @Override
    public String toString() {
        return JsonUtil.encode(this);
    }
}
