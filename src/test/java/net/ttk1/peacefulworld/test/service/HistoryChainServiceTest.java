package net.ttk1.peacefulworld.test.service;

import net.ttk1.peacefulworld.model.HistoryChainModel;
import net.ttk1.peacefulworld.service.HistoryChainService;
import net.ttk1.peacefulworld.service.PlayerService;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.junit.Before;
import org.junit.Test;

import static net.ttk1.peacefulworld.model.HistoryChainModel.HistoryChainFinder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HistoryChainServiceTest extends HistoryChainService {
    private HistoryChainFinder historyChainFinder;

    @Before
    public void before() {
        this.historyChainFinder = new HistoryChainFinder("db");
    }

    @Test
    public void registerHistoryTest() {
        // location
        Location location = mock(Location.class, RETURNS_DEEP_STUBS);
        when(location.getWorld().getName()).thenReturn("test_world");
        when(location.getBlockX()).thenReturn(101);
        when(location.getBlockY()).thenReturn(102);
        when(location.getBlockZ()).thenReturn(103);

        // blockData
        BlockData blockData = mock(BlockData.class);
        when(blockData.getAsString()).thenReturn("test_data");

        long historyId = registerHistory(1001L, 1002L, 1003L, location, blockData);

        HistoryChainModel historyChain = historyChainFinder.byId(historyId);

        assertThat(historyChain.getWorldName(), is("test_world"));
        assertThat(historyChain.getX(), is(101));
        assertThat(historyChain.getY(), is(102));
        assertThat(historyChain.getZ(), is(103));

        assertThat(historyChain.getBlockDataString(), is("test_data"));
        assertThat(historyChain.getOriginId(), is(1001L));
        assertThat(historyChain.getParentId(), is(1002L));
        assertThat(historyChain.getPlayerId(), is(1003L));
    }

    /*
    @Test
    public void getHistoryTest() {
        PlayerService playerService = mock(PlayerService.class);
        when(playerService.getPlayerUuid(anyLong())).thenReturn("test_uuid");
        setPlayerService(playerService);
    }*/
}
