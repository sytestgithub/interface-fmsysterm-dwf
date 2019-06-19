package com.daojia.qa.entity;

import java.util.List;

import com.daojia.spat.dao.annotation.Column;
import com.daojia.spat.dao.annotation.Id;
import com.daojia.spat.dao.annotation.NotDBColumn;
import com.daojia.spat.dao.annotation.Table;

/**
 * Created by daojia on 2018-3-23.
 */
public class Stamp {
    private Long id;
    private String stampCode;
    private String stampName;
    private String stampType;
    private String stampTypeName;
    private String companyName;
    private String keeperUserName;
    private Long keeperUserId;
    private String keeperDeptName;
    private Long keeperDeptId;
    private int useState;
    private String updateUserName;
    private String updateUserId;
    private String updateDate;
    private String logoutDate;
    
    @NotDBColumn
    private List<ButtonDto> btns;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStampName() {
        return stampName;
    }

    public void setStampName(String stampName) {
        this.stampName = stampName;
    }

    public String getStampType() {
        return stampType;
    }

    public void setStampType(String stampType) {
        this.stampType = stampType;
    }

    public String getStampTypeName() {
        return stampTypeName;
    }

    public void setStampTypeName(String stampTypeName) {
        this.stampTypeName = stampTypeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getKeeperUserName() {
        return keeperUserName;
    }

    public void setKeeperUserName(String keeperUserName) {
        this.keeperUserName = keeperUserName;
    }

    public Long getKeeperUserId() {
        return keeperUserId;
    }

    public void setKeeperUserId(Long keeperUserId) {
        this.keeperUserId = keeperUserId;
    }

    public String getKeeperDeptName() {
        return keeperDeptName;
    }

    public void setKeeperDeptName(String keeperDeptName) {
        this.keeperDeptName = keeperDeptName;
    }

    public Long getKeeperDeptId() {
        return keeperDeptId;
    }

    public void setKeeperDeptId(Long keeperDeptId) {
        this.keeperDeptId = keeperDeptId;
    }

    public int getUseState() {
        return useState;
    }

    public void setUseState(int useState) {
        this.useState = useState;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(String logoutDate) {
        this.logoutDate = logoutDate;
    }

	public String getStampCode() {
		return stampCode;
	}

	public void setStampCode(String stampCode) {
		this.stampCode = stampCode;
	}

	public List<ButtonDto> getBtns() {
		return btns;
	}

	public void setBtns(List<ButtonDto> btns) {
		this.btns = btns;
	}

	@Override
	public String toString() {
		return "Stamp [id=" + id + ", stampCode=" + stampCode + ", stampName=" + stampName + ", stampType=" + stampType
				+ ", stampTypeName=" + stampTypeName + ", companyName=" + companyName + ", keeperUserName="
				+ keeperUserName + ", keeperUserId=" + keeperUserId + ", keeperDeptName=" + keeperDeptName
				+ ", keeperDeptId=" + keeperDeptId + ", useState=" + useState + ", updateUserName=" + updateUserName
				+ ", updateUserId=" + updateUserId + ", updateDate=" + updateDate + ", logoutDate=" + logoutDate + "]";
	}
}
