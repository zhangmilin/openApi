package com.zhang.openApi.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlatClientTokenExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PlatClientTokenExample() {
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

    protected abstract static class GeneratedCriteria implements Serializable {
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

        public Criteria andClientIdIsNull() {
            addCriterion("client_id is null");
            return (Criteria) this;
        }

        public Criteria andClientIdIsNotNull() {
            addCriterion("client_id is not null");
            return (Criteria) this;
        }

        public Criteria andClientIdEqualTo(String value) {
            addCriterion("client_id =", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotEqualTo(String value) {
            addCriterion("client_id <>", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThan(String value) {
            addCriterion("client_id >", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThanOrEqualTo(String value) {
            addCriterion("client_id >=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThan(String value) {
            addCriterion("client_id <", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThanOrEqualTo(String value) {
            addCriterion("client_id <=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLike(String value) {
            addCriterion("client_id like", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotLike(String value) {
            addCriterion("client_id not like", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdIn(List<String> values) {
            addCriterion("client_id in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotIn(List<String> values) {
            addCriterion("client_id not in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdBetween(String value1, String value2) {
            addCriterion("client_id between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotBetween(String value1, String value2) {
            addCriterion("client_id not between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenIsNull() {
            addCriterion("refresh_token is null");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenIsNotNull() {
            addCriterion("refresh_token is not null");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenEqualTo(String value) {
            addCriterion("refresh_token =", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotEqualTo(String value) {
            addCriterion("refresh_token <>", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenGreaterThan(String value) {
            addCriterion("refresh_token >", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenGreaterThanOrEqualTo(String value) {
            addCriterion("refresh_token >=", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenLessThan(String value) {
            addCriterion("refresh_token <", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenLessThanOrEqualTo(String value) {
            addCriterion("refresh_token <=", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenLike(String value) {
            addCriterion("refresh_token like", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotLike(String value) {
            addCriterion("refresh_token not like", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenIn(List<String> values) {
            addCriterion("refresh_token in", values, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotIn(List<String> values) {
            addCriterion("refresh_token not in", values, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenBetween(String value1, String value2) {
            addCriterion("refresh_token between", value1, value2, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotBetween(String value1, String value2) {
            addCriterion("refresh_token not between", value1, value2, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andCreateDtsIsNull() {
            addCriterion("create_dts is null");
            return (Criteria) this;
        }

        public Criteria andCreateDtsIsNotNull() {
            addCriterion("create_dts is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDtsEqualTo(Date value) {
            addCriterion("create_dts =", value, "createDts");
            return (Criteria) this;
        }

        public Criteria andCreateDtsNotEqualTo(Date value) {
            addCriterion("create_dts <>", value, "createDts");
            return (Criteria) this;
        }

        public Criteria andCreateDtsGreaterThan(Date value) {
            addCriterion("create_dts >", value, "createDts");
            return (Criteria) this;
        }

        public Criteria andCreateDtsGreaterThanOrEqualTo(Date value) {
            addCriterion("create_dts >=", value, "createDts");
            return (Criteria) this;
        }

        public Criteria andCreateDtsLessThan(Date value) {
            addCriterion("create_dts <", value, "createDts");
            return (Criteria) this;
        }

        public Criteria andCreateDtsLessThanOrEqualTo(Date value) {
            addCriterion("create_dts <=", value, "createDts");
            return (Criteria) this;
        }

        public Criteria andCreateDtsIn(List<Date> values) {
            addCriterion("create_dts in", values, "createDts");
            return (Criteria) this;
        }

        public Criteria andCreateDtsNotIn(List<Date> values) {
            addCriterion("create_dts not in", values, "createDts");
            return (Criteria) this;
        }

        public Criteria andCreateDtsBetween(Date value1, Date value2) {
            addCriterion("create_dts between", value1, value2, "createDts");
            return (Criteria) this;
        }

        public Criteria andCreateDtsNotBetween(Date value1, Date value2) {
            addCriterion("create_dts not between", value1, value2, "createDts");
            return (Criteria) this;
        }

        public Criteria andUpdateDtsIsNull() {
            addCriterion("update_dts is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDtsIsNotNull() {
            addCriterion("update_dts is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDtsEqualTo(Date value) {
            addCriterion("update_dts =", value, "updateDts");
            return (Criteria) this;
        }

        public Criteria andUpdateDtsNotEqualTo(Date value) {
            addCriterion("update_dts <>", value, "updateDts");
            return (Criteria) this;
        }

        public Criteria andUpdateDtsGreaterThan(Date value) {
            addCriterion("update_dts >", value, "updateDts");
            return (Criteria) this;
        }

        public Criteria andUpdateDtsGreaterThanOrEqualTo(Date value) {
            addCriterion("update_dts >=", value, "updateDts");
            return (Criteria) this;
        }

        public Criteria andUpdateDtsLessThan(Date value) {
            addCriterion("update_dts <", value, "updateDts");
            return (Criteria) this;
        }

        public Criteria andUpdateDtsLessThanOrEqualTo(Date value) {
            addCriterion("update_dts <=", value, "updateDts");
            return (Criteria) this;
        }

        public Criteria andUpdateDtsIn(List<Date> values) {
            addCriterion("update_dts in", values, "updateDts");
            return (Criteria) this;
        }

        public Criteria andUpdateDtsNotIn(List<Date> values) {
            addCriterion("update_dts not in", values, "updateDts");
            return (Criteria) this;
        }

        public Criteria andUpdateDtsBetween(Date value1, Date value2) {
            addCriterion("update_dts between", value1, value2, "updateDts");
            return (Criteria) this;
        }

        public Criteria andUpdateDtsNotBetween(Date value1, Date value2) {
            addCriterion("update_dts not between", value1, value2, "updateDts");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
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