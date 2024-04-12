package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.VolcanoSeedDto;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.entity.Volcanologist;
import softuni.exam.models.enums.VolcanoType;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO: Implement all methods
@Service
public class VolcanoServiceImpl implements VolcanoService {
    private static final String FILE_PATH = "src/main/resources/files/json/volcanoes.json";
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CountryRepository countryRepository;
    private final VolcanoRepository volcanoRepository;

    public VolcanoServiceImpl(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, CountryRepository countryRepository, VolcanoRepository volcanoRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.countryRepository = countryRepository;
        this.volcanoRepository = volcanoRepository;
    }

    @Override
    public boolean areImported() {
        return this.volcanoRepository.count()>0;
    }

    @Override
    public String readVolcanoesFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importVolcanoes() throws IOException {

        StringBuilder sb = new StringBuilder();

        VolcanoSeedDto[] volcanoSeedDtos = this.gson.fromJson(readVolcanoesFileContent(), VolcanoSeedDto[].class);

        for (VolcanoSeedDto volcanoSeedDto : volcanoSeedDtos) {
            Optional<Volcano> optional = this.volcanoRepository.findByName(volcanoSeedDto.getName());
            if (!this.validationUtil.isValid(volcanoSeedDto) || optional.isPresent()) {
                sb.append("Invalid volcano\n");
                continue;
            }

            Volcano volcano = this.modelMapper.map(volcanoSeedDto, Volcano.class);
            volcano.setVolcanoType(VolcanoType.valueOf(volcanoSeedDto.getVolcanoType()));
            volcano.setCountry(this.countryRepository.getById(volcanoSeedDto.getCountry()));



            this.volcanoRepository.saveAndFlush(volcano);
            sb.append(String.format("Successfully imported volcano %s of type %s\n", volcano.getName(), volcano.getVolcanoType()));
        }
        return sb.toString();
    }

    @Override
    public Volcano findVolcanoById(Long volcanoId) {
        return volcanoRepository.findById(volcanoId).orElse(null);
    }



    @Override
    public String exportVolcanoes() {
        return this.volcanoRepository.findAllActiveVolcanosAbove3000m()
                .stream()
               .filter(v -> v.getLastEruption() != null)
                .map(v -> String.format("Volcano: %s\n" +
                                "   *Located in: %s\n" +
                                "   **Elevation: %d\n" +
                                "   ***Last eruption on: %s\n",
                        v.getName(), v.getCountry().getName(), v.getElevation(), v.getLastEruption()))
                .collect(Collectors.joining());



    }
}