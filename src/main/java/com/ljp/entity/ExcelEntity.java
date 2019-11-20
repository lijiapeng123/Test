package com.ljp.entity;

import java.io.Serializable;

public class ExcelEntity{
    private String paperTitle;
    private String warArea;
    private String shopCode;
    private String shopName;
    private String province;
    private String longitude;
    private String latitude;
    private String address;
    private String urgetName;
    private Integer total_score;
    private Integer total_score_reviewed;
    private Integer total_score_appealed;
    private String answer_start_time;
    private String answer_end_time;
    private String answer;
    private Integer score;
    private Integer score_reviewed;
    private Integer score_appealed;
    private String remark;//备注 附加题的答案
    private String questionId;
    private String questionTitle;
    private String parent_id;
    private Integer type;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getWarArea() {
        return warArea;
    }

    public void setWarArea(String warArea) {
        this.warArea = warArea;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrgetName() {
        return urgetName;
    }

    public void setUrgetName(String urgetName) {
        this.urgetName = urgetName;
    }

    public Integer getTotal_score() {
        return total_score;
    }

    public void setTotal_score(Integer total_score) {
        this.total_score = total_score;
    }

    public Integer getTotal_score_reviewed() {
        return total_score_reviewed;
    }

    public void setTotal_score_reviewed(Integer total_score_reviewed) {
        this.total_score_reviewed = total_score_reviewed;
    }

    public String getAnswer_start_time() {
        return answer_start_time;
    }

    public void setAnswer_start_time(String answer_start_time) {
        this.answer_start_time = answer_start_time;
    }

    public String getAnswer_end_time() {
        return answer_end_time;
    }

    public void setAnswer_end_time(String answer_end_time) {
        this.answer_end_time = answer_end_time;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getScore_reviewed() {
        return score_reviewed;
    }

    public void setScore_reviewed(Integer score_reviewed) {
        this.score_reviewed = score_reviewed;
    }

    public Integer getScore_appealed() {
        return score_appealed;
    }

    public void setScore_appealed(Integer score_appealed) {
        this.score_appealed = score_appealed;
    }

    public Integer getTotal_score_appealed() {
        return total_score_appealed;
    }

    public void setTotal_score_appealed(Integer total_score_appealed) {
        this.total_score_appealed = total_score_appealed;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public ExcelEntity() {
    }

    public ExcelEntity(String paperTitle, String warArea, String shopCode, String shopName, String province, String longitude, String latitude, String address, String urgetName, Integer total_score, Integer total_score_reviewed, Integer total_score_appealed, String answer_start_time, String answer_end_time, String answer, Integer score, Integer score_reviewed, Integer score_appealed, String remark, String questionId, String questionTitle, String parent_id, Integer type) {
        this.paperTitle = paperTitle;
        this.warArea = warArea;
        this.shopCode = shopCode;
        this.shopName = shopName;
        this.province = province;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.urgetName = urgetName;
        this.total_score = total_score;
        this.total_score_reviewed = total_score_reviewed;
        this.total_score_appealed = total_score_appealed;
        this.answer_start_time = answer_start_time;
        this.answer_end_time = answer_end_time;
        this.answer = answer;
        this.score = score;
        this.score_reviewed = score_reviewed;
        this.score_appealed = score_appealed;
        this.remark = remark;
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.parent_id = parent_id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "ExcelEntity{" +
                "paperTitle='" + paperTitle + '\'' +
                ", warArea='" + warArea + '\'' +
                ", shopCode='" + shopCode + '\'' +
                ", shopName='" + shopName + '\'' +
                ", province='" + province + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", address='" + address + '\'' +
                ", urgetName='" + urgetName + '\'' +
                ", total_score=" + total_score +
                ", total_score_reviewed=" + total_score_reviewed +
                ", total_score_appealed=" + total_score_appealed +
                ", answer_start_time='" + answer_start_time + '\'' +
                ", answer_end_time='" + answer_end_time + '\'' +
                ", answer='" + answer + '\'' +
                ", score=" + score +
                ", score_reviewed=" + score_reviewed +
                ", score_appealed=" + score_appealed +
                ", remark='" + remark + '\'' +
                ", questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", type=" + type +
                '}';
    }
}
