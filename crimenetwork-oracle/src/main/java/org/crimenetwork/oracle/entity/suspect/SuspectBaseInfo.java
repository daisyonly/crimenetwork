package org.crimenetwork.oracle.entity.suspect;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;





import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.entity.share.Location;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



@Entity
@Table(name="suspect_info",schema="SYSTEM")
public class SuspectBaseInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="SEQUENCE_1")
	private Long id;
	@Column(name="suspect_id")
	private String suspectId;
	@Column
	private String name;
	@Column(name="id_card_number")
	private String idCardNumber;
	@ManyToOne
	@JoinColumn(name="native_place")
	@NotFound(action=NotFoundAction.IGNORE)
	private Location nativeLocation;
	@ManyToOne
	@JoinColumn(name="gender")
	@NotFound(action=NotFoundAction.IGNORE)
	private Gender gender;
	@Column(name="phone_number")
	private String phoneNumber;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="case_suspect",
		joinColumns=@JoinColumn(name="suspect_id"),
		inverseJoinColumns=@JoinColumn(name="case_id")
	)	
	private Set<CaseBaseInfo> cases = new HashSet<CaseBaseInfo>();
	
	@ManyToOne
	@JoinColumn(name="nation")
	@NotFound(action=NotFoundAction.IGNORE)
	private Nation nation;
	public Nation getNation() {
		return nation;
	}
	public void setNation(Nation nation) {
		this.nation = nation;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSuspectId() {
		return suspectId;
	}
	public void setSuspectId(String suspectId) {
		this.suspectId = suspectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCardNumber() {
		return idCardNumber;
	}
	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}
	public Location getNativeLocation() {
		return nativeLocation;
	}
	public void setNativeLocation(Location nativeLocation) {
		this.nativeLocation = nativeLocation;
	}
	public Set<CaseBaseInfo> getCases() {
		return cases;
	}
	public void setCases(Set<CaseBaseInfo> cases) {
		this.cases = cases;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
