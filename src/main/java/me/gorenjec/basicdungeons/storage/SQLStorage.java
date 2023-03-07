package me.gorenjec.basicdungeons.storage;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.gorenjec.basicdungeons.BasicDungeons;
import me.gorenjec.basicdungeons.models.BasicPlayer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLStorage {
    private HikariDataSource dataSource;
    private final BasicDungeons instance;
    private final FileConfiguration config;
    private static final String PLAYERDATA_TABLE = "bd_player_data";

    public SQLStorage(BasicDungeons instance) {
        this.instance = instance;
        this.config = instance.getConfig();

        boolean useMySQL = config.getBoolean("data_storage.use_mysql");
        String path = instance.getDataFolder().getPath();

        HikariConfig hikariConfig = new HikariConfig();

        if (useMySQL) {
            hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
            hikariConfig.setUsername(config.getString("data_storage.username"));
            hikariConfig.setPassword(config.getString("data_storage.password"));
            String hostname = config.getString("data_storage.ip");
            String port = config.getString("data_storage.port");
            String database = config.getString("data_storage.database");
            String useSSL = config.getBoolean("data_storage.database") ? "true" : "false";
            hikariConfig.setJdbcUrl("jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=" + useSSL);
        } else {
            hikariConfig.setDriverClassName("org.sqlite.JDBC");
            hikariConfig.setJdbcUrl("jdbc:sqlite:" + path + "/database.sqlite");
        }

        hikariConfig.setPoolName("BasicDungeonsPlugin");
        hikariConfig.setMaxLifetime(60000);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.addDataSourceProperty("database", config.getString("data_storage.database"));

        this.dataSource = new HikariDataSource(hikariConfig);

        constructTables();
    }

    private void constructTables() {
        try (Connection connection = dataSource.getConnection()) {
            String createTableSql = "CREATE TABLE IF NOT EXISTS " + PLAYERDATA_TABLE + "("
                    + "uuid VARCHAR(36) PRIMARY KEY, "
                    + "kills INT, "
                    + "deaths INT, "
                    + "level INT, "
                    + "xp DOUBLE, "
                    + "time_played BIGINT(32)"
                    + ")";
            PreparedStatement statement = connection.prepareStatement(createTableSql);
            statement.execute();

            instance.getLogger().info("Verified data tables.");
        } catch (SQLException e) {
            instance.getLogger().severe("Could not create tables!");
            e.printStackTrace();
        }
    }

    public void addPlayer(BasicPlayer basicPlayer) {
        String sql = "INSERT INTO " + PLAYERDATA_TABLE + " VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, basicPlayer.getPlayer().getUniqueId().toString());
            statement.setInt(2, basicPlayer.getKills());
            statement.setInt(3, basicPlayer.getDeaths());
            statement.setInt(4, basicPlayer.getLevel());
            statement.setDouble(5, basicPlayer.getXp());
            statement.setInt(6, basicPlayer.getTimePlayed());

            statement.execute();
        } catch (SQLException e) {
            instance.getLogger().severe("Could not store player data!");
            e.printStackTrace();
        }
    }

    public BasicPlayer getPlayer(UUID uuid) {
        BasicPlayer basicPlayer = null;

        String sql = "SELECT * FROM " + PLAYERDATA_TABLE + " WHERE uuid = ? LIMIT 1";

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, uuid.toString());

            ResultSet playerDataSet = statement.executeQuery();

            if (playerDataSet.next()) {
                basicPlayer = new BasicPlayer(
                        Bukkit.getPlayer(uuid),
                        playerDataSet.getInt("kills"),
                        playerDataSet.getInt("deaths"),
                        playerDataSet.getInt("level"),
                        playerDataSet.getDouble("xp"),
                        playerDataSet.getInt("time_played")
                );
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        if (basicPlayer == null) {
            basicPlayer = new BasicPlayer(
                    Bukkit.getPlayer(uuid),
                    0,
                    0,
                    0,
                    0,
                    0
            );

            addPlayer(basicPlayer);
        }

        return basicPlayer;
    }

    public void updatePlayer(BasicPlayer basicPlayer) {
        String sql = "UPDATE " + PLAYERDATA_TABLE + " SET kills = ?, deaths = ?, level = ?, xp = ?," +
                " time_played = ? WHERE uuid = ?";


        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, basicPlayer.getKills());
            statement.setInt(2, basicPlayer.getDeaths());
            statement.setInt(3, basicPlayer.getLevel());
            statement.setDouble(4, basicPlayer.getXp());
            statement.setInt(5, basicPlayer.getTimePlayed());
            statement.setString(6, basicPlayer.getPlayer().getUniqueId().toString());

            statement.execute();
            instance.getLogger().info("Updated " + basicPlayer.getPlayer().getName() + ".");

        }catch (SQLException exception){
            exception.printStackTrace();
            instance.getLogger().severe("Could not store player data!");
        }
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }
}


