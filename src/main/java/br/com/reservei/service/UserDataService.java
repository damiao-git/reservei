package br.com.reservei.service;


import br.com.reservei.dto.UserDataRecordDto;
import br.com.reservei.entity.Gender;
import br.com.reservei.entity.Race;
import br.com.reservei.entity.SubCategories;
import br.com.reservei.entity.UserData;
import br.com.reservei.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataService {


    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private RaceRepository raceRepository;


    private final UserDataRepository userDataRepository;

    @Autowired
    public UserDataService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public List<UserData> getAllUserData() { return userDataRepository.findAll(); }

    public Optional<UserData> getUserDataById(Long id) { return userDataRepository.findById(id); }

    public UserData saveUserData(UserDataRecordDto dto) {

        UserData savedUserData = new UserData();
        savedUserData.setBirthday(dto.birthday());

        Gender gender = genderRepository.findById(dto.gender()).orElseThrow(() -> new IllegalArgumentException("Genero não encontrada"));
        savedUserData.setGender(gender);

        Race race = raceRepository.findById(dto.race()).orElseThrow(() -> new IllegalArgumentException("Raça não encontrada"));
        savedUserData.setRace(race);

        return userDataRepository.save(savedUserData);
    }


}
