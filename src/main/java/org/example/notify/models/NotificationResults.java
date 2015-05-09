package org.example.notify.models;

import org.springframework.data.domain.Page;

/**
 * Model holds metadata related to pagination of Notification information, and the current result set
 */
public class NotificationResults {

    /** The current page of the result set */
    private Integer currentPage;

    /** The beginning page of the result set */
    private Integer beginPage;

    /** The last page of the result set */
    private Integer endPage;

    /** A subset of the Pageable result set */
    private Page<Notification> notifications;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(Integer beginPage) {
        this.beginPage = beginPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

    public Page<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Page<Notification> notifications) {
        this.notifications = notifications;
    }
}
