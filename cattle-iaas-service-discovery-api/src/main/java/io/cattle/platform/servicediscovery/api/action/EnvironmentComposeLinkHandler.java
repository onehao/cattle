package io.cattle.platform.servicediscovery.api.action;

import static io.cattle.platform.core.model.tables.ServiceTable.SERVICE;
import io.cattle.platform.api.link.LinkHandler;
import io.cattle.platform.core.model.Environment;
import io.cattle.platform.core.model.Service;
import io.cattle.platform.object.ObjectManager;
import io.cattle.platform.servicediscovery.api.constants.ServiceDiscoveryConstants;
import io.cattle.platform.servicediscovery.api.service.ServiceDiscoveryApiService;
import io.github.ibuildthecloud.gdapi.request.ApiRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class EnvironmentComposeLinkHandler implements LinkHandler {
    @Inject
    ServiceDiscoveryApiService discoverySvc;

    @Inject
    ObjectManager objectManager;

    @Override
    public String[] getTypes() {
        return new String[] { "environment" };
    }

    @Override
    public boolean handles(String type, String id, String link, ApiRequest request) {
        return ServiceDiscoveryConstants.LINK_COMPOSE_CONFIG.equalsIgnoreCase(link);
    }

    @Override
    public Object link(String name, Object obj, ApiRequest request) throws IOException {
        Environment env = (Environment) obj;
        List<? extends Service> services = objectManager.find(Service.class, SERVICE.ENVIRONMENT_ID, env.getId(),
                SERVICE.REMOVED, null);
        String dockerCompose = discoverySvc.buildDockerComposeConfig(services);
        String rancherCompose = discoverySvc.buildRancherComposeConfig(services);

        if (StringUtils.isNotEmpty(dockerCompose) || StringUtils.isNotEmpty(rancherCompose)) {
            ByteArrayOutputStream baos = zipFiles(dockerCompose, rancherCompose);
            HttpServletResponse response = request.getServletContext().getResponse();
            response.setContentLength(baos.toByteArray().length);
            response.setContentType("application/zip");
            response.setHeader("Content-Encoding", "zip");
            response.setHeader("Content-Disposition", "attachment; filename=compose.zip");
            response.setHeader("Cache-Control", "private");
            response.setHeader("Pragma", "private");
            response.setHeader("Expires", "Wed 24 Feb 1982 18:42:00 GMT");
            response.getOutputStream().write(baos.toByteArray());
            return new Object();
        }
        return null;
    }

    private ByteArrayOutputStream zipFiles(String dockerCompose, String rancherCompose) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        try {
            zipFile(dockerCompose, zos, "docker-compose.yml");
            zipFile(rancherCompose, zos, "rancher-compose.yml");
        } finally {
            zos.flush();
            baos.flush();
            zos.close();
            baos.close();
        }

        return baos;
    }

    private void zipFile(String compose, ZipOutputStream zos, String name) throws IOException {
        if (StringUtils.isNotEmpty(compose)) {
            byte[] content = compose.getBytes("UTF-8");
            zos.putNextEntry(new ZipEntry(name));
            zos.write(content);
            zos.closeEntry();
        }
    }
}
