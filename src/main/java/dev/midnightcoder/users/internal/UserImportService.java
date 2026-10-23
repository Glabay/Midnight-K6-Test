package dev.midnightcoder.users.internal;

import dev.midnightcoder.users.IUserImportService;
import dev.midnightcoder.users.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight K6 Test
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-10-20
 */

@Service
@RequiredArgsConstructor
public class UserImportService implements IUserImportService {
    private final RestClient restClient;
    private final UserRepository userRepository;
    private final GeoLocationRepository geoLocationRepository;
    private final AddressRepository addressRepository;
    private final CompanyRepository companyRepository;

    @Override
    public void importUsers() {
        var response = restClient.get()
            .uri("/users")
            .retrieve()
            .body(new ParameterizedTypeReference<List<UserResponse>>() {});
        if (response == null || response.isEmpty()) {
            return;
        }

        var users = response.stream()
            .map(user -> {
                var geo = new GeoLocation();
                    geo.setUserId(user.id());
                    geo.setLat(user.address().geo().lat());
                    geo.setLng(user.address().geo().lng());
                geoLocationRepository.save(geo);

                var address = new Address();
                    address.setUserId(user.id());
                    address.setStreet(user.address().street());
                    address.setSuite(user.address().suite());
                    address.setCity(user.address().city());
                    address.setZipcode(user.address().zipcode());
                    address.setGeo(geo);
                addressRepository.save(address);

                var company = new Company();
                    company.setUserId(user.id());
                    company.setName(user.company().name());
                    company.setCatchPhrase(user.company().catchPhrase());
                    company.setBs(user.company().bs());
                companyRepository.save(company);

                var model = new User();
                    model.setUserId(user.id());
                    model.setName(user.name());
                    model.setUsername(user.username());
                    model.setEmail(user.email());
                    model.setAddress(address);
                    model.setPhone(user.phone());
                    model.setWebsite(user.website());
                    model.setCompany(company);
                return model;
            })
            .toList();

        userRepository.saveAll(users);
    }
}
