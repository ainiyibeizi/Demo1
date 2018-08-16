package com.briup.pgc.anew.bean;

import java.util.List;

public class NewBean {

    private List<NewsBean> news;

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public static class NewsBean {
        /**
         * message : 嗷嗷喊什么程度了深V吗流程看电视运动健将强无敌
         * id : 1
         * title : 1运动健将强无敌
         * newicon : file/a.png
         * newdesc : 运动健将此次胜利在望，为他们祝贺
         */

        private String message;
        private int id;
        private String title;
        private String newicon;
        private String newdesc;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNewicon() {
            return newicon;
        }

        public void setNewicon(String newicon) {
            this.newicon = newicon;
        }

        public String getNewdesc() {
            return newdesc;
        }

        public void setNewdesc(String newdesc) {
            this.newdesc = newdesc;
        }
    }
}
