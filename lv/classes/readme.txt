数据库字段的详细说明

表 TbBill
intIsHasEffective  -1表示作废, 0表示未提交状态，1表示未生效，2表示已生效,
intIsBxSure   0表示保险公司还没有确认 1表示已确认，-1表示保单有问题
intIsLxsSure  0表示保险旅行社还未确认 1表示已确认
intIsApplyCancel 0表示未申请退保 1表示申请退保
intIsAgreeCancel -1表示还没有退保处理信息，0表示同意退保，1表示拒绝退保
