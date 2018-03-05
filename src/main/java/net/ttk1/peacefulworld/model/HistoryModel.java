package net.ttk1.peacefulworld.model;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.Cache;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import net.ttk1.peacefulworld.model.query.*;

@Cache
@Entity
@Table(name = "history")
public class HistoryModel extends Model{
    public static final HistoryFinder find = new HistoryFinder();
    @Id
    private long id;
    private String name;

    public HistoryModel(long id, String name){
        this.id = id;
        this.name = name;
    }

    public HistoryModel(String name){
        this.name = name;
    }

    public long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public static class HistoryFinder extends Finder<String, HistoryModel>{
        HistoryFinder(){
            super(HistoryModel.class);
        }
        HistoryFinder(String serverName){
            super(HistoryModel.class, serverName);
        }
        /**
         * Start a new typed query.
         */
        public QHistoryModel where() {
            return new QHistoryModel(db());
        }

        /**
         * Start a new document store query.
         */
        public QHistoryModel text() {
            return new QHistoryModel(db()).text();
        }
    }
}
