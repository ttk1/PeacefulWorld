package net.ttk1.peacefulworld.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import net.ttk1.peacefulworld.api.History;
import net.ttk1.peacefulworld.history.HistoryImpl;
import net.ttk1.peacefulworld.model.HistoryChainModel;

import static net.ttk1.peacefulworld.model.HistoryChainModel.HistoryChainFinder;

@Singleton
public class HistoryChainService {
    private String ebeanServerName;
    private HistoryChainFinder historyChainFinder;

    @Inject
    private  void setEbeanServerName(@Named("ebeanServerName") String ebeanServerName) {
        this.ebeanServerName = ebeanServerName;
        historyChainFinder = new HistoryChainFinder(ebeanServerName);
    }

    public long registerHistory() {
        return 0;
    }

    // nullが返ったら未登録
    public History getHistory(long histotyId) {
        HistoryChainModel historyChain = historyChainFinder.byId(histotyId);
        if (historyChain == null) {
            return null;
        } else {
            return new HistoryImpl();
        }
    }
}
