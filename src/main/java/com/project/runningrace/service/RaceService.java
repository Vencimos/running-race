package com.project.runningrace.service;

import com.project.runningrace.RaceNotFoundException;
import com.project.runningrace.entity.Race;
import com.project.runningrace.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceService {

    @Autowired
    private RaceRepository raceRepository;

    public void addRace(Race race) {
        raceRepository.save(race);
    }

    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    public Race getRaceById(Integer id) throws RaceNotFoundException {
        Optional<Race> raceOptional = raceRepository.findById(id);
        if (raceOptional.isPresent()) {
            return raceOptional.get();
        } else throw new RaceNotFoundException("Could not find any race with id: " + id);
    }

    public void updateRace(Race race) throws RaceNotFoundException {
        Optional<Race> raceOptional = raceRepository.findById(race.getId());
        if (raceOptional.isPresent()) {
            Race raceInDB = raceOptional.get();
            raceRepository.updateRaceDetails(raceInDB.getId(), race.getName(), race.getDistance());
        } else {
            throw new RaceNotFoundException("Could not found any race with id: " + race.getId());
        }
    }
}
