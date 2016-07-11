package io.cattle.platform.extension.spring.parser;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ExtensionNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("discover", new DiscoverParser());
    }

}
