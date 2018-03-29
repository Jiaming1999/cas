package org.apereo.cas.support.geo.maxmind;

import com.maxmind.db.CHMCache;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apereo.cas.authentication.adaptive.geo.GeoLocationResponse;
import org.apereo.cas.configuration.model.support.geo.maxmind.MaxmindProperties;
import org.apereo.cas.support.geo.AbstractGeoLocationService;

import java.net.InetAddress;

/**
 * This is {@link MaxmindDatabaseGeoLocationService} that reads geo data
 * from a maxmind database and constructs a geo location based on the ip address.
 * Default caching of the databases is enabled by default.
 *
 * @author Misagh Moayyed
 * @since 5.0.0
 */
@Slf4j
public class MaxmindDatabaseGeoLocationService extends AbstractGeoLocationService {


    private final DatabaseReader cityDatabaseReader;
    private final DatabaseReader countryDatabaseReader;

    @SneakyThrows
    public MaxmindDatabaseGeoLocationService(final MaxmindProperties properties) {

        if (properties.getCityDatabase().exists()) {
            this.cityDatabaseReader = new DatabaseReader.Builder(properties.getCityDatabase().getFile())
                .withCache(new CHMCache()).build();
        } else {
            this.cityDatabaseReader = null;
        }

        if (properties.getCountryDatabase().exists()) {
            this.countryDatabaseReader = new DatabaseReader.Builder(properties.getCountryDatabase().getFile())
                .withCache(new CHMCache()).build();
        } else {
            this.countryDatabaseReader = null;
        }

        if (this.cityDatabaseReader == null && this.countryDatabaseReader == null) {
            throw new IllegalArgumentException("No geolocation services have been defined for Maxmind");
        }
    }

    @Override
    public GeoLocationResponse locate(final InetAddress address) {
        try {
            final var location = new GeoLocationResponse();
            if (this.cityDatabaseReader != null) {
                final var response = this.cityDatabaseReader.city(address);
                location.addAddress(response.getCity().getName());
                location.setLatitude(response.getLocation().getLatitude());
                location.setLongitude(response.getLocation().getLongitude());
            }
            if (this.countryDatabaseReader != null) {
                final var response = this.countryDatabaseReader.country(address);
                location.addAddress(response.getCountry().getName());
            }
            LOGGER.debug("Geo location for [{}] is calculated as [{}]", address, location);
            return location;
        } catch (final AddressNotFoundException e) {
            LOGGER.info(e.getMessage(), e);
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public GeoLocationResponse locate(final String address) {
        try {
            return locate(InetAddress.getByName(address));
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public GeoLocationResponse locate(final Double latitude, final Double longitude) {
        LOGGER.warn("Geolocating an address by latitude/longitude [{}]/[{}] is not supported", latitude, longitude);
        return null;
    }
}
