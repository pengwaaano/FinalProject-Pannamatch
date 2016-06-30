package com.jacobgreenland.finalproject.league;

import com.jacobgreenland.finalproject.BasePresenter;
import com.jacobgreenland.finalproject.BaseView;

/**
 * Created by Jacob on 16/06/16.
 */
public interface LeagueContract {

    interface View extends BaseView<Presenter> {
        void setAdapters(LeagueTable leagueTable, boolean fromAPI);
        void showDialog();
    }

    interface Presenter extends BasePresenter {
        void loadLeagueTable(LeagueAPI _api, final boolean initialLoad, String id);
        //void loadLocalPopSongs();
        //LeagueRepository getRepository();
        //void setRepository(LeagueRepository songRepo);
    }
}
