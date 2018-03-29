package org.apereo.cas.services;

import lombok.extern.slf4j.Slf4j;
import org.apereo.cas.services.replication.NoOpRegisteredServiceReplicationStrategy;
import org.apereo.cas.services.util.DefaultRegisteredServiceJsonSerializer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Handles test cases for {@link JsonServiceRegistry}.
 *
 * @author Misagh Moayyed
 * @since 4.1.0
 */
@Slf4j
public class JsonServiceRegistryTests extends AbstractResourceBasedServiceRegistryTests {

    @Before
    public void setUp() {
        try {
            this.dao = new JsonServiceRegistry(RESOURCE, false,
                    mock(ApplicationEventPublisher.class),
                    new NoOpRegisteredServiceReplicationStrategy());
        } catch (final Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Test
    public void verifyLegacyServiceDefn() throws Exception {
        final var resource = new ClassPathResource("Legacy-10000003.json");
        final var serializer = new DefaultRegisteredServiceJsonSerializer();
        final var service = serializer.from(resource.getInputStream());
        assertNotNull(service);
    }

    @Test
    public void verifyExistingDefinitionForCompatibility2() throws IOException {
        final Resource resource = new ClassPathResource("returnMappedAttributeReleasePolicyTest2.json");
        final var serializer = new DefaultRegisteredServiceJsonSerializer();
        final var service = serializer.from(resource.getInputStream());
        assertNotNull(service);
        assertNotNull(service.getAttributeReleasePolicy());
        final var policy = (ReturnMappedAttributeReleasePolicy) service.getAttributeReleasePolicy();
        assertNotNull(policy);
        assertEquals(2, policy.getAllowedAttributes().size());
    }

    @Test
    public void verifyExistingDefinitionForCompatibility1() throws IOException {
        final Resource resource = new ClassPathResource("returnMappedAttributeReleasePolicyTest1.json");
        final var serializer = new DefaultRegisteredServiceJsonSerializer();
        final var service = serializer.from(resource.getInputStream());
        assertNotNull(service);
        assertNotNull(service.getAttributeReleasePolicy());
        final var policy = (ReturnMappedAttributeReleasePolicy) service.getAttributeReleasePolicy();
        assertNotNull(policy);
        assertEquals(2, policy.getAllowedAttributes().size());
    }
    
}
