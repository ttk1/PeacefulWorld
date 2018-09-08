package net.ttk1.peacefulworld.test.service;

import net.ttk1.peacefulworld.history.HistoryImpl;
import net.ttk1.peacefulworld.model.HistoryChainModel;
import net.ttk1.peacefulworld.service.HistoryChainService;
import net.ttk1.peacefulworld.service.PlayerService;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.junit.Test;

import static net.ttk1.peacefulworld.model.HistoryChainModel.HistoryChainFinder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HistoryChainServiceTest extends HistoryChainService {

    @Test
    public void registerHistoryTest() {
        // historyChainFinder
        HistoryChainFinder historyChainFinder = new HistoryChainFinder("db");

        // location
        Location location = mock(Location.class, RETURNS_DEEP_STUBS);
        when(location.getWorld().getName()).thenReturn("test_world");
        when(location.getBlockX()).thenReturn(101);
        when(location.getBlockY()).thenReturn(102);
        when(location.getBlockZ()).thenReturn(103);

        // blockData
        BlockData blockData = mock(BlockData.class);
        when(blockData.getAsString()).thenReturn("test_data");

        // originId != 0
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

        // originId == 0
        historyId = registerHistory(0L, 1002L, 1003L, location, blockData);

        historyChain = historyChainFinder.byId(historyId);

        assertThat(historyChain.getWorldName(), is("test_world"));
        assertThat(historyChain.getX(), is(101));
        assertThat(historyChain.getY(), is(102));
        assertThat(historyChain.getZ(), is(103));

        assertThat(historyChain.getBlockDataString(), is("test_data"));
        assertThat(historyChain.getOriginId(), is(historyId));
        assertThat(historyChain.getParentId(), is(1002L));
        assertThat(historyChain.getPlayerId(), is(1003L));
    }

    @Test
    public void getOriginIdTest() {
        // not null
        HistoryChainModel historyChain = mock(HistoryChainModel.class);
        when(historyChain.getOriginId()).thenReturn(1001L);

        HistoryChainFinder historyChainFinder = mock(HistoryChainFinder.class);
        when(historyChainFinder.byId(anyLong())).thenReturn(historyChain);

        this.setHistoryChainFinder(historyChainFinder);
        assertThat(getOriginId(1002L), is(1001L));

        // zero and negative id
        assertThat(getOriginId(0L), is(-1L));
        assertThat(getOriginId(-1L), is(-1L));

        // null
        when(historyChainFinder.byId(anyLong())).thenReturn(null);
        assertThat(getOriginId(1002L), is(-1L));
    }

    @Test
    public void getParentIdTest() {
        // not null
        HistoryChainModel historyChain = mock(HistoryChainModel.class);
        when(historyChain.getParentId()).thenReturn(1001L);

        HistoryChainFinder historyChainFinder = mock(HistoryChainFinder.class);
        when(historyChainFinder.byId(anyLong())).thenReturn(historyChain);

        setHistoryChainFinder(historyChainFinder);
        assertThat(getParentId(1002L), is(1001L));

        // zero and negative id
        assertThat(getParentId(0L), is(-1L));
        assertThat(getParentId(-1L), is(-1L));

        // null
        when(historyChainFinder.byId(anyLong())).thenReturn(null);
        assertThat(getParentId(1002L), is(-1L));
    }


    @Test
    public void getHistoryTest() {
        // HistoryChainFinder
        HistoryChainFinder historyChainFinder = mock(HistoryChainFinder.class);
        setHistoryChainFinder(historyChainFinder);

        // PlayerService
        PlayerService playerService = mock(PlayerService.class);
        setPlayerService(playerService);

        // null
        when(historyChainFinder.byId(1000L)).thenReturn(null);

        // assertion
        assertThat(getHistory(1000L), is(nullValue()));

        // not null
        HistoryChainModel historyChain = mock(HistoryChainModel.class);
        when(historyChainFinder.byId(1000L)).thenReturn(historyChain);

        //// time, originId, parentId
        when(historyChain.getTime()).thenReturn(1001L);
        when(historyChain.getOriginId()).thenReturn(1002L);
        when(historyChain.getParentId()).thenReturn(1003L);

        //// playerUuid
        when(playerService.getPlayerUuid(anyLong())).thenReturn("test_uuid");

        //// worldName, x, y, z
        when(historyChain.getWorldName()).thenReturn("test_world");
        when(historyChain.getX()).thenReturn(101);
        when(historyChain.getY()).thenReturn(102);
        when(historyChain.getZ()).thenReturn(103);

        //// blockDataString
        when(historyChain.getBlockDataString()).thenReturn("minecraft:grass_block[snowy=false]");

        // assertion
        HistoryImpl history = (HistoryImpl) getHistory(1000L);
        assertThat(history, is(notNullValue()));

        assertThat(history.getTime(), is(1001L));
        assertThat(history.getOriginId(), is(1002L));
        assertThat(history.getParentId(), is(1003L));

        assertThat(history.getPlayerUuid(), is("test_uuid"));

        assertThat(history.getX(), is(101));
        assertThat(history.getY(), is(102));
        assertThat(history.getZ(), is(103));

        assertThat(history.getBlockDataString(), is("minecraft:grass_block[snowy=false]"));

        // zero and negative id
        when(historyChainFinder.byId(anyLong())).thenReturn(historyChain);
        assertThat(getHistory(0L), is(nullValue()));
        assertThat(getHistory(-1L), is(nullValue()));
    }
}
