package com.jacobgreenland.finalproject.league.remote;

import android.util.Log;

import com.jacobgreenland.finalproject.league.model.League;
import com.jacobgreenland.finalproject.league.LeagueAPI;
import com.jacobgreenland.finalproject.league.LeagueContract;
import com.jacobgreenland.finalproject.league.LeagueRepository;
import com.jacobgreenland.finalproject.league.LeagueTable;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Jacob on 17/06/16.
 */
public class RemoteLeagueSource {

    private CompositeSubscription _subscriptions = new CompositeSubscription();

    List<League> leagues;
    LeagueTable leagueTable;

    public RemoteLeagueSource()
    {

    }
    public List<League> getLeagueList()
    {
        return leagues;
    }

    public LeagueTable getLeagueTable()
    {
        return leagueTable;
    }

    public void getLeagueTable(LeagueAPI _api, final boolean initialLoad, final LeagueContract.View mView, final LeagueRepository leagueRepository, String id)
    {
        _subscriptions.add(_api.getLeagueTable(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(15000, TimeUnit.MILLISECONDS)
                .retry()
                .distinct()
                .subscribe(new Observer<LeagueTable>() {
                    @Override
                    public void onError(Throwable e) {
                        Log.i("Retrofit", "Error");
                    }
                    @Override
                    public void onCompleted() {
                        Log.i("Retrofit", "onCompleted");
                        /*if(initialLoad) {
                            leagueRepository.getLocalSource().addData(leagues);
                        }
                        else {*/
                        Log.d("TEST", "ARRAY SIZE IS : " + leagueTable.getStanding().size());
                            mView.setAdapters(leagueTable, true);
                            mView.showDialog();
                        //}
                    }
                    @Override
                    public void onNext(LeagueTable leagueT) {
                        Log.i("Retrofit", "onNext");

                        leagueTable = leagueT;
                    }
                }));
    }
}
