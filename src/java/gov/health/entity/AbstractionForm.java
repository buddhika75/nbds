/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.health.entity;

import gov.health.data.Ethnicity;
import gov.health.data.Relationship;
import gov.health.enums.LivingStatus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
public class AbstractionForm implements Serializable {

    @OneToOne(mappedBy = "abstractionForm", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private DysmorphologyExamination dysmorphologyExamination;

    @OneToOne(mappedBy = "abstractionForm", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LabTest labTest;

    @OneToOne(mappedBy = "abstractionForm", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    NotificationForm notificationForm;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    Person infant;
    //@ManyToOne
    //Person inSex;
    @ManyToOne
    Institution hospital;
    @ManyToOne
    Institution placeOfDelivery;
    @ManyToOne
    Department ward;
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
    String bhtno;
    boolean live;
    String diagnosis;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date diagnosisDate;
    @ManyToOne(cascade = CascadeType.ALL)
    Person mother;
    //String motherName;
    //@Temporal(javax.persistence.TemporalType.DATE)
    //Date motherDoB;
    int age;
    int ageYears;
    int ageMonths;
    int ageDays;
    //@ManyToOne
    //Person mnic;
    @Enumerated(EnumType.STRING)
    Ethnicity methnicity;
    @Enumerated(EnumType.STRING)
    LivingStatus livingStatus;
    //@ManyToOne
    //Person address;
    String tp1;
    String tp2;
    int totPreg;
    int liveBirth;
    int sfDeath; // Spontaneous Fetal Deaths
    int ufDeath; // Unspecified fetal deaths / pregnancy terminations
    boolean iddm; // Pre-pregnancy diabetes mellitus, IDDM
    boolean nidm; // Pre-pregnancy diabetes mellitus, NIDM
    boolean gdm; // GDM During this pregnancy
    boolean hyten; // Hypertension
    boolean epileps; // Epilepsy/seizures before or during this pregnancy
    boolean bmi; // Maternal obesity 
    boolean Cons; // Consanguinity
    boolean falicSupp; // Pre-pregnancy Folic Acid Supplementation
    boolean rub; // Rubella
    boolean cmv; // Cytomegalovirus (CMV)
    boolean othrFebIll; // Other febrile illness:
    boolean medDurPreg; // Medications during pregnancy
    boolean actSmk; // Active Smoking
    boolean pasSmk; // Passive Smoking
    boolean alBfrPreg; // Alcohol Before Pregnancy
    boolean alDurPreg; // Alcohol During Pregnancy
    boolean othrSubs; // Other substances
    String relToChild; // Relationship to Child
    String famBrthDef; // Family Member’s Birth Defect
    Relationship relationship1;
    Relationship relationship2;
    Relationship relationship3;
    Relationship relationship4;
    @ManyToOne(cascade = CascadeType.ALL)
    Person perRelationship1;
    @ManyToOne(cascade = CascadeType.ALL)
    Person perRelationship2;
    @ManyToOne(cascade = CascadeType.ALL)
    Person perRelationship3;
    @ManyToOne(cascade = CascadeType.ALL)
    Person perRelationship4;
    boolean retired;
    @ManyToOne
    WebUser retiredUser;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date retiredAt;
    boolean autopsyDone;
    String autopsyNo;
    String autopsyDoneBy;
    @Lob
    String autopsyDetails;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date autopsyDate;

    public Date getAutopsyDate() {
        return autopsyDate;
    }

    public void setAutopsyDate(Date autopsyDate) {
        this.autopsyDate = autopsyDate;
    }
    
    

    public boolean isAutopsyDone() {
        return autopsyDone;
    }

    public void setAutopsyDone(boolean autopsyDone) {
        this.autopsyDone = autopsyDone;
    }

    public String getAutopsyNo() {
        return autopsyNo;
    }

    public void setAutopsyNo(String autopsyNo) {
        this.autopsyNo = autopsyNo;
    }

    public String getAutopsyDoneBy() {
        return autopsyDoneBy;
    }

    public void setAutopsyDoneBy(String autopsyDoneBy) {
        this.autopsyDoneBy = autopsyDoneBy;
    }

    public String getAutopsyDetails() {
        return autopsyDetails;
    }

    public void setAutopsyDetails(String autopsyDetails) {
        this.autopsyDetails = autopsyDetails;
    }
    
    

    public LivingStatus getLivingStatus() {
        return livingStatus;
    }

    public void setLivingStatus(LivingStatus livingStatus) {
        this.livingStatus = livingStatus;
    }

    
    
    public int getAgeYears() {
        return ageYears;
    }

    public void setAgeYears(int ageYears) {
        this.ageYears = ageYears;
    }

    public int getAgeMonths() {
        return ageMonths;
    }

    public void setAgeMonths(int ageMonths) {
        this.ageMonths = ageMonths;
    }

    public int getAgeDays() {
        return ageDays;
    }

    public void setAgeDays(int ageDays) {
        this.ageDays = ageDays;
    }

    
    
    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public Institution getPlaceOfDelivery() {
        return placeOfDelivery;
    }

    public void setPlaceOfDelivery(Institution placeOfDelivery) {
        this.placeOfDelivery = placeOfDelivery;
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
    

    public AbstractionForm() {
    }

    public LabTest getLabTest() {
        return labTest;
    }

    public void setLabTest(LabTest labTest) {
        this.labTest = labTest;
    }

    public DysmorphologyExamination getDysmorphologyExamination() {
        return dysmorphologyExamination;
    }

    public void setDysmorphologyExamination(DysmorphologyExamination dysmorphologyExamination) {
        this.dysmorphologyExamination = dysmorphologyExamination;
    }

    public Relationship getRelationship1() {
        return relationship1;
    }

    public void setRelationship1(Relationship relationship1) {
        this.relationship1 = relationship1;
    }

    public Relationship getRelationship2() {
        return relationship2;
    }

    public void setRelationship2(Relationship relationship2) {
        this.relationship2 = relationship2;
    }

    public Relationship getRelationship3() {
        return relationship3;
    }

    public void setRelationship3(Relationship relationship3) {
        this.relationship3 = relationship3;
    }

    public Relationship getRelationship4() {
        return relationship4;
    }

    public void setRelationship4(Relationship relationship4) {
        this.relationship4 = relationship4;
    }

    public Person getPerRelationship1() {
        if (perRelationship1 == null) {
            perRelationship1 = new Person();
        }
        return perRelationship1;
    }

    public void setPerRelationship1(Person perRelationship1) {
        this.perRelationship1 = perRelationship1;
    }

    public Person getPerRelationship2() {
         if (perRelationship2 == null) {
            perRelationship2 = new Person();
        }
        return perRelationship2;
    }

    public void setPerRelationship2(Person perRelationship2) {
        this.perRelationship2 = perRelationship2;
    }

    public Person getPerRelationship3() {
         if (perRelationship3 == null) {
            perRelationship3 = new Person();
        }
        return perRelationship3;
    }

    public void setPerRelationship3(Person perRelationship3) {
        
        this.perRelationship3 = perRelationship3;
    }

    public Person getPerRelationship4() {
         if (perRelationship4 == null) {
            perRelationship4 = new Person();
        }
        return perRelationship4;
    }

    public void setPerRelationship4(Person perRelationship4) {
        this.perRelationship4 = perRelationship4;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof AbstractionForm)) {
            return false;
        }
        AbstractionForm other = (AbstractionForm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.health.entity.AbstractionForm[ id=" + id + " ]";
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

    public String getBhtno() {
        return bhtno;
    }

    public void setBhtno(String bhtno) {
        this.bhtno = bhtno;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTp1() {
        return tp1;
    }

    public void setTp1(String tp1) {
        this.tp1 = tp1;
    }

    public Ethnicity getMethnicity() {
        return methnicity;
    }

    public void setMethnicity(Ethnicity methnicity) {
        this.methnicity = methnicity;
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

    public String getTp2() {
        return tp2;
    }

    public void setTp2(String tp2) {
        this.tp2 = tp2;
    }

    public int getTotPreg() {
        return totPreg;
    }

    public void setTotPreg(int totPreg) {
        this.totPreg = totPreg;
    }

    public int getLiveBirth() {
        return liveBirth;
    }

    public void setLiveBirth(int liveBirth) {
        this.liveBirth = liveBirth;
    }

    public int getSfDeath() {
        return sfDeath;
    }

    public void setSfDeath(int sfDeath) {
        this.sfDeath = sfDeath;
    }

    public int getUfDeath() {
        return ufDeath;
    }

    public void setUfDeath(int ufDeath) {
        this.ufDeath = ufDeath;
    }

    public boolean getIddm() {
        return iddm;
    }

    public void setIddm(boolean iddm) {
        this.iddm = iddm;
    }

    public boolean getNidm() {
        return nidm;
    }

    public void setNidm(boolean nidm) {
        this.nidm = nidm;
    }

    public boolean getGdm() {
        return gdm;
    }

    public void setGdm(boolean gdm) {
        this.gdm = gdm;
    }

    public boolean getHyten() {
        return hyten;
    }

    public void setHyten(boolean hyten) {
        this.hyten = hyten;
    }

    public boolean getEpileps() {
        return epileps;
    }

    public void setEpileps(boolean epileps) {
        this.epileps = epileps;
    }

    public boolean getBmi() {
        return bmi;
    }

    public void setBmi(boolean bmi) {
        this.bmi = bmi;
    }

    public boolean getCons() {
        return Cons;
    }

    public void setCons(boolean Cons) {
        this.Cons = Cons;
    }

    public boolean getFalicSupp() {
        return falicSupp;
    }

    public void setFalicSupp(boolean falicSupp) {
        this.falicSupp = falicSupp;
    }

    public boolean getRub() {
        return rub;
    }

    public void setRub(boolean rub) {
        this.rub = rub;
    }

    public boolean getCmv() {
        return cmv;
    }

    public void setCmv(boolean cmv) {
        this.cmv = cmv;
    }

    public boolean getOthrFebIll() {
        return othrFebIll;
    }

    public void setOthrFebIll(boolean othrFebIll) {
        this.othrFebIll = othrFebIll;
    }

    public boolean getMedDurPreg() {
        return medDurPreg;
    }

    public void setMedDurPreg(boolean medDurPreg) {
        this.medDurPreg = medDurPreg;
    }

    public boolean getActSmk() {
        return actSmk;
    }

    public void setActSmk(boolean actSmk) {
        this.actSmk = actSmk;
    }

    public boolean getPasSmk() {
        return pasSmk;
    }

    public void setPasSmk(boolean pasSmk) {
        this.pasSmk = pasSmk;
    }

    public boolean getAlBfrPreg() {
        return alBfrPreg;
    }

    public void setAlBfrPreg(boolean alBfrPreg) {
        this.alBfrPreg = alBfrPreg;
    }

    public boolean getAlDurPreg() {
        return alDurPreg;
    }

    public void setAlDurPreg(boolean alDurPreg) {
        this.alDurPreg = alDurPreg;
    }

    public boolean getOthrSubs() {
        return othrSubs;
    }

    public void setOthrSubs(boolean othrSubs) {
        this.othrSubs = othrSubs;
    }

    public String getRelToChild() {
        return relToChild;
    }

    public void setRelToChild(String relToChild) {
        this.relToChild = relToChild;
    }

    public String getFamBrthDef() {
        return famBrthDef;
    }

    public void setFamBrthDef(String famBrthDef) {
        this.famBrthDef = famBrthDef;
    }

    public NotificationForm getNotificationForm() {
        return notificationForm;
    }

    public void setNotificationForm(NotificationForm notificationForm) {
        this.notificationForm = notificationForm;
    }

}
