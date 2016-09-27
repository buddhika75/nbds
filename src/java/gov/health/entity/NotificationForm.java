/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.health.entity;

import gov.health.data.Birth;
import gov.health.enums.LivingStatus;
import gov.health.enums.PresenceOfCongenitalAbnormalities;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author pdhs
 */
@Entity
public class NotificationForm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    Person infant;
    @ManyToOne(cascade = CascadeType.ALL)
    Person mother;
    @ManyToOne(cascade = CascadeType.ALL)
    Person inSex;
    //@Enumerated(EnumType.STRING)
    @ManyToOne
    Institution hospital;
    @ManyToOne
    private Department ward;
    @ManyToOne
    Area district;
    @ManyToOne
    Area rdhsArea;
    @ManyToOne
    Area mohArea;
    @ManyToOne
    Area gnArea;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date caseIdentifiedDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateOfDeath;
    String bhtNo;
    String bhtNo2;
    boolean live;
    String diagnosis;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date diagnosisDate;
    String motherName;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date motherDoB;
    int age;
    String nic;
    String address;
    String tp1;
    String tp2;
    @ManyToOne(cascade = CascadeType.ALL)
    Person infantDod; // infant date of death
    Long infantAodYrs; // infant age of death by year
    Long infantAodMnths; // infant age of death by year
    Long infantAodDys; // infant age of death by year
    String placeOfDead;
    String placeOfDelivary;
    boolean postMortem; // yes or no
    @ManyToOne(cascade = CascadeType.ALL)
    Person jmo;
    String underlyingCause; // Causes of death
    String immediateCause; // Causes of death
    String conDeath; // Conditions contributing to Death
    @ManyToOne(cascade = CascadeType.ALL)
    Person informant; // imformant name
    String designation; // imformant designation
    String inTp; // informant telephone
    @ManyToOne(cascade = CascadeType.ALL)
    Person hoi; // Head of the institutions' name
    @Temporal(javax.persistence.TemporalType.DATE)
    Date approveDate; // by head of the institution
    @ManyToOne
    Institution institution;
    @ManyToOne
    Area area;
    String pregregno;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date registeredAt;
    boolean registered;
    @ManyToOne
    WebUser registeredUser;
    String registeredNumber;
    boolean retired;
    @ManyToOne
    WebUser retiredUser;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date retiredAt;
    @Lob
    String retiredComments;
    @ManyToOne
    WebUser setRetiredUser;

    @ManyToOne
    NotificationForm originalNotification;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date originalSetAt;
    @ManyToOne
    WebUser originalSetUser;
    @Lob
    String originalSetComments;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date createdAt;
    @ManyToOne
    WebUser createdUser;
    
    @OneToOne
    AbstractionForm abstractionForm;
    
    @Enumerated(EnumType.STRING)
    Birth birthType;
    int multiBirths;
    
    int ageAtCaseDetectionYears;
    int ageAtCaseDetectionMonths;
    int ageAtCaseDetectionDays;

    @Enumerated(EnumType.STRING)
    LivingStatus livingStatus;
    @Enumerated(EnumType.STRING)
    PresenceOfCongenitalAbnormalities presenceOfCongenitalAbnormalities;

    @Lob
    String descriptionOfTheCongenitalAbnormalities;

    public String getDescriptionOfTheCongenitalAbnormalities() {
        return descriptionOfTheCongenitalAbnormalities;
    }

    public void setDescriptionOfTheCongenitalAbnormalities(String descriptionOfTheCongenitalAbnormalities) {
        this.descriptionOfTheCongenitalAbnormalities = descriptionOfTheCongenitalAbnormalities;
    }
    
    
    
    public PresenceOfCongenitalAbnormalities getPresenceOfCongenitalAbnormalities() {
        return presenceOfCongenitalAbnormalities;
    }

    public void setPresenceOfCongenitalAbnormalities(PresenceOfCongenitalAbnormalities presenceOfCongenitalAbnormalities) {
        this.presenceOfCongenitalAbnormalities = presenceOfCongenitalAbnormalities;
    }

    
    
    public LivingStatus getLivingStatus() {
        return livingStatus;
    }

    public void setLivingStatus(LivingStatus livingStatus) {
        this.livingStatus = livingStatus;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
    
    
    
    public int getAgeAtCaseDetectionYears() {
        return ageAtCaseDetectionYears;
    }

    public void setAgeAtCaseDetectionYears(int ageAtCaseDetectionYears) {
        this.ageAtCaseDetectionYears = ageAtCaseDetectionYears;
    }

    public int getAgeAtCaseDetectionMonths() {
        return ageAtCaseDetectionMonths;
    }

    public void setAgeAtCaseDetectionMonths(int ageAtCaseDetectionMonths) {
        this.ageAtCaseDetectionMonths = ageAtCaseDetectionMonths;
    }

    public int getAgeAtCaseDetectionDays() {
        return ageAtCaseDetectionDays;
    }

    public void setAgeAtCaseDetectionDays(int ageAtCaseDetectionDays) {
        this.ageAtCaseDetectionDays = ageAtCaseDetectionDays;
    }

    
    
    public String getPlaceOfDelivary() {
        return placeOfDelivary;
    }

    public void setPlaceOfDelivary(String placeOfDelivary) {
        this.placeOfDelivary = placeOfDelivary;
    }

    
    
    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    
    
    public int getMultiBirths() {
        return multiBirths;
    }

    public void setMultiBirths(int multiBirths) {
        this.multiBirths = multiBirths;
    }
    

    public Birth getBirthType() {
        return birthType;
    }

    public void setBirthType(Birth birthType) {
        this.birthType = birthType;
    }

    public WebUser getSetRetiredUser() {
        return setRetiredUser;
    }

    public void setSetRetiredUser(WebUser setRetiredUser) {
        this.setRetiredUser = setRetiredUser;
    }

    
    
    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public WebUser getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(WebUser registeredUser) {
        this.registeredUser = registeredUser;
    }

    public String getRegisteredNumber() {
        return registeredNumber;
    }

    public void setRegisteredNumber(String registeredNumber) {
        this.registeredNumber = registeredNumber;
    }

    public String getPregregno() {
        return pregregno;
    }

    public void setPregregno(String pregregno) {
        this.pregregno = pregregno;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public boolean getPostMortem() {
        return postMortem;
    }

    public void setPostMortem(boolean postMortem) {
        this.postMortem = postMortem;
    }

    public String getInTp() {
        return inTp;
    }

    public void setInTp(String inTp) {
        this.inTp = inTp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaceOfDead() {
        return placeOfDead;
    }

    public void setPlaceOfDead(String placeOfDead) {
        this.placeOfDead = placeOfDead;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificationForm)) {
            return false;
        }
        NotificationForm other = (NotificationForm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.health.entity.NotificationForm[ id=" + id + " ]";
    }

    public Person getInfant() {
        return infant;
    }

    public void setInfant(Person infant) {
        this.infant = infant;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getInSex() {
        return inSex;
    }

    public void setInSex(Person inSex) {
        this.inSex = inSex;
    }

    public Institution getHospital() {
        return hospital;
    }

    public void setHospital(Institution hospital) {
        this.hospital = hospital;
    }

    public Department getWard() {
        return ward;
    }

    public void setWard(Department ward) {
        this.ward = ward;
    }

    public Area getDistrict() {
        return district;
    }

    public void setDistrict(Area district) {
        this.district = district;
    }

    public Area getRdhsArea() {
        return rdhsArea;
    }

    public void setRdhsArea(Area rdhsArea) {
        this.rdhsArea = rdhsArea;
    }

    public Area getMohArea() {
        return mohArea;
    }

    public void setMohArea(Area mohArea) {
        this.mohArea = mohArea;
    }

    public Area getGnArea() {
        return gnArea;
    }

    public void setGnArea(Area gnArea) {
        this.gnArea = gnArea;
    }

    public Date getCaseIdentifiedDate() {
        return caseIdentifiedDate;
    }

    public void setCaseIdentifiedDate(Date caseIdentifiedDate) {
        this.caseIdentifiedDate = caseIdentifiedDate;
    }

    public String getBhtNo() {
        return bhtNo;
    }

    public void setBhtNo(String bhtNo) {
        this.bhtNo = bhtNo;
    }

    public String getBhtNo2() {
        return bhtNo2;
    }

    public void setBhtNo2(String bhtNo2) {
        this.bhtNo2 = bhtNo2;
    }

    
    public boolean getLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public Date getMotherDoB() {
        return motherDoB;
    }

    public void setMotherDoB(Date motherDoB) {
        this.motherDoB = motherDoB;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTp1() {
        return tp1;
    }

    public void setTp1(String tp1) {
        this.tp1 = tp1;
    }

    public String getTp2() {
        return tp2;
    }

    public void setTp2(String tp2) {
        this.tp2 = tp2;
    }

    public Person getInfantDod() {
        return infantDod;
    }

    public void setInfantDod(Person infantDod) {
        this.infantDod = infantDod;
    }

    public Long getInfantAodYrs() {
        return infantAodYrs;
    }

    public void setInfantAodYrs(Long infantAodYrs) {
        this.infantAodYrs = infantAodYrs;
    }

    public Long getInfantAodMnths() {
        return infantAodMnths;
    }

    public void setInfantAodMnths(Long infantAodMnths) {
        this.infantAodMnths = infantAodMnths;
    }

    public Long getInfantAodDys() {
        return infantAodDys;
    }

    public void setInfantAodDys(Long infantAodDys) {
        this.infantAodDys = infantAodDys;
    }

    public Person getJmo() {
        return jmo;
    }

    public void setJmo(Person jmo) {
        this.jmo = jmo;
    }

    public String getUnderlyingCause() {
        return underlyingCause;
    }

    public void setUnderlyingCause(String underlyingCause) {
        this.underlyingCause = underlyingCause;
    }

    public String getImmediateCause() {
        return immediateCause;
    }

    public void setImmediateCause(String immediateCause) {
        this.immediateCause = immediateCause;
    }

    public String getConDeath() {
        return conDeath;
    }

    public void setConDeath(String conDeath) {
        this.conDeath = conDeath;
    }

    public Person getInformant() {
        return informant;
    }

    public void setInformant(Person informant) {
        this.informant = informant;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Person getHoi() {
        return hoi;
    }

    public void setHoi(Person hoi) {
        this.hoi = hoi;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public NotificationForm() {
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public WebUser getRetiredUser() {
        return retiredUser;
    }

    public void setRetiredUser(WebUser retiredUser) {
        this.retiredUser = retiredUser;
    }

    public Date getRetiredAt() {
        return retiredAt;
    }

    public void setRetiredAt(Date retiredAt) {
        this.retiredAt = retiredAt;
    }

    public String getRetiredComments() {
        return retiredComments;
    }

    public void setRetiredComments(String retiredComments) {
        this.retiredComments = retiredComments;
    }

    public NotificationForm getOriginalNotification() {
        return originalNotification;
    }

    public void setOriginalNotification(NotificationForm originalNotification) {
        this.originalNotification = originalNotification;
    }

    public Date getOriginalSetAt() {
        return originalSetAt;
    }

    public void setOriginalSetAt(Date originalSetAt) {
        this.originalSetAt = originalSetAt;
    }

    public WebUser getOriginalSetUser() {
        return originalSetUser;
    }

    public void setOriginalSetUser(WebUser originalSetUser) {
        this.originalSetUser = originalSetUser;
    }

    public String getOriginalSetComments() {
        return originalSetComments;
    }

    public void setOriginalSetComments(String originalSetComments) {
        this.originalSetComments = originalSetComments;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public WebUser getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(WebUser createdUser) {
        this.createdUser = createdUser;
    }

    public AbstractionForm getAbstractionForm() {
        return abstractionForm;
    }

    public void setAbstractionForm(AbstractionForm abstractionForm) {
        this.abstractionForm = abstractionForm;
    }

 

}
