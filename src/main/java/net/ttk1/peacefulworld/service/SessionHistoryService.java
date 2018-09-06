package net.ttk1.peacefulworld.service;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.ttk1.peacefulworld.model.SessionHistoryModel;

import static net.ttk1.peacefulworld.model.SessionHistoryModel.SessionHistoryFinder;
import static net.ttk1.peacefulworld.model.SessionHistoryModel.TYPE_LOGIN;
import static net.ttk1.peacefulworld.model.SessionHistoryModel.TYPE_LOGOUT;

public class SessionHistoryService {
    private String ebeanServerName;
    private SessionHistoryFinder sessionHistoryFinder;

    @Inject
    private void setEbeanServerName(@Named("ebeanServerName") String ebeanServerName) {
        this.ebeanServerName = ebeanServerName;
        setSessionHistoryFinder(new SessionHistoryFinder(ebeanServerName));
    }

    private void setSessionHistoryFinder(SessionHistoryFinder sessionHistoryFinder) {
        this.sessionHistoryFinder = sessionHistoryFinder;
    }

    public long login(long playerId) {
        // time
        long time = System.currentTimeMillis();

        SessionHistoryModel sessionHistory = new SessionHistoryModel(time, playerId, TYPE_LOGIN);

        return sessionHistory.getId();
    }

    public long logout(long playerId) {
        // time
        long time = System.currentTimeMillis();

        SessionHistoryModel sessionHistory = new SessionHistoryModel(time, playerId, TYPE_LOGOUT);

        return sessionHistory.getId();
    }
}
