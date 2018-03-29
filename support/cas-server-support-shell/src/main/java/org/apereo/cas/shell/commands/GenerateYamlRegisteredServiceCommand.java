package org.apereo.cas.shell.commands;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apereo.cas.services.RegisteredService;
import org.apereo.cas.services.util.DefaultRegisteredServiceJsonSerializer;
import org.apereo.cas.services.util.RegisteredServiceYamlSerializer;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.StringWriter;

/**
 * This is {@link GenerateYamlRegisteredServiceCommand}.
 *
 * @author Misagh Moayyed
 * @since 5.2.0
 */
@Service
@Slf4j
public class GenerateYamlRegisteredServiceCommand implements CommandMarker {

    private static final int SEP_LINE_LENGTH = 70;

    /**
     * Validate service.
     *
     * @param file        the file
     * @param destination the destination
     */
    @CliCommand(value = "generate-yaml", help = "Generate a YAML registered service definition")
    public void generateYaml(
            @CliOption(key = {"file"},
                    help = "Path to the JSON service definition file",
                    specifiedDefaultValue = "",
                    unspecifiedDefaultValue = "",
                    mandatory = true,
                    optionContext = "Path to the JSON service definition") final String file,
            @CliOption(key = {"destination"},
                    help = "Path to the destination YAML service definition file",
                    specifiedDefaultValue = "",
                    unspecifiedDefaultValue = "",
                    optionContext = "Path to the destination YAML service definition file") final String destination) {

        if (StringUtils.isBlank(file)) {
            LOGGER.warn("File must be specified");
            return;
        }

        final var filePath = new File(file);
        final var result = StringUtils.isBlank(destination) ? null : new File(destination);
        generate(filePath, result);
    }

    private void generate(final File filePath, final File result) {
        try {
            final var validator = new DefaultRegisteredServiceJsonSerializer();
            if (filePath.isFile() && filePath.exists() && filePath.canRead() && filePath.length() > 0) {
                final var svc = validator.from(filePath);
                LOGGER.info("Service [{}] is valid at [{}].", svc.getName(), filePath.getCanonicalPath());
                final var yaml = new RegisteredServiceYamlSerializer();
                try (var writer = new StringWriter()) {
                    yaml.to(writer, svc);
                    LOGGER.info(writer.toString());
                    
                    if (result != null) {
                        yaml.to(result, svc);
                        LOGGER.info("YAML service definition is saved at [{}].", result.getCanonicalPath());
                    }
                }
            } else {
                LOGGER.warn("File [{}] is does not exist, is not readable or is empty", filePath.getCanonicalPath());
            }
        } catch (final Exception e) {
            LOGGER.error("Could not understand and validate [{}]: [{}]", filePath.getPath(), e.getMessage());
        } finally {
            LOGGER.info(StringUtils.repeat('-', SEP_LINE_LENGTH));
        }

    }
}
