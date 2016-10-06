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

     //Created Properties
    @ManyToOne
    WebUser creater;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date createdAt;
    //Retairing properties
    boolean retired;
    @ManyToOne
    WebUser retirer;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date retiredAt;
    String retireComments;

    public WebUser getCreater() {
        return creater;
    }

    public void setCreater(WebUser creater) {
        this.creater = creater;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public WebUser getRetirer() {
        return retirer;
    }

    public void setRetirer(WebUser retirer) {
        this.retirer = retirer;
    }

    public Date getRetiredAt() {
        return retiredAt;
    }

    public void setRetiredAt(Date retiredAt) {
        this.retiredAt = retiredAt;
    }

    public String getRetireComments() {
        return retireComments;
    }

    public void setRetireComments(String retireComments) {
        this.retireComments = retireComments;
    }
    
    
    
    public NotificationCategoryType getType() {
        return type;
    }

    public void setType(NotificationCategoryType type) {
        this.type = type;
    }

    public NotificationForm getNotificationForm() {
        return notificationForm;
    }

    public void setNotificationForm(NotificationForm notificationForm) {
        this.notificationForm = notificationForm;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getType_of_Birth_Defect() {
        return type_of_Birth_Defect;
    }

    public void setType_of_Birth_Defect(String type_of_Birth_Defect) {
        this.type_of_Birth_Defect = type_of_Birth_Defect;
    }

    public String getFull_Description() {
        return full_Description;
    }

    public void setFull_Description(String full_Description) {
        this.full_Description = full_Description;
    }

    public Category getBirth_Defect_Code_ICD_10() {
        return birth_Defect_Code_ICD_10;
    }

    public void setBirth_Defect_Code_ICD_10(Category birth_Defect_Code_ICD_10) {
        this.birth_Defect_Code_ICD_10 = birth_Defect_Code_ICD_10;
    }

    public Category getRcpch__Extension() {
        return rcpch__Extension;
    }

    public void setRcpch__Extension(Category rcpch__Extension) {
        this.rcpch__Extension = rcpch__Extension;
    }

    public Boolean getConfirmed_Possible() {
        return confirmed_Possible;
    }

    public void setConfirmed_Possible(Boolean confirmed_Possible) {
        this.confirmed_Possible = confirmed_Possible;
    }

    public Date getDate_Performed() {
        return date_Performed;
    }

    public void setDate_Performed(Date date_Performed) {
        this.date_Performed = date_Performed;
    }

    public Institution getFacility() {
        return facility;
    }

    public void setFacility(Institution facility) {
        this.facility = facility;
    }

    public String getOutcome_Comments() {
        return outcome_Comments;
    }

    public void setOutcome_Comments(String outcome_Comments) {
        this.outcome_Comments = outcome_Comments;
    }

    public Category getRelationship() {
        return relationship;
    }

    public void setRelationship(Category relationship) {
        this.relationship = relationship;
    }

    public Category getCongenital_abnormality() {
        return congenital_abnormality;
    }

    public void setCongenital_abnormality(Category congenital_abnormality) {
        this.congenital_abnormality = congenital_abnormality;
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
