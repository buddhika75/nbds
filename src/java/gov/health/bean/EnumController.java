/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.health.bean;

import gov.health.data.Birth;
import gov.health.data.Dermatolglyphics;
import gov.health.data.Ethnicity;
import gov.health.data.LivingStatus;
import gov.health.data.PresenceOfCongenitalAbnormalities;
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
    
    public LivingStatus[] getLivingStatus(){
        return LivingStatus.values();
    }
    
    public LivingStatus[] getLivingStatusLimited(){
        LivingStatus[] ls = new LivingStatus[]{LivingStatus.Living, LivingStatus.StillBirth , LivingStatus.NeonatalDeath, LivingStatus.PostNeonatalDeath};
        return ls;
    }
    
    public PresenceOfCongenitalAbnormalities[] getPresenceOfCongenitalAbnormalities(){
        return PresenceOfCongenitalAbnormalities.values();
    }
    
    
}
