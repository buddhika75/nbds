/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.health.bean;

import gov.health.data.Birth;
import gov.health.data.BirthAttendedBy;
import gov.health.data.CategoryType;
import gov.health.data.Dermatolglyphics;
import gov.health.data.EducationLevel;
import gov.health.data.Ethnicity;
import gov.health.data.LivingStatus;
import gov.health.data.MariatalStatus;
import gov.health.data.ModeOfDelivery;
import gov.health.data.Plurality;
import gov.health.data.PregnancyOutcome;
import gov.health.data.PresenceOfCongenitalAbnormalities;
import gov.health.data.SourceOfPog;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author pdhs
 */
@Named
@ApplicationScoped
public class EnumController {

    /**
     * Creates a new instance of EnumController
     */
    public EnumController() {
    }
    
    public Ethnicity[] getEthnicities(){
        return Ethnicity.values();
    } 
    
    public Dermatolglyphics[] getDermatolglyphicses(){
        return Dermatolglyphics.values();
    }
    
    public Birth[] getBirths(){
        return Birth.values();
    }
    
    public CategoryType[] getCategoryTypes(){
        return CategoryType.values();
    }
    
    public LivingStatus[] getLivingStatus(){
        return LivingStatus.values();
    }
    
    public MariatalStatus[] getMariatalStatus(){
        return MariatalStatus.values();
    }
   
    public EducationLevel[] getEducationLevels(){
        return EducationLevel.values();
    }
    
    public PresenceOfCongenitalAbnormalities[] getPresenceOfCongenitalAbnormalities(){
        return PresenceOfCongenitalAbnormalities.values();
    }
    
    public SourceOfPog[] getSourceOfPogs(){
        return SourceOfPog.values();
    }
    
    public ModeOfDelivery[] getModeOfDeliverys(){
        return ModeOfDelivery.values();
    }
    
    public Plurality[] getPluralities(){
        return Plurality.values();
    }
    
    public BirthAttendedBy[] getBirthAttendedBys(){
        return BirthAttendedBy.values();
    }
    
    public PregnancyOutcome[] getPregnancyOutcomes(){
        return PregnancyOutcome.values();
    }

}
