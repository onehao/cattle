package io.cattle.platform.servicediscovery.api.action;

import io.cattle.platform.api.action.ActionHandler;
import io.cattle.platform.core.model.Environment;
import io.cattle.platform.object.ObjectManager;
import io.cattle.platform.object.util.DataAccessor;
import io.cattle.platform.servicediscovery.api.constants.ServiceDiscoveryConstants;
import io.cattle.platform.util.type.CollectionUtils;
import io.github.ibuildthecloud.gdapi.request.ApiRequest;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AddOutputsActionHandler implements ActionHandler {

    @Inject
    ObjectManager objectManager;

    @Override
    public String getName() {
        return ServiceDiscoveryConstants.TYPE_ENVIRONMENT + "." + ServiceDiscoveryConstants.ACTION_ADD_OUTPUTS;
    }

    @Override
    public Object perform(String name, Object obj, ApiRequest request) {
        if (!(obj instanceof Environment)) {
            return null;
        }
        Environment env = (Environment)obj;
        Map<String, Object> updates = new HashMap<>(DataAccessor.fieldMap(env, ServiceDiscoveryConstants.FIELD_OUTPUTS));
        updates.putAll(CollectionUtils.<String, Object>toMap(CollectionUtils.toMap(request.getRequestObject()).get(ServiceDiscoveryConstants.FIELD_OUTPUTS)));
        objectManager.setFields(obj, ServiceDiscoveryConstants.FIELD_OUTPUTS, updates);
        return objectManager.reload(env);
    }
}
