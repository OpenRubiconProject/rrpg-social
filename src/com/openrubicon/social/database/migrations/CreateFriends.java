package com.openrubicon.social.database.migrations;

import com.openrubicon.core.api.database.Connection;
import com.openrubicon.core.api.database.interfaces.DatabaseMigration;


public class CreateFriends implements DatabaseMigration {

    @Override
    public boolean up(Connection connection) {
        connection.createTable("CREATE TABLE IF NOT EXISTS `rubicon_friends` (\n" +
                " `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                " `player1_id` int(11) NOT NULL,\n" +
                " `player2_id` int(11) NOT NULL,\n" +
                " `bff` tinyint(4) NOT NULL DEFAULT '0',\n" +
                " `state` mediumint(9) DEFAULT '1',\n" +
                " `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                " `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                " `deleted_at` datetime DEFAULT NULL,\n" +
                " PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");
        return true;
    }

    @Override
    public boolean down(Connection connection) {
        connection.dropTable("rubicon_friends");
        return true;
    }

    @Override
    public int getVersion() {
        return 1;
    }
}
