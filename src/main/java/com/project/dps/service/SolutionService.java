package com.project.dps.service;

import com.project.dps.domain.scenario.stage.poc.Solution;
import com.project.dps.dto.scenario.stage.poc.SolutionDto;
import com.project.dps.repository.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SolutionService {

    private final SolutionRepository solutionRepository;

    public SolutionDto findBySolutionTitle(String solutionTitle) {
        return SolutionDto.toDto(getSolutionIfExist(solutionTitle));
    }

    Solution getSolutionIfExist(String solutionTitle) {
        Optional<Solution> solution = solutionRepository.findByTitle(solutionTitle);
        // EXCEPTION
        return solution.orElseThrow(() -> new IllegalStateException("Not existed solution"));
    }
}
