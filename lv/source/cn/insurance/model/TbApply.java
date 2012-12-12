package cn.insurance.model;

import java.util.Date;


/**
 * TbApply generated by MyEclipse - Hibernate Tools
 */

public class TbApply  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer intFromUserId;
     private String strApplyUserName;
     private Integer intBillId;
     private Integer intPartmentId;
     private Date dteApplyTime;
     private String strApplyContent;
     private Integer intIsReply;
     private Integer intReplyUserId;
     private String strReplyUserName;
     private String strReplyContent;
     private Date dteReplyTime;
     
     private String strBillNumber ;
     
     private TbUser fromUser = new TbUser() ; 
     
     private TbUser replyUser = new TbUser() ;
     
     private TbPartment tbPartment ;
     
     
    // Constructors

    /** default constructor */
    public TbApply() {
    }

	/** minimal constructor */
    public TbApply(Integer id) {
        this.id = id;
    }
    
    /** full constructor */
    public TbApply(Integer id, Integer intFromUserId, String strApplyUserName, Integer intBillId, Integer intPartmentId, Date dteApplyTime, String strApplyContent, Integer intIsReply, Integer intReplyUserId, String strReplyUserName, String strReplyContent, Date dteReplyTime) {
        this.id = id;
        this.intFromUserId = intFromUserId;
        this.strApplyUserName = strApplyUserName;
        this.intBillId = intBillId;
        this.intPartmentId = intPartmentId;
        this.dteApplyTime = dteApplyTime;
        this.strApplyContent = strApplyContent;
        this.intIsReply = intIsReply;
        this.intReplyUserId = intReplyUserId;
        this.strReplyUserName = strReplyUserName;
        this.strReplyContent = strReplyContent;
        this.dteReplyTime = dteReplyTime;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIntFromUserId() {
        return this.intFromUserId;
    }
    
    public void setIntFromUserId(Integer intFromUserId) {
        this.intFromUserId = intFromUserId;
    }

    public String getStrApplyUserName() {
        return this.strApplyUserName;
    }
    
    public void setStrApplyUserName(String strApplyUserName) {
        this.strApplyUserName = strApplyUserName;
    }

    public Integer getIntBillId() {
        return this.intBillId;
    }
    
    public void setIntBillId(Integer intBillId) {
        this.intBillId = intBillId;
    }

    public Integer getIntPartmentId() {
        return this.intPartmentId;
    }
    
    public void setIntPartmentId(Integer intPartmentId) {
        this.intPartmentId = intPartmentId;
    }

    public Date getDteApplyTime() {
        return this.dteApplyTime;
    }
    
    public void setDteApplyTime(Date dteApplyTime) {
        this.dteApplyTime = dteApplyTime;
    }

    public String getStrApplyContent() {
        return this.strApplyContent;
    }
    
    public void setStrApplyContent(String strApplyContent) {
        this.strApplyContent = strApplyContent;
    }

    public Integer getIntIsReply() {
        return this.intIsReply;
    }
    
    public void setIntIsReply(Integer intIsReply) {
        this.intIsReply = intIsReply;
    }

    public Integer getIntReplyUserId() {
        return this.intReplyUserId;
    }
    
    public void setIntReplyUserId(Integer intReplyUserId) {
        this.intReplyUserId = intReplyUserId;
    }

    public String getStrReplyUserName() {
        return this.strReplyUserName;
    }
    
    public void setStrReplyUserName(String strReplyUserName) {
        this.strReplyUserName = strReplyUserName;
    }

    public String getStrReplyContent() {
        return this.strReplyContent;
    }
    
    public void setStrReplyContent(String strReplyContent) {
        this.strReplyContent = strReplyContent;
    }

    public Date getDteReplyTime() {
        return this.dteReplyTime;
    }
    
    public void setDteReplyTime(Date dteReplyTime) {
        this.dteReplyTime = dteReplyTime;
    }

	public TbUser getFromUser() {
		return fromUser;
	}

	public void setFromUser(TbUser fromUser) {
		this.fromUser = fromUser;
	}

	public TbUser getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(TbUser replyUser) {
		this.replyUser = replyUser;
	}

	public String getStrBillNumber() {
		return strBillNumber;
	}

	public void setStrBillNumber(String strBillNumber) {
		this.strBillNumber = strBillNumber;
	}

	public TbPartment getTbPartment() {
		return tbPartment;
	}

	public void setTbPartment(TbPartment tbPartment) {
		this.tbPartment = tbPartment;
	}
   








}