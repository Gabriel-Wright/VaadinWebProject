package com.gabeWebTest.webTest.services;

import com.gabeWebTest.webTest.data.visualSource.VisualSource;
import com.gabeWebTest.webTest.data.visualSource.VisualSourceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisualSourceService {

    private final VisualSourceRepository visualSourceRepository;

    public VisualSourceService(VisualSourceRepository visualSourceRepository) {
        this.visualSourceRepository = visualSourceRepository;
    }

    public Optional<VisualSource> findVisualSource(long id) {
        return visualSourceRepository.findById(id);
    }
}
