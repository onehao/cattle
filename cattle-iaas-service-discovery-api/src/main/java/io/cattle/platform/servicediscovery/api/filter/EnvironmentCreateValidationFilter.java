package io.cattle.platform.servicediscovery.api.filter;

import io.cattle.platform.core.model.Environment;
import io.cattle.platform.iaas.api.filter.common.AbstractDefaultResourceManagerFilter;
import io.cattle.platform.object.meta.ObjectMetaDataManager;
import io.github.ibuildthecloud.gdapi.condition.Condition;
import io.github.ibuildthecloud.gdapi.condition.ConditionType;
import io.github.ibuildthecloud.gdapi.request.ApiRequest;
import io.github.ibuildthecloud.gdapi.request.resource.ResourceManager;
import io.github.ibuildthecloud.gdapi.request.resource.ResourceManagerLocator;
import io.github.ibuildthecloud.gdapi.validation.ValidationErrorCodes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class EnvironmentCreateValidationFilter extends AbstractDefaultResourceManagerFilter {

    @Inject
    ResourceManagerLocator locator;

    @Override
    public Class<?>[] getTypeClasses() {
        return new Class<?>[] { Environment.class };
    }

    @Override
    public Object create(String type, ApiRequest request, ResourceManager next) {
        Environment env = request.proxyRequestObject(Environment.class);

        if (env.getName().startsWith("-") || env.getName().endsWith("-")) {
            ValidationErrorCodes.throwValidationError(ValidationErrorCodes.INVALID_CHARACTERS,
                    "name");
        }

        ResourceManager rm = locator.getResourceManagerByType(type);

        Map<Object, Object> criteria = new HashMap<>();
        criteria.put(ObjectMetaDataManager.NAME_FIELD, env.getName());
        criteria.put(ObjectMetaDataManager.REMOVED_FIELD, new Condition(ConditionType.NULL));
        List<?> existingEnv = rm.list(type, criteria, null);
        if (!existingEnv.isEmpty()) {
            ValidationErrorCodes.throwValidationError(ValidationErrorCodes.NOT_UNIQUE,
                    "name");
        }
        return super.create(type, request, next);
    }
}
