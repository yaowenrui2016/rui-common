package indi.rui.common.web.exception;

import indi.rui.common.base.dto.DefaultStatus;

/**
 * 无访问权限异常
 *
 * @author: yaowr
 * @create: 2019-09-12
 */
public class NoPermissionException extends BizException {
    public NoPermissionException() {
        super(DefaultStatus.NO_PERMISSION);
    }
}
