package com.carejava.core.build.code;

import com.carejava.core.build.model.Frame;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 模板引擎类型
 *
 * @author xiongzhenhai
 * @version [V1.0, 2018/2/28]
 */
public class OSType {

    private Map<String, Frame> clientTypes;

    private Map<String, Frame> serviceTypes;

    private Map<String, Frame> mobileTypes;

    public Map<String, Frame> getClientTypes() {
        return clientTypes;
    }

    public void setClientTypes(Map<String, Frame> clientTypes) {
        this.clientTypes = clientTypes;
    }

    public Map<String, Frame> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(Map<String, Frame> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public Map<String, Frame> getMobileTypes() {
        return mobileTypes;
    }

    public void setMobileTypes(Map<String, Frame> mobileTypes) {
        this.mobileTypes = mobileTypes;
    }
}
