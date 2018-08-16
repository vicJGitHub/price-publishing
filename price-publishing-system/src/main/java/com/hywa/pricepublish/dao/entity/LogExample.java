package com.hywa.pricepublish.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userId like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userId not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andReqIpIsNull() {
            addCriterion("req_ip is null");
            return (Criteria) this;
        }

        public Criteria andReqIpIsNotNull() {
            addCriterion("req_ip is not null");
            return (Criteria) this;
        }

        public Criteria andReqIpEqualTo(String value) {
            addCriterion("req_ip =", value, "reqIp");
            return (Criteria) this;
        }

        public Criteria andReqIpNotEqualTo(String value) {
            addCriterion("req_ip <>", value, "reqIp");
            return (Criteria) this;
        }

        public Criteria andReqIpGreaterThan(String value) {
            addCriterion("req_ip >", value, "reqIp");
            return (Criteria) this;
        }

        public Criteria andReqIpGreaterThanOrEqualTo(String value) {
            addCriterion("req_ip >=", value, "reqIp");
            return (Criteria) this;
        }

        public Criteria andReqIpLessThan(String value) {
            addCriterion("req_ip <", value, "reqIp");
            return (Criteria) this;
        }

        public Criteria andReqIpLessThanOrEqualTo(String value) {
            addCriterion("req_ip <=", value, "reqIp");
            return (Criteria) this;
        }

        public Criteria andReqIpLike(String value) {
            addCriterion("req_ip like", value, "reqIp");
            return (Criteria) this;
        }

        public Criteria andReqIpNotLike(String value) {
            addCriterion("req_ip not like", value, "reqIp");
            return (Criteria) this;
        }

        public Criteria andReqIpIn(List<String> values) {
            addCriterion("req_ip in", values, "reqIp");
            return (Criteria) this;
        }

        public Criteria andReqIpNotIn(List<String> values) {
            addCriterion("req_ip not in", values, "reqIp");
            return (Criteria) this;
        }

        public Criteria andReqIpBetween(String value1, String value2) {
            addCriterion("req_ip between", value1, value2, "reqIp");
            return (Criteria) this;
        }

        public Criteria andReqIpNotBetween(String value1, String value2) {
            addCriterion("req_ip not between", value1, value2, "reqIp");
            return (Criteria) this;
        }

        public Criteria andReqUriIsNull() {
            addCriterion("req_uri is null");
            return (Criteria) this;
        }

        public Criteria andReqUriIsNotNull() {
            addCriterion("req_uri is not null");
            return (Criteria) this;
        }

        public Criteria andReqUriEqualTo(String value) {
            addCriterion("req_uri =", value, "reqUri");
            return (Criteria) this;
        }

        public Criteria andReqUriNotEqualTo(String value) {
            addCriterion("req_uri <>", value, "reqUri");
            return (Criteria) this;
        }

        public Criteria andReqUriGreaterThan(String value) {
            addCriterion("req_uri >", value, "reqUri");
            return (Criteria) this;
        }

        public Criteria andReqUriGreaterThanOrEqualTo(String value) {
            addCriterion("req_uri >=", value, "reqUri");
            return (Criteria) this;
        }

        public Criteria andReqUriLessThan(String value) {
            addCriterion("req_uri <", value, "reqUri");
            return (Criteria) this;
        }

        public Criteria andReqUriLessThanOrEqualTo(String value) {
            addCriterion("req_uri <=", value, "reqUri");
            return (Criteria) this;
        }

        public Criteria andReqUriLike(String value) {
            addCriterion("req_uri like", value, "reqUri");
            return (Criteria) this;
        }

        public Criteria andReqUriNotLike(String value) {
            addCriterion("req_uri not like", value, "reqUri");
            return (Criteria) this;
        }

        public Criteria andReqUriIn(List<String> values) {
            addCriterion("req_uri in", values, "reqUri");
            return (Criteria) this;
        }

        public Criteria andReqUriNotIn(List<String> values) {
            addCriterion("req_uri not in", values, "reqUri");
            return (Criteria) this;
        }

        public Criteria andReqUriBetween(String value1, String value2) {
            addCriterion("req_uri between", value1, value2, "reqUri");
            return (Criteria) this;
        }

        public Criteria andReqUriNotBetween(String value1, String value2) {
            addCriterion("req_uri not between", value1, value2, "reqUri");
            return (Criteria) this;
        }

        public Criteria andReqHostIsNull() {
            addCriterion("req_host is null");
            return (Criteria) this;
        }

        public Criteria andReqHostIsNotNull() {
            addCriterion("req_host is not null");
            return (Criteria) this;
        }

        public Criteria andReqHostEqualTo(String value) {
            addCriterion("req_host =", value, "reqHost");
            return (Criteria) this;
        }

        public Criteria andReqHostNotEqualTo(String value) {
            addCriterion("req_host <>", value, "reqHost");
            return (Criteria) this;
        }

        public Criteria andReqHostGreaterThan(String value) {
            addCriterion("req_host >", value, "reqHost");
            return (Criteria) this;
        }

        public Criteria andReqHostGreaterThanOrEqualTo(String value) {
            addCriterion("req_host >=", value, "reqHost");
            return (Criteria) this;
        }

        public Criteria andReqHostLessThan(String value) {
            addCriterion("req_host <", value, "reqHost");
            return (Criteria) this;
        }

        public Criteria andReqHostLessThanOrEqualTo(String value) {
            addCriterion("req_host <=", value, "reqHost");
            return (Criteria) this;
        }

        public Criteria andReqHostLike(String value) {
            addCriterion("req_host like", value, "reqHost");
            return (Criteria) this;
        }

        public Criteria andReqHostNotLike(String value) {
            addCriterion("req_host not like", value, "reqHost");
            return (Criteria) this;
        }

        public Criteria andReqHostIn(List<String> values) {
            addCriterion("req_host in", values, "reqHost");
            return (Criteria) this;
        }

        public Criteria andReqHostNotIn(List<String> values) {
            addCriterion("req_host not in", values, "reqHost");
            return (Criteria) this;
        }

        public Criteria andReqHostBetween(String value1, String value2) {
            addCriterion("req_host between", value1, value2, "reqHost");
            return (Criteria) this;
        }

        public Criteria andReqHostNotBetween(String value1, String value2) {
            addCriterion("req_host not between", value1, value2, "reqHost");
            return (Criteria) this;
        }

        public Criteria andReqPortIsNull() {
            addCriterion("req_port is null");
            return (Criteria) this;
        }

        public Criteria andReqPortIsNotNull() {
            addCriterion("req_port is not null");
            return (Criteria) this;
        }

        public Criteria andReqPortEqualTo(Integer value) {
            addCriterion("req_port =", value, "reqPort");
            return (Criteria) this;
        }

        public Criteria andReqPortNotEqualTo(Integer value) {
            addCriterion("req_port <>", value, "reqPort");
            return (Criteria) this;
        }

        public Criteria andReqPortGreaterThan(Integer value) {
            addCriterion("req_port >", value, "reqPort");
            return (Criteria) this;
        }

        public Criteria andReqPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("req_port >=", value, "reqPort");
            return (Criteria) this;
        }

        public Criteria andReqPortLessThan(Integer value) {
            addCriterion("req_port <", value, "reqPort");
            return (Criteria) this;
        }

        public Criteria andReqPortLessThanOrEqualTo(Integer value) {
            addCriterion("req_port <=", value, "reqPort");
            return (Criteria) this;
        }

        public Criteria andReqPortIn(List<Integer> values) {
            addCriterion("req_port in", values, "reqPort");
            return (Criteria) this;
        }

        public Criteria andReqPortNotIn(List<Integer> values) {
            addCriterion("req_port not in", values, "reqPort");
            return (Criteria) this;
        }

        public Criteria andReqPortBetween(Integer value1, Integer value2) {
            addCriterion("req_port between", value1, value2, "reqPort");
            return (Criteria) this;
        }

        public Criteria andReqPortNotBetween(Integer value1, Integer value2) {
            addCriterion("req_port not between", value1, value2, "reqPort");
            return (Criteria) this;
        }

        public Criteria andErrorCodeIsNull() {
            addCriterion("error_code is null");
            return (Criteria) this;
        }

        public Criteria andErrorCodeIsNotNull() {
            addCriterion("error_code is not null");
            return (Criteria) this;
        }

        public Criteria andErrorCodeEqualTo(Short value) {
            addCriterion("error_code =", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotEqualTo(Short value) {
            addCriterion("error_code <>", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeGreaterThan(Short value) {
            addCriterion("error_code >", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeGreaterThanOrEqualTo(Short value) {
            addCriterion("error_code >=", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeLessThan(Short value) {
            addCriterion("error_code <", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeLessThanOrEqualTo(Short value) {
            addCriterion("error_code <=", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeIn(List<Short> values) {
            addCriterion("error_code in", values, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotIn(List<Short> values) {
            addCriterion("error_code not in", values, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeBetween(Short value1, Short value2) {
            addCriterion("error_code between", value1, value2, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotBetween(Short value1, Short value2) {
            addCriterion("error_code not between", value1, value2, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorMessageIsNull() {
            addCriterion("error_message is null");
            return (Criteria) this;
        }

        public Criteria andErrorMessageIsNotNull() {
            addCriterion("error_message is not null");
            return (Criteria) this;
        }

        public Criteria andErrorMessageEqualTo(String value) {
            addCriterion("error_message =", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageNotEqualTo(String value) {
            addCriterion("error_message <>", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageGreaterThan(String value) {
            addCriterion("error_message >", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageGreaterThanOrEqualTo(String value) {
            addCriterion("error_message >=", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageLessThan(String value) {
            addCriterion("error_message <", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageLessThanOrEqualTo(String value) {
            addCriterion("error_message <=", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageLike(String value) {
            addCriterion("error_message like", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageNotLike(String value) {
            addCriterion("error_message not like", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageIn(List<String> values) {
            addCriterion("error_message in", values, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageNotIn(List<String> values) {
            addCriterion("error_message not in", values, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageBetween(String value1, String value2) {
            addCriterion("error_message between", value1, value2, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageNotBetween(String value1, String value2) {
            addCriterion("error_message not between", value1, value2, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andRealErrorMessageIsNull() {
            addCriterion("real_error_message is null");
            return (Criteria) this;
        }

        public Criteria andRealErrorMessageIsNotNull() {
            addCriterion("real_error_message is not null");
            return (Criteria) this;
        }

        public Criteria andRealErrorMessageEqualTo(String value) {
            addCriterion("real_error_message =", value, "realErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRealErrorMessageNotEqualTo(String value) {
            addCriterion("real_error_message <>", value, "realErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRealErrorMessageGreaterThan(String value) {
            addCriterion("real_error_message >", value, "realErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRealErrorMessageGreaterThanOrEqualTo(String value) {
            addCriterion("real_error_message >=", value, "realErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRealErrorMessageLessThan(String value) {
            addCriterion("real_error_message <", value, "realErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRealErrorMessageLessThanOrEqualTo(String value) {
            addCriterion("real_error_message <=", value, "realErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRealErrorMessageLike(String value) {
            addCriterion("real_error_message like", value, "realErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRealErrorMessageNotLike(String value) {
            addCriterion("real_error_message not like", value, "realErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRealErrorMessageIn(List<String> values) {
            addCriterion("real_error_message in", values, "realErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRealErrorMessageNotIn(List<String> values) {
            addCriterion("real_error_message not in", values, "realErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRealErrorMessageBetween(String value1, String value2) {
            addCriterion("real_error_message between", value1, value2, "realErrorMessage");
            return (Criteria) this;
        }

        public Criteria andRealErrorMessageNotBetween(String value1, String value2) {
            addCriterion("real_error_message not between", value1, value2, "realErrorMessage");
            return (Criteria) this;
        }

        public Criteria andParameterIsNull() {
            addCriterion("parameter is null");
            return (Criteria) this;
        }

        public Criteria andParameterIsNotNull() {
            addCriterion("parameter is not null");
            return (Criteria) this;
        }

        public Criteria andParameterEqualTo(String value) {
            addCriterion("parameter =", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualTo(String value) {
            addCriterion("parameter <>", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThan(String value) {
            addCriterion("parameter >", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualTo(String value) {
            addCriterion("parameter >=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThan(String value) {
            addCriterion("parameter <", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualTo(String value) {
            addCriterion("parameter <=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLike(String value) {
            addCriterion("parameter like", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotLike(String value) {
            addCriterion("parameter not like", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterIn(List<String> values) {
            addCriterion("parameter in", values, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotIn(List<String> values) {
            addCriterion("parameter not in", values, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterBetween(String value1, String value2) {
            addCriterion("parameter between", value1, value2, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotBetween(String value1, String value2) {
            addCriterion("parameter not between", value1, value2, "parameter");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}