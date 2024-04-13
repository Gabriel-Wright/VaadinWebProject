package com.gabeWebTest.webTest.data.visualSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisualSourceRepository extends JpaRepository<VisualSource, Long> {

    public Optional<VisualSource> findByImagePath(String imagePath);

    public VisualSource save(VisualSource visualSource);
}
