package indi.rui.common.base.dto;

import java.util.*;

import indi.rui.common.base.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

@Getter
@Setter
public abstract class AbstractData implements IData {
    private String id;
    private String name;
    private Boolean state;
    private Date createTime;
    private Date lastModifyTime;
    /**
     * 更新时可以为null的字段
     */
    @JsonIgnore
    private Map<String, Object> nullAbles = new HashMap<>();

    /**
     * 搜索的关键字
     */
    private List<String> keywords = new ArrayList<>();

    @Override
    public String toString() {
        return JsonUtil.encode(this);
    }
}
