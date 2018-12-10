package com.mlasek.battleships.application.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NewGameResponse{

    public NewGameResponse(String invitationUrl) {
        this.invitationUrl = invitationUrl;
    }

    private String invitationUrl;

    public String getInvitationUrl() {
        return invitationUrl;
    }

    public void setInvitationUrl(String invitationUrl) {
        this.invitationUrl = invitationUrl;
    }
}