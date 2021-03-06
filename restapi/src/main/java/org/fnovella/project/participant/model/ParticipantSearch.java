package org.fnovella.project.participant.model;

import org.fnovella.project.participant.repository.ParticipantRepository;
import org.fnovella.project.utility.APIUtility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ParticipantSearch {
	private Integer id;
	private String firstName;
	private String appCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	
	public Page<Participant> getResults(ParticipantRepository participantRepository, Pageable pageable) {
		boolean useId = this.id != null && this.id > 0;
		boolean useName = APIUtility.isNotNullOrEmpty(this.firstName);
		boolean useAppCode = APIUtility.isNotNullOrEmpty(this.appCode);
		if (useName && useAppCode && useId) {
			return participantRepository.findByFirstNameAndAppCodeAndId(this.firstName, this.appCode, this.id, pageable);
		} else if (useName && useAppCode) {
			return participantRepository.findByFirstNameAndAppCode(this.firstName, this.appCode, pageable);
		} else if (useName && useId) {
			return participantRepository.findByFirstNameAndId(this.firstName, this.id, pageable);
		}  else if (useAppCode && useId) {
			return participantRepository.findByAppCodeAndId(this.appCode, this.id, pageable);
		}  else if (useName) {
			return participantRepository.findByFirstName(this.firstName, pageable);
		}  else if (useAppCode) {
			return participantRepository.findByAppCode(this.appCode, pageable);
		}  else if (useId) {
			return participantRepository.findById(this.id, pageable);
		} else {
			return participantRepository.findAll(pageable);
		}
	}
}