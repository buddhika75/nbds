/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.health.entity;

import gov.health.data.Birth;
import gov.health.data.Sex;
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

    String infants_Name;
    Sex sex_of_Infant;
    @ManyToOne
    Institution location_where_case_identified_Hospital;
    String location_where_case_identified_Ward_Unit;
    String location_where_case_identified_Bht_No;
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
    int age_at_case_detection_days;
    int age_at_case_detection_months;
    int age_at_case_detection_years;
    LivingStatus living_Status;
    Boolean post_mortem_Necropsy_Done;
    String pathological_Forensic_post_mortem_Record_No;
    String pathological_Forensic_post_mortem_details;
    String pathological_Forensic_post_mortem_Done_by;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date pathological_Forensic_post_mortem_date;

    
}
