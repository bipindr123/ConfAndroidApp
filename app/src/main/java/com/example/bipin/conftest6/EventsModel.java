package com.example.bipin.conftest6;

public class EventsModel {

    String id;
    String eventName;
    String eventDesc;
    String eventDate;
    String eventLink;

    public EventsModel() { }

    public String getName() {
        return eventName;
    }

    public void setName(String name) {
        this.eventName = name;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setDescription(String description) {
        this.eventDesc = description;
    }


    public String getEventLink() {
        return eventLink;
    }

    public void setEventLink(String link) {
        this.eventLink = link;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String date) {
        this.eventDate = date;
    }
}
