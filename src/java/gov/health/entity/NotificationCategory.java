/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.health.entity;

import gov.health.data.NotificationCategoryType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author User
 */
@Entity
public class NotificationCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    NotificationCategoryType type;
    @ManyToOne
    NotificationForm notificationForm;
    @ManyToOne
    Category category;
    int orderNo;
    String type_of_Birth_Defect;
    @Lob
    String full_Description;
    @ManyToOne
    Category birth_Defect_Code_ICD_10;
    @ManyToOne
    Category rcpch__Extension;
    Boolean confirmed_Possible;

    @Temporal(javax.persistence.TemporalType.DATE)
    Date date_Performed;
    @ManyToOne
    Institution facility;
    @Lob
    String outcome_Comments;

    @ManyToOne
    Category relationship;
    @ManyToOne
    Category congenital_abnormality;
    
    
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
        if (!(object instanceof NotificationCategory)) {
            return false;
        }
        NotificationCategory other = (NotificationCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.health.entity.NotificationCategory[ id=" + id + " ]";
    }

}
