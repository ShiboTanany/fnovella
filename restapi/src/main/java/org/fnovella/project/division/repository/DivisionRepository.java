package org.fnovella.project.division.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.fnovella.project.division.model.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("divisionRepository")
public interface DivisionRepository extends JpaRepository<Division, Integer> {
	
	List<Division> findByPrograma(Integer programId);
	@Modifying
    @Transactional
    @Query("delete from Division where programa = ?1")
	void deleteByPrograma(Integer idProgram);
	
}