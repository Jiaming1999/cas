package org.apereo.cas;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@ToString(of = "title")
@Getter
public enum CasLabels {
    LABEL_AWS_CLOUD_DIRECTORY("AWS Cloud Directory"),
    LABEL_AWS_DYNAMODB("AWS DynamoDb", "dynamodb"),
    LABEL_ACCEPTABLE_USAGE_POLICY("Acceptable Usage Policy", "aup|terms of use"),
    LABEL_ANALYTICS__STATISTICS("Analytics & Statistics", "analytics|statistics|micrometer"),
    LABEL_APACHE_CASSANDRA("Apache Cassandra", "Cassandra"),
    LABEL_APACHE_GROOVY("Apache Groovy", "groovy"),
    LABEL_APACHE_IGNITE("Apache Ignite", "ignite"),
    LABEL_APACHE_SHIRO("Apache Shiro", "shiro"),
    LABEL_APACHE_TOMCAT("Apache Tomcat", "tomcat"),
    LABEL_ARCHITECTURE_APIS("Architecture & APIs", "annotations"),
    LABEL_ATTRIBUTE_CONSENT("Attribute Consent", "consent"),
    LABEL_ATTRIBUTE_RELEASE("Attribute Release", "attribute"),
    LABEL_ATTRIBUTE_RESOLUTION("Attribute Resolution", "attribute"),
    LABEL_AUDITS_LOGGING("Audits & Logging", "logger|logs|log4j|audit|inspektr|logback|\\blog\\b"),
    LABEL_AUTHENTICATION("Authentication", "authn|authentication"),
    LABEL_AUTHENTICATION_EVENTS("Authentication Events", "events"),
    LABEL_AUTHENTICATION_POLICIES("Authentication Policies"),
    LABEL_AUTHENTICATION_THROTTLING("Authentication Throttling"),
    LABEL_AUTHORIZATION_ACCESS_STRATEGY("Authorization & Access Strategy", "authz|authorization|abac"),
    LABEL_BASIC_AUTHENTICATION("Basic Authentication"),
    LABEL_CAS_PROTOCOL("CAS Protocol"),
    LABEL_CLEARPASS("ClearPass", "credential cache"),
    LABEL_COMMAND_LINE_SHELL("Command-line Shell", "\\bcli\\b|shell"),
    LABEL_CONFIGURATION("Configuration", "properties|property"),
    LABEL_CONFIGURATION_DISCOVERY("Configuration Discovery", "discovery"),
    LABEL_CONFIGURATION_METADATA("Configuration Metadata","properties|property|replacement|migrator"),
    LABEL_COSMOSDB("CosmosDb", "documentdb"),
    LABEL_COUCHDB("CouchDb"),
    LABEL_COUCHBASE("Couchbase", "couchbase"),
    LABEL_PENDING_DOCUMENT_PROPERTY("Remember: Document Property"),
    LABEL_DELEGATED_AUTHENTICATION_PAC4J("Delegated Authentication Pac4J", "pac4j|delegation"),
    LABEL_DELEGATED_AUTHENTICATION_WS_FED("Delegated Authentication WS-FED", "adfs"),
    LABEL_DEPENDENCIES_MODULES("Dependencies & Modules", "gradle|dependency|bom|maven|dependencies|version|upgrade|update.+to\\sv.+"),
    LABEL_DIGEST_AUTHENTICATION("Digest Authentication"),
    LABEL_DOCUMENTATION("Documentation", "docs|markdown|gh-pages"),
    LABEL_EHCACHE("EhCache"),
    LABEL_ELECTROFENCE_RISK_BASED_AUTHN("Electrofence Risk-based AuthN", "adaptive"),
    LABEL_EMAIL_SMS_NOTIFICATIONS("Email & SMS Notifications", "nexmo|mail|twilio|email|sms"),
    LABEL_GUA("GUA"),
    LABEL_GEOLOCATION("GeoLocation"),
    LABEL_GRADLE_BUILD_RELEASE("Gradle Build & Release", "renovatebot|jdk|gradle|sonatype|snapshot|dependency|module|pom|bom|travis-ci|dependencies|update\\s.+to\\sv.+"),
    LABEL_HAZELCAST("Hazelcast", "\\bhz\\b"),
    LABEL_INFINISPAN("Infinispan"),
    LABEL_INTERNATIONALIZATION("Internationalization", "localization|localized|message bundle|translation|language bundle|I18n|french|spanish|german|italian"),
    LABEL_INTERRUPT_NOTIFICATIONS("Interrupt Notifications", "interrupt"),
    LABEL_JAAS("JAAS"),
    LABEL_JDBC_HIBERNATE_JPA("JDBC & Hibernate & JPA", "mysql|postgres|oracle|mariadb|jpa|hibernate|database|jdbc|rdbms"),
    LABEL_JWT_AUTHN_TICKETS("JWT AuthN & Tickets", "jwt"),
    LABEL_LDAP_ACTIVE_DIRECTORY("LDAP & Active Directory", "ldap|\\bad\\b"),
    LABEL_LOGOUT_SLO("Logout & SLO", "\\bslo\\b|logout|signoff|signout"),
    LABEL_MFA("MFA", "multifactor authentication|MFA|FailureMode"),
    LABEL_MFA_AUTHY("MFA Authy", "authy"),
    LABEL_MFA_BYPASS("MFA Bypass", "\\bbypass\\b"),
    LABEL_MFA_DUO_SECURITY("MFA Duo Security", "duo"),
    LABEL_MFA_FIDO_U2F("MFA FIDO U2F", "fido|u2f"),
    LABEL_MFA_GOOGLE_AUTHENTICATOR("MFA Google Authenticator", "gauth"),
    LABEL_MFA_SWIVEL_SECURE("MFA Swivel Secure", "swivel"),
    LABEL_MFA_TRUSTED_DEVICES("MFA Trusted Devices"),
    LABEL_MFA_YUBIKEY("MFA YubiKey", "yubikey"),
    LABEL_MEMCACHED("Memcached", "memcached|kryo"),
    LABEL_MONGODB("MongoDb"),
    LABEL_MONITORING("Monitoring", "healthcheck|status|@DeleteOperation|@ReadOperation|@WriteOperation"),
    LABEL_OAUTH2_PROTOCOL("OAuth2 Protocol", "oauth"),
    LABEL_OPENID_CONNECT_PROTOCOL("OpenID Connect Protocol", "oidc"),
    LABEL_OPENID_PROTOCOL("OpenID Protocol"),
    LABEL_PAC4J("Pac4J"),
    LABEL_PASSWORD_ENCODING("Password Encoding", "password encoder|\\bbcrypt\\b"),
    LABEL_PASSWORD_MANAGEMENT("Password Management", "password reset|\\bpm\\b"),
    LABEL_PASSWORD_POLICY("Password Policy", "lppe"),
    LABEL_PENDING("Pending"),
    LABEL_PENDING_PORT_FORWARD("Remember: Port Forward"),
    LABEL_PERFORMANCE_LOAD_TESTS("Performance & Load Tests", "threadsafe|jmeter|performance|non-threadsafe|overhead"),
    LABEL_PRINCIPAL_RESOLUTION("Principal Resolution"),
    LABEL_PROPOSAL_DECLINED("Proposal Declined"),
    LABEL_PROXY_AUTHENTICATION("Proxy Authentication"),
    LABEL_RADIUS("RADIUS"),
    LABEL_REST_PROTOCOL("REST Protocol & Integration", "rest"),
    LABEL_REDIS("Redis", "redis|sentinel"),
    LABEL_REFACTORING_CODE_CLEANUP("Refactoring & Code CleanUp", "refactor|NPE|cleanup"),
    LABEL_REMEMBER_ME("Remember-Me", "long-term|rememberme"),
    LABEL_SAML_CORE("SAML Core", "saml"),
    LABEL_SAML_MDUI("SAML MDUI", mdui),
    LABEL_SAML1_PROTOCOL("SAML1 Protocol", "saml1|samlValidate"),
    LABEL_SAML2_ECP("SAML2 ECP", "ecp"),
    LABEL_SAML2_GOOGLE_APPS("SAML2 Google Apps", "googleapps"),
    LABEL_SAML2_PROTOCOL("SAML2 Protocol", "saml2"),
    LABEL_SAML2_SP("SAML2 SP", "service provider"),
    LABEL_SPNEGO("SPNEGO"),
    LABEL_SSO_COOKIES("SSO & Cookies", "sso sessions|sso session"),
    LABEL_SEE_MAINTENANCE_POLICY("See Maintenance Policy"),
    LABEL_SERVICE_EXPIRATION_POLICY("Service Expiration Policy"),
    LABEL_SERVICES_REGISTRIES("Services & Registries", "service registry|service files|\\bservices\\b|\\bRegisteredServices\\b"),
    LABEL_SESSION_MANAGEMENT("Session Management", "sso sessions"),
    LABEL_SHIBBOLETH("Shibboleth", "shib"),
    LABEL_SIGNING_ENCRYPTION("Signing & Encryption", "cipher|aes|encryption|crypto|decrypt|decryption"),
    LABEL_SPRING_BOOT("Spring Boot", "bean|autoconfiguration"),
    LABEL_SPRING_BOOT_ADMIN_SERVER("Spring Boot Admin Server"),
    LABEL_SPRING_CLOUD("Spring Cloud", "RefreshScope"),
    LABEL_SPRING_CLOUD_CONFIGURATION_SERVER("Spring Cloud Configuration Server"),
    LABEL_SPRING_CLOUD_EUREKA_DISCOVERY("Spring Cloud Eureka Discovery", "eureka"),
    LABEL_SPRING_CLOUD_VAULT("Spring Cloud Vault", "vault"),
    LABEL_SPRING_CLOUD_ZIPKIN("Spring Cloud Zipkin", "zipkin"),
    LABEL_SPRING_WEBFLOW("Spring Webflow", "webflow|swf"),
    LABEL_STATISTICS_METRICS("Statistics & Metrics", "metrics|dropwizard|micrometer"),
    LABEL_SURROGATE_AUTHENTICATION("Surrogate Authentication", "surrogate|impersonation"),
    LABEL_TICKET_EXPIRATION_POLICY("Ticket Expiration Policy", "expiration policy"),
    LABEL_TICKET_VALIDATION("Ticket Validation"),
    LABEL_TICKETS_REGISTRIES("Tickets & Registries", "ticket registry|ticket id|ticketid"),
    LABEL_UNIT_INTEGRATION_TESTS("Unit/Integration Tests", "\\btest\\b|junit|coverage|tests|mockito|suite|\\bunit test\\b|junit5"),
    LABEL_USER_INTERFACE_THEMES("User Interface & Themes", "\\bIE\\b|jquery|ux|thymeleaf|\\bhtml\\b|views|screen|page|gulp|javascript|js|sass|css|themes|\\btheme\\b"),
    LABEL_WS_FED_PROTOCOL("WS-FED Protocol", "WSSecurity"),
    LABEL_X509("X.509", "x509|certificates|tls|\bclient authentication\b|CRL"),
    LABEL_WIP("Pending: Work in Progress", "WIP|\\[WIP\\]"),
    LABEL_RECAPTCHA("reCAPTCHA", "\\bcaptcha\\b"),
    LABEL_BOT("Bot");

    private final String title;
    private Pattern keywords;

    CasLabels(final String title, final String keywords) {
        this.title = title;
        if (keywords != null) {
            this.keywords = Pattern.compile(keywords, Pattern.CASE_INSENSITIVE);
        }
    }
}