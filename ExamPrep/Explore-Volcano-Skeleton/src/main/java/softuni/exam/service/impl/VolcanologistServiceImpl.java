package softuni.exam.service.impl;

import com.google.gson.Gson;
//import org.apache.tomcat.jni.File;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmls.VolcanologistRootDto;
import softuni.exam.models.dto.xmls.VolcanologistSeedDto;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.entity.Volcanologist;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.repository.VolcanologistRepository;
import softuni.exam.service.VolcanologistService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

// TODO: Implement all methods
@Service
public class VolcanologistServiceImpl implements VolcanologistService {
    private static final String FILE_PATH = "src/main/resources/files/xml/volcanologists.xml";
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final VolcanoRepository volcanoRepository;
    private final XmlParser xmlParser;
    private final VolcanologistRepository volcanologistRepository;

    public VolcanologistServiceImpl(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, CountryRepository countryRepository, ModelMapper modelMapper1, ValidationUtil validationUtil1, VolcanoRepository volcanoRepository, XmlParser xmlParser, VolcanologistRepository volcanologistRepository) {
        this.modelMapper = modelMapper1;
        this.validationUtil = validationUtil1;
        this.volcanoRepository = volcanoRepository;
        this.xmlParser = xmlParser;


        this.volcanologistRepository = volcanologistRepository;
    }

    @Override
    public boolean areImported() {
        return this.volcanologistRepository.count()>0;
    }

    @Override
    public String readVolcanologistsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importVolcanologists() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();


        JAXBContext jaxbContext = JAXBContext.newInstance(VolcanologistRootDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        VolcanologistRootDto volcanologistRootDto = (VolcanologistRootDto) unmarshaller.unmarshal(new File(FILE_PATH));

        for (VolcanologistSeedDto seedDto : volcanologistRootDto.getVolcanologistSeedDtoList()) {

            Optional<Volcanologist> optionalVolcanologist = this.volcanologistRepository
                    .findByFirstNameAndLastName(seedDto.getFirstName(), seedDto.getLastName());
            Optional<Volcano> optionalVolcano = this.volcanoRepository.findById(seedDto.getExploringVolcanoId());

            if (!this.validationUtil.isValid(seedDto) || optionalVolcanologist.isPresent() || optionalVolcano.isEmpty()) {
                sb.append("Invalid volcanologist\n");
                continue;
            }

            Volcanologist volcanologist = this.modelMapper.map(seedDto, Volcanologist.class);
            volcanologist.setExploredVolcano(optionalVolcano.get());
            this.volcanologistRepository.saveAndFlush(volcanologist);

            sb.append(String.format("Successfully imported volcanologist %s %s\n",
                    volcanologist.getFirstName(), volcanologist.getLastName()));
        }

        return sb.toString();
    }
}