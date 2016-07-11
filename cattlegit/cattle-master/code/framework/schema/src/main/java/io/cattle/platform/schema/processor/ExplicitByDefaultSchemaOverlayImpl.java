package io.cattle.platform.schema.processor;

public class ExplicitByDefaultSchemaOverlayImpl extends SchemaOverlayImpl {

    public ExplicitByDefaultSchemaOverlayImpl() {
        setCollectionActionsExplicit(true);
        setCollectionFieldsExplicit(true);
        setCollectionFiltersExplicit(true);
        setResourceActionsExplicit(true);
        setResourceFieldsExplicit(true);
    }

}
