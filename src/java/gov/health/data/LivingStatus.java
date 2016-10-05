/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.health.data;

/**
 *
 * @author User
 */
public enum LivingStatus {
    Living,
    StillBirth,
    StillBirthFresh,
    StillBirthMacerated,
    NeonatalDeath,
    PostNeonatalDeath;

    public String getLabel() {
        switch (this) {
            case Living:
                return "Living";
            case StillBirthFresh:
                return "Still Birth - Fresh";
            case StillBirth:
                return "Still Birth";
            case StillBirthMacerated:
                return "Still Birth - Macerated";
            case NeonatalDeath:
                return "Neonatal Death";
            case PostNeonatalDeath:
                return "Post Neonatal Death";

        }
        return this.toString();
    }
}
