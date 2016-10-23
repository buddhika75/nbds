/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.health.entity;

import gov.health.data.BirthAttendedBy;
import gov.health.data.EducationLevel;
import gov.health.data.Ethnicity;
import gov.health.data.Sex;
import gov.health.data.LivingStatus;
import gov.health.data.MariatalStatus;
import gov.health.data.ModeOfDelivery;
import gov.health.data.Plurality;
import gov.health.data.PregnancyOutcome;
import gov.health.data.PresenceOfCongenitalAbnormalities;
import gov.health.data.Sector;
import gov.health.data.SourceOfPog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    //A. Bsic Details
    String infants_Name;
    Sex sex_of_Infant;
    @ManyToOne
    Institution location_Where_Case_Identified_Hospital;
    String location_Where_Case_Identified_Ward_Unit;
    String location_Where_Case_Identified_Bht_No;
    @ManyToOne
    Area residence_Rdhs;
    @ManyToOne
    Area residence_Moh;
    @ManyToOne
    Area residence_Gn;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date date_of_Birth_Delivery;
    @ManyToOne
    Institution place_of_Delivery;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date date_of_Case_Identified;
    int age_at_Case_Detection_Days;
    int age_at_Case_Detection_Months;
    int age_at_Case_Detection_Years;
    LivingStatus living_Status;
    Boolean post_Mortem_Necropsy_Done;
    String pathological_Forensic_Post_Mortem_Record_No;
    @Lob
    String pathological_Forensic_Post_Mortem_Details;
    String pathological_Forensic_Post_Mortem_Done_by;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date pathological_Forensic_Post_Mortem_Date;
    /**
     * B. Description of the congenital abnormalities
     *
     */
    PresenceOfCongenitalAbnormalities presence_of_Congenital_Abnormalities;
    @OneToMany
    List<NotificationCategory> congenital_Abnormalities;
    @Lob
    String additional_Information__On_Congenital_Abnormalities;

    /**
     *
     * C. PAENTAL SOCIO-DEMOGRAPHIC DETAILS
     *
     */
    String name_of_the_Mother;
    @Lob
    String residential_Address;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date mothers_Date_of_Birth;
    int mothers_Age_in_Years;
    @Enumerated(EnumType.STRING)
    MariatalStatus mariatal_Status;
    @Enumerated(EnumType.STRING)
    EducationLevel education_Level;
    @Enumerated(EnumType.STRING)
    Ethnicity ethnicity;
    int fathers_Age;
    Boolean parental_Consanguinity;
    @Lob
    String parental_Consanguinity_Details;
    @Enumerated(EnumType.STRING)
    Sector sector;
    Double monthly_Average_Family_Income;
    String national_Identity_Card_No;
    String contact_Phone;

    /**
     *
     * D. PREGNANCY HISTORY & DELIVERY INFORMATION
     *
     */
    int total_Pregnancies;
    int live_Births;
    Boolean previous_Terminations_of_Pregnancy_for_Congenital_Malformations;
    @Lob
    @Column(name = "PREVIOUS_TERMINATIONS_OF_PREGNANCY_FOR_CM_DETAILS")
    String previous_Terminations_of_Pregnancy_for_Congenital_Malformations_Details;
    Boolean previous_Still_Births;
    @Lob
    String previous_Still_Births_Details;
    Boolean previous_Spontaneous_Abortions;
    @Lob
    String previous_Spontaneous_Abortions_details;
    Boolean previous_Live_Births_With_Congenital_Malformations;
    @Lob
    String previous_Live_Births_with_Congenital_Malformations_Details;
    @Enumerated(EnumType.STRING)
    SourceOfPog Source_of_POG_Information;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date lmp;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date edd;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date dateAndTimeOfDelivary;
    @Enumerated(EnumType.STRING)
    ModeOfDelivery modeOfDelivery;
    String modeOfDelivery_Other;
    @Enumerated(EnumType.STRING)
    Plurality plurality;
    @Enumerated(EnumType.STRING)
    BirthAttendedBy birthAttendedBy;
    @Enumerated(EnumType.STRING)
    PregnancyOutcome pregnancyOutcome;
    int apgar_1st_minute;
    int apgar_5th_minute;
    int apgar_10th_minute;
    double birth_weight_in_grams;
    double length_in_cm;
    double head_circumference_in_cm;
    

    /**
     *
     * E. NEONATAL COMPLICATIONS
     *
     */
    @Lob
    String neonatal_Complication_Details;

    /**
     *
     * F. MATERNAL ILLNESSES, CONDITIONS, COMPLICATIONS and EXPOSURES
     *
     */
    Boolean pre_Pregnancy_Diabetes_Mellitus_IDDM;
    Boolean pre_Pregnancy_Diabetes_Mellitus_NIDM;
    Boolean gdm_During_This_Pregnancy;
    Boolean hypertension;
    Boolean epilepsy_Seizures_Before_or_During_This_Pregnancy;
    Boolean maternal_Obesity_BMI;
    Boolean rubella;
    Boolean rubella_Vaccinated;
    Boolean cytomegalovirus_CMV;
    Boolean pre_Pregnancy_Folic_Acid;
    Boolean antenatal_Anaemia;
    Double antenatal_Anaemia_Hb_g_per_dl;
    Boolean active_Smoking;
    Boolean passive_Smoking;
    Boolean alcohol_Before_Pregnancy;
    Boolean alcohol_During_Pregnancy;
    Boolean other_Substances;
    Boolean medications_During_Pregnancy;
    Boolean any_Antenatal_Febrile_Illness;
    @Lob
    String additional_Information_Remarks_on_Maternal_Risk_Factors;

    /**
     * G. FAMILY HISTORY OF CONGENITAL ABNORMALITIES
     */
    @OneToMany
    List<NotificationCategory> family_History_Of_Congenital_Abnormalities;

    /**
     *
     * H. Prenatal_Antenatal_Postnatal_Investigations
     *
     */
    @OneToMany
    List<NotificationCategory> prenatal_Antenatal_Postnatal_Investigations;

    /**
     *
     * I. THERAPEUTIC / SURGICAL INTERVENTIONS / REFERRALS CARRIED OUT ON
     * ANOMALIES
     *
     */
    @OneToMany
    List<NotificationCategory> therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomalies;

    /**
     *
     * J. DEATH DETAILS
     *
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    Date date_of_Death;
    int age_at_Death_Year;
    int age_at_Death_Month;
    int age_at_Death_Days;
    @ManyToOne
    Institution place_of_Death;
    @OneToMany
    List<NotificationCategory> underlyning_Causes_of_Death;
    @OneToMany
    List<NotificationCategory> immediate_Causes_of_Death;
    @OneToMany
    List<NotificationCategory> conditions_Contributing_to_Death;

    /**
     * Submission Details
     */
    String clinician_or_Medical_Officer_Name;
    @ManyToOne
    Designation clinician_or_Medical_Officer_Designation;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date clinician_Form_Filled_Date;
    String head_of_Institution_Name;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date head_of_Institution_Date;

    // Program Specific
    boolean registered;
    @ManyToOne
    WebUser registeredUser;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date registeredAt;
    String registeredNumber;
    @ManyToOne
    Institution hospital;
    @ManyToOne
    Area district;
    @ManyToOne
    Area mohArea;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date createdAt;
    @ManyToOne
    WebUser createdUser;
    boolean retired;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date retiredAt;
    @ManyToOne
    WebUser retiredUser;

    //Gettors & Settors
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfants_Name() {
        return infants_Name;
    }

    public void setInfants_Name(String infants_Name) {
        this.infants_Name = infants_Name;
    }

    public Sex getSex_of_Infant() {
        return sex_of_Infant;
    }

    public void setSex_of_Infant(Sex sex_of_Infant) {
        this.sex_of_Infant = sex_of_Infant;
    }

    public Institution getLocation_Where_Case_Identified_Hospital() {
        return location_Where_Case_Identified_Hospital;
    }

    public void setLocation_Where_Case_Identified_Hospital(Institution location_Where_Case_Identified_Hospital) {
        this.location_Where_Case_Identified_Hospital = location_Where_Case_Identified_Hospital;
    }

    public String getLocation_Where_Case_Identified_Ward_Unit() {
        return location_Where_Case_Identified_Ward_Unit;
    }

    public void setLocation_Where_Case_Identified_Ward_Unit(String location_Where_Case_Identified_Ward_Unit) {
        this.location_Where_Case_Identified_Ward_Unit = location_Where_Case_Identified_Ward_Unit;
    }

    public String getLocation_Where_Case_Identified_Bht_No() {
        return location_Where_Case_Identified_Bht_No;
    }

    public void setLocation_Where_Case_Identified_Bht_No(String location_Where_Case_Identified_Bht_No) {
        this.location_Where_Case_Identified_Bht_No = location_Where_Case_Identified_Bht_No;
    }

    public Area getResidence_Rdhs() {
        return residence_Rdhs;
    }

    public void setResidence_Rdhs(Area residence_Rdhs) {
        this.residence_Rdhs = residence_Rdhs;
    }

    public Area getResidence_Moh() {
        return residence_Moh;
    }

    public void setResidence_Moh(Area residence_Moh) {
        this.residence_Moh = residence_Moh;
    }

    public Area getResidence_Gn() {
        return residence_Gn;
    }

    public void setResidence_Gn(Area residence_Gn) {
        this.residence_Gn = residence_Gn;
    }

    public Date getDate_of_Birth_Delivery() {
        return date_of_Birth_Delivery;
    }

    public void setDate_of_Birth_Delivery(Date date_of_Birth_Delivery) {
        this.date_of_Birth_Delivery = date_of_Birth_Delivery;
    }

    public Institution getPlace_of_Delivery() {
        return place_of_Delivery;
    }

    public void setPlace_of_Delivery(Institution place_of_Delivery) {
        this.place_of_Delivery = place_of_Delivery;
    }

    public Date getDate_of_Case_Identified() {
        return date_of_Case_Identified;
    }

    public void setDate_of_Case_Identified(Date date_of_Case_Identified) {
        this.date_of_Case_Identified = date_of_Case_Identified;
    }

    public int getAge_at_Case_Detection_Days() {
        return age_at_Case_Detection_Days;
    }

    public void setAge_at_Case_Detection_Days(int age_at_Case_Detection_Days) {
        this.age_at_Case_Detection_Days = age_at_Case_Detection_Days;
    }

    public int getAge_at_Case_Detection_Months() {
        return age_at_Case_Detection_Months;
    }

    public void setAge_at_Case_Detection_Months(int age_at_Case_Detection_Months) {
        this.age_at_Case_Detection_Months = age_at_Case_Detection_Months;
    }

    public int getAge_at_Case_Detection_Years() {
        return age_at_Case_Detection_Years;
    }

    public void setAge_at_Case_Detection_Years(int age_at_Case_Detection_Years) {
        this.age_at_Case_Detection_Years = age_at_Case_Detection_Years;
    }

    public LivingStatus getLiving_Status() {
        return living_Status;
    }

    public void setLiving_Status(LivingStatus living_Status) {
        this.living_Status = living_Status;
    }

    public Boolean getPost_Mortem_Necropsy_Done() {
        return post_Mortem_Necropsy_Done;
    }

    public void setPost_Mortem_Necropsy_Done(Boolean post_Mortem_Necropsy_Done) {
        this.post_Mortem_Necropsy_Done = post_Mortem_Necropsy_Done;
    }

    public String getPathological_Forensic_Post_Mortem_Record_No() {
        return pathological_Forensic_Post_Mortem_Record_No;
    }

    public void setPathological_Forensic_Post_Mortem_Record_No(String pathological_Forensic_Post_Mortem_Record_No) {
        this.pathological_Forensic_Post_Mortem_Record_No = pathological_Forensic_Post_Mortem_Record_No;
    }

    public String getPathological_Forensic_Post_Mortem_Details() {
        return pathological_Forensic_Post_Mortem_Details;
    }

    public void setPathological_Forensic_Post_Mortem_Details(String pathological_Forensic_Post_Mortem_Details) {
        this.pathological_Forensic_Post_Mortem_Details = pathological_Forensic_Post_Mortem_Details;
    }

    public String getPathological_Forensic_Post_Mortem_Done_by() {
        return pathological_Forensic_Post_Mortem_Done_by;
    }

    public void setPathological_Forensic_Post_Mortem_Done_by(String pathological_Forensic_Post_Mortem_Done_by) {
        this.pathological_Forensic_Post_Mortem_Done_by = pathological_Forensic_Post_Mortem_Done_by;
    }

    public Date getPathological_Forensic_Post_Mortem_Date() {
        return pathological_Forensic_Post_Mortem_Date;
    }

    public void setPathological_Forensic_Post_Mortem_Date(Date pathological_Forensic_Post_Mortem_Date) {
        this.pathological_Forensic_Post_Mortem_Date = pathological_Forensic_Post_Mortem_Date;
    }

    public PresenceOfCongenitalAbnormalities getPresence_of_Congenital_Abnormalities() {
        return presence_of_Congenital_Abnormalities;
    }

    public void setPresence_of_Congenital_Abnormalities(PresenceOfCongenitalAbnormalities presence_of_Congenital_Abnormalities) {
        this.presence_of_Congenital_Abnormalities = presence_of_Congenital_Abnormalities;
    }

    public List<NotificationCategory> getCongenital_Abnormalities() {
        if (congenital_Abnormalities == null) {
            congenital_Abnormalities = new ArrayList<NotificationCategory>();
        }
        return congenital_Abnormalities;
    }

    public void setCongenital_Abnormalities(List<NotificationCategory> congenital_Abnormalities) {
        this.congenital_Abnormalities = congenital_Abnormalities;
    }

    public String getAdditional_Information__On_Congenital_Abnormalities() {
        return additional_Information__On_Congenital_Abnormalities;
    }

    public void setAdditional_Information__On_Congenital_Abnormalities(String additional_Information__On_Congenital_Abnormalities) {
        this.additional_Information__On_Congenital_Abnormalities = additional_Information__On_Congenital_Abnormalities;
    }

    public String getName_of_the_Mother() {
        return name_of_the_Mother;
    }

    public void setName_of_the_Mother(String name_of_the_Mother) {
        this.name_of_the_Mother = name_of_the_Mother;
    }

    public String getResidential_Address() {
        return residential_Address;
    }

    public void setResidential_Address(String residential_Address) {
        this.residential_Address = residential_Address;
    }

    public Date getMothers_Date_of_Birth() {
        return mothers_Date_of_Birth;
    }

    public void setMothers_Date_of_Birth(Date mothers_Date_of_Birth) {
        this.mothers_Date_of_Birth = mothers_Date_of_Birth;
    }

    public int getMothers_Age_in_Years() {
        return mothers_Age_in_Years;
    }

    public void setMothers_Age_in_Years(int mothers_Age_in_Years) {
        this.mothers_Age_in_Years = mothers_Age_in_Years;
    }

    public MariatalStatus getMariatal_Status() {
        return mariatal_Status;
    }

    public void setMariatal_Status(MariatalStatus mariatal_Status) {
        this.mariatal_Status = mariatal_Status;
    }

    public EducationLevel getEducation_Level() {
        return education_Level;
    }

    public void setEducation_Level(EducationLevel education_Level) {
        this.education_Level = education_Level;
    }

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Ethnicity ethnicity) {
        this.ethnicity = ethnicity;
    }

    public int getFathers_Age() {
        return fathers_Age;
    }

    public void setFathers_Age(int fathers_Age) {
        this.fathers_Age = fathers_Age;
    }

    public Boolean getParental_Consanguinity() {
        return parental_Consanguinity;
    }

    public void setParental_Consanguinity(Boolean parental_Consanguinity) {
        this.parental_Consanguinity = parental_Consanguinity;
    }

    public String getParental_Consanguinity_Details() {
        return parental_Consanguinity_Details;
    }

    public void setParental_Consanguinity_Details(String parental_Consanguinity_Details) {
        this.parental_Consanguinity_Details = parental_Consanguinity_Details;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Double getMonthly_Average_Family_Income() {
        return monthly_Average_Family_Income;
    }

    public void setMonthly_Average_Family_Income(Double monthly_Average_Family_Income) {
        this.monthly_Average_Family_Income = monthly_Average_Family_Income;
    }

    public String getNational_Identity_Card_No() {
        return national_Identity_Card_No;
    }

    public void setNational_Identity_Card_No(String national_Identity_Card_No) {
        this.national_Identity_Card_No = national_Identity_Card_No;
    }

    public String getContact_Phone() {
        return contact_Phone;
    }

    public void setContact_Phone(String contact_Phone) {
        this.contact_Phone = contact_Phone;
    }

    public int getTotal_Pregnancies() {
        return total_Pregnancies;
    }

    public void setTotal_Pregnancies(int total_Pregnancies) {
        this.total_Pregnancies = total_Pregnancies;
    }

    public int getLive_Births() {
        return live_Births;
    }

    public void setLive_Births(int live_Births) {
        this.live_Births = live_Births;
    }

    public Boolean getPrevious_Terminations_of_Pregnancy_for_Congenital_Malformations() {
        return previous_Terminations_of_Pregnancy_for_Congenital_Malformations;
    }

    public void setPrevious_Terminations_of_Pregnancy_for_Congenital_Malformations(Boolean previous_Terminations_of_Pregnancy_for_Congenital_Malformations) {
        this.previous_Terminations_of_Pregnancy_for_Congenital_Malformations = previous_Terminations_of_Pregnancy_for_Congenital_Malformations;
    }

    public String getPrevious_Terminations_of_Pregnancy_for_Congenital_Malformations_Details() {
        return previous_Terminations_of_Pregnancy_for_Congenital_Malformations_Details;
    }

    public void setPrevious_Terminations_of_Pregnancy_for_Congenital_Malformations_Details(String previous_Terminations_of_Pregnancy_for_Congenital_Malformations_Details) {
        this.previous_Terminations_of_Pregnancy_for_Congenital_Malformations_Details = previous_Terminations_of_Pregnancy_for_Congenital_Malformations_Details;
    }

    public Boolean getPrevious_Still_Births() {
        return previous_Still_Births;
    }

    public void setPrevious_Still_Births(Boolean previous_Still_Births) {
        this.previous_Still_Births = previous_Still_Births;
    }

    public String getPrevious_Still_Births_Details() {
        return previous_Still_Births_Details;
    }

    public void setPrevious_Still_Births_Details(String previous_Still_Births_Details) {
        this.previous_Still_Births_Details = previous_Still_Births_Details;
    }

    public Boolean getPrevious_Spontaneous_Abortions() {
        return previous_Spontaneous_Abortions;
    }

    public void setPrevious_Spontaneous_Abortions(Boolean previous_Spontaneous_Abortions) {
        this.previous_Spontaneous_Abortions = previous_Spontaneous_Abortions;
    }

    public String getPrevious_Spontaneous_Abortions_details() {
        return previous_Spontaneous_Abortions_details;
    }

    public void setPrevious_Spontaneous_Abortions_details(String previous_Spontaneous_Abortions_details) {
        this.previous_Spontaneous_Abortions_details = previous_Spontaneous_Abortions_details;
    }

    public Boolean getPrevious_Live_Births_With_Congenital_Malformations() {
        return previous_Live_Births_With_Congenital_Malformations;
    }

    public void setPrevious_Live_Births_With_Congenital_Malformations(Boolean previous_Live_Births_With_Congenital_Malformations) {
        this.previous_Live_Births_With_Congenital_Malformations = previous_Live_Births_With_Congenital_Malformations;
    }

    public String getPrevious_Live_Births_with_Congenital_Malformations_Details() {
        return previous_Live_Births_with_Congenital_Malformations_Details;
    }

    public void setPrevious_Live_Births_with_Congenital_Malformations_Details(String previous_Live_Births_with_Congenital_Malformations_Details) {
        this.previous_Live_Births_with_Congenital_Malformations_Details = previous_Live_Births_with_Congenital_Malformations_Details;
    }

    public String getNeonatal_Complication_Details() {
        return neonatal_Complication_Details;
    }

    public void setNeonatal_Complication_Details(String neonatal_Complication_Details) {
        this.neonatal_Complication_Details = neonatal_Complication_Details;
    }

    public Boolean getPre_Pregnancy_Diabetes_Mellitus_IDDM() {
        return pre_Pregnancy_Diabetes_Mellitus_IDDM;
    }

    public void setPre_Pregnancy_Diabetes_Mellitus_IDDM(Boolean pre_Pregnancy_Diabetes_Mellitus_IDDM) {
        this.pre_Pregnancy_Diabetes_Mellitus_IDDM = pre_Pregnancy_Diabetes_Mellitus_IDDM;
    }

    public Boolean getPre_Pregnancy_Diabetes_Mellitus_NIDM() {
        return pre_Pregnancy_Diabetes_Mellitus_NIDM;
    }

    public void setPre_Pregnancy_Diabetes_Mellitus_NIDM(Boolean pre_Pregnancy_Diabetes_Mellitus_NIDM) {
        this.pre_Pregnancy_Diabetes_Mellitus_NIDM = pre_Pregnancy_Diabetes_Mellitus_NIDM;
    }

    public Boolean getGdm_During_This_Pregnancy() {
        return gdm_During_This_Pregnancy;
    }

    public void setGdm_During_This_Pregnancy(Boolean gdm_During_This_Pregnancy) {
        this.gdm_During_This_Pregnancy = gdm_During_This_Pregnancy;
    }

    public Boolean getHypertension() {
        return hypertension;
    }

    public void setHypertension(Boolean hypertension) {
        this.hypertension = hypertension;
    }

    public Boolean getEpilepsy_Seizures_Before_or_During_This_Pregnancy() {
        return epilepsy_Seizures_Before_or_During_This_Pregnancy;
    }

    public void setEpilepsy_Seizures_Before_or_During_This_Pregnancy(Boolean epilepsy_Seizures_Before_or_During_This_Pregnancy) {
        this.epilepsy_Seizures_Before_or_During_This_Pregnancy = epilepsy_Seizures_Before_or_During_This_Pregnancy;
    }

    public Boolean getMaternal_Obesity_BMI() {
        return maternal_Obesity_BMI;
    }

    public void setMaternal_Obesity_BMI(Boolean maternal_Obesity_BMI) {
        this.maternal_Obesity_BMI = maternal_Obesity_BMI;
    }

    public Boolean getRubella() {
        return rubella;
    }

    public void setRubella(Boolean rubella) {
        this.rubella = rubella;
    }

    public Boolean getRubella_Vaccinated() {
        return rubella_Vaccinated;
    }

    public void setRubella_Vaccinated(Boolean rubella_Vaccinated) {
        this.rubella_Vaccinated = rubella_Vaccinated;
    }

    public Boolean getCytomegalovirus_CMV() {
        return cytomegalovirus_CMV;
    }

    public void setCytomegalovirus_CMV(Boolean cytomegalovirus_CMV) {
        this.cytomegalovirus_CMV = cytomegalovirus_CMV;
    }

    public Boolean getPre_Pregnancy_Folic_Acid() {
        return pre_Pregnancy_Folic_Acid;
    }

    public void setPre_Pregnancy_Folic_Acid(Boolean pre_Pregnancy_Folic_Acid) {
        this.pre_Pregnancy_Folic_Acid = pre_Pregnancy_Folic_Acid;
    }

    public Boolean getAntenatal_Anaemia() {
        return antenatal_Anaemia;
    }

    public void setAntenatal_Anaemia(Boolean antenatal_Anaemia) {
        this.antenatal_Anaemia = antenatal_Anaemia;
    }

    public Double getAntenatal_Anaemia_Hb_g_per_dl() {
        return antenatal_Anaemia_Hb_g_per_dl;
    }

    public void setAntenatal_Anaemia_Hb_g_per_dl(Double antenatal_Anaemia_Hb_g_per_dl) {
        this.antenatal_Anaemia_Hb_g_per_dl = antenatal_Anaemia_Hb_g_per_dl;
    }

    public Boolean getActive_Smoking() {
        return active_Smoking;
    }

    public void setActive_Smoking(Boolean active_Smoking) {
        this.active_Smoking = active_Smoking;
    }

    public Boolean getPassive_Smoking() {
        return passive_Smoking;
    }

    public void setPassive_Smoking(Boolean passive_Smoking) {
        this.passive_Smoking = passive_Smoking;
    }

    public Boolean getAlcohol_Before_Pregnancy() {
        return alcohol_Before_Pregnancy;
    }

    public void setAlcohol_Before_Pregnancy(Boolean alcohol_Before_Pregnancy) {
        this.alcohol_Before_Pregnancy = alcohol_Before_Pregnancy;
    }

    public Boolean getAlcohol_During_Pregnancy() {
        return alcohol_During_Pregnancy;
    }

    public void setAlcohol_During_Pregnancy(Boolean alcohol_During_Pregnancy) {
        this.alcohol_During_Pregnancy = alcohol_During_Pregnancy;
    }

    public Boolean getOther_Substances() {
        return other_Substances;
    }

    public void setOther_Substances(Boolean other_Substances) {
        this.other_Substances = other_Substances;
    }

    public Boolean getMedications_During_Pregnancy() {
        return medications_During_Pregnancy;
    }

    public void setMedications_During_Pregnancy(Boolean medications_During_Pregnancy) {
        this.medications_During_Pregnancy = medications_During_Pregnancy;
    }

    public Boolean getAny_Antenatal_Febrile_Illness() {
        return any_Antenatal_Febrile_Illness;
    }

    public void setAny_Antenatal_Febrile_Illness(Boolean any_Antenatal_Febrile_Illness) {
        this.any_Antenatal_Febrile_Illness = any_Antenatal_Febrile_Illness;
    }

    public String getAdditional_Information_Remarks_on_Maternal_Risk_Factors() {
        return additional_Information_Remarks_on_Maternal_Risk_Factors;
    }

    public void setAdditional_Information_Remarks_on_Maternal_Risk_Factors(String additional_Information_Remarks_on_Maternal_Risk_Factors) {
        this.additional_Information_Remarks_on_Maternal_Risk_Factors = additional_Information_Remarks_on_Maternal_Risk_Factors;
    }

    public List<NotificationCategory> getFamily_History_Of_Congenital_Abnormalities() {
        if (family_History_Of_Congenital_Abnormalities == null) {
            family_History_Of_Congenital_Abnormalities = new ArrayList<NotificationCategory>();
        }
        return family_History_Of_Congenital_Abnormalities;
    }

    public void setFamily_History_Of_Congenital_Abnormalities(List<NotificationCategory> family_History_Of_Congenital_Abnormalities) {
        this.family_History_Of_Congenital_Abnormalities = family_History_Of_Congenital_Abnormalities;
    }

    public List<NotificationCategory> getPrenatal_Antenatal_Postnatal_Investigations() {
        if (prenatal_Antenatal_Postnatal_Investigations == null) {
            prenatal_Antenatal_Postnatal_Investigations = new ArrayList<NotificationCategory>();
        }
        return prenatal_Antenatal_Postnatal_Investigations;
    }

    public void setPrenatal_Antenatal_Postnatal_Investigations(List<NotificationCategory> prenatal_Antenatal_Postnatal_Investigations) {
        this.prenatal_Antenatal_Postnatal_Investigations = prenatal_Antenatal_Postnatal_Investigations;
    }

    public List<NotificationCategory> getTherapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomalies() {
        if (therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomalies == null) {
            therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomalies = new ArrayList<NotificationCategory>();
        }
        return therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomalies;
    }

    public void setTherapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomalies(List<NotificationCategory> therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomalies) {
        this.therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomalies = therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomalies;
    }

    public Date getDate_of_Death() {
        return date_of_Death;
    }

    public void setDate_of_Death(Date date_of_Death) {
        this.date_of_Death = date_of_Death;
    }

    public int getAge_at_Death_Year() {
        return age_at_Death_Year;
    }

    public void setAge_at_Death_Year(int age_at_Death_Year) {
        this.age_at_Death_Year = age_at_Death_Year;
    }

    public int getAge_at_Death_Month() {
        return age_at_Death_Month;
    }

    public void setAge_at_Death_Month(int age_at_Death_Month) {
        this.age_at_Death_Month = age_at_Death_Month;
    }

    public int getAge_at_Death_Days() {
        return age_at_Death_Days;
    }

    public void setAge_at_Death_Days(int age_at_Death_Days) {
        this.age_at_Death_Days = age_at_Death_Days;
    }

    public Institution getPlace_of_Death() {
        return place_of_Death;
    }

    public void setPlace_of_Death(Institution place_of_Death) {
        this.place_of_Death = place_of_Death;
    }

    public List<NotificationCategory> getUnderlyning_Causes_of_Death() {
        if (underlyning_Causes_of_Death == null) {
            underlyning_Causes_of_Death = new ArrayList<NotificationCategory>();
        }
        return underlyning_Causes_of_Death;
    }

    public void setUnderlyning_Causes_of_Death(List<NotificationCategory> underlyning_Causes_of_Death) {
        this.underlyning_Causes_of_Death = underlyning_Causes_of_Death;
    }

    public List<NotificationCategory> getImmediate_Causes_of_Death() {
        if (immediate_Causes_of_Death == null) {
            immediate_Causes_of_Death = new ArrayList<NotificationCategory>();
        }
        return immediate_Causes_of_Death;
    }

    public void setImmediate_Causes_of_Death(List<NotificationCategory> immediate_Causes_of_Death) {
        this.immediate_Causes_of_Death = immediate_Causes_of_Death;
    }

    public List<NotificationCategory> getConditions_Contributing_to_Death() {
        if (conditions_Contributing_to_Death == null) {
            conditions_Contributing_to_Death = new ArrayList<NotificationCategory>();
        }
        return conditions_Contributing_to_Death;
    }

    public void setConditions_Contributing_to_Death(List<NotificationCategory> conditions_Contributing_to_Death) {
        this.conditions_Contributing_to_Death = conditions_Contributing_to_Death;
    }

    public String getClinician_or_Medical_Officer_Name() {
        return clinician_or_Medical_Officer_Name;
    }

    public void setClinician_or_Medical_Officer_Name(String clinician_or_Medical_Officer_Name) {
        this.clinician_or_Medical_Officer_Name = clinician_or_Medical_Officer_Name;
    }

    public Designation getClinician_or_Medical_Officer_Designation() {
        return clinician_or_Medical_Officer_Designation;
    }

    public void setClinician_or_Medical_Officer_Designation(Designation clinician_or_Medical_Officer_Designation) {
        this.clinician_or_Medical_Officer_Designation = clinician_or_Medical_Officer_Designation;
    }

    public Date getClinician_Form_Filled_Date() {
        return clinician_Form_Filled_Date;
    }

    public void setClinician_Form_Filled_Date(Date clinician_Form_Filled_Date) {
        this.clinician_Form_Filled_Date = clinician_Form_Filled_Date;
    }

    public String getHead_of_Institution_Name() {
        return head_of_Institution_Name;
    }

    public void setHead_of_Institution_Name(String head_of_Institution_Name) {
        this.head_of_Institution_Name = head_of_Institution_Name;
    }

    public Date getHead_of_Institution_Date() {
        return head_of_Institution_Date;
    }

    public void setHead_of_Institution_Date(Date head_of_Institution_Date) {
        this.head_of_Institution_Date = head_of_Institution_Date;
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

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getRegisteredNumber() {
        return registeredNumber;
    }

    public void setRegisteredNumber(String registeredNumber) {
        this.registeredNumber = registeredNumber;
    }

    public Institution getHospital() {
        return hospital;
    }

    public void setHospital(Institution hospital) {
        this.hospital = hospital;
    }

    public Area getDistrict() {
        return district;
    }

    public void setDistrict(Area district) {
        this.district = district;
    }

    public Area getMohArea() {
        return mohArea;
    }

    public void setMohArea(Area mohArea) {
        this.mohArea = mohArea;
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

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public Date getRetiredAt() {
        return retiredAt;
    }

    public void setRetiredAt(Date retiredAt) {
        this.retiredAt = retiredAt;
    }

    public WebUser getRetiredUser() {
        return retiredUser;
    }

    public void setRetiredUser(WebUser retiredUser) {
        this.retiredUser = retiredUser;
    }

    public SourceOfPog getSource_of_POG_Information() {
        return Source_of_POG_Information;
    }

    public void setSource_of_POG_Information(SourceOfPog Source_of_POG_Information) {
        this.Source_of_POG_Information = Source_of_POG_Information;
    }

    public Date getLmp() {
        return lmp;
    }

    public void setLmp(Date lmp) {
        this.lmp = lmp;
    }

    public Date getEdd() {
        return edd;
    }

    public void setEdd(Date edd) {
        this.edd = edd;
    }

    public Date getDateAndTimeOfDelivary() {
        return dateAndTimeOfDelivary;
    }

    public void setDateAndTimeOfDelivary(Date dateAndTimeOfDelivary) {
        this.dateAndTimeOfDelivary = dateAndTimeOfDelivary;
    }

    public ModeOfDelivery getModeOfDelivery() {
        return modeOfDelivery;
    }

    public void setModeOfDelivery(ModeOfDelivery modeOfDelivery) {
        this.modeOfDelivery = modeOfDelivery;
    }

    public String getModeOfDelivery_Other() {
        return modeOfDelivery_Other;
    }

    public void setModeOfDelivery_Other(String modeOfDelivery_Other) {
        this.modeOfDelivery_Other = modeOfDelivery_Other;
    }

    public Plurality getPlurality() {
        return plurality;
    }

    public void setPlurality(Plurality plurality) {
        this.plurality = plurality;
    }

    public BirthAttendedBy getBirthAttendedBy() {
        return birthAttendedBy;
    }

    public void setBirthAttendedBy(BirthAttendedBy birthAttendedBy) {
        this.birthAttendedBy = birthAttendedBy;
    }

    public PregnancyOutcome getPregnancyOutcome() {
        return pregnancyOutcome;
    }

    public void setPregnancyOutcome(PregnancyOutcome pregnancyOutcome) {
        this.pregnancyOutcome = pregnancyOutcome;
    }

    public int getApgar_1st_minute() {
        return apgar_1st_minute;
    }

    public void setApgar_1st_minute(int apgar_1st_minute) {
        this.apgar_1st_minute = apgar_1st_minute;
    }

    public int getApgar_5th_minute() {
        return apgar_5th_minute;
    }

    public void setApgar_5th_minute(int apgar_5th_minute) {
        this.apgar_5th_minute = apgar_5th_minute;
    }

    public int getApgar_10th_minute() {
        return apgar_10th_minute;
    }

    public void setApgar_10th_minute(int apgar_10th_minute) {
        this.apgar_10th_minute = apgar_10th_minute;
    }

    public double getBirth_weight_in_grams() {
        return birth_weight_in_grams;
    }

    public void setBirth_weight_in_grams(double birth_weight_in_grams) {
        this.birth_weight_in_grams = birth_weight_in_grams;
    }

    public double getLength_in_cm() {
        return length_in_cm;
    }

    public void setLength_in_cm(double length_in_cm) {
        this.length_in_cm = length_in_cm;
    }

    public double getHead_circumference_in_cm() {
        return head_circumference_in_cm;
    }

    public void setHead_circumference_in_cm(double head_circumference_in_cm) {
        this.head_circumference_in_cm = head_circumference_in_cm;
    }

    
    
}
